package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.init.ModEnchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

import java.util.ArrayList;
import java.util.List;

public class DisloyaltyHandler {
    private static final int FLY_TIME_MAX = 30;
    private static final double SEARCH_RADIUS = 64;

    public static void handleTridentThrow(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ThrownTrident trident) {
            CompoundTag tridentNBT = trident.serializeNBT();
            // Try to access the Trident item data directly
            if (tridentNBT.contains("Trident", CompoundTag.TAG_COMPOUND)) {
                CompoundTag tridentItemNBT = tridentNBT.getCompound("Trident");
                if (hasEnchantment(tridentItemNBT, ModEnchantments.DISLOYALTY.getId())) {
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
            Player owner = (Player) trident.getOwner();
            if (owner == null) {
                //GoofyEnchants.LOGGER.info("Trident has no owner, skipping");
                continue;
            }

            int flyTicks = trident.getPersistentData().getInt("flyTicks");
            flyTicks++;
            trident.getPersistentData().putInt("flyTicks", flyTicks);
            //GoofyEnchants.LOGGER.info("Trident flyTicks: {}", flyTicks);

            if (flyTicks > FLY_TIME_MAX) {
                if (!isAcceptableReturnOwner(owner)) {
                    trident.discard();
                    return;
                } else {
                    trident.setNoPhysics(true);

                    Vec3 direction = owner.getEyePosition().subtract(trident.position());
                    trident.setPosRaw(trident.getX(), trident.getY() + direction.y * 0.015D, trident.getZ());

                    double speed = 1.05D;
                    trident.setDeltaMovement(trident.getDeltaMovement().scale(0.95D).add(direction.normalize().scale(speed)));

                    if (trident.clientSideReturnTridentTickCount == 0) {
                        trident.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                    }

                    ++trident.clientSideReturnTridentTickCount;

                    if (trident.position().closerThan(owner.position(), 10.0D)) {
                        //GoofyEnchants.LOGGER.info("Trident close to owner, applying damage");
                        owner.hurt(trident.damageSources().trident(trident, owner), 8.0F);
                        trident.discard();
                    }
                }
            }
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

    private static boolean isAcceptableReturnOwner(Player owner) {
        if (owner != null && owner.isAlive()) {
            return !(owner instanceof ServerPlayer) || !owner.isSpectator();
        } else {
            return false;
        }
    }
}