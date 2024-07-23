package fun.teamti.goofyenchants.event;

import fun.teamti.goofyenchants.enchantment.handler.DisloyaltyHandler;
import fun.teamti.goofyenchants.enchantment.handler.RandomnessHandler;
import fun.teamti.goofyenchants.enchantment.handler.SizeHandler;
import fun.teamti.goofyenchants.enchantment.curses.RandomnessCurse;
import fun.teamti.goofyenchants.enchantment.handler.UnoReverseHandler;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.level.BlockEvent;
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
    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        SizeHandler.handleLivingEquipmentChange(event);
        SizeHandler.handleLivingEquipmentChange(event);
    }

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        DisloyaltyHandler.handleTridentThrow(event);
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        DisloyaltyHandler.handleTridentTick(event);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        RandomnessHandler.handleRandomDrop(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
    }
}
