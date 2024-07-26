package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.init.ModEnchantment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CombustionHandler {

    public static void handleShootingCombustion(LivingHurtEvent event) {

        Entity target = event.getSource().getEntity();
        Level level = event.getEntity().level();

        ItemStack activeItem = target instanceof LivingEntity livingEntity ? livingEntity.getMainHandItem() : ItemStack.EMPTY;
        int enchantmentLevel = activeItem.getEnchantmentLevel(ModEnchantment.COMBUSTION.get());

        double chance = 0.05 * enchantmentLevel;

        if (enchantmentLevel <= 0) {
            return;
        }

        if (!level.isClientSide()) {
            if (level.random.nextDouble() < chance) {
                level.explode(null, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), 3, Level.ExplosionInteraction.NONE);
            }
        }

    }
}

