package net.rokiro.rokiromod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.entity.ModEntities;
import net.rokiro.rokiromod.item.custom.artifact.*;
import net.rokiro.rokiromod.item.custom.MetalDetectorItem;
import net.rokiro.rokiromod.item.custom.WWMasterSwordItem;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static List<Item> ARITFACTS_ITEMS = new ArrayList<>();

    public static final Item HYLIAN_STEEL = registerItem("hylian_steel", new Item(new FabricItemSettings()));
    public static final Item HYLIAN_ROCK_CHUNK = registerItem("hylian_rock_chunk", new Item(new FabricItemSettings()));
    public static final Item HYLIAN_METAL_DETECTOR = registerItem("hylian_metal_detector", new MetalDetectorItem(new FabricItemSettings().maxDamage(64)));
    public static final Item SMALL_KEY = registerItem("small_key", new Item(new FabricItemSettings()));
    public static final Item GORON_ROCKS = registerItem("goron_rocks", new Item(new FabricItemSettings()));
    public static final Item BOKOBLIN_FANG = registerItem("bokoblin_fang", new Item(new FabricItemSettings()));
    public static final Item BOKOBLIN_HORN = registerItem("bokoblin_horn", new Item(new FabricItemSettings()));
    public static final Item BOKOBLIN_VISCERA = registerItem("bokoblin_viscera", new Item(new FabricItemSettings()));

    public static final Item MEDIUM_RUPEE_BAG = registerItem("medium_rupee_bag", new Item(new FabricItemSettings()));
    public static final Item HYLIAN_PICKAXE = registerItem("hylian_pickaxe",
            new PickaxeItem(ModToolMaterials.HYLIAN_SET, -1, -2.8f, new FabricItemSettings()));
    public static final Item HYLIAN_AXE = registerItem("hylian_axe",
            new AxeItem(ModToolMaterials.HYLIAN_SET, 6, -2.9f, new FabricItemSettings()));
    public static final Item HYLIAN_SHOVEL = registerItem("hylian_shovel",
            new ShovelItem(ModToolMaterials.HYLIAN_SET, -1, -3.0f, new FabricItemSettings()));

    public static final Item HYLIAN_SWORD = registerItem("hylian_sword",
            new SwordItem(ModToolMaterials.HYLIAN_SET, 3, -2.4f, new FabricItemSettings()));

    public static final Item BOKOBLIN_SPAWN_EGG = registerItem("bokoblin_spawn_egg",
        new SpawnEggItem(ModEntities.BOKOBLIN,0xFA5E3D, 0x6FF7FD,new FabricItemSettings()));

    public static final Item WW_MASTER_SWORD = registerItem("ww_master_sword",
            new WWMasterSwordItem(new FabricItemSettings()));

    public static final Item TRIFORCE = registerArtifact("triforce", new TriforceItem(new FabricItemSettings()));
    public static final Item MAJORAS_MASK = registerArtifact("majoras_mask",
            new MajorasMaskItem(ModArmorMaterials.MAJORAS, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item DEKU_STICK = registerArtifact("deku_stick", new DekuStickItem(new FabricItemSettings()));
    public static final Item DEKU_NUT = registerArtifact("deku_nut", new DekuNutItem(new FabricItemSettings()));
    public static final Item SLINGSHOT = registerArtifact("slingshot", new SlingShotItem(new FabricItemSettings()));
    public static final Item BEETLE = registerArtifact("beetle",new BeetleItem(new FabricItemSettings()));
    public static final Item BOMB = registerArtifact("bomb", new BombItem(new FabricItemSettings()));
    public static final Item BOMBCHU = registerArtifact("bombchu", new BombchuItem(new FabricItemSettings()));
    public static final Item BOOMERANG = registerArtifact("boomerang", new BoomerangItem(new FabricItemSettings()));
    public static final Item DEKU_LEAF = registerArtifact("deku_leaf", new DekuLeafItem(new FabricItemSettings()));
    public static final Item FAIRY_OCARINE = registerArtifact("fairy_ocarine", new FairyOcarineItem(new FabricItemSettings()));
    public static final Item GODDESS_LYRE = registerArtifact("goddess_lyre", new GoddessLyreItem(new FabricItemSettings()));
    public static final Item OCARINE_OF_TIME = registerArtifact("ocarine_of_time", new OcarineOfTimeItem(new FabricItemSettings()));
    public static final Item SAIL = registerArtifact("sail", new SailItem(new FabricItemSettings()));
    public static final Item SWIFT_SAIL = registerArtifact("swift_sail", new SwiftSailItem(new FabricItemSettings()));
    public static final Item SIMPLE_BOW = registerArtifact("simple_bow", new SimpleBowItem(new FabricItemSettings()));
    public static final Item WIND_WAKER = registerArtifact("wind_waker", new WindWakerItem(new FabricItemSettings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(RokirosMod.MOD_ID,name), item);
    }

    private static Item registerArtifact(String name, Item item){
        var i = Registry.register(Registries.ITEM, new Identifier(RokirosMod.MOD_ID,name), item);
        ARITFACTS_ITEMS.add(i);
        return i;
    }

    public static void registerModItems(){
        RokirosMod.LOGGER.info("Registering mod items for " + RokirosMod.MOD_ID);
    }

}
