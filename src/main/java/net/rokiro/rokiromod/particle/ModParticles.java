package net.rokiro.rokiromod.particle;


import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;

public class ModParticles {

    public static final DefaultParticleType PURPLE_SPIRAL_CLOUD_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType SHINING = FabricParticleTypes.simple();

    public static void registerParticles(){
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(RokirosMod.MOD_ID, "purple_spiral_cloud_particle"),
                PURPLE_SPIRAL_CLOUD_PARTICLE);

        Registry.register(Registries.PARTICLE_TYPE, new Identifier(RokirosMod.MOD_ID, "shining_particle"),
                SHINING);
    }

}
