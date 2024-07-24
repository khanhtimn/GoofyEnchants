package fun.teamti.goofyenchants.event;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.enchantment.handler.*;
import fun.teamti.goofyenchants.init.ModParticle;
import fun.teamti.goofyenchants.particle.UnoReverseParticle;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoofyEnchants.MOD_ID)
public class ForgeEventSubscriber {

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        UnoReverseHandler.handlerLivingAttack(event);
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event) {
        SwappinessHandler.handleEntitySwapItem(event);
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

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        ModParticle.handleRegisterParticle(event);
    }
}
