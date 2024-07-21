package fun.teamti.goofyenchants.event;

import fun.teamti.goofyenchants.enchantment.handler.SmallHandler;
import fun.teamti.goofyenchants.enchantment.handler.UnoReverseHandler;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "goofyenchants")
public class ForgeEventSubscriber {

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        UnoReverseHandler.handlerLivingAttack(event);
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        UnoReverseHandler.handlerServerTick(event);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        SmallHandler.handlePlayerTick(event);
    }

    @SubscribeEvent
    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        SmallHandler.handleLivingEquipmentChange(event);
    }
}
