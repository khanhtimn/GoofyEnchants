package fun.teamti.goofyenchants.network;

import fun.teamti.goofyenchants.event.ClientEvents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UnoReverseAnimationPacket {
    private final ItemStack totemItem;

    public UnoReverseAnimationPacket(ItemStack totemItem) {
        this.totemItem = totemItem;
    }

    public UnoReverseAnimationPacket(FriendlyByteBuf buf) {
        this.totemItem = buf.readItem();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeItem(totemItem);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Client-side handling
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                ClientEvents.playUnoReverseAnimation(totemItem);
            });
        });
    }
}