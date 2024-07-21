package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.init.ModEnchantments;
import fun.teamti.goofyenchants.init.ModScale;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleType;
import virtuoel.pehkui.api.ScaleTypes;

public class SmallHandler {

    private static final ScaleType SIZE_SCALE_TYPE = ModScale.SIZE;
    private static final ScaleType REACH_SCALE_TYPE = ScaleTypes.REACH;


    public static void handleLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        LivingEntity entity = event.getEntity();
        updateEntityScale(entity);

    }

    public static void handlePlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            updateEntityScale(event.player);
        }
    }

    private static void updateEntityScale(LivingEntity entity) {
        boolean hasSmallEnchantment = false;
        for (ItemStack armor : entity.getArmorSlots()) {
            if (armor.getEnchantmentLevel(ModEnchantments.SMALL.get()) > 0) {
                hasSmallEnchantment = true;
                break;
            }
        }

        ScaleData sizeScaleData = SIZE_SCALE_TYPE.getScaleData(entity);
        ScaleData reachScaleData = REACH_SCALE_TYPE.getScaleData(entity);

        if (hasSmallEnchantment) {
            sizeScaleData.setScale(0.1F);
            reachScaleData.setScale(0.2F);
        } else {
            sizeScaleData.setScale(1.0F);
            reachScaleData.setScale(1.0F);
        }
    }
}
