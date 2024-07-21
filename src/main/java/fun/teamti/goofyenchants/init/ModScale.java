package fun.teamti.goofyenchants.init;

import fun.teamti.goofyenchants.GoofyEnchants;
import net.minecraft.resources.ResourceLocation;
import virtuoel.pehkui.api.*;

public class ModScale {
    public static ScaleModifier SIZE_MODIFIER;
    public static ScaleType SIZE;

    public static void init() {
        SIZE_MODIFIER = registerModifier(new TypedScaleModifier(() -> SIZE));
        SIZE = registerScale();

        ScaleTypes.HEIGHT.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.WIDTH.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.VISIBILITY.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.MOTION.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
    }

    private static ScaleType registerScale() {
        ScaleType.Builder builder = ScaleType.Builder.create().affectsDimensions();
        builder.addDependentModifier(ScaleModifiers.REACH_MULTIPLIER);
        builder.addDependentModifier(SIZE_MODIFIER);
        return ScaleRegistries.register(ScaleRegistries.SCALE_TYPES, new ResourceLocation(GoofyEnchants.MOD_ID, "size"), builder.build());
    }

    private static ScaleModifier registerModifier(ScaleModifier modifier) {
        return ScaleRegistries.register(ScaleRegistries.SCALE_MODIFIERS, new ResourceLocation(GoofyEnchants.MOD_ID, "size_modifier"), modifier);
    }
}
