package trex0225.trexs.mod.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import trex0225.trexs.mod.managers.ManaManager;
import trex0225.trexs.mod.access.ManaManagerAccess;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
    @Shadow
    @Final
    @Mutable
    private final MinecraftClient client;
    @Shadow
    private int ticks;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;

    private static final Identifier MANA_ICON = new Identifier("trexsmod:textures/gui/mana.png");

    public InGameHudMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(method = "renderStatusBars", at = @At(value = "TAIL"))
    private void renderStatusBarsMixin(MatrixStack matrices, CallbackInfo info) {
        PlayerEntity playerEntity = this.getCameraPlayer();
        if (playerEntity != null && !playerEntity.isInvulnerable()) {
            ManaManager manaManager = ((ManaManagerAccess) playerEntity).getManaManager(playerEntity);
            int mana = manaManager.getManaLevel();
            LivingEntity livingEntity = this.getRiddenEntity();
            int variable_one;
            int variable_two;
            int variable_three;
            int height = this.scaledHeight - 49;
            int width = this.scaledWidth / 2 + 91;
            if (this.getHeartCount(livingEntity) == 0) {
                for (variable_one = 0; variable_one < 10; ++variable_one) {
                    variable_three = height;
                    if (manaManager.isNotFull() && this.ticks % (mana * 3 + 1) == 0) {
                        variable_three = height + (client.world.random.nextInt(3) - 1); // bouncy
                    } else if (manaManager.isNotFull() && this.ticks % (mana * 8 + 3) == 0) {
                        variable_three = height + (client.world.random.nextInt(3) - 1); // bouncy
                    }
                    int uppderCoord = 9;
                    int beneathCoord = 0;
                    variable_two = width - variable_one * 8 - 9;
                    RenderSystem.setShaderTexture(0, MANA_ICON);
                    this.drawTexture(matrices, variable_two, variable_three, 0, 0, 9, 9);
                    if (variable_one * 2 + 1 < mana) {
                        this.drawTexture(matrices, variable_two, variable_three, beneathCoord, uppderCoord, 9, 9); // Big icon
                    }
                    if (variable_one * 2 + 1 == mana) {
                        this.drawTexture(matrices, variable_two, variable_three, beneathCoord, uppderCoord + 9, 9, 9); // Small icon
                    }
                    if (variable_one * 2 + 1 == mana) {
                        //this.drawTexture(matrices, variable_two, variable_three, beneathCoord + 18, uppderCoord, 9, 9); // Small icon
                    }
                }
            }
        }
    }

    @Inject(method = "getHeartRows", at = @At(value = "HEAD"), cancellable = true)
    private void getHeartRowsMixin(int heartCount, CallbackInfoReturnable<Integer> info) {
        info.setReturnValue((int) Math.ceil((double) heartCount / 10.0D) + 1);
    }

    @Shadow
    private PlayerEntity getCameraPlayer() {
        return null;
    }

    @Shadow
    private LivingEntity getRiddenEntity() {
        return null;
    }

    @Shadow
    private int getHeartCount(LivingEntity entity) {
        return 0;
    }

}