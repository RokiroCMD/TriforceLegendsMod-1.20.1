package net.rokiro.rokiromod.entity.custom;

import com.mojang.datafixers.kinds.IdF;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.entity.ModEntities;
import net.rokiro.rokiromod.entity.ai.BokoblinAttackGoal;
import net.rokiro.rokiromod.entity.ai.BokoblinMeatSearchGoal;
import net.rokiro.rokiromod.particle.ParticleUtils;
import net.rokiro.rokiromod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BokoblinEntity extends HostileEntity implements GeoEntity {

    public static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(BokoblinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> EATING =
            DataTracker.registerData(BokoblinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public BokoblinEntity(EntityType<? extends HostileEntity> entityType, World world) {

        super(entityType, world);
    }

    public boolean isOnDeathAnimation = false;

    @Override
    public void takeKnockback(double strength, double x, double z) {
        if (getHealth() < 10f){

            super.takeKnockback(strength/4, x, z);
            return;
        }
        super.takeKnockback(strength, x, z);

    }


    @Override
    protected void updatePostDeath() {
        if (!isOnDeathAnimation){
            if (this.getWorld().isClient()){
                ParticleUtils.spawnCirclePurpleSpiralCloudParticle(this.getWorld(), (float) this.getX(), (float) this.getY()+0.5f, (float) this.getZ(),0.035f);
            }
            isOnDeathAnimation = true;
        }

        this.deathTime++;
        if (this.deathTime >= 20 && !this.getWorld().isClient() && !this.isRemoved()) {
            if (random.nextFloat() > 0.7){
                RupeeEntity rupeeEntity = new RupeeEntity(ModEntities.RUPEE_ENTITY,getWorld());
                rupeeEntity.setRupeeType(RupeeEntity.RupeeType.GREEN);
                rupeeEntity.refreshPositionAndAngles(this.getX(), this.getY()+0.5f, this.getZ(), 0f, 0f);
                getWorld().spawnEntity(rupeeEntity);
            }
            this.remove(Entity.RemovalReason.KILLED);
        }
    }




    @Override
    protected void updateLimbs(float posDelta) {
        float f;
        if (this.getPose() == EntityPose.STANDING) {
            f = Math.min(posDelta * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.limbAnimator.updateLimbs(f, 0.2F);
    }
/*
    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()){
            setupAnimationStates();
        }
    }
*/
    public void setAttacking(boolean attacking){
        this.dataTracker.set(ATTACKING,attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING,false);
        this.dataTracker.startTracking(EATING,false);
    }

    public void setEating(boolean eating) {this.dataTracker.set(EATING,eating); }
    public boolean isEating(){return this.dataTracker.get(EATING);}

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.BOKOBLIN_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.BOKOBLIN_DEAD;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.BOKOBLIN_HIT;
    }



    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new BokoblinMeatSearchGoal(ModBlocks.LARGE_PIECE_MEAT,this,1.0d,3));
        this.goalSelector.add(2, new BokoblinAttackGoal(this,1.2d,false));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(5, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class,false));
    }




    public static DefaultAttributeContainer.Builder createBokoblinAttributes(){
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 80.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH,30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this,"controller",0, this::predicate));
    }

    private <T extends GeoAnimatable>PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState){

        if (isEating()){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("attack_animation", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        if (submergedInWater){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("drowning_animation", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        if (isAttacking()){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("attack_animation", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }

        if (tAnimationState.isMoving()){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("walk_animation", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }


        tAnimationState.getController().setAnimation(RawAnimation.begin().then("iddle_animation", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
