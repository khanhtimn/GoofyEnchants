package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.util.ModEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

public class CombustionEnchantment extends Enchantment {
    public CombustionEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, ModEnchantmentCategory.SHOOTABLE, pApplicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return pEnchantmentLevel * 10;
    }

    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 20;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof ProjectileWeaponItem || super.canEnchant(pStack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return stack.getItem() instanceof ProjectileWeaponItem || super.canApplyAtEnchantingTable(stack);
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment pOther) {
        return pOther != Enchantments.INFINITY_ARROWS;
    }
}
