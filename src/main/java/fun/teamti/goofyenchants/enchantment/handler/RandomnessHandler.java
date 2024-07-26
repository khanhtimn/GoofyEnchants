package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.init.ModEnchantment;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class RandomnessHandler {
    public static void handleRandomDrop(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        LevelAccessor levelAccessor = event.getLevel();
        if (!(levelAccessor instanceof Level level)) return;
        if (level.isClientSide()) return;

        ItemStack stack = player.getMainHandItem();
        int enchantmentLevel = stack.getEnchantmentLevel(ModEnchantment.RANDOMNESS.get());
        if (enchantmentLevel <= 0) return;

        double dropChance = 0.05 * enchantmentLevel;
        if (level.random.nextDouble() >= dropChance) return;

        BlockPos pos = event.getPos();
        List<Item> items = ForgeRegistries.ITEMS.getValues().stream().toList();
        Item randomItem = items.get(level.random.nextInt(items.size()));
        ItemStack randomItemStack = new ItemStack(randomItem);

        if (level instanceof ServerLevel serverLevel) {
            ItemEntity dropToSpawn = new ItemEntity(serverLevel,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    randomItemStack);
            dropToSpawn.setPickUpDelay(10);

            event.setCanceled(true);
            level.destroyBlock(pos, false);
            serverLevel.addFreshEntity(dropToSpawn);
        }
    }
}
