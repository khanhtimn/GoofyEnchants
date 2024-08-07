package fun.teamti.goofyenchants.network.packet;

import fun.teamti.goofyenchants.client.particle.UnoReverseParticle;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
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
        context.enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                        UnoReverseParticle.handleUnoReverseAnimationClientSide(itemStack)));
        context.setPacketHandled(true);
    }
}


