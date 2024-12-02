package net.rokiro.rokiromod.networking.packet.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.PacketByteBuf;
import net.rokiro.rokiromod.client.hud.SpecialEquipmentHudOverlay;
import net.rokiro.rokiromod.util.ArtifactEquipmentData;
import net.rokiro.rokiromod.util.IEntityDataSaver;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ArtifactEquipmentSyncS2CPacket {

    public static void recieve(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        if (client.player != null){

        byte[] byteArray =  buf.readByteArray();
        String concatenatedString = new String(byteArray, StandardCharsets.UTF_8);
        List<String> artifactsIds = List.of(concatenatedString.split("\\|"));

        NbtList nbtList = new NbtList();

        for (String s: artifactsIds) nbtList.add(NbtString.of(s));

        ((IEntityDataSaver) client.player).getPersistentData().put(ArtifactEquipmentData.ARTIFACT_EQUIPMENT_KEY,nbtList);
        
        SpecialEquipmentHudOverlay.updateArtifactEquipment();

        }
    }

}
