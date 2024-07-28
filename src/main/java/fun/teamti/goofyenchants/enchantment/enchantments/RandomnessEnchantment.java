package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.util.ModEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class RandomnessEnchantment extends Enchantment {

    public RandomnessEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, ModEnchantmentCategory.WEAPON_DIGGER, slots);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return pEnchantmentLevel * 10;
    }

    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 15;
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof DiggerItem || pStack.getItem() instanceof SwordItem || super.canEnchant(pStack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return stack.getItem() instanceof DiggerItem || stack.getItem() instanceof SwordItem|| super.canApplyAtEnchantingTable(stack);
    }
}
