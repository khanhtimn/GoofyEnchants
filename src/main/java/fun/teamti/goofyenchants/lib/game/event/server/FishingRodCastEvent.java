package fun.teamti.goofyenchants.lib.game.event.server;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class FishingRodCastEvent extends ServerPlayerEvent {
    private final ItemStack fishingRod;
    private int speedBonus;
    private int luckBonus;

    public FishingRodCastEvent(ServerPlayer serverPlayer, ItemStack fishingRod, int speedBonus, int luckBonus) {
        super(serverPlayer);
        this.fishingRod = fishingRod;
        this.speedBonus = speedBonus;
        this.luckBonus = luckBonus;
    }

    public ItemStack getFishingRod() {
        return fishingRod;
    }

    public int getSpeedBonus() {
        return speedBonus;
    }

    public void setSpeedBonus(int speedBonus) {
        this.speedBonus = speedBonus;
    }

    public int getLuckBonus() {
        return luckBonus;
    }

    public void setLuckBonus(int luckBonus) {
        this.luckBonus = luckBonus;
    }
}
