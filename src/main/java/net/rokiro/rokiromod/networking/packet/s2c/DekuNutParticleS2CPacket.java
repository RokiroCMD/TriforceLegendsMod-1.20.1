package net.rokiro.rokiromod.networking.packet.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.rokiro.rokiromod.client.hud.FlashHudOverlay;
import net.rokiro.rokiromod.particle.ModParticles;

public class DekuNutParticleS2CPacket {

    public static void recieve(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){

        BlockPos blockPos = buf.readBlockPos();
        client.world.addParticle(ModParticles.DEKU_NUT_FLASH,blockPos.getX() + 0.5,blockPos.getY() +0.5,blockPos.getZ()+0.5,0,0,0);


        FlashHudOverlay.activateFlash();
    }

}
