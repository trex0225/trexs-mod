package trex0225.trexs.mod.entity.renderer;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.registry.Registry;
import trex0225.trexs.mod.entity.SpearEntity;
import trex0225.trexs.mod.entity.model.SpearEntityModel;

public class SpearEntityRenderer extends EntityRenderer<SpearEntity>{
    private static final Map<EntityType<?>, Identifier> TEXTURES = new HashMap<>();
    private final SpearEntityModel model = new SpearEntityModel(SpearEntityModel.getTexturedModelData().createModel());

    public SpearEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(SpearEntity spear_Entity, float f, float g, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumerProvider,
                model.getLayer(this.getTexture(spear_Entity)), false, spear_Entity.enchantingGlint());

        matrixStack.multiply(Vec3f.POSITIVE_Y
                .getDegreesQuaternion(MathHelper.lerp(g, spear_Entity.prevYaw, spear_Entity.getYaw()) - 90.0F));
        matrixStack.multiply(Vec3f.POSITIVE_Z
                .getDegreesQuaternion(MathHelper.lerp(g, spear_Entity.prevPitch, spear_Entity.getPitch()) + 90.0F));

        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.scale(1.0F, -1.0F, 1.0F);
        matrixStack.translate(0.0D, 0.0D, -0.2D);
        matrixStack.pop();
        super.render(spear_Entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(SpearEntity spear_Entity) {
        return getTexture(spear_Entity.getType());
    }

    public static Identifier getTexture(EntityType<?> type) {
        if (!TEXTURES.containsKey(type)) {
            TEXTURES.put(type, new Identifier("trexsmod",
                    "textures/entity/" + Registry.ENTITY_TYPE.getId(type).getPath() + ".png"));
        }
        return TEXTURES.get(type);
    }
}
