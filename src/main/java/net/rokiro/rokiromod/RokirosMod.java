package net.rokiro.rokiromod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.item.ModItemGroup;
import net.rokiro.rokiromod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RokirosMod implements ModInitializer {
	public static final String MOD_ID = "rokiros-mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FuelRegistry.INSTANCE.add(ModItems.GORON_ROCKS,60*20);
	}
}