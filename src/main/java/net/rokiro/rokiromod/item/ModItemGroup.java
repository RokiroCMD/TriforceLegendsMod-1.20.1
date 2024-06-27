package net.rokiro.rokiromod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.block.ModBlocks;

public class ModItemGroup {

    public static final ItemGroup ZELDA_ITEMS_GROUP = Registry.register(
            Registries.ITEM_GROUP, new Identifier(RokirosMod.MOD_ID,"triforce_items"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.triforce_items"))
                    .icon(() -> new ItemStack(ModItems.TRIFORCE))
                    .entries((displayContext, entries) -> {
                    entries.add(ModItems.HYLIAN_ROCK_CHUNK);
                    entries.add(ModItems.HYLIAN_STEEL);
                    entries.add(ModItems.HYLIAN_METAL_DETECTOR);
                    entries.add(ModItems.TRIFORCE);
                    entries.add(ModItems.SMALL_KEY);
                    entries.add(ModItems.GORON_ROCKS);

                    }).build()
    );

    public static final ItemGroup ZELDA_TOOLS_GROUP = Registry.register(
            Registries.ITEM_GROUP, new Identifier(RokirosMod.MOD_ID,"triforce_tools"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.triforce_tools"))
                    .icon(() -> new ItemStack(ModItems.HYLIAN_AXE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.HYLIAN_PICKAXE);
                        entries.add(ModItems.HYLIAN_AXE);
                        entries.add(ModItems.HYLIAN_SHOVEL);
                    }).build()
    );

    public static final ItemGroup ZELDA_WEAPONS_GROUP = Registry.register(
            Registries.ITEM_GROUP, new Identifier(RokirosMod.MOD_ID,"triforce_weapons"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.triforce_weapons"))
                    .icon(() -> new ItemStack(ModItems.HYLIAN_SWORD))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.HYLIAN_SWORD);
                    }).build()
    );

    public static final ItemGroup ZELDA_BLOCKS_GROUP = Registry.register(
            Registries.ITEM_GROUP, new Identifier(RokirosMod.MOD_ID,"triforce_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.triforce_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.HYLIAN_STEEL_BLOCK))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.HYLIAN_ORE);
                        entries.add(ModBlocks.HYLIAN_STEEL_BLOCK);

                        // Dungeon forest wood SET
                        entries.add(ModBlocks.DUNGEON_FOREST_WOOD);
                        entries.add(ModBlocks.DUNGEON_FOREST_WOOD_SLAB);
                        entries.add(ModBlocks.DUNGEON_FOREST_WOOD_STAIRS);
                        entries.add(ModBlocks.DUNGEON_FOREST_WOOD_FENCE);
                        entries.add(ModBlocks.DUNGEON_FOREST_WOOD_WALL);

                        // Dungeon bricks SET
                        entries.add(ModBlocks.DUNGEON_FOREST_BRICK);
                        entries.add(ModBlocks.DUNGEON_FOREST_BRICK_LIGHT);
                        entries.add(ModBlocks.DUNGEON_FOREST_BRICK_SLAB);
                        entries.add(ModBlocks.DUNGEON_FOREST_BRICK_STAIRS);
                        entries.add(ModBlocks.DUNGEON_FOREST_BRICK_WALL);

                        // Dungeon rocks SET
                        entries.add(ModBlocks.DUNGEON_FOREST_ROCK);
                        entries.add(ModBlocks.DUNGEON_FOREST_ROCK_LIGHT);
                        entries.add(ModBlocks.DUNGEON_FOREST_ROCK_SLAB);
                        entries.add(ModBlocks.DUNGEON_FOREST_ROCK_STAIRS);
                        entries.add(ModBlocks.DUNGEON_FOREST_ROCK_WALL);

                        // Dungeon grass SET
                        entries.add(ModBlocks.DUNGEON_FOREST_GRASS);
                        entries.add(ModBlocks.DUNGEON_FOREST_GRASS_SLAB);
                        entries.add(ModBlocks.DUNGEON_FOREST_GRASS_STAIRS);


                        // Dungeon wood SET
                        entries.add(ModBlocks.DUNGEON_WOOD);
                        entries.add(ModBlocks.DUNGEON_WOOD_SLAB);
                        entries.add(ModBlocks.DUNGEON_WOOD_STAIRS);
                        entries.add(ModBlocks.DUNGEON_WOOD_FENCE);
                        entries.add(ModBlocks.DUNGEON_WOOD_FENCE_GATE);
                        entries.add(ModBlocks.DUNGEON_WOOD_WALL);
                        entries.add(ModBlocks.DUNGEON_WOOD_TRAPDOOR);
                    }).build()
    );


    public static final ItemGroup ZELDA_SPECIAL_BLOCKS_GROUP = Registry.register(
            Registries.ITEM_GROUP, new Identifier(RokirosMod.MOD_ID,"triforce_special_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.triforce_special_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.DUNGEON_WOOD_LOCK))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.DUNGEON_WOOD_LOCK);
                    }).build()
    );

    public static void registerItemGroups(){
        RokirosMod.LOGGER.info("Registering item grops for " + RokirosMod.MOD_ID);
    }
}
