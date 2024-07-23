package fun.teamti.goofyenchants.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.UntouchingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UntouchingEnchantment.class)
public class SilkTouchEnchantmentMixin extends Enchantment {
    protected SilkTouchEnchantmentMixin(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots){
        super(rarity, EnchantmentCategory.DIGGER, slots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
    @Inject(method = "getMinCost", at = @At(value = "RETURN"), cancellable = true)
    private void modifyMinCost(int level, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(cir.getReturnValue() + (level - 1) * 20);
    }

    @Inject(method = "getMaxCost", at = @At(value = "HEAD"), cancellable = true)
    private void modifyMaxCost(int level, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(40 + level * 20);
    }
}
