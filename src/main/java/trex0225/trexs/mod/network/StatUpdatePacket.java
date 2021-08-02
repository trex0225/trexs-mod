package trex0225.trexs.mod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import trex0225.trexs.mod.TrexsMod;
import trex0225.trexs.mod.managers.StatManager;
import trex0225.trexs.mod.access.StatManagerAccess;

public class StatUpdatePacket {
    public static final Identifier STAT_UPDATE = new Identifier(TrexsMod.MOD_ID, "stat_update");

    public static void init() {
        /*
        ClientPlayNetworking.registerGlobalReceiver(STAT_UPDATE, (client, handler, buffer, responseSender) -> {
            int[] bufferArray = buffer.readIntArray();
            int entityId = bufferArray[0];
            int stats = bufferArray[1];
            client.execute(() -> {
                if (client.player.world.getEntityById(entityId) != null) {
                    PlayerEntity player = (PlayerEntity) client.player.world.getEntityById(entityId);
                    StatManager statManager = ((StatManagerAccess) player).getStatManager(player);
                    statManager.setMelee(stats);
                    statManager.setMelee(stats);
                    statManager.setMelee(stats);
                }
            });
        });
        */
    }
}
