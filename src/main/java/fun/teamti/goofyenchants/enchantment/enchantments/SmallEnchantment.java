package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.init.ModEnchantment;
import fun.teamti.goofyenchants.util.IScale;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;


public class SmallEnchantment extends Enchantment implements IScale {

    public SmallEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, EnchantmentCategory.ARMOR_HEAD, slots);
    }

    @Override
    public float getScaleValue(int level) {
        return switch (level) {
            case 1 -> 0.5F;
            case 2 -> 0.25F;
            case 3 -> 0.1F;
            default -> 1.0F;
        };
    }

    @Override
    public float getReachScaleValue(int level) {
        return switch (level) {
            case 1 -> 0.7F;
            case 2 -> 0.5F;
            case 3 -> 0.2F;
            default -> 1.0F;
        };
    }

    @Override
    public float getMotionScaleValue(int level) {
        return switch (level) {
            case 1 -> 1.5F;
            case 2 -> 1.8F;
            case 3 -> 2.0F;
            default -> 1.0F;
        };
    }

    @Override
    public float getJumpHeightScaleValue(int level) {
        return switch (level) {
            case 1 -> 2.0F;
            case 2 -> 4.0F;
            case 3 -> 10.0F;
            default -> 1.0F;
        };
    }

    @Override
    public float getAttackScaleValue(int level) {
        return switch (level) {
            case 1 -> 0.9F;
            case 2 -> 0.8F;
            case 3 -> 0.75F;
            default -> 1.0F;
        };
    }

    @Override
    public float getAttackSpeedScaleValue(int level) {
        return switch (level) {
            case 1 -> 0.8F;
            case 2 -> 0.7F;
            case 3 -> 0.6F;
            default -> 1.0F;
        };
    }

    @Override
    public float getVisibilityScaleValue(int level) {
        return switch (level) {
            case 2 -> 0.78125F;
            case 3 -> 0.625F;
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
        return (pStack.getItem() instanceof ArmorItem &&
                pStack.getEquipmentSlot() == EquipmentSlot.HEAD)
                || super.canEnchant(pStack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return (stack.getItem() instanceof ArmorItem &&
                stack.getEquipmentSlot() == EquipmentSlot.HEAD)
                || super.canApplyAtEnchantingTable(stack);
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment pOther) {
        return pOther != ModEnchantment.BIG.get();
    }

}
