package fun.teamti.goofyenchants.event;

import com.mojang.blaze3d.vertex.PoseStack;
import fun.teamti.goofyenchants.GoofyEnchants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemDisplayContext;

@Mod.EventBusSubscriber(modid = GoofyEnchants.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    private static ItemStack totemAnimationItem = ItemStack.EMPTY;

    public static void playUnoReverseAnimation(ItemStack itemStack) {
        totemAnimationItem = itemStack;
    }

    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (!totemAnimationItem.isEmpty() && mc.gameRenderer.currentEffect() != null) {
            // Cancel the original rendering
            event.setCanceled(true);

            // Render our custom item instead
            PoseStack poseStack = event.getPoseStack();
            MultiBufferSource bufferSource = event.getMultiBufferSource();
            int light = event.getPackedLight();

            ItemInHandRenderer itemInHandRenderer = mc.gameRenderer.itemInHandRenderer;

            poseStack.pushPose();
            assert mc.player != null;
            itemInHandRenderer.renderItem(mc.player, totemAnimationItem,
                    event.getHand() == InteractionHand.MAIN_HAND ?
                            ItemDisplayContext.FIRST_PERSON_RIGHT_HAND :
                            ItemDisplayContext.FIRST_PERSON_LEFT_HAND,
                    event.getHand() == InteractionHand.OFF_HAND,
                    poseStack, bufferSource, light);
            poseStack.popPose();

            totemAnimationItem = ItemStack.EMPTY;  // Reset after use
        }
    }
}