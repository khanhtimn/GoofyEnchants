package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.init.ModEnchantment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class SwappinessHandler {

    private static final double SWAP_CHANCE = 0.2;

    public static void handleEntitySwapItem(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        Entity target = event.getEntity();

        if (source instanceof LivingEntity attacker && target instanceof LivingEntity defender) {
            ItemStack attackerItem = attacker.getMainHandItem();
            int enchantmentLevel = attackerItem.getEnchantmentLevel(ModEnchantment.SWAPPINESS.get());

            if (enchantmentLevel <= 0) {
                return;
            }

            if (GoofyEnchants.rand.nextDouble() < SWAP_CHANCE) {
                swapItem(attacker, defender, attackerItem);
            }
        }
    }

    private static void swapItem(LivingEntity attacker, LivingEntity defender, ItemStack attackerItem) {
        ItemStack defenderItem = defender.getMainHandItem();
        EquipmentSlot slot = EquipmentSlot.MAINHAND;

        defender.setItemSlot(slot, attackerItem.copy());
        if (defender instanceof Mob mob) {
            mob.setDropChance(slot, 1.0F);
        }

        if (!defenderItem.isEmpty()) {
            attacker.setItemSlot(slot, defenderItem.copy());
        } else {
            attacker.getMainHandItem().setCount(0);
        }
    }
}
