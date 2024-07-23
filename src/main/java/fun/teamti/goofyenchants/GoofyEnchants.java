package fun.teamti.goofyenchants;

import com.mojang.logging.LogUtils;
import fun.teamti.goofyenchants.init.ModEnchantments;
import fun.teamti.goofyenchants.init.ModItems;
import fun.teamti.goofyenchants.init.ModNetwork;
import fun.teamti.goofyenchants.init.ModScale;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

import java.util.Random;

@Mod(GoofyEnchants.MOD_ID)
public class GoofyEnchants
{
    public static final String MOD_ID = "goofyenchants";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Random rand = new Random();

    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("goofyenchants", "goofyenchants"), () -> "1", "1"::equals, "1"::equals);

    public GoofyEnchants()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        ModItems.ITEMS.register(modEventBus);
        ModEnchantments.ENCHANTMENTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ModNetwork.init();
        ModScale.init();
    }
}
