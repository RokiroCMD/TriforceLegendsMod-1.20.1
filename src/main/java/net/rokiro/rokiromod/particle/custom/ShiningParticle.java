package net.rokiro.rokiromod.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class ShiningParticle extends SpriteBillboardParticle {

    private int tickEndDuration = 4;
    private int tickStartDuration = 4;

    protected ShiningParticle(ClientWorld level, double xCord, double yCord, double zCord,
                              SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCord, yCord, zCord, xd, yd, zd);

        this.velocityMultiplier = 0.8f;
        this.x = xCord;
        this.y = yCord;
        this.z = zCord;

        this.velocityX = xd;
        this.velocityY = yd;
        this.velocityZ = zd;
        this.scale *= 1;
        this.maxAge = 10;
        this.setSprite(spriteSet);
        this.angle = this.random.nextFloat() * 1.03f;
        this.prevAngle=angle;
        int col = random.nextInt(10) ;
        switch (col) {
            case 0 -> {
                this.red = 0.8f;
                this.green = 1f;
                this.blue = 0.8f;
            }
            case 1 -> {
                this.red = 0.8f;
                this.green = 1f;
                this.blue = 1f;
            }
            case 2 -> {
                this.red = 1f;
                this.green = 0.8f;
                this.blue = 1f;
            }
            case 3 -> {
                this.red = 1f;
                this.green = 1f;
                this.blue = 0.8f;
            }
            default -> {
                this.red = 1f;
                this.green = 1f;
                this.blue = 1f;
            }
        }

        this.setAlpha(0f);
        this.spacingXZ = 0f;
        this.spacingY = 0f;
        this.ascending = false;
    }


    private boolean shrinking =false;
    private boolean growing =true;
    @Override
    public void tick() {
        super.tick();

        float alphaValue = 0.95f;

        if (growing){
            alphaValue = MathHelper.sin((MathHelper.HALF_PI / tickStartDuration) * (age) ) * alphaValue;
        }

        if (shrinking){
            alphaValue = MathHelper.cos( ((MathHelper.HALF_PI / tickEndDuration) * (age - maxAge + tickEndDuration))) * alphaValue;

        }
        this.alpha = alphaValue;


        if (growing & age >= tickStartDuration){
            growing = false;
        }

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

            return new ShiningParticle(world,x,y,z,this.sprites,velocityX,velocityY,velocityZ);

        }
    }
}
