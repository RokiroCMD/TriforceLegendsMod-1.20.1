package net.rokiro.rokiromod;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.client.MagicHudOverlay;
import net.rokiro.rokiromod.client.RupeesHudOverlay;
import net.rokiro.rokiromod.client.SpecialEquipmentHudOverlay;
import net.rokiro.rokiromod.entity.ModEntities;
import net.rokiro.rokiromod.entity.client.BokoblinRenderer;
import net.rokiro.rokiromod.entity.client.RupeeEntityRenderer;
import net.rokiro.rokiromod.event.KeyInputHandler;
import net.rokiro.rokiromod.networking.ModPackets;
import net.rokiro.rokiromod.particle.ModParticles;
import net.rokiro.rokiromod.particle.custom.PurpleSpiralCloudParticle;
import net.rokiro.rokiromod.particle.custom.ShiningParticle;

public class RokirosModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DUNGEON_WOOD_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_PIECE_MEAT, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(ModParticles.PURPLE_SPIRAL_CLOUD_PARTICLE, PurpleSpiralCloudParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SHINING, ShiningParticle.Factory::new);

        EntityRendererRegistry.register(ModEntities.BOKOBLIN, BokoblinRenderer::new);
        EntityRendererRegistry.register(ModEntities.RUPEE_ENTITY, RupeeEntityRenderer::new);

        HudRenderCallback.EVENT.register(new RupeesHudOverlay());
        HudRenderCallback.EVENT.register(new SpecialEquipmentHudOverlay());
        HudRenderCallback.EVENT.register(new MagicHudOverlay());

        ModPackets.registerS2CPackets();

        KeyInputHandler.register();

    }
}
