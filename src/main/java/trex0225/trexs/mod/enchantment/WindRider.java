package trex0225.trexs.mod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class WindRider extends Enchantment {
    public WindRider() {
        super(Enchantment.Rarity.UNCOMMON, null, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    
}
