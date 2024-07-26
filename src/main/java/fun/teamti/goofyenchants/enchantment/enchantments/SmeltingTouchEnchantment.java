package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.util.ModEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

public class SmeltingTouchEnchantment extends Enchantment {
    public SmeltingTouchEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, ModEnchantmentCategory.WEAPON_DIGGER, pApplicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 4;
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
        return stack.getItem() instanceof DiggerItem || stack.getItem() instanceof SwordItem || super.canApplyAtEnchantingTable(stack);
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment pOther) {
        return pOther != Enchantments.SILK_TOUCH;
    }
}
