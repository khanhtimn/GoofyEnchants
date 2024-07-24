package fun.teamti.goofyenchants.enchantment.handler;

import fun.teamti.goofyenchants.init.ModScale;
import fun.teamti.goofyenchants.util.IScale;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleModifiers;
import virtuoel.pehkui.api.ScaleType;
import virtuoel.pehkui.api.ScaleTypes;
import virtuoel.pehkui.util.ScaleUtils;

import java.util.Map;

public class SizeHandler {

    private static final ScaleType SIZE_SCALE_TYPE = ModScale.SIZE;
    private static final ScaleType REACH_SCALE_TYPE = ScaleTypes.REACH;
    private static final ScaleType MOTION_SCALE_TYPE = ScaleTypes.MOTION;
    private static final ScaleType JUMP_HEIGHT_SCALE_TYPE = ScaleTypes.JUMP_HEIGHT;
    private static final ScaleType ATTACK_SCALE_TYPE = ScaleTypes.ATTACK;
    private static final ScaleType ATTACK_SPEED_SCALE_TYPE = ScaleTypes.ATTACK_SPEED;
    private static final ScaleType VISIBILITY_SCALE_TYPE = ScaleTypes.VISIBILITY;



    public static void handleLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        LivingEntity entity = event.getEntity();
        updateEntityScale(entity);
    }

    private static void updateEntityScale(LivingEntity entity) {
        ScaleData sizeScaleData = SIZE_SCALE_TYPE.getScaleData(entity);
        ScaleData reachScaleData = REACH_SCALE_TYPE.getScaleData(entity);
        ScaleData motionScaleData = MOTION_SCALE_TYPE.getScaleData(entity);
        ScaleData jumpHeightScaleData = JUMP_HEIGHT_SCALE_TYPE.getScaleData(entity);
        ScaleData attackScaleData = ATTACK_SCALE_TYPE.getScaleData(entity);
        ScaleData attackSpeedScaleData = ATTACK_SPEED_SCALE_TYPE.getScaleData(entity);
        ScaleData visibilityScaleData = VISIBILITY_SCALE_TYPE.getScaleData(entity);

        ItemStack stack = entity.getItemBySlot(EquipmentSlot.HEAD);

        boolean hasRelevantEnchantment = false;

        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);

        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            Enchantment enchantment = entry.getKey();
            int level = entry.getValue();
            if (enchantment instanceof IScale scaleEnchantment) {
                sizeScaleData.setScale(scaleEnchantment.getScaleValue(level));
                reachScaleData.setScale(scaleEnchantment.getReachScaleValue(level));
                motionScaleData.setScale(scaleEnchantment.getMotionScaleValue(level));
                jumpHeightScaleData.setScale(scaleEnchantment.getJumpHeightScaleValue(level));
                attackScaleData.setScale(scaleEnchantment.getAttackScaleValue(level));
                attackSpeedScaleData.setScale(scaleEnchantment.getAttackSpeedScaleValue(level));
                visibilityScaleData.setScale(scaleEnchantment.getVisibilityScaleValue(level));
                hasRelevantEnchantment = true;
                break;
            }
        }

        if (!hasRelevantEnchantment) {
            sizeScaleData.setScale(1.0f);
            reachScaleData.setScale(1.0f);
            motionScaleData.setScale(1.0f);
            jumpHeightScaleData.setScale(1.0f);
            attackScaleData.setScale(1.0f);
            attackSpeedScaleData.setScale(1.0f);
            visibilityScaleData.setScale(1.0f);
        }
    }
}
