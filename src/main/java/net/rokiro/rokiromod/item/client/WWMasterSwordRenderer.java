package net.rokiro.rokiromod.item.client;

import net.rokiro.rokiromod.item.custom.WWMasterSwordItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WWMasterSwordRenderer extends GeoItemRenderer<WWMasterSwordItem> {
    public WWMasterSwordRenderer() {
        super(new WWMasterSwordModel());
    }
}
