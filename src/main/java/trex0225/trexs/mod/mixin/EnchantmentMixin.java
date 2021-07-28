package trex0225.trexs.mod.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import trex0225.trexs.mod.init.EnchantmentInit;
import trex0225.trexs.mod.item.SpearItem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void isAcceptableItemMixin(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if (((Object) this == Enchantments.PIERCING || (Object) this == Enchantments.LOYALTY || 
            (Object) this == EnchantmentInit.WIND_RIDER || (Object) this == EnchantmentInit.HUNT) && stack.getItem() instanceof SpearItem) {
            info.setReturnValue(true);
        }
    }
}
