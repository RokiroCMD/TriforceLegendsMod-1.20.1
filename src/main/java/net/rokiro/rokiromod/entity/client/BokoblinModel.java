package net.rokiro.rokiromod.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.entity.custom.BokoblinEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BokoblinModel extends GeoModel<BokoblinEntity>{

	@Override
	public Identifier getModelResource(BokoblinEntity animatable) {
		return new Identifier(RokirosMod.MOD_ID,"geo/bokoblin.geo.json");
	}

	@Override
	public Identifier getTextureResource(BokoblinEntity animatable) {
		return new Identifier(RokirosMod.MOD_ID,"textures/entity/bokoblin.png");
	}

	@Override
	public Identifier getAnimationResource(BokoblinEntity animatable) {
		return new Identifier(RokirosMod.MOD_ID,"animations/bokoblin.animation.json");
	}



	@Override
	public void setCustomAnimations(BokoblinEntity animatable, long instanceId, AnimationState<BokoblinEntity> animationState) {
		super.setCustomAnimations(animatable, instanceId, animationState);

		CoreGeoBone head = getAnimationProcessor().getBone("Head");

		if (head != null){
			EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
			head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
		}

	}

}