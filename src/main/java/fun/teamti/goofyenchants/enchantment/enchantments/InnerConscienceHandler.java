package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.init.ModEnchantment;
import fun.teamti.goofyenchants.init.ModSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.DistExecutor;


import java.util.List;
import java.util.WeakHashMap;

public class InnerConscienceHandler {

    private static final WeakHashMap<SoundEvent, SimpleSoundInstance> SOUND_TRACKER = new WeakHashMap<>();

    private static final List<SoundEvent> SOUND_EVENTS = List.of(
            ModSound.HUY_NGOC_DO.get(),
            ModSound.YEAT_BEING_REAL.get(),
            ModSound.SOI_ECH_EVERYNIGHT.get()
    );

    public static void handleSoundPlay(TickEvent.PlayerTickEvent event) {

        if (event.player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantment.INNER_CONSCIENCE.get()) <= 0) {
            return;
        }

        if (event.player.tickCount % 20 == 0) {
            if (event.player.getRandom().nextFloat() < 0.25) {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                        playInnerConscienceSound(event.player));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void playInnerConscienceSound(Player player) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.level != null;
        assert mc.player != null;

        for (SoundEvent soundEvent : SOUND_EVENTS) {
            SimpleSoundInstance soundInstance = SOUND_TRACKER.get(soundEvent);
            if (soundInstance != null && mc.getSoundManager().isActive(soundInstance)) {
                return;
            }
        }

        SoundEvent soundEvent = SOUND_EVENTS.get(player.getRandom().nextInt(SOUND_EVENTS.size()));
        SimpleSoundInstance soundInstance = SimpleSoundInstance.forUI(soundEvent, 1.0F, 1.0F);
        SOUND_TRACKER.put(soundEvent, soundInstance);
        mc.getSoundManager().play(soundInstance);
    }
}
