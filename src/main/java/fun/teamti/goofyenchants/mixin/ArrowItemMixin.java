package fun.teamti.goofyenchants.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArrowItem.class)
public class ArrowItemMixin {
    @Inject(method = "isInfinite", remap = false, at = @At(value = "RETURN"), cancellable = true)
    private void setInfiniteIfUsingInfinityII(ItemStack stack, ItemStack bow, Player player, CallbackInfoReturnable<Boolean> cir){
        if (!cir.getReturnValue()){
            int enchant = bow.getEnchantmentLevel(Enchantments.INFINITY_ARROWS);
            if ( enchant > 1 ) cir.setReturnValue(true);
        }
    }
}
