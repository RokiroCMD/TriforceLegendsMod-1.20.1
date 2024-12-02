package net.rokiro.rokiromod.item.custom.artifact;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.rokiro.rokiromod.networking.ModPackets;
import net.rokiro.rokiromod.util.ArtifactItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DekuNutItem extends Item implements ArtifactItem {
    public DekuNutItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Pequeña nuez que explota en una"));
        tooltip.add(Text.literal("cegadora ráfaga de luz al lanzarse."));
        tooltip.add(Text.literal("Úsala para aturdir a los enemigos "));
        tooltip.add(Text.literal("cercanos y ganar ventaja en combate."));
        super.appendTooltip(stack, world, tooltip, context);

    }

    @Override
    public int getColorTitle() {
        return 0xFFe6af39;
    }

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.OOT;
    }
    @Override
    public String getArtifactId() {
        return getTranslationKey();
    }
    @Override
    public void onActivateAbility(PlayerEntity player) {
        MinecraftClient client = MinecraftClient.getInstance();

        double maxReach = 8; //The farthest target the cameraEntity can detect
        float tickDelta = 1.0F; //Used for tracking animation progress; no tracking is 1.0F
        boolean includeFluids = true; //Whether to detect fluids as blocks

        HitResult hit = client.cameraEntity.raycast(maxReach, tickDelta, includeFluids);
        BlockPos blockPos = null;


        switch(hit.getType()) {
            case MISS:
                //nothing near enough
                break;
            case BLOCK:
                BlockHitResult blockHit = (BlockHitResult) hit;
                blockPos = blockHit.getBlockPos();
                Direction dir = blockHit.getSide();
                blockPos =  blockPos.add(dir.getVector());
                break;
            case ENTITY:
                EntityHitResult entityHit = (EntityHitResult) hit;
                Entity entity = entityHit.getEntity();
                blockPos= entity.getBlockPos();
                blockPos = blockPos.add(0,1,0);
                break;
        }

        if (blockPos != null){

            player.swingHand(Hand.MAIN_HAND);

            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeBlockPos(blockPos);
            ClientPlayNetworking.send(ModPackets.DEKU_NUT_IMPACT, buffer);
        }


    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public int maxStack() {
        return 40;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFFa84d19;
    }
}
