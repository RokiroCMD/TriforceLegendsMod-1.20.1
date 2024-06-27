package net.rokiro.rokiromod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.item.ModItems;

public class DungeonWoodLockBlock extends Block {

    public DungeonWoodLockBlock(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()){
            if (player != null){
                if (hand != Hand.MAIN_HAND){
                    return ActionResult.CONSUME;
                }
                if (player.getInventory().getMainHandStack().getItem() != null){
                    ItemStack itemStack = player.getInventory().getMainHandStack();
                    if (itemStack.getItem() == ModItems.SMALL_KEY){
                        player.playSound(SoundEvents.BLOCK_CHAIN_BREAK, SoundCategory.BLOCKS, 10f,1f);
                        player.playSound(SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 10f,0.85f);
                        itemStack.setCount(itemStack.getCount()-1);
                        breakNearDoorBlocks(world, pos);
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    }
                }
            }
            return ActionResult.SUCCESS;
        } else{
            if (hand != Hand.MAIN_HAND){
                return ActionResult.CONSUME;
            }
            if (player.getInventory().getMainHandStack().getItem() != null){
                ItemStack itemStack = player.getInventory().getMainHandStack();
                if (itemStack.getItem() == ModItems.SMALL_KEY){
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.CONSUME;

        }
    }

    private void breakNearDoorBlocks(World world, BlockPos blockPos){
        checkDungeonWoodBlock(world, blockPos.up(1));
        checkDungeonWoodBlock(world, blockPos.down(1));


        checkDungeonWoodBlock(world, blockPos.south(1));
        checkDungeonWoodBlock(world, blockPos.north(1));

        checkDungeonWoodBlock(world, blockPos.west(1));
        checkDungeonWoodBlock(world, blockPos.east(1));

        checkDungeonWoodBlock(world, blockPos.up(1).south(1));
        checkDungeonWoodBlock(world, blockPos.up(1).north(1));
        checkDungeonWoodBlock(world, blockPos.up(1).east(1));
        checkDungeonWoodBlock(world, blockPos.up(1).west(1));

        checkDungeonWoodBlock(world, blockPos.down(1).south(1));
        checkDungeonWoodBlock(world, blockPos.down(1).north(1));
        checkDungeonWoodBlock(world, blockPos.down(1).east(1));
        checkDungeonWoodBlock(world, blockPos.down(1).west(1));

    }

    private void checkDungeonWoodBlock(World world, BlockPos blockPos){
        if(world.getBlockState(blockPos).isOf(ModBlocks.DUNGEON_WOOD)){
            world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 3);
        }
    }
}
