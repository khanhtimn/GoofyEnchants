package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.init.ModEnchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class RandomnessHandler {
    public static void handleRandomDrop(BlockEvent.BreakEvent event, LevelAccessor world, double x, double y, double z) {
        Player player = event.getPlayer();
        ItemStack stack = player.getMainHandItem();

        int enchantmentLevel = stack.getEnchantmentLevel(ModEnchantments.RANDOMNESS.get());
        if (enchantmentLevel <= 0 || event.getLevel().isClientSide()) {
            return;
        }

        double dropChance = 0.05 * enchantmentLevel;

        if (world.getRandom().nextDouble() < dropChance) {
            List<Item> items = ForgeRegistries.ITEMS
                    .getValues()
                    .stream()
                    .toList();

            ItemStack randomItem = new ItemStack(items.get(world.getRandom().nextInt(
                    ForgeRegistries.ITEMS.getValues().size())));

            if (world instanceof ServerLevel level) {
                ItemEntity dropToSpawn = new ItemEntity(level,
                        (double)Math.round(x) + 0.5,
                        (double)Math.round(y) + 0.5,
                        (double)Math.round(z) + 0.5,
                        randomItem);
                dropToSpawn.setPickUpDelay(10);
                event.setCanceled(true);
                world.destroyBlock(event.getPos(), false);
                level.addFreshEntity(dropToSpawn);
            }
        }
    }
}
