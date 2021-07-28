package trex0225.trexs.mod.entity.model;

import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;
import net.fabricmc.api.EnvType;
import net.minecraft.client.model.*;

@Environment(EnvType.CLIENT)
public class SpearEntityModel extends Model {
    private final ModelPart base;
    //private final ModelPart base = new ModelPart(54, 32, 0, 0);

	public SpearEntityModel(ModelPart base) {
		super(RenderLayer::getEntitySolid);
        this.base = base.getChild("base");

		//base.setTextureOffset(10, 27).addCuboid(0.0F, -1.0F, 8.0F, 1, 1, 4, 0.0F, false);
		//base.setTextureOffset(0, 27).addCuboid(-2.0F, -1.0F, 8.0F, 1, 1, 4, 0.0F, false);
		//base.setTextureOffset(0, 0).addCuboid(-1.0F, -1.0F, -12.0F, 1, 1, 26, 0.0F, false);
	}

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("base",
                ModelPartBuilder.create()
                        .uv(10, 27).cuboid(0.0F, -1.0F, 8.0F, 1.0F, 1.0F, 4.0F)
                        .uv(0, 27).cuboid(-2.0F, -1.0F, 8.0F, 1.0F, 1.0F, 4.0F)
                        .uv(0, 0).cuboid(-1.0F, -1.0F, -12.0F, 1.0F, 1.0F, 26.0F),
                ModelTransform.pivot(0.0F, 0.0F, -3.0F));
        return TexturedModelData.of(modelData, 54, 32);
    }

	@Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red,
            float green, float blue, float alpha) {
        //this.base.roll=8f;
        //this.base.yaw=13.5f;
        //System.out.println("roll: "+this.base.roll);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90F));
        //matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
