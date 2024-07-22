package fun.teamti.goofyenchants.init;

import fun.teamti.goofyenchants.GoofyEnchants;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntity {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister
            .create(ForgeRegistries.ENTITY_TYPES, GoofyEnchants.MOD_ID);
}
