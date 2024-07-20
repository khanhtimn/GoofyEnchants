package fun.teamti.goofyenchants;

import com.mojang.logging.LogUtils;
import fun.teamti.goofyenchants.init.ModEnchantments;
import fun.teamti.goofyenchants.init.ModItems;
import fun.teamti.goofyenchants.init.ModNetwork;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(GoofyEnchants.MOD_ID)
public class GoofyEnchants
{
    public static final String MOD_ID = "goofyenchants";
    private static final Logger LOGGER = LogUtils.getLogger();


    public GoofyEnchants()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        ModItems.ITEMS.register(modEventBus);
        ModEnchantments.ENCHANTMENTS.register(modEventBus);
        ModNetwork.register();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }
}
