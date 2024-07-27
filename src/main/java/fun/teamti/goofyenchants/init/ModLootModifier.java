package fun.teamti.goofyenchants.init;

import com.mojang.serialization.Codec;
import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.enchantment.handler.RandomnessLootModifier;
import fun.teamti.goofyenchants.enchantment.handler.SmeltingTouchLootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifier {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, GoofyEnchants.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> SMELTING_TOUCH =
            LOOT_MODIFIER_SERIALIZERS.register("smelting_touch", SmeltingTouchLootModifier.CODEC);

    public static final RegistryObject<Codec<RandomnessLootModifier>> RANDOMNESS =
            LOOT_MODIFIER_SERIALIZERS.register("randomness", RandomnessLootModifier.CODEC);

}
