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
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
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

        if (enchantmentLevel <= 0) {
            return generatedLoot;
        }

        ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();
        double smeltChance = switch (enchantmentLevel) {
            case 1 -> 0.25;
            case 2 -> 0.5;
            case 3 -> 0.75;
            case 4 -> 1.0;
            default -> 0.0;
        };

        for (ItemStack stack : generatedLoot) {
            if (context.getRandom().nextDouble() < smeltChance) {
                newLoot.add(smeltItem(stack, context));
            } else {
                newLoot.add(stack);
            }
        }

        return newLoot;
    }

    private ItemStack smeltItem(ItemStack pStack, LootContext pContext) {
        if (!pStack.isEmpty()) {
            Optional<SmeltingRecipe> optional = pContext.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(pStack), pContext.getLevel());
            if (optional.isPresent()) {
                ItemStack itemstack = optional.get().getResultItem(pContext.getLevel().registryAccess());
                if (!itemstack.isEmpty()) {
                    return itemstack.copyWithCount(pStack.getCount() * itemstack.getCount());
                }
            }
        }
        return pStack;
    }

}