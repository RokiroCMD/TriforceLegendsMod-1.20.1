package net.rokiro.rokiromod.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.*;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.networking.ModPackets;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtifactEquipmentData {

    public static String ARTIFACT_EQUIPMENT_KEY = "artifact_equipment";

    public static void setArtifactInSlot(ServerPlayerEntity player, String artifact, int slot, @Nullable Integer count ){

        NbtCompound nbt = ((IEntityDataSaver) (player)).getPersistentData();

        NbtList nbtList = nbt.getList(ARTIFACT_EQUIPMENT_KEY,NbtElement.STRING_TYPE);
        nbtList.set(slot, NbtString.of(artifact+ count !=null ? "{count:"+ count+"}" : ""));
        nbt.put(ARTIFACT_EQUIPMENT_KEY,nbtList);

        syncArtifactEquipment(player,nbtList.stream().map(nbtElement -> nbtElement.asString()).toList());
    }

    public static void setArtifactAll(ServerPlayerEntity player, List<String> artifacts){
        NbtCompound nbt = ((IEntityDataSaver) (player)).getPersistentData();

        NbtList nbtList = new NbtList();

        artifacts.stream().forEach(s -> {
            nbtList.add(NbtString.of(s));
        });

        nbt.put(ARTIFACT_EQUIPMENT_KEY,nbtList);
        syncArtifactEquipment(player,nbtList.stream().map(nbtElement -> nbtElement.asString()).toList());
    }

    public static void clientSetArtifactInSlot(ArtifactItem artifactItem, int slot, @Nullable Integer count){
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeString(artifactItem.getArtifactId()+ count !=null ? "{count:"+ count+"}" : "");
        buffer.writeInt(slot);

        ClientPlayNetworking.send(ModPackets.ARTIFACT_EQUIPMENT_SET, buffer);
    }

    public static void clientSetArtifactsAll(List<String> artifacts){
        PacketByteBuf buffer = PacketByteBufs.create();
        StringBuilder sb = new StringBuilder();
        for (String str : artifacts) {
            String key = str;
            sb.append(key);
            sb.append("|");
        }

        String concatenatedString = sb.toString().trim();

        byte[] array = concatenatedString.getBytes(StandardCharsets.UTF_8);

        buffer.writeByteArray(array);
        ClientPlayNetworking.send(ModPackets.ARTIFACT_EQUIPMENT_SET_ALL, buffer);
    }

    public static List<String> getArtifactEquipment(ServerPlayerEntity player){
        NbtCompound nbt = ((IEntityDataSaver) (player)).getPersistentData();

        try {
            NbtList artifactList = nbt.getList(ARTIFACT_EQUIPMENT_KEY,NbtElement.STRING_TYPE);
            if (artifactList == null){
                artifactList = createArtifactEquipment(player);
            }
            if (artifactList.isEmpty()){
                artifactList = createArtifactEquipment(player);
            }

            List<String> stringList =  artifactList.stream().map(NbtElement::asString).toList();

            return stringList;

        } catch (Exception e){
            return null;
        }
    }

    public static List<String> clientGetArtifactEquipment(PlayerEntity player){

        NbtCompound nbt = ((IEntityDataSaver) player).getPersistentData();
        NbtList artifactList = nbt.getList(ARTIFACT_EQUIPMENT_KEY,NbtElement.STRING_TYPE);
        return artifactList.stream().map(NbtElement::asString).toList();
    }

    public static NbtList createArtifactEquipment(ServerPlayerEntity player){
        NbtCompound nbt = ((IEntityDataSaver) (player)).getPersistentData();

        NbtList artifactList = new NbtList();
        artifactList.add(NbtString.of("none"));
        artifactList.add(NbtString.of("none"));
        artifactList.add(NbtString.of("none"));
        artifactList.add(NbtString.of("none"));

        nbt.put(ARTIFACT_EQUIPMENT_KEY,artifactList);
        return artifactList;
    }


    public static void syncArtifactEquipment(ServerPlayerEntity player, List<String> stringList){

        PacketByteBuf buffer = PacketByteBufs.create();
        StringBuilder sb = new StringBuilder();
        for (String str : stringList) {
            String key = str;
            if (!key.equals("none")){
                key = str.substring(5);
                key = key.replaceFirst("\\.",":");
            }
            sb.append(key);
            sb.append("|");
        }

        String concatenatedString = sb.toString().trim();

        byte[] array = concatenatedString.getBytes(StandardCharsets.UTF_8);

        buffer.writeByteArray(array);
        ServerPlayNetworking.send(player, ModPackets.ARTIFACT_EQUIPMENT_SYNC, buffer);
    }

    public static ArtifactItemStack getArtifactItemStackById(String id){
        String input = id;

        if (id.equals("null")) return null;

        Pattern pattern = Pattern.compile("\\{count:(\\d+)\\}");
        Matcher matcher = pattern.matcher(input);

        Integer countValue = null;
        if (matcher.find()) {
            countValue = Integer.parseInt(matcher.group(1));
        }

        String result = input.replaceAll("\\{count:\\d+\\}", "");

        Item item = Registries.ITEM.get(new Identifier(result));
        ArtifactItem artifactItem = ((ArtifactItem) item);

        ArtifactItemStack artifactItemStack = new ArtifactItemStack(artifactItem, countValue);

        return artifactItemStack;
    }
}
