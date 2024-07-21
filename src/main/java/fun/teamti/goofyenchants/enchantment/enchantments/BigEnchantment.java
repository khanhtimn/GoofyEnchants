package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.init.ModEnchantments;
import fun.teamti.goofyenchants.util.IScale;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class BigEnchantment extends Enchantment implements IScale {

    public BigEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, EnchantmentCategory.ARMOR_HEAD, slots);
    }

    @Override
    public float getScaleValue(int level) {
        return switch (level) {
            case 1 -> 1.5F;
            case 2 -> 2.0F;
            case 3 -> 2.5F;
            default -> 1.0F;
        };
    }

    @Override
    public float getReachScaleValue(int level) {
        return switch (level) {
            case 1 -> 1.2F;
            case 2 -> 1.5F;
            case 3 -> 2.0F;
            default -> 1.0F;
        };
    }

    @Override
    public float getMotionScaleValue(int level) {
        return switch (level) {
            case 1 -> 0.7F;
            case 2 -> 0.6F;
            case 3 -> 0.5F;
            default -> 1.0F;
        };
    }

    @Override
    public float getAttackScaleValue(int level) {
        return switch (level) {
            case 1 -> 1.1F;
            case 2 -> 1.25F;
            case 3 -> 1.5F;
            default -> 1.0F;
        };
    }

    @Override
    public float getAttackSpeedScaleValue(int level) {
        return switch (level) {
            case 1 -> 0.7F;
            case 2 -> 0.6F;
            case 3 -> 0.5F;
            default -> 1.0F;
        };
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
        return pStack.getItem() instanceof ArmorItem &&
                pStack.getEquipmentSlot() == EquipmentSlot.HEAD;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return stack.getItem() instanceof ArmorItem &&
                stack.getEquipmentSlot() == EquipmentSlot.HEAD;
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment pOther) {
        return !pOther.equals(ModEnchantments.SMALL.get());
    }
}
