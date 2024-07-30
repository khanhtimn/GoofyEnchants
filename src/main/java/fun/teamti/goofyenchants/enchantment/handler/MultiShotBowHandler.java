package fun.teamti.goofyenchants.enchantment.handler;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MultiShotBowHandler {

    @SubscribeEvent
    public static void handleMultishotBow(ArrowLooseEvent event) {
        ItemStack stack = event.getBow();
        Level world = event.getLevel();
        if (!(stack.getItem() instanceof BowItem)) {
            return;
        }

        int multishotLevel = stack.getEnchantmentLevel(Enchantments.MULTISHOT);
        if (multishotLevel > 0 && !world.isClientSide) {
            shootMultipleArrows(world, event.getEntity(), stack, event.getCharge());
            event.setCanceled(true);
        }
    }

    private static void shootMultipleArrows(Level world, LivingEntity shooter, ItemStack stack, int charge) {
        if (!(shooter instanceof ServerPlayer player)) {
            return;
        }

        ItemStack arrowStack = player.getProjectile(stack);
        if (arrowStack.isEmpty()) {
            arrowStack = new ItemStack(Items.ARROW);
        }

        float power = BowItem.getPowerForTime(charge);
        if (power < 0.1D) {
            return;
        }

        boolean infiniteArrows = player.getAbilities().instabuild ||stack.getEnchantmentLevel(Enchantments.INFINITY_ARROWS) > 0;
        int arrowsToShoot = 3;

        // Adjust the number of arrows to consume
        int arrowsNeeded = infiniteArrows ? 0 : arrowsToShoot;

        for (int i = 0; i < arrowsToShoot; i++) {
            float yRotationOffset = (i - 1) * 10.0F; // -10, 0, 10 degrees
            shootArrow(world, player, stack, arrowStack, power, yRotationOffset, infiniteArrows);
        }

        stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F);

        // Consume the correct number of arrows
        if (!infiniteArrows && !player.getAbilities().instabuild) {
            arrowStack.shrink(arrowsNeeded);
            if (arrowStack.isEmpty()) {
                player.getInventory().removeItem(arrowStack);
            }
        }

        player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
    }

    private static void shootArrow(Level world, Player shooter, ItemStack bowStack, ItemStack arrowStack, float velocity, float yRotationOffset, boolean infiniteArrows) {
        Arrow arrowEntity = new Arrow(world, shooter);
        arrowEntity.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + yRotationOffset, 0.0F, velocity * 3.0F, 1.0F);

        if (velocity == 1.0F) {
            arrowEntity.setCritArrow(true);
        }

        int powerLevel = bowStack.getEnchantmentLevel(Enchantments.POWER_ARROWS);
        if (powerLevel > 0) {
            arrowEntity.setBaseDamage(arrowEntity.getBaseDamage() + (double) powerLevel * 0.5D + 0.5D);
        }

        int punchLevel = bowStack.getEnchantmentLevel(Enchantments.PUNCH_ARROWS);
        if (punchLevel > 0) {
            arrowEntity.setKnockback(punchLevel);
        }

        if (bowStack.getEnchantmentLevel(Enchantments.FLAMING_ARROWS) > 0) {
            arrowEntity.setSecondsOnFire(100);
        }

        if (infiniteArrows || (shooter.getAbilities().instabuild && (arrowStack.getItem() == Items.SPECTRAL_ARROW || arrowStack.getItem() == Items.TIPPED_ARROW))) {
            arrowEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }

        world.addFreshEntity(arrowEntity);
    }
}