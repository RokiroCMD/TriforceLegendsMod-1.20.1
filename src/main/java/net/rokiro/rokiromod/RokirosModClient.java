package net.rokiro.rokiromod;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.rokiro.rokiromod.block.ModBlocks;

public class RokirosModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DUNGEON_WOOD_TRAPDOOR, RenderLayer.getCutout()
        );
    }
}
