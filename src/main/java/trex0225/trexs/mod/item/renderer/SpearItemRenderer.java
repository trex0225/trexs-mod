package trex0225.trexs.mod.item.renderer;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;
import trex0225.trexs.mod.entity.model.SpearEntityModel;
import trex0225.trexs.mod.entity.renderer.SpearEntityRenderer;
import trex0225.trexs.mod.item.SpearItem;

public enum SpearItemRenderer {
    INSTANCE;

    private final SpearEntityModel spearEntityModel = new SpearEntityModel(SpearEntityModel.getTexturedModelData().createModel());

    public boolean render(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded,
    MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model) {
    if (renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND
        || renderMode == ModelTransformation.Mode.FIXED) {
      return false;
    }

    matrices.push();

    model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);

    if (entity != null && entity.isUsingItem() && entity.getActiveItem() == stack
        && (renderMode == ModelTransformation.Mode.THIRD_PERSON_LEFT_HAND
            || renderMode == ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND)) {
      matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(120F));
      //matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
      //matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-45-0F));
      matrices.translate(0.0D, 0.5D, 0.0D);
    } else if (renderMode != ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND
        && renderMode != ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND) {
      //matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
      //matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-45-0F));
      matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-48-0F));
      matrices.translate(0.05D, 0D, 0.0D);
    } else {
      //matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
      //matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-45-0F));
      matrices.translate(0.0D, 0.5D, 0.0D);
    }

    matrices.scale(1.0F, -1.0F, -1.0F);
    VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, this.spearEntityModel.getLayer(
        SpearEntityRenderer.getTexture(((SpearItem) stack.getItem()).getType())), false, stack.hasGlint());
    this.spearEntityModel.render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
    matrices.pop();
    return true;
  }
}
