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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import org.jetbrains.annotations.Nullable;

public class BokoblinMeatSearchGoal extends MoveToTargetPosGoal {


    private final Block targetBlock;
    private final MobEntity stepAndDestroyMob;
    private int counter;
    private boolean hasFoundMeat = false;
    private static final int MAX_COOLDOWN = 20;

    public BokoblinMeatSearchGoal(Block targetBlock, PathAwareEntity mob, double speed, int maxYDifference) {
        super(mob, speed, 30, maxYDifference);
        this.targetBlock = targetBlock;
        this.stepAndDestroyMob = mob;
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

    public void stop() {
        super.stop();
        this.stepAndDestroyMob.fallDistance = 1.0F;
    }

    public void start() {
        super.start();
        this.counter = 0;
    }

    public void tickStepping(WorldAccess world, BlockPos pos) {
    }

    public void onDestroyBlock(World world, BlockPos pos) {
    }

    public void tick() {
        super.tick();
        World world = this.stepAndDestroyMob.getWorld();
        BlockPos blockPos = this.stepAndDestroyMob.getBlockPos();
        BlockPos blockPos2 = this.tweakToProperPos(blockPos, world);
        Random random = this.stepAndDestroyMob.getRandom();
        if (this.hasReached() && blockPos2 != null) {
            Vec3d vec3d;
            double d;
            if (this.counter > 0) {
                vec3d = this.stepAndDestroyMob.getVelocity();
                this.stepAndDestroyMob.setVelocity(vec3d.x, 0.3, vec3d.z);
                if (!world.isClient) {
                    d = 0.08;
                    ((ServerWorld)world).spawnParticles(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(targetBlock)), (double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.7, (double)blockPos2.getZ() + 0.5, 15, ((double)random.nextFloat() - 0.5) * 0.5, ((double)random.nextFloat() - 0.5) * 0.5, ((double)random.nextFloat() - 0.5) * 0.5, 0.15);
                }
            }

            if (this.counter % 2 == 0) {
                vec3d = this.stepAndDestroyMob.getVelocity();
                this.stepAndDestroyMob.setVelocity(vec3d.x, -0.3, vec3d.z);
                if (this.counter % 6 == 0) {
                    this.tickStepping(world, this.targetPos);
                }
            }

            if (this.counter > 60) {
                world.removeBlock(blockPos2, false);
                if (!world.isClient) {
                    for(int i = 0; i < 20; ++i) {
                        d = random.nextGaussian() * 0.2;
                        double e = random.nextGaussian() * 0.2;
                        double f = random.nextGaussian() * 0.2;
                        ((ServerWorld)world).spawnParticles(ParticleTypes.SWEEP_ATTACK, (double)blockPos2.getX() + 1.5, (double)blockPos2.getY() + 1, (double)blockPos2.getZ() + 0.5, 3, d, e, f, 0.15);
                    }

                    this.onDestroyBlock(world, blockPos2);
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
