package net.rokiro.rokiromod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.item.custom.MetalDetectorItem;

public class ModItems {

    public static final Item HYLIAN_STEEL = registerItem("hylian_steel", new Item(new FabricItemSettings()));
    public static final Item TRIFORCE = registerItem("triforce", new Item(new FabricItemSettings()));
    public static final Item HYLIAN_ROCK_CHUNK = registerItem("hylian_rock_chunk", new Item(new FabricItemSettings()));
    public static final Item HYLIAN_METAL_DETECTOR = registerItem("hylian_metal_detector", new MetalDetectorItem(new FabricItemSettings().maxDamage(64)));
    public static final Item SMALL_KEY = registerItem("small_key", new Item(new FabricItemSettings()));
    public static final Item GORON_ROCKS = registerItem("goron_rocks", new Item(new FabricItemSettings()));

    public static final Item HYLIAN_PICKAXE = registerItem("hylian_pickaxe",
            new PickaxeItem(ModToolMaterials.HYLIAN_SET, -1, -2.8f, new FabricItemSettings()));
    public static final Item HYLIAN_AXE = registerItem("hylian_axe",
            new AxeItem(ModToolMaterials.HYLIAN_SET, 6, -2.9f, new FabricItemSettings()));
    public static final Item HYLIAN_SHOVEL = registerItem("hylian_shovel",
            new ShovelItem(ModToolMaterials.HYLIAN_SET, -1, -3.0f, new FabricItemSettings()));

    public static final Item HYLIAN_SWORD = registerItem("hylian_sword",
            new SwordItem(ModToolMaterials.HYLIAN_SET, 3, -2.4f, new FabricItemSettings()));


    /*private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries){
        entries.add(HYLIAN_STEEL);
        entries.add(TRIFORCE);
        entries.add(HYLIAN_STEEL_NUGGET);
    }*/

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(RokirosMod.MOD_ID,name), item);
    }

    public static void registerModItems(){
        RokirosMod.LOGGER.info("Registering mod items for " + RokirosMod.MOD_ID);
        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }

}
