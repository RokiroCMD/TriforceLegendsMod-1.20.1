package net.rokiro.rokiromod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.entity.custom.BokoblinEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BokoblinRenderer extends GeoEntityRenderer<BokoblinEntity>{

    public BokoblinRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BokoblinModel());
    }

    @Override
    public Identifier getTextureLocation(BokoblinEntity animatable) {
        return new Identifier(RokirosMod.MOD_ID, "textures/entity/bokoblin.png");
    }


    @Override
    public void render(BokoblinEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
