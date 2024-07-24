package fun.teamti.goofyenchants.init;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.particle.UnoReverseParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticle {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
            .create(ForgeRegistries.PARTICLE_TYPES, GoofyEnchants.MOD_ID);


    public static final RegistryObject<SimpleParticleType> UNO_REVERSE_PARTICLE = PARTICLE_TYPES
            .register("uno_reverse_particle", () -> new SimpleParticleType(false));

    public static void handleRegisterParticle(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticle.UNO_REVERSE_PARTICLE.get(), UnoReverseParticle.Provider::new);
    }
}
