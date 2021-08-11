package trex0225.trexs.mod.toolmaterial;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import trex0225.trexs.mod.init.ItemInit;

public class MithrilToolMaterial implements ToolMaterial{
    @Override
    public int getDurability() {
        return 1300;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 8.0F;
    }

    @Override
    public float getAttackDamage() {
        return 3.0F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 17;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ItemInit.MITHRIL_INGOT);
    }
}
