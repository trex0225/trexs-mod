package trex0225.trexs.mod.init;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import trex0225.trexs.mod.entity.model.SpearEntityModel;
import trex0225.trexs.mod.entity.renderer.MagicMissileEntityRenderer;
//import trex0225.trexs.mod.entity.renderer.MagicBoltEntityRenderer;
import trex0225.trexs.mod.entity.renderer.SpearEntityRenderer;
import trex0225.trexs.mod.network.EntitySpawnPacket;

public class RendererInit {
    public static final EntityModelLayer SPEAR_LAYER = new EntityModelLayer(new Identifier("trexsmod:spear_render_layer"), "spear_render_layer");

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(EntitySpawnPacket.ID, EntitySpawnPacket::onPacket);

        EntityRendererRegistry.INSTANCE.register(EntityInit.WOODEN_SPEAR, SpearEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityInit.STONE_SPEAR, SpearEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityInit.IRON_SPEAR, SpearEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityInit.GOLDEN_SPEAR, SpearEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityInit.DIAMOND_SPEAR, SpearEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityInit.NETHERITE_SPEAR, SpearEntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(EntityInit.MAGIC_MISSILE_ENTITY, MagicMissileEntityRenderer::new);
        //EntityRendererRegistry.INSTANCE.register(EntityInit.MAGIC_BOLT_ENTITY, MagicBoltEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(SPEAR_LAYER, SpearEntityModel::getTexturedModelData);
    }

}
