package fun.teamti.goofyenchants.enchantment.handler;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fun.teamti.goofyenchants.init.ModEnchantment;
import fun.teamti.goofyenchants.init.ModLootModifier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SmeltingTouchLootModifier extends LootModifier {

    public static final Supplier<Codec<SmeltingTouchLootModifier>> CODEC = Suppliers.memoize(
            () -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, SmeltingTouchLootModifier::new)));

    public SmeltingTouchLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return ModLootModifier.SMELTING_TOUCH.get();
    }

    @Override
    public @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ItemStack tool = context.getParam(LootContextParams.TOOL);
        int enchantmentLevel = tool.getEnchantmentLevel(ModEnchantment.SMELTING_TOUCH.get());
        double smeltChance = switch (enchantmentLevel) {
            case 1 -> 0.25;
            case 2 -> 0.5;
            case 3 -> 0.75;
            case 4 -> 1.0;
            default -> 0.0;
        };
        if (context.getRandom().nextDouble() < smeltChance) {
            return new ObjectArrayList<>(generatedLoot.stream()
                    .map(stack -> smelt(stack, context.getLevel()))
                    .toList());
        }
        return generatedLoot;
    }

    private static ItemStack smelt(ItemStack stack, Level world) {
        return world.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), world)
                .map(recipe -> recipe.getResultItem(world.registryAccess()))
                .filter(itemStack -> !itemStack.isEmpty())
                .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                .orElse(stack);
    }
}