package fun.teamti.goofyenchants.server;

import fun.teamti.goofyenchants.client.ClientHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UnoReverseAnimationPacket {
    private final ItemStack itemStack;

    public UnoReverseAnimationPacket(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public UnoReverseAnimationPacket(FriendlyByteBuf buf) {
        this.itemStack = buf.readItem();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeItem(itemStack);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // Client-side handling to play custom animation
            ClientHandler.handleUnoReverseAnimation(itemStack);
        });
        context.setPacketHandled(true);
    }
}
