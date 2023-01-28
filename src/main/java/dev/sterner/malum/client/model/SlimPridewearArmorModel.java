package dev.sterner.malum.client.model;

import dev.sterner.malum.Malum;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class SlimPridewearArmorModel extends ArmorModel {
	public static EntityModelLayer LAYER = new EntityModelLayer(Malum.id("slim_pridewear"), "main");
	public SlimPridewearArmorModel(ModelPart root) {
		super(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(100, 60).cuboid(-1.5F, -33.3512F, -8.053F, 3.0F, 2.0F, 2.0F, new Dilation(0.2F)).uv(64, 53).cuboid(-4.6F, -32.4238F, -15.2587F, 9.0F, 3.0F, 8.0F, new Dilation(0.0F)).uv(64, 53).cuboid(-4.6F, -32.4238F, -15.2587F, 9.0F, 3.0F, 8.0F, new Dilation(0.2F)).uv(64, 35).cuboid(-5.0F, -29.3512F, -16.303F, 10.0F, 3.0F, 10.0F, new Dilation(0.2F)).uv(100, 28).cuboid(-1.5F, -33.3512F, -8.053F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)).uv(64, 21).cuboid(-4.6F, -32.4238F, -15.2587F, 9.0F, 3.0F, 8.0F, new Dilation(0.0F)).uv(64, 3).cuboid(-5.0F, -29.3512F, -16.303F, 10.0F, 3.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, -0.3927F, 0.0F, 0.0F));
		ModelPartData arm_l = modelPartData.addChild("arm_l", ModelPartBuilder.create().uv(32, 32).cuboid(1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.525F)).uv(48, 32).cuboid(1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.75F)), ModelTransform.pivot(3.0F, 2.0F, 0.0F));
		ModelPartData arm_r = modelPartData.addChild("arm_r", ModelPartBuilder.create().uv(40, 0).cuboid(-4.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.525F)).uv(40, 16).cuboid(-4.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.75F)), ModelTransform.pivot(-3.0F, 2.0F, 0.0F));
		ModelPartData torso = modelPartData.addChild("torso", ModelPartBuilder.create().uv(16, 0).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.525F)).uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.75F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData leg_l = modelPartData.addChild("leg_l", ModelPartBuilder.create().uv(32, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.525F)).uv(48, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.75F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
		ModelPartData boot_l = leg_l.addChild("boot_l", ModelPartBuilder.create().uv(16, 36).cuboid(-2.1F, 4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.625F)).uv(0, 36).cuboid(-2.1F, 4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.85F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData leg_r = modelPartData.addChild("leg_r", ModelPartBuilder.create().uv(0, 48).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.525F)).uv(16, 48).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.75F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
		ModelPartData boot_r = leg_r.addChild("boot_r", ModelPartBuilder.create().uv(0, 4).cuboid(-1.9F, 4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.625F)).uv(0, 20).cuboid(-1.9F, 4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.85F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData codpiece = modelPartData.addChild("codpiece", ModelPartBuilder.create().uv(0, 56).cuboid(-5.0F, 10.0F, -3.0F, 10.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 64);
	}

}
