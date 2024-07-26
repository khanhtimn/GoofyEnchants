package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.init.ModEnchantment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;

import java.util.Optional;

public class SmeltingTouchHandler {

    public static void handleSmeltingTouch(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        LevelAccessor world = event.getLevel();
        if (!(world instanceof Level level)) return;

        ItemStack tool = player.getMainHandItem();
        int enchantmentLevel = tool.getEnchantmentLevel(ModEnchantment.SMELTING_TOUCH.get());
        if (enchantmentLevel <= 0) return;

        BlockPos pos = event.getPos();
        BlockState blockState = world.getBlockState(pos);
        ItemStack blockStack = new ItemStack(blockState.getBlock());

        Optional<SmeltingRecipe> optional = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(blockStack), level);
        if (optional.isEmpty()) return;

        ItemStack result = optional.get().getResultItem(level.registryAccess());
        if (result.isEmpty()) return;

        world.levelEvent(2001, pos, Block.getId(blockState));

        if (!level.isClientSide()) {
            double smeltChance = switch (enchantmentLevel) {
                case 1 -> 0.25;
                case 2 -> 0.5;
                case 3 -> 0.75;
                case 4 -> 1.0;
                default -> 0.0;
            };

            if (level.random.nextDouble() < smeltChance) {
                ItemStack smeltedStack = result.copyWithCount(blockStack.getCount() * result.getCount());
                ItemEntity itemEntity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, smeltedStack);
                itemEntity.setPickUpDelay(10);
                level.addFreshEntity(itemEntity);

                level.addFreshEntity(new ExperienceOrb(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 1));
                event.setCanceled(true);
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            } else {
                event.setCanceled(false);
            }
        }
    }
}
