package fun.teamti.goofyenchants.event;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.init.ModParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoofyEnchants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventsSubscriber {

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        ModParticle.handleRegisterParticle(event);
    }
}
