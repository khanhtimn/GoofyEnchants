package fun.teamti.goofyenchants.util;

import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.Predicate;

public class ModEnchantmentCategory {

    public static final EnchantmentCategory SHIELD = addEnchantmentCategory("shield", ShieldItem.class::isInstance);
    public static final EnchantmentCategory WEAPON_DIGGER = addEnchantmentCategory("weapon_digger", item ->
            item instanceof SwordItem ||
                    item instanceof DiggerItem
    );
    public static final EnchantmentCategory SHOOTABLE = addEnchantmentCategory("shootable", ProjectileWeaponItem.class::isInstance);

    private static EnchantmentCategory addEnchantmentCategory(String name, Predicate<Item> condition) {
        return EnchantmentCategory.create(name, condition);
    }
}
