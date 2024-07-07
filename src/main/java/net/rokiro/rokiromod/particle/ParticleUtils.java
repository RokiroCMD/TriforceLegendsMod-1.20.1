package net.rokiro.rokiromod.particle;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ParticleUtils {

    public static void spawnPurpleSpiralCloudParticle(World world, float x, float y, float z,
                                                      float vx, float vy, float vz){
        world.addParticle(ModParticles.PURPLE_SPIRAL_CLOUD_PARTICLE, true,
                x, y, z, vx, vy, vz);
    }

    public static void spawnCirclePurpleSpiralCloudParticle(World world ,float x, float y, float z, float radius){
        for (int i = 0; i < 16 ; i++){
            float vx = MathHelper.sin((2*MathHelper.PI /16) * i) * radius;
            float vz = MathHelper.cos((2*MathHelper.PI / 16) * i) * radius;

            spawnPurpleSpiralCloudParticle(world, x,y,z,vx,0,vz);

        }
    }


}
