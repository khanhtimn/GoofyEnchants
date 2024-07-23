package fun.teamti.goofyenchants.lib.game.enchantment;

import fun.teamti.goofyenchants.lib.game.enchantment.visitor.BiEnchantmentVisitor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;

public class EnchantmentIterator {
    public static void run(BiEnchantmentVisitor visitor, Map<Enchantment, Integer> enchMap) {
        for (var enchEntry : enchMap.entrySet()) visitor.accept(enchEntry.getKey(), enchEntry.getValue());
    }
    public static void runOnItem(BiEnchantmentVisitor visitor, ItemStack itemStack) {
        if (!itemStack.isEmpty()) run(visitor, EnchantmentHelper.getEnchantments(itemStack));
    }

}
