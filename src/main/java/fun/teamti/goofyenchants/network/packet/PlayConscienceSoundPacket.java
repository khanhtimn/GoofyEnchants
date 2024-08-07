package fun.teamti.goofyenchants.network.packet;

import fun.teamti.goofyenchants.client.sound.ModSoundManager;
import fun.teamti.goofyenchants.enchantment.enchantments.InnerConscienceHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PlayConscienceSoundPacket {

    private final List<SoundEvent> soundEvents;

    public PlayConscienceSoundPacket(List<SoundEvent> soundEvents) {
        this.soundEvents = soundEvents;
    }

    public PlayConscienceSoundPacket(FriendlyByteBuf buf) {
        int size = buf.readInt();
        soundEvents = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            soundEvents.add(buf.readRegistryIdUnsafe(ForgeRegistries.SOUND_EVENTS));
        }
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(soundEvents.size());
        for (SoundEvent soundEvent : soundEvents) {
            buf.writeRegistryIdUnsafe(ForgeRegistries.SOUND_EVENTS, soundEvent);
        }
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                        ModSoundManager.playSoundFromList(InnerConscienceHandler.CONSCIENCE_SOUND_TRACKER, soundEvents))
        );
        context.setPacketHandled(true);
    }
}
