package fun.teamti.goofyenchants.init;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.enchantment.curses.DisloyaltyCurse;
import fun.teamti.goofyenchants.enchantment.enchantments.BigEnchantment;
import fun.teamti.goofyenchants.enchantment.enchantments.SmallEnchantment;
import fun.teamti.goofyenchants.enchantment.enchantments.UnoReverseEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister
            .create(ForgeRegistries.ENCHANTMENTS, GoofyEnchants.MOD_ID);

    public static final RegistryObject<Enchantment> UNO_REVERSE = ENCHANTMENTS
            .register("uno_reverse", () -> new UnoReverseEnchantment(
                    Enchantment.Rarity.RARE,
                    EquipmentSlot.MAINHAND,
                    EquipmentSlot.OFFHAND));

    public static final RegistryObject<Enchantment> SMALL = ENCHANTMENTS
            .register("small", () -> new SmallEnchantment(
                    Enchantment.Rarity.RARE));

    public static final RegistryObject<Enchantment> BIG = ENCHANTMENTS
            .register("big", () -> new BigEnchantment(
                    Enchantment.Rarity.RARE));

    public static final RegistryObject<Enchantment> DISLOYALTY = ENCHANTMENTS
            .register("disloyalty", () -> new DisloyaltyCurse(
                    Enchantment.Rarity.RARE));
}
