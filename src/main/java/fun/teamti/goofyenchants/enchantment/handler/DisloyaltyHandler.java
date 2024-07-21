package fun.teamti.goofyenchants.enchantment.handler;


import fun.teamti.goofyenchants.init.ModEnchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class DisloyaltyHandler {

    private static final int FLY_TIME_MAX = 40;

    public static void onTridentTick(TickEvent.LevelTickEvent event) {
        if (event.level.isClientSide) {
            return;
        }

        for (Entity entity : event.level.getEntitiesOfClass(ThrownTrident.class, event.level.get, entity -> entity instanceof ThrownTrident)) {
            ThrownTrident trident = (ThrownTrident) entity;
            Player owner = trident.getOwner() instanceof Player ? (Player) trident.getOwner() : null;

            if (owner != null) {
                ItemStack itemStack = owner.getMainHandItem();  // Assume trident is thrown with main hand item

                if (itemStack.getItem() instanceof TridentItem) {
                    if (itemStack.getEnchantmentLevel(ModEnchantments.DISLOYALTY.get()) > 0) {
                        // Update trident's flight time
                        int flyTicks = trident.getPersistentData().getInt("flyTicks");
                        flyTicks++;
                        trident.getPersistentData().putInt("flyTicks", flyTicks);

                        if (flyTicks > FLY_TIME_MAX) {
                            Vec3 direction = new Vec3(owner.getX() - trident.getX(), owner.getY() - trident.getY(), owner.getZ() - trident.getZ());
                            trident.setDeltaMovement(trident.getDeltaMovement().scale(0.9).add(direction.normalize().scale(0.25)));

                            if (trident.getLevel().getGameTime() % 10 == 0) {
                                trident.playSound(SoundEvents.TRIDENT_RETURN, 1.0F, 1.0F);
                            }

                            if (!owner.isInWater()) {
                                trident.setDeltaMovement(Vec3.ZERO);
                                trident.hurt(DamageSource.OUT_OF_WORLD, 4.0F);
                                trident.discard();
                            }
                        }
                    }
                }
            }
        }
    }

//    @SubscribeEvent
//    public static void onEntityHit(LivingHurtEvent event) {
//        Entity entity = event.getEntity();
//        if (entity instanceof ThrownTrident) {
//            ThrownTrident trident = (ThrownTrident) entity;
//            trident.
//            ItemStack itemStack = trident.getItem();
//
//        }
//    }
}
