package net.rokiro.rokiromod.networking.packet.s2c;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.rokiro.rokiromod.client.hud.RupeesHudOverlay;
import net.rokiro.rokiromod.util.IEntityDataSaver;

public class RupeesSyncS2CPacket {

    public static void recieve(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        int rupees = buf.readInt();
        if (client.player != null){
            ((IEntityDataSaver) client.player).getPersistentData().putInt("rupees", rupees);
            RupeesHudOverlay.updateRupees(rupees);
        }
    }

}
