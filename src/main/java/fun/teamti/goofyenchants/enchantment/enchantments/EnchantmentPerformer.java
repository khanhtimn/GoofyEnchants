package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.enchantment.enchantments.api.InterfaceItemEnchantment;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import fun.teamti.goofyenchants.enchantment.enchantments.api.isEnable;

import java.util.function.Consumer;

import static fun.teamti.goofyenchants.lib.game.enchantment.EnchantmentIterator.runOnItem;

public class EnchantmentPerformer {

    public static boolean isEnabled(Enchantment ench) {
        if (ench instanceof isEnable hench) {
            return hench.isEnabled();
        }
        return true;
    }

    public static <T> boolean perform(Enchantment ench, Class<T> clazz, Consumer<T> action) {
        if (clazz.isInstance(ench)) {
            if (isEnabled(ench)) {
                action.accept(clazz.cast(ench));
                return true;
            }
        }
        return false;
    }

    public static Pair<Integer, Integer> preFishingRodCast(ItemStack fishingRod, ServerPlayer player, int originalSpeedBonus, int originalLuckBonus) {
        var bonus = new MutablePair<Integer, Integer>(originalSpeedBonus, originalLuckBonus);
        runOnItem(
                (ench, lvl) -> perform(
                        ench, InterfaceItemEnchantment.FishingRod.class,
                        (e) -> {
                            var r = e.preFishingRodCast(lvl, fishingRod, player, bonus.getLeft(), bonus.getRight());
                            bonus.setLeft(r.x());
                            bonus.setRight(r.y());
                        }
                ),
                fishingRod
        );
        return bonus;
    }
}
