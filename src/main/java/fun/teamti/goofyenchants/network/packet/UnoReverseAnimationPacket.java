package fun.teamti.goofyenchants.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
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
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                Minecraft.getInstance().gameRenderer.displayItemActivation(itemStack);
            }
        });
        context.setPacketHandled(true);
    }
}
