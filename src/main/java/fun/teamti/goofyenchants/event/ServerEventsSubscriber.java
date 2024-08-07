package fun.teamti.goofyenchants.event;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.enchantment.enchantments.InnerConscienceHandler;
import fun.teamti.goofyenchants.enchantment.handler.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoofyEnchants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventsSubscriber {

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        InnerConscienceHandler.handleSoundPlay(event);
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        InnerConscienceHandler.handleDeathStopPlay(event);
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        UnoReverseHandler.handleLivingAttack(event);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        SwappinessHandler.handleEntitySwapItem(event);
    }

    @SubscribeEvent
    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        SizeHandler.handleLivingEquipmentChange(event);
    }

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {
        MultiShotBowHandler.handleMultishotBow(event);
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        CombustionHandler.handleShootingCombustion(event);
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