package fun.teamti.goofyenchants.init;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.enchantment.curses.DisloyaltyCurse;
import fun.teamti.goofyenchants.enchantment.curses.InnerConscienceCurse;
import fun.teamti.goofyenchants.enchantment.curses.SwappinessCurse;
import fun.teamti.goofyenchants.enchantment.enchantments.*;
import fun.teamti.goofyenchants.enchantment.enchantments.RandomnessEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantment {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister
            .create(ForgeRegistries.ENCHANTMENTS, GoofyEnchants.MOD_ID);

    public static final RegistryObject<Enchantment> UNO_REVERSE = ENCHANTMENTS
            .register("uno_reverse", () -> new UnoReverseEnchantment(
                    Enchantment.Rarity.RARE));

    public static final RegistryObject<Enchantment> SMALL = ENCHANTMENTS
            .register("small", () -> new SmallEnchantment(
                    Enchantment.Rarity.RARE));

    public static final RegistryObject<Enchantment> BIG = ENCHANTMENTS
            .register("big", () -> new BigEnchantment(
                    Enchantment.Rarity.VERY_RARE));

    public static final RegistryObject<Enchantment> DISLOYALTY = ENCHANTMENTS
            .register("disloyalty", () -> new DisloyaltyCurse(
                    Enchantment.Rarity.COMMON));

    public static final RegistryObject<Enchantment> RANDOMNESS = ENCHANTMENTS
            .register("randomness", () -> new RandomnessEnchantment(
                    Enchantment.Rarity.RARE));

    public static final RegistryObject<Enchantment> SWAPPINESS = ENCHANTMENTS
            .register("swappiness", () -> new SwappinessCurse(
                    Enchantment.Rarity.COMMON));

    public static final RegistryObject<Enchantment> SMELTING_TOUCH = ENCHANTMENTS
            .register("smelting_touch", () -> new SmeltingTouchEnchantment(
                    Enchantment.Rarity.COMMON));

    public static final RegistryObject<Enchantment> COMBUSTION = ENCHANTMENTS
            .register("combustion", () -> new CombustionEnchantment(
                    Enchantment.Rarity.VERY_RARE));

    public static final RegistryObject<Enchantment> INNER_CONSCIENCE = ENCHANTMENTS
            .register("inner_conscience", () -> new InnerConscienceCurse(
                    Enchantment.Rarity.COMMON));
}
