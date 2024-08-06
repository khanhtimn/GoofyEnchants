package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.init.ModEnchantment;
import fun.teamti.goofyenchants.init.ModItems;
import fun.teamti.goofyenchants.init.ModNetwork;
import fun.teamti.goofyenchants.network.packet.UnoReverseAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.network.PacketDistributor;

public class UnoReverseHandler {

    public static void handleLivingAttack(LivingAttackEvent event) {

        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) {
            return;
        }

        ItemStack activeItem = event.getEntity().getUseItem();
        int enchantmentLevel = activeItem.getEnchantmentLevel(ModEnchantment.UNO_REVERSE.get());
        if (enchantmentLevel <= 0) {
            return;
        }
        if (activeItem.getItem() instanceof ShieldItem && event.getEntity().isBlocking()) {
            double chance = enchantmentLevel * 0.1;
            if (attacker.getRandom().nextDouble() < chance) {
                event.setCanceled(true);
                reflectDamageAndKnockback(event.getEntity(), attacker, event.getAmount());
            }
        }
    }

    private static void reflectDamageAndKnockback(LivingEntity defender, LivingEntity attacker, float damage) {
        DamageSources damageSources = defender.level().damageSources();
        attacker.hurt(damageSources.generic(), damage);

        double knockbackStrength = 0.5;
        Vec3 knockbackVector = attacker.position().subtract(defender.position()).normalize().scale(knockbackStrength);
        attacker.push(knockbackVector.x, 0.1, knockbackVector.z);

        if (attacker instanceof ServerPlayer serverAttacker) {
            ItemStack unoReverseStack = new ItemStack(ModItems.UNO_REVERSE.get());
            ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverAttacker), new UnoReverseAnimationPacket(unoReverseStack));
        }
    }
}

