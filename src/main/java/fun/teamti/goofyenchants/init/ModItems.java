package fun.teamti.goofyenchants.init;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.item.UnoReverseItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GoofyEnchants.MOD_ID);

    public static final RegistryObject<Item> UNO_REVERSE = ITEMS.register("uno_reverse",
            () -> new UnoReverseItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).setNoRepair()));

}
