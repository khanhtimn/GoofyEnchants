package fun.teamti.goofyenchants.enchantment.curses;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.TridentLoyaltyEnchantment;
import org.jetbrains.annotations.NotNull;

public class DisloyaltyCurse extends Enchantment {

    public DisloyaltyCurse(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.TRIDENT, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof TridentItem;
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment pOther) {
        return !(pOther instanceof TridentLoyaltyEnchantment) && super.checkCompatibility(pOther);
    }
}
