package fun.teamti.goofyenchants.util;

import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.Predicate;

public class ModEnchantmentCategory {

    public static final EnchantmentCategory SHIELD = addEnchantment("shield", ShieldItem.class::isInstance);
    public static final EnchantmentCategory WEAPON_DIGGER = addEnchantment("weapon_digger", item ->
            item instanceof SwordItem ||
                    item instanceof DiggerItem
    );
    public static final EnchantmentCategory SHOOTABLE = addEnchantment("shootable", ProjectileWeaponItem.class::isInstance);

    private static EnchantmentCategory addEnchantment(String name, Predicate<Item> condition) {
        return EnchantmentCategory.create(name, condition);
    }
}
