package net.rokiro.rokiromod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.effects.ModEffects;
import net.rokiro.rokiromod.entity.ModEntities;
import net.rokiro.rokiromod.entity.custom.BokoblinEntity;
import net.rokiro.rokiromod.entity.custom.RupeeEntity;
import net.rokiro.rokiromod.item.ModItemGroup;
import net.rokiro.rokiromod.item.ModItems;
import net.rokiro.rokiromod.networking.ModPackets;
import net.rokiro.rokiromod.particle.ModParticles;
import net.rokiro.rokiromod.sound.ModSounds;
import net.rokiro.rokiromod.world.gen.ModWorldGeneration;
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
		ModSounds.registerSound();
		ModEffects.registerStatusEffects();
		ModParticles.registerParticles();

		FuelRegistry.INSTANCE.add(ModItems.GORON_ROCKS,60*20);
		FabricDefaultAttributeRegistry.register(ModEntities.BOKOBLIN, BokoblinEntity.createBokoblinAttributes());

		ModWorldGeneration.generateModWorldGen();
		ModPackets.registerC2SPackets();


	}
}