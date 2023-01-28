package dev.sterner.malum.client.model;

import dev.sterner.malum.Malum;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class AncientSoulStainedSteelArmorModel extends ArmorModel {
	public static EntityModelLayer LAYER = new EntityModelLayer(Malum.id("ancient_soul_stained_steel_armor"), "main");
	public AncientSoulStainedSteelArmorModel(ModelPart root) {
		super(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 33).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.5F)).uv(0, 45).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.85F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
		ModelPartData right_boot = right_leg.addChild("right_boot", ModelPartBuilder.create().uv(16, 40).cuboid(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.5F)).uv(32, 40).cuboid(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.75F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 33).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.5001F)).mirrored(false).uv(0, 45).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.8501F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
		ModelPartData left_boot = left_leg.addChild("left_boot", ModelPartBuilder.create().uv(16, 40).mirrored().cuboid(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.5001F)).mirrored(false).uv(32, 40).mirrored().cuboid(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.7501F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData torso = modelPartData.addChild("torso", ModelPartBuilder.create().uv(24, 17).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(1.001F)).uv(0, 17).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.51F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData right_shoulder = modelPartData.addChild("right_shoulder", ModelPartBuilder.create().uv(48, 17).mirrored().cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.5F)).mirrored(false).uv(48, 30).mirrored().cuboid(-3.25F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.75F)).mirrored(false), ModelTransform.pivot(-6.0F, 2.0F, 0.0F));
		ModelPartData left_shoulder = modelPartData.addChild("left_shoulder", ModelPartBuilder.create().uv(48, 30).cuboid(-0.75F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.75F)).uv(48, 17).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(6.0F, 2.0F, 0.0F));
		ModelPartData helmet = modelPartData.addChild("helmet", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.5F)).uv(32, 0).cuboid(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.75F)), ModelTransform.pivot(0.0F, 1.0F, 0.0F));
		ModelPartData codpiece = modelPartData.addChild("codpiece", ModelPartBuilder.create().uv(16, 33).cuboid(-4.0F, 9.0F, -2.0F, 8.0F, 3.0F, 4.0F, new Dilation(0.501F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
}
