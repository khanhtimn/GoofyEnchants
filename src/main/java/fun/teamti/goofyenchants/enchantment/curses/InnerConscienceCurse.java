package fun.teamti.goofyenchants.enchantment.curses;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class InnerConscienceCurse extends Enchantment {
    public InnerConscienceCurse(Rarity rarity, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentCategory.ARMOR, equipmentSlots);
    }

    @Override
    public boolean isCurse() {
        return true;
    }
}
