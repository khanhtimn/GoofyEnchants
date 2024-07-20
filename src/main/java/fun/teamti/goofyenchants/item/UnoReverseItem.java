package fun.teamti.goofyenchants.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;
import org.jetbrains.annotations.NotNull;

public class UnoReverseItem extends Item {
    public UnoReverseItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        // Custom behavior for the item if needed
        return super.useOn(context);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true; // If you want the item to have the enchanted glint effect
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.world.item.enchantment.Enchantment enchantment) {
        return enchantment.category == EnchantmentCategory.BREAKABLE || super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        // Custom behavior when the item is used to attack an enemy
        return super.hurtEnemy(stack, target, attacker);
    }
}
