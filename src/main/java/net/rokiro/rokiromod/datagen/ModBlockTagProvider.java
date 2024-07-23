package net.rokiro.rokiromod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS)
                .add(ModBlocks.HYLIAN_ORE)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.HYLIAN_ORE)
                .add(ModBlocks.HYLIAN_STEEL_BLOCK)
                .add(ModBlocks.BOKO_STONE)
                .add(ModBlocks.BOKO_SLAB_STONE)
                .add(ModBlocks.BOKO_STAIRS_STONE)
                .add(ModBlocks.BOKO_COBBLESTONE)
                .add(ModBlocks.BOKO_SLAB_COBBLESTONE)
                .add(ModBlocks.BOKO_STAIRS_COBBLESTONE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.HYLIAN_ORE)
                .add(ModBlocks.HYLIAN_STEEL_BLOCK);


        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.BOKO_COBBLESTONE)
                .add(ModBlocks.BOKO_SLAB_COBBLESTONE)
                .add(ModBlocks.BOKO_STAIRS_COBBLESTONE)
                .add(ModBlocks.BOKO_STONE)
                .add(ModBlocks.BOKO_SLAB_STONE)
                .add(ModBlocks.BOKO_STAIRS_STONE);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.DUNGEON_WOOD_FENCE)
                .add(ModBlocks.DUNGEON_FOREST_WOOD_FENCE)
                .add(ModBlocks.BOKO_FENCE_PLANKS);
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.DUNGEON_WOOD_WALL)
                .add(ModBlocks.DUNGEON_FOREST_WOOD_WALL)
                .add(ModBlocks.DUNGEON_FOREST_BRICK_WALL)
                .add(ModBlocks.DUNGEON_FOREST_ROCK_WALL);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.DUNGEON_WOOD_FENCE_GATE)
                .add(ModBlocks.BOKO_FENCE_GATE_PLANKS);



    }
}
