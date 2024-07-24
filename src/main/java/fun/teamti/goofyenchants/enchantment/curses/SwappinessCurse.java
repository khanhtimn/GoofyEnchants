package fun.teamti.goofyenchants.enchantment.curses;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SwappinessCurse extends Enchantment {
    public SwappinessCurse(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.BREAKABLE, pApplicableSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }

}
