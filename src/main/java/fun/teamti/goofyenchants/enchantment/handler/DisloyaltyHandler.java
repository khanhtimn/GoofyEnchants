package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.init.ModEnchantment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

import java.util.ArrayList;
import java.util.List;

public class DisloyaltyHandler {
    private static final int FLY_TIME_MAX = 30;
    private static final double SEARCH_RADIUS = 160;
    private static final int DESPAWN_TIME = 400;

    public static void handleTridentThrow(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ThrownTrident trident) {
            CompoundTag tridentNBT = trident.serializeNBT();
            // Try to access the Trident item data directly
            if (tridentNBT.contains("Trident", CompoundTag.TAG_COMPOUND)) {
                CompoundTag tridentItemNBT = tridentNBT.getCompound("Trident");
                if (hasEnchantment(tridentItemNBT, ModEnchantment.DISLOYALTY.getId())) {
                    //GoofyEnchants.LOGGER.info("Trident has Disloyalty");
                    trident.getPersistentData().putBoolean("hasDisloyalty", true);
                }
            } else {
                GoofyEnchants.LOGGER.info("Trident item data not found in NBT");
            }
        }
    }

    public static void handleTridentTick(TickEvent.LevelTickEvent event) {
        if (event.level.isClientSide || event.phase != TickEvent.Phase.END) {
            return;
        }

        ServerLevel serverLevel = (ServerLevel) event.level;
        List<ThrownTrident> allTridents = new ArrayList<>();

        // Get all players in the level
        for (ServerPlayer player : serverLevel.players()) {
            // Search in a radius around each player
            AABB searchBox = player.getBoundingBox().inflate(SEARCH_RADIUS);
            List<ThrownTrident> nearbyTridents = serverLevel.getEntitiesOfClass(ThrownTrident.class, searchBox);
            allTridents.addAll(nearbyTridents);
        }

        List<ThrownTrident> disloyalTridents = allTridents.stream()
                .filter(trident -> {
                    //GoofyEnchants.LOGGER.info("Trident at {} has hasDisloyalty: {}", trident.position(), hasFlag);
                    return trident.getPersistentData().getBoolean("hasDisloyalty");
                })
                .toList();

        for (ThrownTrident trident : disloyalTridents) {
            //GoofyEnchants.LOGGER.info("Processing Disloyal Trident at {}", trident.position());
            CompoundTag nbt = trident.getPersistentData();
            int flyTicks = nbt.getInt("flyTicks");
            flyTicks++;

            if (flyTicks > FLY_TIME_MAX) {
                Entity owner = trident.getOwner();
                if (owner instanceof LivingEntity user) {
                    Vec3 direction = new Vec3(user.getX() - trident.getX(), user.getY() - trident.getY(), user.getZ() - trident.getZ());
                    trident.setDeltaMovement(trident.getDeltaMovement().scale(0.9).add(direction.normalize().scale(0.25)));
                    trident.setNoGravity(true);

                    if (trident.level().getGameTime() % 10 == 0) {
                        trident.playSound(SoundEvents.TRIDENT_RETURN, 1.0F, 1.0F);
                    }

                    if (trident.position().closerThan(owner.position(), 2.0D)) {
                        //GoofyEnchants.LOGGER.info("Trident close to owner, applying damage");
                        owner.hurt(trident.damageSources().trident(trident, owner), 8.0F);
                    }
                }
                if (flyTicks > FLY_TIME_MAX + DESPAWN_TIME) {
                    trident.discard();
                }

            }
            trident.getPersistentData().putInt("flyTicks", flyTicks);
        }
    }

    private static boolean hasEnchantment(CompoundTag itemNBT, ResourceLocation enchantmentId) {
        if (itemNBT.contains("tag", CompoundTag.TAG_COMPOUND)) {
            CompoundTag tag = itemNBT.getCompound("tag");
            if (tag.contains("Enchantments", CompoundTag.TAG_LIST)) {
                var enchantments = tag.getList("Enchantments", CompoundTag.TAG_COMPOUND);
                for (int i = 0; i < enchantments.size(); i++) {
                    CompoundTag enchTag = enchantments.getCompound(i);
                    if (enchantmentId.toString().equals(enchTag.getString("id"))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}