package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.init.ModEnchantment;
import fun.teamti.goofyenchants.init.ModNetwork;
import fun.teamti.goofyenchants.init.ModSound;
import fun.teamti.goofyenchants.network.packet.PlayConscienceSoundPacket;
import fun.teamti.goofyenchants.network.packet.StopConscienceSoundPacket;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;
import java.util.WeakHashMap;

public class InnerConscienceHandler {

    public static final List<SoundEvent> CONSCIENCE_SOUND_EVENTS = List.of(
            ModSound.HUY_NGOC_DO.get(),
            ModSound.YEAT_BEING_REAL.get(),
            ModSound.SOI_ECH_EVERYNIGHT.get()
    );

    public static final WeakHashMap<SoundEvent, SimpleSoundInstance> CONSCIENCE_SOUND_TRACKER = new WeakHashMap<>();

    public static void handleSoundPlay(TickEvent.PlayerTickEvent event) {

        if (event.player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantment.INNER_CONSCIENCE.get()) <= 0) {
            return;
        }

        if (event.player.tickCount % 20 == 0) {
            if (event.player.getRandom().nextFloat() < 0.25) {
                if (event.player instanceof ServerPlayer player) {
                    ModNetwork.INSTANCE.send(
                            PacketDistributor.PLAYER.with(() -> player),
                            new PlayConscienceSoundPacket(CONSCIENCE_SOUND_EVENTS));
                }
            }
        }
    }

    public static void handleDeathStopPlay(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantment.INNER_CONSCIENCE.get()) <= 0) {
            return;
        }

        ModNetwork.INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                new StopConscienceSoundPacket());
    }
}

