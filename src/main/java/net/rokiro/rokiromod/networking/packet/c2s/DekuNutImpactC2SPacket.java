package net.rokiro.rokiromod.networking.packet.c2s;

import com.mojang.datafixers.kinds.IdF;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.rokiro.rokiromod.effects.ModEffects;
import net.rokiro.rokiromod.networking.ModPackets;
import net.rokiro.rokiromod.sound.ModSounds;

public class DekuNutImpactC2SPacket {
    public static void recieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        BlockPos blockPos =  buf.readBlockPos();
        player.getWorld().playSound(null,blockPos, ModSounds.DEKU_NUT_THROW, SoundCategory.BLOCKS,2f, (float) (Math.random() *0.2 + 0.9f));

        try {
            player.getWorld().getOtherEntities(null, Box.from(blockPos.toCenterPos()).expand(2), entity -> entity != null && entity instanceof LivingEntity)
                    .stream().forEach(entity -> {
                LivingEntity livingEntity = (LivingEntity) entity;
                if (livingEntity instanceof ServerPlayerEntity){
                     if (!livingEntity.equals(player)){
                        livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.NUT_STUNNED_EFFECT, 60, 2,true,true),player);
                    }
                } else {
                    livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.NUT_STUNNED_EFFECT, 60, 2,true,true),player);
                }


            });
        } catch (Exception e){

        }



        PacketByteBuf packetByteBuf = PacketByteBufs.create();

        responseSender.sendPacket(ModPackets.DEKU_NUT_PARTICLE, packetByteBuf.writeBlockPos(blockPos));
    }

}
