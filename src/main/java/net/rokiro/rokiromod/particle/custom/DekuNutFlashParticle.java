package net.rokiro.rokiromod.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class DekuNutFlashParticle extends SpriteBillboardParticle {


    private float maxScale = 1.25f;
    private int tickEndDuration = 4;

    public DekuNutFlashParticle(ClientWorld level, double xCord, double yCord, double zCord,
                                   SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCord, yCord, zCord, xd, yd, zd);
        this.velocityMultiplier = 0.8f;
        this.x = xCord;
        this.y = yCord;
        this.z = zCord;

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;

        this.velocityX = xd;
        this.velocityY = yd;
        this.velocityZ = zd;

        this.scale = maxScale;
        this.maxAge = 10;
        this.setSprite(spriteSet);
        this.spacingXZ = 0f;
        this.spacingY = 0f;
        this.ascending = false;
        this.collidesWithWorld = false;
        this.angle = this.random.nextFloat() * 0.43f;
        this.prevAngle=angle;
        this.setAlpha(0f);
    }


    private boolean shrinking = false;

    @Override
    public void tick() {
        super.tick();


        float scaleValue = maxScale;
        float alphaValue = 1f;



        if (shrinking){
            scaleValue = MathHelper.cos( ((MathHelper.HALF_PI / tickEndDuration) * (age - maxAge + tickEndDuration)) )  * maxScale;
            alphaValue = MathHelper.cos( ((MathHelper.HALF_PI / tickEndDuration) * (age - maxAge + tickEndDuration))) * alphaValue;

        }
        this.alpha = alphaValue;
        this.scale = scaleValue;


        if (!shrinking & age >= maxAge - tickEndDuration){
            shrinking = true;
        }

    }


    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {

        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet){
            this.sprites = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world,
                                       double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            return new DekuNutFlashParticle(world,x,y,z,this.sprites,velocityX,velocityY,velocityZ);

        }
    }

}
