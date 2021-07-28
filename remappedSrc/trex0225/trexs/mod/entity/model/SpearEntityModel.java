package trex0225.trexs.mod.entity.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;

public class SpearEntityModel extends Model {

    private final ModelPart base = new ModelPart(54, 32, 0, 0);

	public SpearEntityModel() {
		super(RenderLayer::getEntitySolid);
        base.setPivot(0.0F, -1.125F, 0.0F);

		base.setTextureOffset(10, 27).addCuboid(0.0F, -1.0F, 8.0F, 1, 1, 4, 0.0F, false);
		base.setTextureOffset(0, 27).addCuboid(-2.0F, -1.0F, 8.0F, 1, 1, 4, 0.0F, false);
		base.setTextureOffset(0, 0).addCuboid(-1.0F, -1.0F, -12.0F, 1, 1, 26, 0.0F, false);
	}

	@Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red,
            float green, float blue, float alpha) {
        //this.base.roll=8f;
        //this.base.yaw=13.5f;
        //System.out.println("roll: "+this.base.roll);
        matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90F));
        //matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
    /*
    private final ModelPart base = new ModelPart(32, 32, 0, 0);

    public SpearEntityModel() {
        super(RenderLayer::getEntitySolid);
        base.setPivot(0.0F, 0.0F, 0.0F);
        base.setTextureOffset(0, 0).addCuboid(-8.0F, -1.0F, -8.0F, 2, 1, 1, 0.0F);
		base.setTextureOffset(0, 2).addCuboid( -8.0F, -1.0F, -7.0F, 3, 1, 1, 0.0F, false);
		base.setTextureOffset(0, 4).addCuboid( -7.0F, -1.0F, -6.0F, 3, 1, 1, 0.0F, false);
		base.setTextureOffset(0, 6).addCuboid( -6.0F, -1.0F, -5.0F, 3, 1, 1, 0.0F, false);
		base.setTextureOffset(0, 8).addCuboid( -5.0F, -1.0F, -4.0F, 3, 1, 1, 0.0F, false);
		base.setTextureOffset(0, 10).addCuboid( -4.0F, -1.0F, -3.0F, 3, 1, 1, 0.0F, false);
		base.setTextureOffset(0, 12).addCuboid( -3.0F, -1.0F, -2.0F, 3, 1, 1, 0.0F, false);
		base.setTextureOffset(0, 14).addCuboid( -2.0F, -1.0F, -1.0F, 3, 1, 1, 0.0F, false);
		base.setTextureOffset(18, 30).addCuboid( -1.0F, -1.0F, 0.0F, 6, 1, 1, 0.0F, false);
		base.setTextureOffset(26, 28).addCuboid( 2.0F, -1.0F, -1.0F, 2, 1, 1, 0.0F, false);
		base.setTextureOffset(26, 26).addCuboid( 0.0F, -1.0F, 1.0F, 2, 1, 1, 0.0F, false);
		base.setTextureOffset(18, 15).addCuboid( 2.0F, -1.0F, 1.0F, 2, 1, 5, 0.0F, false);
		base.setTextureOffset(22, 28).addCuboid( 4.0F, -1.0F, 1.0F, 1, 1, 1, 0.0F, false);
		base.setTextureOffset(22, 23).addCuboid( -1.0F, -1.0F, 2.0F, 3, 1, 2, 0.0F, false);
		base.setTextureOffset(18, 9).addCuboid( 4.0F, -1.0F, 2.0F, 2, 1, 5, 0.0F, false);
		base.setTextureOffset(24, 0).addCuboid( 6.0F, -1.0F, 6.0F, 2, 1, 2, 0.0F, false);
		base.setTextureOffset(26, 6).addCuboid( 6.0F, -1.0F, 4.0F, 1, 1, 2, 0.0F, false);
		base.setTextureOffset(26, 21).addCuboid( 0.0F, -1.0F, 4.0F, 2, 1, 1, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red,
            float green, float blue, float alpha) {
        //this.base.roll=8f;
        //this.base.yaw=13.5f;
        //System.out.println("roll: "+this.base.roll);
        matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90F));
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-45-0F));
        //matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {

	}
    */
}
