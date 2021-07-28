package trex0225.trexs.mod.access;

import net.minecraft.entity.player.PlayerEntity;
import trex0225.trexs.mod.managers.ManaManager;

public interface ManaManagerAccess {
    public ManaManager getManaManager(PlayerEntity player);
}
