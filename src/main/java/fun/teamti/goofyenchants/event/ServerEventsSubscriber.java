package fun.teamti.goofyenchants.event;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.enchantment.handler.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoofyEnchants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventsSubscriber {

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        UnoReverseHandler.handleLivingAttack(event);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        CombustionHandler.handleShootingCombustion(event);
        SwappinessHandler.handleEntitySwapItem(event);
    }

    @SubscribeEvent
    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        SizeHandler.handleLivingEquipmentChange(event);
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        UnoReverseHandler.handleServerTick(event);
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        DisloyaltyHandler.handleTridentTick(event);
    }

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        DisloyaltyHandler.handleTridentThrow(event);
    }

}
