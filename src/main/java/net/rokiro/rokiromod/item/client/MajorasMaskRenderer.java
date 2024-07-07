package net.rokiro.rokiromod.item.client;

import net.rokiro.rokiromod.item.custom.MajorasMaskItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class MajorasMaskRenderer extends GeoArmorRenderer<MajorasMaskItem> {
    public MajorasMaskRenderer() {
        super(new MajorasMaskModel());
    }

}
