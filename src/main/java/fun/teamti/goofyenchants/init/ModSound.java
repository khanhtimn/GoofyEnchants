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

    public static final RegistryObject<SoundEvent> SOI_ECH_EVERYNIGHT = registerSoundEvent("soi_ech_everynight");

    public static final RegistryObject<SoundEvent> FREE_FIRE = registerSoundEvent("free_fire");

    public static final RegistryObject<SoundEvent> HET_SUONG_CAN_GIO = registerSoundEvent("het_suong_can_gio");

    public static final RegistryObject<SoundEvent> HET_XANG_CAN_SO = registerSoundEvent("het_xang_can_so");

    public static final RegistryObject<SoundEvent> AI_CUNG_DU_THUA = registerSoundEvent("ai_cung_du_thua");

    public static final RegistryObject<SoundEvent> VINFLOW = registerSoundEvent("vinflow");

    public static final RegistryObject<SoundEvent> DANG_LONG_BIEN = registerSoundEvent("dang_long_bien");

    public static final RegistryObject<SoundEvent> QUAN_AO_2HAND = registerSoundEvent("quan_ao_2hand");

    public static final RegistryObject<SoundEvent> VIT_QUAY_LANG_SON = registerSoundEvent("vit_quay_lang_son");

    public static final RegistryObject<SoundEvent> TSMT = registerSoundEvent("tsmt");

    public static final RegistryObject<SoundEvent> PHO_TAI = registerSoundEvent("pho_tai");
}

