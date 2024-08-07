package fun.teamti.goofyenchants.init;

import fun.teamti.goofyenchants.GoofyEnchants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSound {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
            .create(ForgeRegistries.SOUND_EVENTS, GoofyEnchants.MOD_ID);

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(GoofyEnchants.MOD_ID, name)));
    }

    public static final RegistryObject<SoundEvent> UNO_REVERSE = registerSoundEvent("uno_reverse");

    public static final RegistryObject<SoundEvent> HUY_NGOC_DO = registerSoundEvent("huy_ngoc_do");

    public static final RegistryObject<SoundEvent> YEAT_BEING_REAL = registerSoundEvent("yeat_being_real");

    public static final RegistryObject<SoundEvent> SOI_ECH_EVERYNIGHT = registerSoundEvent("soi_ech_everynight");
}

