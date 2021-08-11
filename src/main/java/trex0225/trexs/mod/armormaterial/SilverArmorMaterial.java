package trex0225.trexs.mod.armormaterial;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import trex0225.trexs.mod.init.ItemInit;

public class SilverArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
	private static final int[] PROTECTION_VALUES = new int[] {2, 7, 7, 3}; 

    @Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * 33;
	}
 
	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return PROTECTION_VALUES[slot.getEntitySlotId()];
	}
 
	@Override
	public int getEnchantability() {
		return 10;
	}
 
	@Override
	public SoundEvent getEquipSound() {
		return null;
	}
 
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ItemInit.MITHRIL_INGOT);
	}
 
	@Override
	public String getName() {
		return "silver";
	}
 
	@Override
	public float getToughness() {
		return 1.0F;
	}
 
	@Override
	public float getKnockbackResistance() {
		return 0.0F;
	}
}
