package dev.sterner.malum.client.model;

import dev.sterner.malum.Malum;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class AncientSoulHunterArmorModel extends ArmorModel {
	public static EntityModelLayer LAYER = new EntityModelLayer(Malum.id("ancient_soul_hunter_armor"), "main");
	public AncientSoulHunterArmorModel(ModelPart root) {
		super(root);
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 42).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.45F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));
		ModelPartData right_boot = right_leg.addChild("right_boot", ModelPartBuilder.create().uv(0, 55).cuboid(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.9F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 42).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.45F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
		ModelPartData left_boot = left_leg.addChild("left_boot", ModelPartBuilder.create().uv(0, 55).mirrored().cuboid(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.9001F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData torso = modelPartData.addChild("torso", ModelPartBuilder.create().uv(40, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 13.0F, 4.0F, new Dilation(0.95F)).uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 13.0F, 4.0F, new Dilation(0.75F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData right_shoulder = modelPartData.addChild("right_shoulder", ModelPartBuilder.create().uv(0, 29).mirrored().cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.5F)).mirrored(false).uv(0, 16).mirrored().cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.7F)).mirrored(false), ModelTransform.pivot(-6.0F, 2.0F, 0.0F));
		ModelPartData left_shoulder = modelPartData.addChild("left_shoulder", ModelPartBuilder.create().uv(0, 29).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.5F)).uv(0, 16).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.7F)), ModelTransform.pivot(6.0F, 2.0F, 0.0F));
		ModelPartData helmet = modelPartData.addChild("helmet", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)).uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.7F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData codpiece = modelPartData.addChild("codpiece", ModelPartBuilder.create().uv(16, 33).cuboid(-4.0F, 9.0F, -2.0F, 8.0F, 3.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

}
