package trex0225.trexs.mod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import trex0225.trexs.mod.managers.StatManager;
import trex0225.trexs.mod.util.StatHelper;
import trex0225.trexs.mod.access.StatManagerAccess;

@Mixin(SwordItem.class)
public class SwordItemMixin {
    @Inject(method = "postHit", at = @At("HEAD"), cancellable = true)
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> info) {
        System.out.println("Ting");
        PlayerEntity player = (PlayerEntity) attacker;
        StatManager statManager = ((StatManagerAccess) player).getStatManager(player);
        int damageBonus = statManager.getMelee();

        StatHelper.dealBonusMelee(attacker, statManager, target);
        
        //target.setHealth(target.getHealth() - damageBonus);
        info.setReturnValue(true);
    }
    
}
