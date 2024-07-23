package net.rokiro.rokiromod.entity.custom;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.particle.ModParticles;
import net.rokiro.rokiromod.particle.ParticleUtils;
import net.rokiro.rokiromod.sound.ModSounds;
import net.rokiro.rokiromod.util.IEntityDataSaver;
import net.rokiro.rokiromod.util.RupeesData;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class RupeeEntity extends Entity implements GeoEntity {


    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final TrackedData<Integer> RUPEE_TYPE = DataTracker.registerData(RupeeEntity.class, TrackedDataHandlerRegistry.INTEGER);


    public RupeeEntity(EntityType<? extends RupeeEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(false);
    }

    public RupeeType getRupeeType() {
        return RupeeType.values()[dataTracker.get(RUPEE_TYPE)];
        }

    public void setRupeeType(RupeeType rupeeType) {
        ((IEntityDataSaver) this).getPersistentData().putInt("type", rupeeType.ordinal());
        this.dataTracker.set(RUPEE_TYPE, rupeeType.ordinal());
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(RUPEE_TYPE, 0);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return super.writeNbt(nbt);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this,"controller",0, this::predicate));
    }

    int timer = 0;

    boolean isBouncing = true;
    int bounces = 3;
    int actualBounce = 0;
    boolean isSpawning = true;
    @Override
    public void tick() {
            if (!this.hasNoGravity()) {
                this.setVelocity(this.getVelocity().add(0.0, -0.05, 0.0));
            }
            this.move(MovementType.SELF, this.getVelocity());
            if (isSpawning && age >= 20){
                isSpawning = false;
            }
            if (this.getWorld().isClient){
                if (age % 20 ==0){

                    getWorld().addParticle(ModParticles.SHINING, true,
                            getX(), getY()+0.5f, getZ(),  random.nextFloat() * 0.08 - 0.04, 0, random.nextFloat()* 0.08 - 0.04);
                }
            }

            if (isBouncing) {
                if (this.getWorld().isClient){
                    getWorld().addParticle(ModParticles.SHINING, true,
                            getX(), getY()+0.5f, getZ(),  random.nextFloat() * 0.08 - 0.04, 0, random.nextFloat()* 0.08 - 0.04);
                }
                if (isOnGround()){
                    if (actualBounce < bounces){
                        float bounciness = MathHelper.cos((MathHelper.HALF_PI / bounces) * actualBounce) * 0.52f;
                        this.setVelocity(0f,bounciness,0f);
                        setOnGround(false);
                        actualBounce++;
                        if (!getWorld().isClient){
                            getWorld().playSoundFromEntity(null,this,ModSounds.RUPEE_BOUNCE,SoundCategory.BLOCKS,1f,this.random.nextFloat()*0.3f + 0.95f);
                        }
                    } else{
                        isBouncing = false;
                        if (!getWorld().isClient){
                            getWorld().playSoundFromEntity(null,this,ModSounds.RUPEE_BOUNCE,SoundCategory.BLOCKS,1f,this.random.nextFloat()*0.3f + 0.95f);
                        }
                    }
                }
                timer ++;
            }
        super.tick();
    }


    boolean hasCollided = false;
    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (!isBouncing){
            if (!hasCollided){
                if (!isSpawning){
                    if (this.calculateBoundingBox().intersects(player.getBoundingBox()))  {
                        if (!player.getWorld().isClient){
                            hasCollided = true;
                            RupeesData.addRupee(player,getRupeeType().quantity);
                            player.playSound(ModSounds.RUPEE_OBTAINED, SoundCategory.BLOCKS,10,this.random.nextFloat()*0.2f + 0.95f);
                            this.remove(RemovalReason.KILLED);
                        } else {
                            ParticleUtils.spawnStaticCircleSiningParticle(getWorld(), (this.getX()),this.getY()+0.3,this.getZ(),0.8f);
                        }
                    }
                }
            }
        }
    }

    private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState){
        if (isSpawning){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.rupee.spawn", Animation.LoopType.HOLD_ON_LAST_FRAME));
            return PlayState.CONTINUE;
        }
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.rupee.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public enum RupeeType{
        GREEN(1,"textures/entity/rupee_entity.png"),
        BLUE(5,"textures/entity/rupee_blue_entity.png"),
        YELLOW(10,"textures/entity/rupee_yellow_entity.png"),
        RED(25,"textures/entity/rupee_red_entity.png"),
        PURPLE(50,"textures/entity/rupee_purple_entity.png"),
        SILVER(100,"textures/entity/rupee_gray_entity.png");

        public final int quantity;
        public final String texture;

        RupeeType(int quantity, String texture) {
            this.quantity = quantity;
            this.texture = texture;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getTexture() {
            return texture;
        }
    }
}
