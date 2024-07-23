package fun.teamti.goofyenchants.lib.game.enchantment.visitor;

import net.minecraft.world.item.enchantment.Enchantment;
import java.util.function.BiConsumer;


public interface BiEnchantmentVisitor extends BiConsumer<Enchantment, Integer> {
}
