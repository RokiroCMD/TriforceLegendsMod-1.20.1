package net.rokiro.rokiromod.entity.custom;

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
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.rokiro.rokiromod.entity.ModEntities;
import net.rokiro.rokiromod.entity.ai.BokoblinAttackGoal;
import net.rokiro.rokiromod.entity.ai.BokoblinMeatSearchGoal;
import net.rokiro.rokiromod.particle.ParticleUtils;
import net.rokiro.rokiromod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class BokoblinEntity extends HostileEntity {

    public static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(BokoblinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;


    public BokoblinEntity(EntityType<? extends HostileEntity> entityType, World world) {

        super(entityType, world);
    }

    private void setupAnimationStates(){
        if (this.isAttacking()){
            if (attackAnimationTimeout <= 0){
                attackAnimationTimeout = 20;
                attackAnimationState.start(this.age);
            }
            if (idleAnimationState.isRunning()) {

                idleAnimationState.stop();
            }
        } else {
            if (attackAnimationTimeout > 0){
                --this.attackAnimationTimeout;
                return;
            } else{
                attackAnimationState.stop();
            }
            if (this.getVelocity().horizontalLength() > 0.05){
                if (idleAnimationState.isRunning()) {

                    idleAnimationState.stop();
                }
            } else{
                if (this.idleAnimationTimeout <= 0){
                    this.idleAnimationTimeout = this.random.nextInt(40) + 80;
                    this.idleAnimationState.start(this.age);
                } else {
                    --this.idleAnimationTimeout;
                }
            }
        }
    }

    public boolean isOnDeathAnimation = false;

    @Override
    protected void updatePostDeath() {
        if (!isOnDeathAnimation){
            if (this.getWorld().isClient()){

                ParticleUtils.spawnCirclePurpleSpiralCloudParticle(this.getWorld(), (float) this.getX(), (float) this.getY()+0.5f, (float) this.getZ(),0.035f);

            }
            isOnDeathAnimation = true;
        }

        this.deathTime++;
        if (this.deathTime >= 40 && !this.getWorld().isClient() && !this.isRemoved()) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_DEATH_PARTICLES);
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

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()){
            setupAnimationStates();
        }
    }

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
    }

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
        this.goalSelector.add(1, new BokoblinMeatSearchGoal(Blocks.NETHER_WART_BLOCK,this,1.0d,3));
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

}
