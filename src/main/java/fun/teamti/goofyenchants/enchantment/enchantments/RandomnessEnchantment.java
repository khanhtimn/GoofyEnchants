package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.init.ModEnchantments;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;


import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.*;


public class RandomnessEnchantment extends Enchantment {

    public RandomnessEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, EnchantmentCategory.DIGGER, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 5 + 10 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof DiggerItem || super.canEnchant(pStack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {
        return stack.getItem() instanceof DiggerItem || super.canApplyAtEnchantingTable(stack);
    }

    public static Enchantment getRandomnessEnchant() {
        return ModEnchantments.RANDOMNESS.get();
    }

    public static List<ItemStack> getRandomItems(RandomSource random, int level) {
        List<ItemStack> drops = new ArrayList<>();
        drops.addAll(getNormalBlockItems(random, level));
        drops.addAll(getRandomEnchantmentItems(random, level));
        return drops;
    }

    private static List<ItemStack> getNormalBlockItems(RandomSource random, int level) {
        return new ArrayList<>();
    }

    private static List<ItemStack> getRandomEnchantmentItems(RandomSource random, int level) {
        List<ItemStack> drops = new ArrayList<>();
        for (int i = 0; i < level; i++) {
            Item randomItem = getRandomItem(random);
            if (randomItem != null) {
                int randomCount = 1 + random.nextInt(level); // Random drop count based on enchantment level
                ItemStack drop = new ItemStack(randomItem, randomCount);
                drops.add(drop);
            }
        }
        return drops;
    }

    private static Item getRandomItem(RandomSource random) {
        List<Item> items = new ArrayList<>();
        for (Item item : ForgeRegistries.ITEMS) {
            items.add(item);
        }
        return items.isEmpty() ? null : items.get(random.nextInt(items.size()));
    }


    public static void handleBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = player.getMainHandItem();

        if (EnchantmentHelper.getItemEnchantmentLevel(RandomnessEnchantment.getRandomnessEnchant(), stack) <= 0 || event.getLevel().isClientSide()) {
            return;
        }

        Level world = (Level) event.getLevel();
        int level = EnchantmentHelper.getItemEnchantmentLevel(RandomnessEnchantment.getRandomnessEnchant(), stack);
        BlockState blockState = world.getBlockState(event.getPos());
        event.setCanceled(true);
        world.destroyBlock(event.getPos(), false);
        event.setExpToDrop(0);

        int rand = new Random().nextInt(1_000_000);
        boolean condition = (rand == 0);
        if ( condition ) {
            RandomSource random = world.random;
            List<ItemStack> drops = RandomnessEnchantment.getRandomItems(random, level);


            for (ItemStack drop : drops) {
                if (!drop.isEmpty()) {
                    ItemHandlerHelper.giveItemToPlayer(player, drop);
                }
            }
        }
    }
}
