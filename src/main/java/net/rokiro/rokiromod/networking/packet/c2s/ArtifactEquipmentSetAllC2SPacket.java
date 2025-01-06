package net.rokiro.rokiromod.networking.packet.c2s;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.rokiro.rokiromod.util.ArtifactEquipmentData;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ArtifactEquipmentSetAllC2SPacket {
    public static void recieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){

        byte[] byteArray =  buf.readByteArray();
        String concatenatedString = new String(byteArray, StandardCharsets.UTF_8);
        List<String> artifactsIds = List.of(concatenatedString.split("\\|"));

        ArtifactEquipmentData.setArtifactAllSlots(player,artifactsIds);
    }
}
