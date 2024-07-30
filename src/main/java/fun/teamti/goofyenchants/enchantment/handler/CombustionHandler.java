package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.init.ModEnchantment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.ProjectileImpactEvent;

public class CombustionHandler {

    public static void handleShootingCombustion(ProjectileImpactEvent event) {
        Entity projectile = event.getEntity();
        Level level = projectile.level();

        if (!(projectile instanceof AbstractArrow arrow)) {
            return;
        }

        Entity shooter = arrow.getOwner();
        if (!(shooter instanceof LivingEntity livingShooter)) {
            return;
        }

        ItemStack bow = livingShooter.getMainHandItem();
        int enchantmentLevel = bow.getEnchantmentLevel(ModEnchantment.COMBUSTION.get());

        if (enchantmentLevel <= 0) {
            return;
        }

        double chance = 0.06 * enchantmentLevel;
        int explodeRadius = switch (enchantmentLevel) {
            case 1 -> 3 + level.random.nextInt(2);
            case 2 -> 4 + level.random.nextInt(2);
            case 3 -> 5 + level.random.nextInt(2);
            default -> 0;
        };

        if (!level.isClientSide() && level.random.nextDouble() < chance) {
            level.explode(null, projectile.getX(), projectile.getY(), projectile.getZ(), explodeRadius, Level.ExplosionInteraction.NONE);
            event.setImpactResult(ProjectileImpactEvent.ImpactResult.DEFAULT);
        }
    }
}

