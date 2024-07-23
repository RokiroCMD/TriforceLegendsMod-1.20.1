package net.rokiro.rokiromod.entity.client;

import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.entity.custom.RupeeEntity;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class RupeeEntityModel extends GeoModel<RupeeEntity> {
    @Override
    public Identifier getModelResource(RupeeEntity animatable) {
        return new Identifier(RokirosMod.MOD_ID,"geo/rupee.geo.json");
    }

    @Override
    public Identifier getTextureResource(RupeeEntity animatable) {
        return new Identifier(RokirosMod.MOD_ID,animatable.getRupeeType().getTexture());
    }

    @Override
    public Identifier getAnimationResource(RupeeEntity animatable) {
        return new Identifier(RokirosMod.MOD_ID,"animations/rupee.animation.json");
    }


    @Override
    public void setCustomAnimations(RupeeEntity animatable, long instanceId, AnimationState<RupeeEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
    }
}
