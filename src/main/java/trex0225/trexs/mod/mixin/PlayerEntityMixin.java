package trex0225.trexs.mod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import trex0225.trexs.mod.access.ManaManagerAccess;
import trex0225.trexs.mod.access.StatManagerAccess;
import trex0225.trexs.mod.init.EffectsInit;
import trex0225.trexs.mod.managers.ManaManager;
import trex0225.trexs.mod.managers.StatManager;
import trex0225.trexs.mod.util.StatHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements ManaManagerAccess, StatManagerAccess {

    private ManaManager manaManager = new ManaManager();
    private StatManager statManager = new StatManager();

    @Inject(method = "canFoodHeal", at = @At("HEAD"), cancellable = true)
    private void canFoodHealMixin(CallbackInfoReturnable<Boolean> info) {
        if(((LivingEntity)((Object)this)).getActiveStatusEffects().containsKey(EffectsInit.BLEEDING)) {
            info.setReturnValue(false);
        }
    }

    @Override
    public ManaManager getManaManager(PlayerEntity player) {
        return this.manaManager;
    }

    @Override
    public StatManager getStatManager(PlayerEntity player) {
        return this.statManager;
    }

    @Shadow
    protected HungerManager hungerManager = new HungerManager();
    @Shadow
    private int sleepTimer;

    @Inject(method = "Lnet/minecraft/entity/player/PlayerEntity;tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;update(Lnet/minecraft/entity/player/PlayerEntity;)V", shift = Shift.AFTER))
    private void tickMixin(CallbackInfo info) {
        this.manaManager.update((PlayerEntity) (Object) this);
        StatHelper statHelper = new StatHelper();
        statHelper.UpdateStats((PlayerEntity) (Object) this, statManager);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "TAIL"))
    private void readCustomDataFromTagMixin(NbtCompound tag, CallbackInfo info) {
        this.manaManager.readNbt(tag);
        this.statManager.readNbt(tag);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At(value = "TAIL"))
    private void writeCustomDataToTagMixin(NbtCompound tag, CallbackInfo info) {
        this.manaManager.writeNbt(tag);
        this.statManager.writeNbt(tag);
    }

}
