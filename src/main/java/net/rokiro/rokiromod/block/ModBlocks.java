package net.rokiro.rokiromod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.block.custom.DungeonWoodLockBlock;

public class ModBlocks {

    public static final Block HYLIAN_STEEL_BLOCK = registerBlock("hylian_steel_block",
            new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block HYLIAN_ORE = registerBlock("hylian_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(3.5f, 3.5f), UniformIntProvider.create(3,6)));

    public static final Block DUNGEON_WOOD = registerBlock("dungeon_wood",
            new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK)));

    public static final Block DUNGEON_FOREST_BRICK = registerBlock("dungeon_forest_brick",
            new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block DUNGEON_FOREST_BRICK_LIGHT = registerBlock("dungeon_forest_brick_light",
            new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.DEEPSLATE_BRICKS).luminance(14)));
    public static final Block DUNGEON_FOREST_BRICK_STAIRS = registerBlock("dungeon_forest_brick_stairs",
            new StairsBlock(ModBlocks.DUNGEON_FOREST_BRICK.getDefaultState(),FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block DUNGEON_FOREST_BRICK_SLAB = registerBlock("dungeon_forest_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));
    public static final Block DUNGEON_FOREST_BRICK_WALL = registerBlock("dungeon_forest_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.DEEPSLATE_BRICKS)));



    public static final Block DUNGEON_FOREST_ROCK = registerBlock("dungeon_forest_rock",
            new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE)));
    public static final Block DUNGEON_FOREST_ROCK_LIGHT = registerBlock("dungeon_forest_rock_light",
            new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE).luminance(14)));
    public static final Block DUNGEON_FOREST_ROCK_STAIRS = registerBlock("dungeon_forest_rock_stairs",
            new StairsBlock(ModBlocks.DUNGEON_FOREST_ROCK.getDefaultState(),FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE)));
    public static final Block DUNGEON_FOREST_ROCK_SLAB = registerBlock("dungeon_forest_rock_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE)));
    public static final Block DUNGEON_FOREST_ROCK_WALL = registerBlock("dungeon_forest_rock_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.STONE)));


    public static final Block DUNGEON_FOREST_GRASS = registerBlock("dungeon_forest_grass",
            new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.CHERRY_LEAVES)));
    public static final Block DUNGEON_FOREST_GRASS_STAIRS = registerBlock("dungeon_forest_grass_stairs",
            new StairsBlock(ModBlocks.DUNGEON_FOREST_GRASS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.CHERRY_LEAVES)));
    public static final Block DUNGEON_FOREST_GRASS_SLAB = registerBlock("dungeon_forest_grass_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.CHERRY_LEAVES)));




    public static final Block DUNGEON_FOREST_WOOD = registerBlock("dungeon_forest_wood",
            new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.CHERRY_WOOD)));
    public static final Block DUNGEON_FOREST_WOOD_STAIRS = registerBlock("dungeon_forest_wood_stairs",
            new StairsBlock(ModBlocks.DUNGEON_FOREST_WOOD.getDefaultState(),FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.CHERRY_WOOD)));
    public static final Block DUNGEON_FOREST_WOOD_SLAB = registerBlock("dungeon_forest_wood_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.CHERRY_WOOD)));
    public static final Block DUNGEON_FOREST_WOOD_FENCE = registerBlock("dungeon_forest_wood_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.CHERRY_WOOD)));
    public static final Block DUNGEON_FOREST_WOOD_WALL = registerBlock("dungeon_forest_wood_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).sounds(BlockSoundGroup.CHERRY_WOOD)));



    public static final Block DUNGEON_WOOD_STAIRS = registerBlock("dungeon_wood_stairs",
            new StairsBlock(ModBlocks.DUNGEON_WOOD.getDefaultState(),FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final Block DUNGEON_WOOD_SLAB = registerBlock("dungeon_wood_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final Block DUNGEON_WOOD_FENCE = registerBlock("dungeon_wood_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final Block DUNGEON_WOOD_FENCE_GATE = registerBlock("dungeon_wood_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK), WoodType.DARK_OAK ));
    public static final Block DUNGEON_WOOD_WALL = registerBlock("dungeon_wood_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final Block DUNGEON_WOOD_TRAPDOOR = registerBlock("dungeon_wood_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK).nonOpaque(), BlockSetType.DARK_OAK));

    public static final Block DUNGEON_WOOD_LOCK = registerBlock("dungeon_wood_lock",
            new DungeonWoodLockBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(RokirosMod.MOD_ID, name), block);

    }
    public static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(RokirosMod.MOD_ID,name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        RokirosMod.LOGGER.info("Registering blocks for " + RokirosMod.MOD_ID );
    }
}
