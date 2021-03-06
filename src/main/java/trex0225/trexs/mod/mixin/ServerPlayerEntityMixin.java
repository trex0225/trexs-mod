package trex0225.trexs.mod.mixin;

import com.mojang.authlib.GameProfile;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.netty.buffer.Unpooled;

import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import trex0225.trexs.mod.managers.ManaManager;
import trex0225.trexs.mod.managers.StatManager;
import trex0225.trexs.mod.network.ManaUpdatePacket;
import trex0225.trexs.mod.network.StatUpdatePacket;
import trex0225.trexs.mod.access.ManaManagerAccess;
import trex0225.trexs.mod.access.StatManagerAccess;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    ManaManager manaManager = ((ManaManagerAccess) this).getManaManager(this);
    private int syncedManaLevel = -99999999;

    StatManager statManager = ((StatManagerAccess) this).getStatManager(this);
    private int syncedMeleeLevel = -99999999;
    private int syncedRangedLevel = -99999999;
    private int syncedMagicLevel = -99999999;

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "playerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;tick()V", shift = Shift.AFTER))
    public void playerTickMixin(CallbackInfo info) {
        if (this.syncedManaLevel != this.manaManager.getManaLevel()) {
            PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
            data.writeIntArray(new int[] { this.getId(), manaManager.getManaLevel() });
            ServerPlayNetworking.send((ServerPlayerEntity) (Object) this, ManaUpdatePacket.MANA_UPDATE, data);
            this.syncedManaLevel = manaManager.getManaLevel();
        }

        if (this.syncedMeleeLevel != this.statManager.getMelee()) {
            PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
            data.writeIntArray(new int[] { this.getId(), statManager.getMelee() });
            ServerPlayNetworking.send((ServerPlayerEntity) (Object) this, StatUpdatePacket.STAT_UPDATE, data);
            this.syncedMeleeLevel = statManager.getMelee();
        }
        
        if (this.syncedRangedLevel != this.statManager.getRanged()) {
            PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
            data.writeIntArray(new int[] { this.getId(), statManager.getRanged() });
            ServerPlayNetworking.send((ServerPlayerEntity) (Object) this, StatUpdatePacket.STAT_UPDATE, data);
            this.syncedRangedLevel = statManager.getRanged();
        }

        if (this.syncedMagicLevel != this.statManager.getMagic()) {
            PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
            data.writeIntArray(new int[] { this.getId(), statManager.getMagic() });
            ServerPlayNetworking.send((ServerPlayerEntity) (Object) this, StatUpdatePacket.STAT_UPDATE, data);
            this.syncedMagicLevel = statManager.getMagic();
        }
    }

    @Inject(method = "Lnet/minecraft/server/network/ServerPlayerEntity;copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;clone(Lnet/minecraft/entity/player/PlayerInventory;)V", shift = Shift.AFTER))
    public void copyFromMixinOne(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info) {
        this.manaManager = ((ManaManagerAccess) oldPlayer).getManaManager(oldPlayer);
        this.statManager = ((StatManagerAccess) oldPlayer).getStatManager(oldPlayer);
    }

    @Inject(method = "Lnet/minecraft/server/network/ServerPlayerEntity;copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V", at = @At(value = "TAIL"))
    public void copyFromMixinTwo(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info) {
        this.syncedManaLevel = -1;
        this.syncedMeleeLevel = -1;
        this.syncedRangedLevel = -1;
        this.syncedMagicLevel = -1;
    }

    @Inject(method = "teleport", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setWorld(Lnet/minecraft/server/world/ServerWorld;)V"))
    void teleportFix(ServerWorld targetWorld, double x, double y, double z, float yaw, float pitch, CallbackInfo info) {
        this.syncedManaLevel = -1;
        this.syncedMeleeLevel = -1;
        this.syncedRangedLevel = -1;
        this.syncedMagicLevel = -1;
    }

    @Nullable
    @Inject(method = "moveToWorld", at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayerEntity;syncedFoodLevel:I", ordinal = 0))
    private void moveToWorldMixin(ServerWorld destination, CallbackInfoReturnable<Entity> info) {
        this.syncedManaLevel = -1;
        this.syncedMeleeLevel = -1;
        this.syncedRangedLevel = -1;
        this.syncedMagicLevel = -1;
    }

}