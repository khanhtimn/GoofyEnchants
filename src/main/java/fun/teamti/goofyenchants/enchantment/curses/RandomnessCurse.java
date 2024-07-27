package fun.teamti.goofyenchants.enchantment.curses;

import fun.teamti.goofyenchants.util.ModEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;


public class RandomnessCurse extends Enchantment {

    public RandomnessCurse(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, ModEnchantmentCategory.WEAPON_DIGGER, slots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
