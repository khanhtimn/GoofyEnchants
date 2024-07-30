package fun.teamti.goofyenchants.enchantment.handler;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import java.util.ArrayList;
import java.util.List;

public class MultiShotBowHandler {

    public static void handleMultishotBow(ArrowLooseEvent event) {
        ItemStack bow = event.getBow();
        int multishotLevel = bow.getEnchantmentLevel(Enchantments.MULTISHOT);
        if (multishotLevel <= 0 || event.getLevel().isClientSide) {
            return;
        }

        LivingEntity shooter = event.getEntity();
        if (!(shooter instanceof ServerPlayer player)) {
            return;
        }

        event.setCanceled(true);
        performShooting(event.getLevel(), player, player.getUsedItemHand(), bow, event.getCharge());
    }

    private static void performShooting(Level level, ServerPlayer player, InteractionHand hand, ItemStack bow, int charge) {
        float velocity = BowItem.getPowerForTime(charge);
        if (velocity < 0.1D) {
            return;
        }

        List<ItemStack> projectiles = getProjectiles(player, bow);

        for (int i = 0; i < projectiles.size(); ++i) {
            ItemStack ammo = projectiles.get(i);
            boolean isCreative = player.getAbilities().instabuild;

            if (!ammo.isEmpty()) {
                float angle = i == 1 ? -10.0F : (i == 2 ? 10.0F : 0.0F);
                shootProjectile(level, player, bow, ammo, isCreative, velocity, angle);
            }
        }

        bow.hurtAndBreak(3, player, (p) -> p.broadcastBreakEvent(hand));
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
        player.awardStat(Stats.ITEM_USED.get(bow.getItem()));
    }

    private static List<ItemStack> getProjectiles(ServerPlayer player, ItemStack bow) {
        List<ItemStack> projectiles = new ArrayList<>();
        ItemStack ammo = player.getProjectile(bow);

        boolean infiniteAmmo = player.getAbilities().instabuild || (ammo.getItem() instanceof ArrowItem && bow.getEnchantmentLevel(Enchantments.INFINITY_ARROWS) > 0);

        if (infiniteAmmo && ammo.isEmpty()) {
            ammo = new ItemStack(Items.ARROW);
        }
        for (int i = 0; i < 3; i++) {
            if (!ammo.isEmpty()) {
                projectiles.add(ammo.copy());
                if (!infiniteAmmo) {
                    ammo.shrink(1);
                    if (ammo.isEmpty()) {
                        player.getInventory().removeItem(ammo);
                        break;
                    }
                }
            }
        }
        return projectiles;
    }

    private static void shootProjectile(Level level, ServerPlayer player, ItemStack bow, ItemStack ammo, boolean isCreative, float velocity, float angle) {
        if (!level.isClientSide) {
            AbstractArrow arrow;
            if (ammo.getItem() == Items.SPECTRAL_ARROW) {
                arrow = new SpectralArrow(level, player);
            } else {
                arrow = new Arrow(level, player);
            }
            if (velocity == 1.0F) {
                arrow.setCritArrow(true);
            }
            applyEnchantments(bow, arrow, ammo);

            if (isCreative || angle != 0.0F) {
                arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }

            arrow.shootFromRotation(player, player.getXRot(), player.getYRot() + angle, 0.0F, velocity * 3.0F, 1.0F);

            level.addFreshEntity(arrow);
        }
    }

    private static void applyEnchantments(ItemStack bow, AbstractArrow arrow, ItemStack ammo) {

        int powerLevel = bow.getEnchantmentLevel(Enchantments.POWER_ARROWS);
        if (powerLevel > 0) {
            arrow.setBaseDamage(arrow.getBaseDamage() + (double) powerLevel * 0.5D + 0.5D);
        }

        int punchLevel = bow.getEnchantmentLevel(Enchantments.PUNCH_ARROWS);
        if (punchLevel > 0) {
            arrow.setKnockback(punchLevel);
        }

        if (bow.getEnchantmentLevel(Enchantments.FLAMING_ARROWS) > 0) {
            arrow.setSecondsOnFire(100);
        }

        if (arrow instanceof Arrow && ammo.getItem() instanceof ArrowItem) {
            ((Arrow) arrow).setEffectsFromItem(ammo);
        }
    }
}