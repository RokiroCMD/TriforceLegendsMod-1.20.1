package net.rokiro.rokiromod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.entity.custom.BokoblinEntity;

public class BokoblinRenderer extends MobEntityRenderer<BokoblinEntity, BokoblinModel<BokoblinEntity>> {


    private static final Identifier TEXTURE = new Identifier(RokirosMod.MOD_ID, "textures/entity/bokoblin.png");
    public BokoblinRenderer(EntityRendererFactory.Context context) {
        super(context, new BokoblinModel<>(context.getPart(ModModelLayers.BOKOBLIN)), 1f);
    }

    @Override
    public Identifier getTexture(BokoblinEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BokoblinEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
