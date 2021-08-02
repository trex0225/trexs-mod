package trex0225.trexs.mod.access;

import net.minecraft.entity.player.PlayerEntity;
import trex0225.trexs.mod.managers.StatManager;

public interface StatManagerAccess {
    public StatManager getStatManager(PlayerEntity player);
}
