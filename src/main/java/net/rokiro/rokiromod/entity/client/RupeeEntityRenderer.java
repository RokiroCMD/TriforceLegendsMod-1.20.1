package net.rokiro.rokiromod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.rokiro.rokiromod.entity.custom.RupeeEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RupeeEntityRenderer extends GeoEntityRenderer<RupeeEntity> {

    public RupeeEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new RupeeEntityModel());
    }
    @Override
    public void render(RupeeEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        poseStack.scale(0.7f,0.7f,0.7f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
