package fun.teamti.goofyenchants.network.packet;

import fun.teamti.goofyenchants.init.ModParticle;
import fun.teamti.goofyenchants.init.ModSound;
import net.minecraft.client.Minecraft;
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
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () ->
                        this::handleUnoReverseAnimationClientSide));
        context.setPacketHandled(true);
    }

    private void handleUnoReverseAnimationClientSide() {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        assert mc.level != null;
        mc.particleEngine.createTrackingEmitter(mc.player, ModParticle.UNO_REVERSE_PARTICLE.get(), 30);
        mc.level.playLocalSound(mc.player.getX(), mc.player.getY(), mc.player.getZ(), ModSound.UNO_REVERSE.get(), mc.player.getSoundSource(), 1.0F, 1.0F, false);
        mc.gameRenderer.displayItemActivation(itemStack);
    }
}


