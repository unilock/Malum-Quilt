package dev.sterner.malum.client.model;

import dev.sterner.malum.Malum;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

public class SoulStainedSteelArmorModel extends ArmorModel{
	public static EntityModelLayer LAYER = new EntityModelLayer(Malum.id("soul_stained_steel_armor"), "main");

	public SoulStainedSteelArmorModel(ModelPart root) {
		super(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0), 0);
		ModelPartData root = createHumanoidAlias(modelData);

		ModelPartData body = root.getChild("body");
		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 39).cuboid(-5.0F, 1.0F, -3.0F, 10.0F, 6.0F, 6.0F, new Dilation(0.0F))
				.uv(32, 39).cuboid(-5.0F, 1.0F, -3.0F, 10.0F, 6.0F, 6.0F, new Dilation(0.25F))
				.uv(0, 51).cuboid(-4.5F, 3.5F, -2.5F, 9.0F, 7.0F, 5.0F, new Dilation(0.0F))
				.uv(28, 51).cuboid(-4.5F, 3.5F, -2.5F, 9.0F, 7.0F, 5.0F, new Dilation(0.35F))
				.uv(0, 63).mirrored().cuboid(-5.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(36, 31).cuboid(2.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new Dilation(0.0F))
				.uv(54, 31).mirrored().cuboid(-5.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new Dilation(0.25F)).mirrored(false)
				.uv(54, 31).cuboid(2.0F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new Dilation(0.25F))
				.uv(11, 25).cuboid(2.0F, -1.0F, 3.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F))
				.uv(11, 25).mirrored().cuboid(-5.0F, -1.0F, 3.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(29, 25).cuboid(2.0F, -1.0F, 3.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.25F))
				.uv(29, 25).mirrored().cuboid(-5.0F, -1.0F, 3.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leggings = root.getChild("leggings");
		ModelPartData codpiece = leggings.addChild("codpiece", ModelPartBuilder.create().uv(0, 63).cuboid(-4.0F, -14.5F, -3.0F, 8.0F, 2.0F, 6.0F, new Dilation(0.01F))
				.uv(28, 63).cuboid(-4.0F, -14.5F, -3.0F, 8.0F, 2.0F, 6.0F, new Dilation(0.26F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData right_legging = root.getChild("right_legging");
		ModelPartData right_leg = right_legging.addChild("right_leg", ModelPartBuilder.create().uv(0, 105).cuboid(-2.5F, -0.5F, -2.5F, 5.0F, 7.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0, 0, 0.0F));
		ModelPartData right_thigh_guard = right_leg.addChild("right_thigh_guard", ModelPartBuilder.create().uv(0, 117).cuboid(-3.0F, -1.0003F, -3.0F, 3.0F, 5.0F, 6.0F, new Dilation(0.0F))
				.uv(18, 117).cuboid(-3.0F, -1.0003F, -3.0F, 3.0F, 5.0F, 6.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		ModelPartData right_foot = root.getChild("right_foot");
		ModelPartData right_boot = right_foot.addChild("right_boot", ModelPartBuilder.create().uv(36, 115).mirrored().cuboid(-3.0F, 6.0F, -3.0F, 6.0F, 7.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(60, 115).mirrored().cuboid(-3.0F, 6.0F, -3.0F, 6.0F, 7.0F, 6.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData right_boot_wing = right_boot.addChild("right_boot_wing", ModelPartBuilder.create().uv(84, 117).mirrored().cuboid(-4.0F, 4.3639F, -8.364F, 1.0F, 4.0F, 7.0F, new Dilation(0.0F)).mirrored(false)
				.uv(100, 117).mirrored().cuboid(-4.0F, 4.3639F, -8.364F, 1.0F, 4.0F, 7.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData right_arm = root.getChild("right_arm");
		ModelPartData right_shoulder = right_arm.addChild("right_shoulder", ModelPartBuilder.create().uv(0, 71).mirrored().cuboid(-6.0F, -4.0F, -3.0F, 4.0F, 6.0F, 6.0F, new Dilation(0.01F)).mirrored(false)
				.uv(20, 71).mirrored().cuboid(-6.0F, -4.0F, -3.0F, 4.0F, 6.0F, 6.0F, new Dilation(0.26F)).mirrored(false)
				.uv(0, 83).mirrored().cuboid(-5.5F, 5.0F, -3.0F, 5.0F, 6.0F, 6.0F, new Dilation(0.02F)).mirrored(false)
				.uv(22, 83).mirrored().cuboid(-5.5F, 5.0F, -3.0F, 5.0F, 6.0F, 6.0F, new Dilation(0.27F)).mirrored(false)
				.uv(0, 95).mirrored().cuboid(-6.5F, 4.0F, -2.5F, 4.0F, 5.0F, 5.0F, new Dilation(0.01F)).mirrored(false)
				.uv(18, 95).mirrored().cuboid(-6.5F, 4.0F, -2.5F, 4.0F, 5.0F, 5.0F, new Dilation(0.26F)).mirrored(false), ModelTransform.pivot(2, 0, 0.0F));

		ModelPartData left_legging = root.getChild("left_legging");
		ModelPartData left_leg = left_legging.addChild("left_leg", ModelPartBuilder.create().uv(0, 105).mirrored().cuboid(-2.5F, -0.5F, -2.5F, 5.0F, 7.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0, 0, 0.0F));
		ModelPartData left_thigh_guard = left_leg.addChild("left_thigh_guard", ModelPartBuilder.create().uv(0, 117).mirrored().cuboid(0.0F, -0.9997F, -3.0F, 3.0F, 5.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(18, 117).mirrored().cuboid(0.0F, -0.9997F, -3.0F, 3.0F, 5.0F, 6.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData left_foot = root.getChild("left_foot");
		ModelPartData left_boot = left_foot.addChild("left_boot", ModelPartBuilder.create().uv(36, 115).cuboid(-3.0F, 6.0F, -3.0F, 6.0F, 7.0F, 6.0F, new Dilation(0.01F))
				.uv(60, 115).cuboid(-3.0F, 6.0F, -3.0F, 6.0F, 7.0F, 6.0F, new Dilation(0.26F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData left_boot_wing = left_boot.addChild("left_boot_wing", ModelPartBuilder.create().uv(84, 117).cuboid(3.0F, 4.3639F, -8.364F, 1.0F, 4.0F, 7.0F, new Dilation(0.0F))
				.uv(100, 117).cuboid(3.0F, 4.3639F, -8.364F, 1.0F, 4.0F, 7.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		ModelPartData left_arm = root.getChild("left_arm");
		ModelPartData left_shoulder = left_arm.addChild("left_shoulder", ModelPartBuilder.create().uv(0, 71).cuboid(2.0F, -4.0F, -3.0F, 4.0F, 6.0F, 6.0F, new Dilation(0.01F))
				.uv(20, 71).cuboid(2.0F, -4.0F, -3.0F, 4.0F, 6.0F, 6.0F, new Dilation(0.26F))
				.uv(0, 83).cuboid(0.5F, 5.0F, -3.0F, 5.0F, 6.0F, 6.0F, new Dilation(0.02F))
				.uv(22, 83).cuboid(0.5F, 5.0F, -3.0F, 5.0F, 6.0F, 6.0F, new Dilation(0.27F))
				.uv(0, 95).cuboid(2.5F, 4.0F, -2.5F, 4.0F, 5.0F, 5.0F, new Dilation(0.01F))
				.uv(18, 95).cuboid(2.5F, 4.0F, -2.5F, 4.0F, 5.0F, 5.0F, new Dilation(0.26F)), ModelTransform.pivot(-2, 0, 0.0F));

		ModelPartData head = root.getChild("head");
		ModelPartData helmet = head.addChild("helmet", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -9.0F, -5.0F, 3.0F, 4.0F, 6.0F, new Dilation(0.0F))
				.uv(0, 0).mirrored().cuboid(1.5F, -9.0F, -5.0F, 3.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
				.uv(30, 0).cuboid(-4.5F, -9.0F, -5.0F, 3.0F, 4.0F, 6.0F, new Dilation(0.25F))
				.uv(30, 0).mirrored().cuboid(1.5F, -9.0F, -5.0F, 3.0F, 4.0F, 6.0F, new Dilation(0.25F)).mirrored(false)
				.uv(0, 10).cuboid(-1.5F, -10.0F, -6.0F, 3.0F, 6.0F, 9.0F, new Dilation(0.0F))
				.uv(24, 10).cuboid(-1.5F, -10.0F, -6.0F, 3.0F, 6.0F, 9.0F, new Dilation(0.25F))
				.uv(18, 0).cuboid(-5.0F, -5.0F, -5.0F, 2.0F, 6.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 25).mirrored().cuboid(-5.0F, -10.0F, -1.0F, 2.0F, 7.0F, 7.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 25).cuboid(3.0F, -10.0F, -1.0F, 2.0F, 7.0F, 7.0F, new Dilation(0.0F))
				.uv(18, 25).mirrored().cuboid(-5.0F, -10.0F, -1.0F, 2.0F, 7.0F, 7.0F, new Dilation(0.25F)).mirrored(false)
				.uv(18, 25).cuboid(3.0F, -10.0F, -1.0F, 2.0F, 7.0F, 7.0F, new Dilation(0.25F))
				.uv(15, 13).cuboid(3.0F, -10.0F, 6.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
				.uv(15, 13).mirrored().cuboid(-5.0F, -10.0F, 6.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
				.uv(23, 13).cuboid(3.0F, -10.0F, 6.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.25F))
				.uv(23, 13).mirrored().cuboid(-5.0F, -10.0F, 6.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.25F)).mirrored(false)
				.uv(18, 0).mirrored().cuboid(3.0F, -5.0F, -5.0F, 2.0F, 6.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
				.uv(48, 0).cuboid(-5.0F, -5.0F, -5.0F, 2.0F, 6.0F, 4.0F, new Dilation(0.25F))
				.uv(48, 0).mirrored().cuboid(3.0F, -5.0F, -5.0F, 2.0F, 6.0F, 4.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		return TexturedModelData.of(modelData, 128, 128);
	}
}
