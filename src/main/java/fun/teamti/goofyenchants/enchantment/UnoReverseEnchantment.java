package fun.teamti.goofyenchants.enchantment;

import fun.teamti.goofyenchants.init.ModEnchantments;
import fun.teamti.goofyenchants.init.ModItems;
import fun.teamti.goofyenchants.init.ModNetwork;
import fun.teamti.goofyenchants.network.UnoReverseAnimationPacket;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = "goofyenchants")
public class UnoReverseEnchantment extends Enchantment {

    private static final List<Runnable> SCHEDULED_TASKS = new ArrayList<>();

    public UnoReverseEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, createCategory(), pApplicableSlots);
    }

    private static EnchantmentCategory createCategory() {
        return EnchantmentCategory.create("SHIELD", item -> item instanceof ShieldItem);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        Entity attacker = event.getSource().getEntity();

        if (!(attacker instanceof LivingEntity)) {
            return;
        }

        ItemStack activeItem = player.getUseItem();

        if (activeItem.getItem() instanceof ShieldItem && player.isBlocking()) {
            int level = activeItem.getEnchantmentLevel(ModEnchantments.UNO_REVERSE.get());
            if (level > 0) {
                event.setCanceled(true);

                // Schedule the damage reflection and knockback for the next tick
                SCHEDULED_TASKS.add(() -> reflectDamageAndKnockback(player, (LivingEntity) attacker, event.getAmount()));
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            List<Runnable> tasks = new ArrayList<>(SCHEDULED_TASKS);
            SCHEDULED_TASKS.clear();
            for (Runnable task : tasks) {
                task.run();
            }
        }
    }

    private static void reflectDamageAndKnockback(Player player, LivingEntity attacker, float damage) {
        DamageSources damageSources = player.level().damageSources();
        attacker.hurt(damageSources.playerAttack(player), damage);

        double knockbackStrength = 0.5;
        Vec3 knockbackVector = attacker.position().subtract(player.position()).normalize().scale(knockbackStrength);
        attacker.push(knockbackVector.x, 0.1, knockbackVector.z);

        if (attacker instanceof ServerPlayer serverAttacker) {
            ItemStack unoReverseStack = new ItemStack(ModItems.UNO_REVERSE.get());
            if (!serverAttacker.getInventory().add(unoReverseStack)) {
                serverAttacker.drop(unoReverseStack, false);
            }
        }
    }
}
