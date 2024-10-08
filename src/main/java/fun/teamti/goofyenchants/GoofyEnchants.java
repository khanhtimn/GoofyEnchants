package fun.teamti.goofyenchants;

import com.mojang.logging.LogUtils;
import fun.teamti.goofyenchants.init.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(GoofyEnchants.MOD_ID)
public class GoofyEnchants
{
    public static final String MOD_ID = "goofyenchants";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GoofyEnchants()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        ModItems.ITEMS.register(modEventBus);
        ModEntity.ENTITY_TYPES.register(modEventBus);
        ModSound.SOUND_EVENTS.register(modEventBus);
        ModEnchantment.ENCHANTMENTS.register(modEventBus);
        ModLootModifier.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        ModParticle.PARTICLE_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ModNetwork.init();
        ModScale.init();
    }
}
