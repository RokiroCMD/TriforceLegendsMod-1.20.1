package net.rokiro.rokiromod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        BlockStateModelGenerator.BlockTexturePool dungeonWoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DUNGEON_WOOD);
        BlockStateModelGenerator.BlockTexturePool dungeonForestWoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DUNGEON_FOREST_WOOD);
        BlockStateModelGenerator.BlockTexturePool dungeonForestBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DUNGEON_FOREST_BRICK);
        BlockStateModelGenerator.BlockTexturePool dungeonForestRockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DUNGEON_FOREST_ROCK);
        BlockStateModelGenerator.BlockTexturePool dungeonForestGrassPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DUNGEON_FOREST_GRASS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HYLIAN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HYLIAN_STEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DUNGEON_WOOD_LOCK);

        // DUNGEON FOREST WOOD POOLING
        dungeonForestWoodPool.stairs(ModBlocks.DUNGEON_FOREST_WOOD_STAIRS);
        dungeonForestWoodPool.slab(ModBlocks.DUNGEON_FOREST_WOOD_SLAB);
        dungeonForestWoodPool.fence(ModBlocks.DUNGEON_FOREST_WOOD_FENCE);
        dungeonForestWoodPool.wall(ModBlocks.DUNGEON_FOREST_WOOD_WALL);

        //DUNGEON FOREST BRICKS POOLING
        dungeonForestBricksPool.stairs(ModBlocks.DUNGEON_FOREST_BRICK_STAIRS);
        dungeonForestBricksPool.slab(ModBlocks.DUNGEON_FOREST_BRICK_SLAB);
        dungeonForestBricksPool.wall(ModBlocks.DUNGEON_FOREST_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DUNGEON_FOREST_BRICK_LIGHT);

        //DUNGEON FOREST ROCKS POOLING
        dungeonForestRockPool.stairs(ModBlocks.DUNGEON_FOREST_ROCK_STAIRS);
        dungeonForestRockPool.slab(ModBlocks.DUNGEON_FOREST_ROCK_SLAB);
        dungeonForestRockPool.wall(ModBlocks.DUNGEON_FOREST_ROCK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DUNGEON_FOREST_ROCK_LIGHT);

        //DUNGEON FOREST GRASS POOLING
        dungeonForestGrassPool.stairs(ModBlocks.DUNGEON_FOREST_GRASS_STAIRS);
        dungeonForestGrassPool.slab(ModBlocks.DUNGEON_FOREST_GRASS_SLAB);

        // DUNGEON WOOD POOLING
        dungeonWoodPool.stairs(ModBlocks.DUNGEON_WOOD_STAIRS);
        dungeonWoodPool.slab(ModBlocks.DUNGEON_WOOD_SLAB);
        dungeonWoodPool.fence(ModBlocks.DUNGEON_WOOD_FENCE);
        dungeonWoodPool.fenceGate(ModBlocks.DUNGEON_WOOD_FENCE_GATE);
        dungeonWoodPool.wall(ModBlocks.DUNGEON_WOOD_WALL);

        blockStateModelGenerator.registerTrapdoor(ModBlocks.DUNGEON_WOOD_TRAPDOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HYLIAN_STEEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.TRIFORCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GORON_ROCKS, Models.GENERATED);
        itemModelGenerator.register(ModItems.HYLIAN_METAL_DETECTOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.HYLIAN_ROCK_CHUNK, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMALL_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.HYLIAN_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HYLIAN_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HYLIAN_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HYLIAN_SHOVEL, Models.HANDHELD);
    }
}