package net.rokiro.rokiromod.item.custom;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.networking.ModPackets;
import net.rokiro.rokiromod.util.IEntityDataSaver;
import net.rokiro.rokiromod.util.ModTags;
import net.rokiro.rokiromod.util.RupeesData;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        if (context.getWorld().isClient()){
            BlockPos positionedBlock = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for (int i =0 ; i <= positionedBlock.getY() + 64; i++ ){
                BlockState state = context.getWorld().getBlockState(positionedBlock.down(i));

                if (isValuableBlock(state)){
                    outputValueCoordinates(positionedBlock.down(i), player, state.getBlock());
                    foundBlock = true;
                    break;

                }
            }

            if (!foundBlock){
                player.sendMessage(Text.literal("No valuables found!"),true);
            } else{
                player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BIT.value(), SoundCategory.BLOCKS, 1.0f,1.0f);
            }
            context.getStack().damage(1, context.getPlayer(),
                    playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
            ClientPlayNetworking.send(ModPackets.RUPEES_ID, PacketByteBufs.create());

        }

        return super.useOnBlock(context);
    }

    private void outputValueCoordinates(BlockPos blockPos, PlayerEntity player, Block block){
        player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " at " +
                "("+ blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")").formatted(Formatting.GREEN), true);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip."+ RokirosMod.MOD_ID +".metal_detector.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    private boolean isValuableBlock(BlockState state){
        return state.isIn(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS);
    }
}
