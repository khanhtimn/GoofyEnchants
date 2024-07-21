package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.init.ModEnchantments;
import fun.teamti.goofyenchants.util.IScale;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PlayerHeadItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;


public class SmallEnchantment extends Enchantment implements IScale {

    public SmallEnchantment() {
        this(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, EquipmentSlot.HEAD);
    }

    @SuppressWarnings("SameParameterValue")
    protected SmallEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
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
    public boolean canEnchant(ItemStack itemStack) {
        return itemStack.getItem() instanceof ArmorItem && itemStack.getEquipmentSlot() == EquipmentSlot.HEAD || super.canEnchant(itemStack);
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment pOther) {
        return !pOther.equals(ModEnchantments.BIG.get());
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

}
