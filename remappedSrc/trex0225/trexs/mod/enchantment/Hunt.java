package trex0225.trexs.mod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class Hunt extends Enchantment {
    public Hunt() {
        super(Enchantment.Rarity.UNCOMMON, null, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }
}
