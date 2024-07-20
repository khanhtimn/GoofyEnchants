package fun.teamti.goofyenchants.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;

public class ClientHandler {
    public static void handleUnoReverseAnimation(ItemStack itemStack) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            Minecraft.getInstance().gameRenderer.displayItemActivation(itemStack);
        }
    }
}
