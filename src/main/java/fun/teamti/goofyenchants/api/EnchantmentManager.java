package fun.teamti.goofyenchants.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

import javax.annotation.Nullable;
import java.util.HashMap;

public class EnchantmentManager {


    private static final HashMap<ResourceLocation, BooleanValue> ENABLED = new HashMap<>();

    @Nullable
    public static BooleanValue getConfigEnabled(ResourceLocation id) {
        return ENABLED.get(id);
    }

    public static void put(ResourceLocation id, BooleanValue configEnabled) {
        ENABLED.put(id, configEnabled);
    }
}
