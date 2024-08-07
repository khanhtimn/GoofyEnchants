package fun.teamti.goofyenchants.network.packet;

import fun.teamti.goofyenchants.client.sound.ModSoundManager;
import fun.teamti.goofyenchants.enchantment.enchantments.InnerConscienceHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StopConscienceSoundPacket {

    public StopConscienceSoundPacket() {}

    public StopConscienceSoundPacket(FriendlyByteBuf ignoredBuf) {}

    public void toBytes(FriendlyByteBuf ignoredBuf) {}

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() ->
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                    ModSoundManager.stopSoundInList(InnerConscienceHandler.CONSCIENCE_SOUND_TRACKER)));
        contextSupplier.get().setPacketHandled(true);
    }
}
