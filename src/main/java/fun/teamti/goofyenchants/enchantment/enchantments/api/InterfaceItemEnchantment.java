package fun.teamti.goofyenchants.enchantment.enchantments.api;

import fun.teamti.goofyenchants.lib.base.tuple.IntPair;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class InterfaceItemEnchantment {
    public static interface FishingRod {
        IntPair preFishingRodCast(int lvl, ItemStack fishingRod, ServerPlayer player, int speedBonus, int luckBonus);
    }
}
