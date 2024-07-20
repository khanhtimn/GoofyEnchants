package fun.teamti.goofyenchants.event;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoofyEnchants.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    private static boolean playingCustomAnimation = false;
    private static long animationStartTime;

    public static void playUnoReverseAnimation() {
        playingCustomAnimation = true;
        animationStartTime = System.currentTimeMillis();
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGuiOverlayEvent.Post event) {
        if (playingCustomAnimation) {
            Minecraft mc = Minecraft.getInstance();
            long currentTime = System.currentTimeMillis();
            if (currentTime - animationStartTime > 3000) { // 3 seconds animation
                playingCustomAnimation = false;
                return;
            }

            GuiGraphics guiGraphics = event.getGuiGraphics();
            int width = mc.getWindow().getGuiScaledWidth();
            int height = mc.getWindow().getGuiScaledHeight();
            //guiGraphics.renderItem(ModItems.CUSTOM_TOTEM.get().getDefaultInstance(), width / 2 - 8, height / 2 - 8);
        }
    }
}
