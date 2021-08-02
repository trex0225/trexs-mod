package trex0225.trexs.mod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import trex0225.trexs.mod.managers.StatManager;
import trex0225.trexs.mod.util.StatHelper;
import trex0225.trexs.mod.access.StatManagerAccess;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin {
    @Inject(method = "postHit", at = @At("HEAD"), cancellable = true)
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> info) {
        //System.out.println("calling");
        //System.out.println(stack.getItem().getClass());
        if(stack.getItem() instanceof AxeItem) {
            //System.out.println("apply");
            PlayerEntity player = (PlayerEntity) attacker;
            StatManager statManager = ((StatManagerAccess) player).getStatManager(player);
            int damageBonus = statManager.getMelee();

            StatHelper.dealBonusMelee(attacker, statManager, target);
            
            //target.setHealth(target.getHealth() - damageBonus);
            info.setReturnValue(true);
        }
    }
}
