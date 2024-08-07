package fun.teamti.goofyenchants.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@OnlyIn(Dist.CLIENT)
public class ModSoundManager {

    private static final Minecraft mc = Minecraft.getInstance();

    @OnlyIn(Dist.CLIENT)
    public static void playSoundFromList(WeakHashMap<SoundEvent, SimpleSoundInstance> soundTracker, List<SoundEvent> soundEvents) {

        for (SoundEvent soundEvent : soundEvents) {
            SimpleSoundInstance soundInstance = soundTracker.get(soundEvent);
            if (soundInstance != null && mc.getSoundManager().isActive(soundInstance)) {
                return;
            }
        }

        assert mc.player != null;
        SoundEvent soundEvent = soundEvents.get(mc.player.getRandom().nextInt(soundEvents.size()));
        SimpleSoundInstance soundInstance = SimpleSoundInstance.forUI(soundEvent, 1.0F, 1.0F);
        soundTracker.put(soundEvent, soundInstance);
        mc.getSoundManager().play(soundInstance);
    }


    @OnlyIn(Dist.CLIENT)
    public static void stopSoundInList(WeakHashMap<SoundEvent, SimpleSoundInstance> soundTracker) {
        for (Map.Entry<SoundEvent, SimpleSoundInstance> entry : soundTracker.entrySet()) {
            mc.getSoundManager().stop(entry.getValue());
        }
        soundTracker.clear();
    }
}