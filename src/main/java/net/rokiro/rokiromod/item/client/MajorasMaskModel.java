package net.rokiro.rokiromod.item.client;

import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.item.custom.artifact.MajorasMaskItem;
import software.bernie.geckolib.model.GeoModel;

public class MajorasMaskModel extends GeoModel<MajorasMaskItem> {
    @Override
    public Identifier getModelResource(MajorasMaskItem animatable) {
        return new Identifier(RokirosMod.MOD_ID,"geo/majoras_mask.geo.json");
    }

    @Override
    public Identifier getTextureResource(MajorasMaskItem animatable) {
        return new Identifier(RokirosMod.MOD_ID,"textures/armor/majoras_mask.png");
    }

    @Override
    public Identifier getAnimationResource(MajorasMaskItem animatable) {
        return new Identifier(RokirosMod.MOD_ID,"animations/majoras_mask.animation.json");
    }
}
