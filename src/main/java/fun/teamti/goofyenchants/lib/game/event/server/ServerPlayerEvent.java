package fun.teamti.goofyenchants.lib.game.event.server;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraft.server.level.ServerPlayer;

public class ServerPlayerEvent extends PlayerEvent {
    private final ServerPlayer serverPlayer;

    public ServerPlayerEvent(ServerPlayer serverPlayer) {
        super(serverPlayer);
        this.serverPlayer = serverPlayer;
    }

    public ServerPlayer getServerPlayer() {
        return serverPlayer;
    }
}
