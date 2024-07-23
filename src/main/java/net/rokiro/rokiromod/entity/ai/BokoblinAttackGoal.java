package net.rokiro.rokiromod.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.entity.custom.BokoblinEntity;
import net.rokiro.rokiromod.sound.ModSounds;

public class BokoblinAttackGoal extends MeleeAttackGoal {

    private final BokoblinEntity entity;
    private int attackDelay = 10;
    private int ticksUntilNextAttack = 10;
    private boolean shouldCountTillNextAttack = false;
    public BokoblinAttackGoal(BokoblinEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = mob;
    }



    @Override
    public void start() {
        super.start();
        attackDelay = 5;
        ticksUntilNextAttack = 5;
        RokirosMod.LOGGER.info("starting");

    }
    @Override
    protected void attack(LivingEntity target, double squaredDistance) {
       if (isEnemyInAttackDistance(target)){
            shouldCountTillNextAttack = true;

            if (isTimeToStartAttackAnimation()){
                entity.setAttacking(true);
            }

            if (isTimeToAttack()){
                this.mob.getLookControl().lookAt(target.getX(), target.getY(), target.getZ());
                perfomAttack(target);
                target.getWorld().playSound(null,entity.getX(),entity.getY(),entity.getZ(), ModSounds.BOKOBLIN_ATTACK,
                        SoundCategory.HOSTILE,4, (entity.getRandom().nextFloat() * 0.5f) + 0.9f);
            }
       } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
       }
    }

    private boolean isEnemyInAttackDistance(LivingEntity pEnemy){
        return this.entity.distanceTo(pEnemy) <= 2f;
    }

    protected void resetAttackCooldown(){
        this.ticksUntilNextAttack = this.getTickCount(attackDelay *2);
    }

    protected boolean isTimeToStartAttackAnimation(){
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected boolean isTimeToAttack (){ return this.ticksUntilNextAttack <= 0; }

    protected void perfomAttack(LivingEntity pEnemy){
        this.resetAttackCooldown();
        this.mob.swingHand(Hand.MAIN_HAND);
        this.mob.tryAttack(pEnemy);
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldCountTillNextAttack){
            this.ticksUntilNextAttack = Math.max(ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}
