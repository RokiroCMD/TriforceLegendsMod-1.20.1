package net.rokiro.rokiromod;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.network.ClientDynamicRegistryType;
import net.minecraft.client.particle.ExplosionSmokeParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.entity.ModEntities;
import net.rokiro.rokiromod.entity.client.BokoblinRenderer;
import net.rokiro.rokiromod.entity.client.ModModelLayers;
import net.rokiro.rokiromod.entity.client.BokoblinModel;
import net.rokiro.rokiromod.particle.ModParticles;
import net.rokiro.rokiromod.particle.custom.PurpleSpiralCloudParticle;

public class RokirosModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DUNGEON_WOOD_TRAPDOOR, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(ModParticles.PURPLE_SPIRAL_CLOUD_PARTICLE, PurpleSpiralCloudParticle.Factory::new);

        EntityRendererRegistry.register(ModEntities.BOKOBLIN, BokoblinRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOKOBLIN, BokoblinModel::getTexturedModelData);



    }
}
