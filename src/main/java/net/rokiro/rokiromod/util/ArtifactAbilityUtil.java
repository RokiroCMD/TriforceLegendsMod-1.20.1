package net.rokiro.rokiromod.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.hud.SpecialEquipmentHudOverlay;
import net.rokiro.rokiromod.networking.ModPackets;
import net.rokiro.rokiromod.sound.ModSounds;
import software.bernie.geckolib.util.ClientUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtifactAbilityUtil {

    public static void useArtifactAbility(int slot){
        String id = ArtifactEquipmentData.clientGetArtifactEquipment(ClientUtils.getClientPlayer()).get(slot);

        String input = id;

        Pattern pattern = Pattern.compile("\\{count:(\\d+)\\}");
        Matcher matcher = pattern.matcher(input);

        Integer countValue = null;
        if (matcher.find()) {
            countValue = Integer.parseInt(matcher.group(1)); // Obtiene el valor numérico después de "count:"
        }

        String result = input.replaceAll("\\{count:\\d+\\}", "");


        if (!result.equals("none")){
            Item item = Registries.ITEM.get(new Identifier(result));

            ArtifactItem artifact = (ArtifactItem) item;
            if (artifact != null){

                if (artifact.getConditions(ClientUtils.getClientPlayer())){
                    artifact.onActivateAbility(ClientUtils.getClientPlayer());
                    SpecialEquipmentHudOverlay.animateActivateSlot(slot);
                } else {
                    SpecialEquipmentHudOverlay.animateErrorSlot(slot);
                    MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ModSounds.GENERIC_ERROR, 1.0F));
                }
                return;
            }
        }
        SpecialEquipmentHudOverlay.animateErrorSlot(slot);
        MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ModSounds.GENERIC_ERROR, 1.0F));

    }
}
