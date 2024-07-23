package net.rokiro.rokiromod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.networking.packet.ExampleC2SPacket;
import net.rokiro.rokiromod.networking.packet.RupeesC2SPacket;
import net.rokiro.rokiromod.networking.packet.RupeesSyncS2CPacket;

public class ModPackets {

    public static final Identifier RUPEES_ID = new Identifier(RokirosMod.MOD_ID,"rupees");
    public static final Identifier RUPEES_SYNC = new Identifier(RokirosMod.MOD_ID,"rupees_sync");
    public static final Identifier EXAMPLE_ID = new Identifier(RokirosMod.MOD_ID,"example");

    public static void registerC2SPackets(){
        //ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::recieve);
        ServerPlayNetworking.registerGlobalReceiver(RUPEES_ID, RupeesC2SPacket::recieve);
    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(RUPEES_SYNC, RupeesSyncS2CPacket::recieve);
    }

}
