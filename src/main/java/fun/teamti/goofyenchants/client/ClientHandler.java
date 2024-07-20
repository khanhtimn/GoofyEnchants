package fun.teamti.goofyenchants.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ClientHandler {
    public static void handleUnoReverseAnimation(ItemStack itemStack) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            // Set the animation stack to the custom item stack
            player.setItemInHand(player.getUsedItemHand(), itemStack);
            // Trigger the totem animation
            Minecraft.getInstance().gameRenderer.displayItemActivation(itemStack);
        }
    }
}
