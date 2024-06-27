package net.rokiro.rokiromod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {


    private static final List<ItemConvertible> HYLIAN_STEEL_SMELTABLES = List.of(ModItems.HYLIAN_ROCK_CHUNK);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS,
                ModItems.HYLIAN_STEEL, RecipeCategory.DECORATIONS, ModBlocks.HYLIAN_STEEL_BLOCK,"hylian_steel_compacting","rokiros-mod:hylian_steel",
                "hylian_steel_block_unpacking","rokiros-mod:hylian_steel_block");


        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HYLIAN_STEEL)
                .pattern("hhh")
                .pattern("hih")
                .pattern("hhh")
                .input('h', ModItems.HYLIAN_ROCK_CHUNK)
                .input('i', Items.IRON_INGOT)
                .criterion(hasItem(ModItems.HYLIAN_ROCK_CHUNK), conditionsFromItem(ModItems.HYLIAN_ROCK_CHUNK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HYLIAN_STEEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.HYLIAN_SWORD)
                .pattern(" i ")
                .pattern(" i ")
                .pattern(" s ")
                .input('i', ModItems.HYLIAN_STEEL)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HYLIAN_STEEL), conditionsFromItem(ModItems.HYLIAN_STEEL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HYLIAN_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HYLIAN_PICKAXE)
                .pattern("iii")
                .pattern(" s ")
                .pattern(" s ")
                .input('i', ModItems.HYLIAN_STEEL)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HYLIAN_STEEL), conditionsFromItem(ModItems.HYLIAN_STEEL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HYLIAN_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HYLIAN_AXE)
                .pattern("ii ")
                .pattern("is ")
                .pattern(" s ")
                .input('i', ModItems.HYLIAN_STEEL)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HYLIAN_STEEL), conditionsFromItem(ModItems.HYLIAN_STEEL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HYLIAN_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HYLIAN_SHOVEL)
                .pattern(" i ")
                .pattern(" s ")
                .pattern(" s ")
                .input('i', ModItems.HYLIAN_STEEL)
                .input('s', Items.STICK)
                .criterion(hasItem(ModItems.HYLIAN_STEEL), conditionsFromItem(ModItems.HYLIAN_STEEL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HYLIAN_SHOVEL)));




        /*ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HYLIAN_STEEL_NUGGET, 9)
                .pattern("   ")
                .pattern(" x ")
                .pattern("   ")
                .input('x', ModItems.HYLIAN_STEEL)
                .criterion(hasItem(ModItems.HYLIAN_STEEL), conditionsFromItem(ModItems.HYLIAN_STEEL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HYLIAN_STEEL_NUGGET)));
        ;*/
    }
}
