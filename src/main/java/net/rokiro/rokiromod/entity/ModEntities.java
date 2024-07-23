package net.rokiro.rokiromod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.entity.custom.BokoblinEntity;
import net.rokiro.rokiromod.entity.custom.RupeeEntity;

public class ModEntities {

    public static final EntityType<BokoblinEntity> BOKOBLIN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(RokirosMod.MOD_ID, "bokoblin"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BokoblinEntity::new).
                    dimensions(EntityDimensions.fixed(0.65f,1.8f)).build());

    public static final EntityType<RupeeEntity> RUPEE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(RokirosMod.MOD_ID, "rupee_entity"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, RupeeEntity::new).
                    dimensions(EntityDimensions.fixed(0.35f,1f)).build());


}
