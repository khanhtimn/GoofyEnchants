package fun.teamti.goofyenchants.init;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.enchantment.enchantments.BigEnchantment;
import fun.teamti.goofyenchants.enchantment.enchantments.SmallEnchantment;
import fun.teamti.goofyenchants.enchantment.enchantments.UnoReverseEnchantment;
import fun.teamti.goofyenchants.enchantment.enchantments.RandomnessEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister
            .create(ForgeRegistries.ENCHANTMENTS, GoofyEnchants.MOD_ID);

    public static final RegistryObject<Enchantment> UNO_REVERSE = ENCHANTMENTS
            .register("uno_reverse", UnoReverseEnchantment::new);

    public static final RegistryObject<Enchantment> SMALL = ENCHANTMENTS
            .register("small", SmallEnchantment::new);

    public static final RegistryObject<Enchantment> BIG = ENCHANTMENTS
            .register("big", BigEnchantment::new);

    public static final RegistryObject<Enchantment> RANDOMNESS = ENCHANTMENTS
            .register("randomness", RandomnessEnchantment::new);
}
