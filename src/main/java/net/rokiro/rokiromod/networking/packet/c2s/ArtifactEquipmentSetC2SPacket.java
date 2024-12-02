package net.rokiro.rokiromod.networking.packet.c2s;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.rokiro.rokiromod.util.ArtifactEquipmentData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtifactEquipmentSetC2SPacket {
    public static void recieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){

        String id =  buf.readString();
        int slot = buf.readInt();
        Integer count = null;
        if (!id.equals("none")){

            String input = id;

            Pattern pattern = Pattern.compile("\\{count:(\\d+)\\}");
            Matcher matcher = pattern.matcher(input);

            String countValue = null;
            if (matcher.find()) {
                countValue = matcher.group(1);
                String result = input.replaceAll("\\{count:\\d+\\}", "");

                id  = result;

                count = Integer.valueOf(countValue);
            }
        }

        ArtifactEquipmentData.setArtifactInSlot(player, id, slot, count);


    }
}
