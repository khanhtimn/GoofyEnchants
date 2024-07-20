package fun.teamti.goofyenchants.network;

import fun.teamti.goofyenchants.event.ClientEvents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UnoReverseAnimationPacket {
    public UnoReverseAnimationPacket() {}

    public UnoReverseAnimationPacket(FriendlyByteBuf buf) {}

    public void toBytes(FriendlyByteBuf buf) {}

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        // Client-side handling
        ctx.get().enqueueWork(ClientEvents::playUnoReverseAnimation);
    }
}