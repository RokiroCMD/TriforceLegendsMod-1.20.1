package net.rokiro.rokiromod.item.client;

import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.item.custom.WWMasterSwordItem;
import software.bernie.geckolib.model.GeoModel;

public class WWMasterSwordModel extends GeoModel<WWMasterSwordItem> {
    @Override
    public Identifier getModelResource(WWMasterSwordItem animatable) {
        return new Identifier(RokirosMod.MOD_ID,"geo/master_sword_ww.geo.json");
    }

    @Override
    public Identifier getTextureResource(WWMasterSwordItem animatable) {
        return new Identifier(RokirosMod.MOD_ID,"textures/item/master_sword_ww.png");
    }

    @Override
    public Identifier getAnimationResource(WWMasterSwordItem animatable) {
        return null;
    }
}
