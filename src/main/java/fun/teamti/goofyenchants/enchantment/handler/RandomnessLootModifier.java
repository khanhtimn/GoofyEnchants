package fun.teamti.goofyenchants.enchantment.handler;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fun.teamti.goofyenchants.init.ModEnchantment;
import fun.teamti.goofyenchants.init.ModLootModifier;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public class RandomnessLootModifier extends LootModifier {
    public static final Supplier<Codec<RandomnessLootModifier>> CODEC = Suppliers.memoize(
            () -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, RandomnessLootModifier::new))
    );

    public RandomnessLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return ModLootModifier.RANDOMNESS.get();
    }

    @Override
    public @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ItemStack tool = context.getParam(LootContextParams.TOOL);
        int enchantmentLevel = tool.getEnchantmentLevel(ModEnchantment.RANDOMNESS.get());
        double dropChance = 0.05 * enchantmentLevel;
        if (context.getRandom().nextDouble() < dropChance) {
            return new ObjectArrayList<>(generatedLoot.stream()
                    .map(stack -> getRandomItem(stack, context.getLevel()))
                    .toList());
        }
        return generatedLoot;
    }

    private static ItemStack getRandomItem(ItemStack stack, Level world) {
        return new ItemStack(
                ForgeRegistries.ITEMS.getValues().stream()
                        .skip(world.getRandom().nextInt(ForgeRegistries.ITEMS.getValues().size()))
                        .findFirst()
                        .orElse(stack.getItem()),
                stack.getCount()
        );
    }
}
