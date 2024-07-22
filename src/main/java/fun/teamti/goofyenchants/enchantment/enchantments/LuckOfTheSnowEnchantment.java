package fun.teamti.goofyenchants.enchantment.enchantments;

import fun.teamti.goofyenchants.GoofyEnchants;
import fun.teamti.goofyenchants.lib.base.tuple.IntPair;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import fun.teamti.goofyenchants.enchantment.enchantments.api.InterfaceItemEnchantment;

public class LuckOfTheSnowEnchantment extends Enchantment implements InterfaceItemEnchantment.FishingRod {

    public LuckOfTheSnowEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, EnchantmentCategory.FISHING_ROD, slots);
    }

    @Override
    public int getMinCost(int lvl) {
        return lvl * 9 + 6;
    }

    @Override
    public int getMaxCost(int lvl) {
        return getMinCost(lvl) + 45 + lvl;
    }
    public int getMaxLevel() {

        return 3;
    }

    @Override
    public IntPair preFishingRodCast(int lvl, ItemStack fishingRod, ServerPlayer player, int speedBonus, int luckBonus) {
        float temperature = player.level().getBiome(player.blockPosition()).value().getBaseTemperature();
        if ( temperature <= 0.05F ){
            luckBonus *= lvl * 3;
        }
        else if (temperature <= 0.2F) {
            luckBonus += lvl * 2;
        } else if (temperature <= 0.3F) {
            luckBonus += lvl;
        }
        return new IntPair(speedBonus, luckBonus);
    }
}
