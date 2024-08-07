package fun.teamti.goofyenchants.init;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.network.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetwork {

    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void init() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(GoofyEnchants.MOD_ID, "networking"),
                () -> "1.0", s -> true, s -> true);

        INSTANCE.registerMessage(nextID(), UnoReverseAnimationPacket.class,
                UnoReverseAnimationPacket::toBytes,
                UnoReverseAnimationPacket::new,
                UnoReverseAnimationPacket::handle);

        INSTANCE.registerMessage(nextID(), StopConscienceSoundPacket.class,
                StopConscienceSoundPacket::toBytes,
                StopConscienceSoundPacket::new,
                StopConscienceSoundPacket::handle);

        INSTANCE.registerMessage(nextID(), PlayConscienceSoundPacket.class,
                PlayConscienceSoundPacket::toBytes,
                PlayConscienceSoundPacket::new,
                PlayConscienceSoundPacket::handle);
    }
}