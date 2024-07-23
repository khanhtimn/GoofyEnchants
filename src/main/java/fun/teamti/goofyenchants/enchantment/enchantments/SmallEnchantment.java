package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.util.IScale;
import net.minecraft.world.entity.EquipmentSlot;
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
            case 2 -> 0.3F;
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
            case 1 -> 1.5F;
            case 2 -> 1.8F;
            case 3 -> 2.0F;
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
    protected boolean checkCompatibility(@NotNull Enchantment pOther) {
        return !(pOther instanceof BigEnchantment);
    }

}
