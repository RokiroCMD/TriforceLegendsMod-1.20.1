package net.rokiro.rokiromod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.networking.packet.c2s.*;
import net.rokiro.rokiromod.networking.packet.s2c.ArtifactEquipmentSyncS2CPacket;
import net.rokiro.rokiromod.networking.packet.s2c.DekuNutParticleS2CPacket;
import net.rokiro.rokiromod.networking.packet.s2c.RupeesSyncS2CPacket;

public class ModPackets {

    public static final Identifier RUPEES_ID = new Identifier(RokirosMod.MOD_ID,"rupees");
    public static final Identifier RUPEES_SYNC = new Identifier(RokirosMod.MOD_ID,"rupees_sync");
    public static final Identifier ARTIFACT_EQUIPMENT_SYNC = new Identifier(RokirosMod.MOD_ID,"artifact_equipment_sync");
    public static final Identifier ARTIFACT_EQUIPMENT_SET = new Identifier(RokirosMod.MOD_ID,"artifact_equipment_set");
    public static final Identifier ARTIFACT_EQUIPMENT_SET_ALL = new Identifier(RokirosMod.MOD_ID,"artifact_equipment_set_all");
    public static final Identifier ARTIFACT_USE_PLAYER = new Identifier(RokirosMod.MOD_ID,"artifact_use_player");
    public static final Identifier DEKU_NUT_IMPACT = new Identifier(RokirosMod.MOD_ID,"deku_nut_impact");
    public static final Identifier DEKU_NUT_PARTICLE = new Identifier(RokirosMod.MOD_ID,"deku_nut_particle");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(RUPEES_ID, RupeesC2SPacket::recieve);
        ServerPlayNetworking.registerGlobalReceiver(ARTIFACT_EQUIPMENT_SET, ArtifactEquipmentSetC2SPacket::recieve);
        ServerPlayNetworking.registerGlobalReceiver(ARTIFACT_EQUIPMENT_SET_ALL, ArtifactEquipmentSetAllC2SPacket::recieve);
        ServerPlayNetworking.registerGlobalReceiver(ARTIFACT_USE_PLAYER, ArtifactUsePlayerC2SPacket::recieve);
        ServerPlayNetworking.registerGlobalReceiver(DEKU_NUT_IMPACT, DekuNutImpactC2SPacket::recieve);
    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(RUPEES_SYNC, RupeesSyncS2CPacket::recieve);
        ClientPlayNetworking.registerGlobalReceiver(ARTIFACT_EQUIPMENT_SYNC, ArtifactEquipmentSyncS2CPacket::recieve);
        ClientPlayNetworking.registerGlobalReceiver(DEKU_NUT_PARTICLE, DekuNutParticleS2CPacket::recieve);
    }

}
