package fun.teamti.goofyenchants.event;

import fun.teamti.goofyenchants.enchantment.enchantments.EnchantmentPerformer;
import fun.teamti.goofyenchants.enchantment.handler.SizeHandler;
import fun.teamti.goofyenchants.enchantment.enchantments.RandomnessEnchantment;
import fun.teamti.goofyenchants.enchantment.handler.SizeHandler;
import fun.teamti.goofyenchants.enchantment.handler.UnoReverseHandler;
import fun.teamti.goofyenchants.lib.game.event.server.FishingRodCastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "goofyenchants")
public final class ForgeEventSubscriber {

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
//        SizeHandler.handlePlayerTick(event);
    }

    @SubscribeEvent
    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        SizeHandler.handleLivingEquipmentChange(event);
        SizeHandler.handleLivingEquipmentChange(event);
    }
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        RandomnessEnchantment.handleBlockBreak(event);
    }
    @SubscribeEvent
    public static void preFishingRodCast(final FishingRodCastEvent event) {
        var r = EnchantmentPerformer.preFishingRodCast(event.getFishingRod(), event.getServerPlayer(), event.getSpeedBonus(), event.getLuckBonus());
        event.setSpeedBonus(r.getLeft());
        event.setLuckBonus(r.getRight());
    }
}
