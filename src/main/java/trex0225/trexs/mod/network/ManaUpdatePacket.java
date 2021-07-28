package trex0225.trexs.mod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import trex0225.trexs.mod.TrexsMod;
import trex0225.trexs.mod.managers.ManaManager;
import trex0225.trexs.mod.access.ManaManagerAccess;

public class ManaUpdatePacket {
    public static final Identifier MANA_UPDATE = new Identifier(TrexsMod.MOD_ID, "mana_update");

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(MANA_UPDATE, (client, handler, buffer, responseSender) -> {
            int[] bufferArray = buffer.readIntArray();
            int entityId = bufferArray[0];
            int manaLevel = bufferArray[1];
            client.execute(() -> {
                if (client.player.world.getEntityById(entityId) != null) {
                    PlayerEntity player = (PlayerEntity) client.player.world.getEntityById(entityId);
                    ManaManager manaManager = ((ManaManagerAccess) player).getManaManager(player);
                    manaManager.setManaLevel(manaLevel);
                }
            });
        });

    }
}
