package net.rokiro.rokiromod.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class PurpleSpiralCloudParticle extends SpriteBillboardParticle {

    private float maxScale = 3f;
    private int tickEndDuration = 20;
    private int tickStartDuration = 15;
    private float yMovementDissapear = 0.2f;
    protected PurpleSpiralCloudParticle(ClientWorld level, double xCord, double yCord, double zCord,
                                        SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCord, yCord, zCord, xd, yd, zd);

        this.velocityMultiplier = 1f;
        this.x = xd;
        this.y = yd;
        this.z = zd;

        this.velocityX = xd;
        this.velocityY = yd;
        this.velocityZ = zd;

        this.scale *= maxScale;
        maxScale = scale;
        this.scale = 0;
        this.maxAge = 60;
        this.setSprite(spriteSet);
        this.angle = this.random.nextFloat() * 2.5f;
        this.prevAngle=0.0f;
        this.red = 1;
        this.green = 1;
        this.blue = 1;
        this.spacingXZ = 0f;
        this.spacingY = 0f;
        this.ascending = false;

        this.yMovementDissapear = (this.random.nextFloat() * 0.15f) +0.03f;

    }

    private boolean shrinking =false;
    private boolean growing =true;

    // Animation of the particle
    @Override
    public void tick() {
        super.tick();

        float scaleValue = maxScale;
        float alphaValue = 0.92f;
        float yMoveValue = 1f;

        if (growing){
            scaleValue = MathHelper.sin((MathHelper.HALF_PI / tickStartDuration) * (age) ) * maxScale;
            alphaValue = MathHelper.sin((MathHelper.HALF_PI / tickStartDuration) * (age) ) * alphaValue;
        }

         if (shrinking){
            scaleValue = MathHelper.cos( ((MathHelper.HALF_PI / tickEndDuration) * (age - maxAge + tickEndDuration)) )  * maxScale;
            alphaValue = MathHelper.cos( ((MathHelper.HALF_PI / tickEndDuration) * (age - maxAge + tickEndDuration))) * alphaValue;

            yMoveValue = MathHelper.sin( ((MathHelper.HALF_PI / tickEndDuration) * (age - maxAge + tickEndDuration))) * yMovementDissapear;
            this.prevPosY = this.y;
            this.move(0f,yMoveValue,0f);

        }
        this.alpha = alphaValue;

        this.scale = scaleValue;

        if (growing & age >= tickStartDuration){
            growing = false;
            this.scale = maxScale;
        }

        if (!shrinking & age >= maxAge - tickEndDuration){
            shrinking = true;
        }

        this.prevAngle=angle;
        float angleValue = ((1/(float)maxAge) * age) *  0.35f;
        this.angle += angleValue;

    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType>{

        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet){
            this.sprites = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world,
                                       double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {

            return new PurpleSpiralCloudParticle(world,x,y,z,this.sprites,velocityX,velocityY,velocityZ);

        }
    }
}
