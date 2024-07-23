package net.rokiro.rokiromod.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.goal.StepAndDestroyBlockGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.rokiro.rokiromod.entity.custom.BokoblinEntity;
import org.jetbrains.annotations.Nullable;

public class BokoblinMeatSearchGoal extends MoveToTargetPosGoal {


    private final Block targetBlock;
    private final BokoblinEntity stepAndDestroyMob;
    private int counter;
    private boolean hasFoundMeat = false;
    private static final int MAX_COOLDOWN = 20;


    public BokoblinMeatSearchGoal(Block targetBlock, PathAwareEntity mob, double speed, int maxYDifference) {
        super(mob, speed, 48, maxYDifference);
        this.targetBlock = targetBlock;
        this.stepAndDestroyMob = (BokoblinEntity) mob;
    }

    public boolean canStart() {
        if (!this.stepAndDestroyMob.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            return false;
        } else if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        } else if (this.findTargetPos()) {
            this.cooldown = toGoalTicks(20);
            return true;
        } else {
            this.cooldown = this.getInterval(this.mob);
            return false;
        }
    }

    @Override
    public double getDesiredDistanceToTarget() {
        return 1.7d;
    }

    public void stop() {
        super.stop();
        this.stepAndDestroyMob.fallDistance = 1.0F;
        stepAndDestroyMob.setEating(false);
    }

    public void start() {
        super.start();
        this.counter = 0;
    }

    public void tickStepping(WorldAccess world, BlockPos pos) {
        Random random = this.stepAndDestroyMob.getRandom();
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.HOSTILE, 2f, stepAndDestroyMob.getRandom().nextFloat() *0.3f  +  0.85f);
        World worldTemp = this.stepAndDestroyMob.getWorld();
        if (!worldTemp.isClient) {
            double d = 0.08;
            ((ServerWorld)world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(targetBlock)), (double)pos.getX() + 0.5, (double)pos.getY() + 0.7, (double)pos.getZ() + 0.5, 5, ((double)random.nextFloat() - 0.5) * 0.5, ((double)random.nextFloat() - 0.5) * 0.5, ((double)random.nextFloat() - 0.5) * 0.5, 0.10);
        }
    }

    public void onDestroyBlock(World world, BlockPos pos) {
        if (pos != null){
            world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.HOSTILE, 10f, stepAndDestroyMob.getRandom().nextFloat() *0.3f  +  0.85f);
            ((ServerWorld)world).spawnParticles(ParticleTypes.SWEEP_ATTACK, (double)pos.getX() + 1.5, (double)pos.getY() + 1, (double)pos.getZ() + 0.5, 1, 0, 0, 0, 0.15);
        }
    }

    public void tick() {
        super.tick();
        World world = this.stepAndDestroyMob.getWorld();
        BlockPos blockPos = this.stepAndDestroyMob.getBlockPos();
        BlockPos blockPos2 = this.tweakToProperPos(blockPos, world);
        if (this.hasReached()) {
            stepAndDestroyMob.getNavigation().stop();
            if (this.counter > 0) {
                if (!stepAndDestroyMob.isEating()){
                    stepAndDestroyMob.setEating(true);
                }
            }

            if (this.counter % 20 == 0) {
                this.tickStepping(world, this.targetPos);
            }

            if (this.counter > 300) {
                if (!world.isClient) {
                    this.onDestroyBlock(world, blockPos2);
                }
                if (blockPos2 != null){
                    world.removeBlock(blockPos2, false);
                }

            }

            ++this.counter;
        }

    }

    @Nullable
    private BlockPos tweakToProperPos(BlockPos pos, BlockView world) {
        if (world.getBlockState(pos).isOf(this.targetBlock)) {
            return pos;
        } else {

            BlockPos[] blockPoss = new BlockPos[]{pos.down(), pos.west(), pos.east(), pos.north(), pos.south(), pos.down().down()};
            BlockPos[] var4 = blockPoss;
            int var5 = blockPoss.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                BlockPos blockPos = var4[var6];
                if (world.getBlockState(blockPos).isOf(this.targetBlock)) {
                    return blockPos;
                }
            }

            return null;
        }
    }

    protected boolean isTargetPos(WorldView world, BlockPos pos) {


        Chunk chunk = world.getChunk(ChunkSectionPos.getSectionCoord(pos.getX()), ChunkSectionPos.getSectionCoord(pos.getZ()), ChunkStatus.FULL, false);
        if (chunk == null) {
            return false;
        } else {
            return chunk.getBlockState(pos).isOf(this.targetBlock) && chunk.getBlockState(pos.up()).isAir() && chunk.getBlockState(pos.up(2)).isAir();
        }
    }


}
