package net.rokiro.rokiromod.networking.packet.c2s;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.util.ArtifactEquipmentData;
import net.rokiro.rokiromod.util.ArtifactItem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtifactUsePlayerC2SPacket {
    public static void recieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){

    }
}
