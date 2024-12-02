package net.rokiro.rokiromod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.rokiro.rokiromod.sound.ModSounds;

public interface ArtifactItem {


    static void equipArtifactItem(PlayerEntity player, ArtifactItem item){
        if  (((ArtifactSelectable) player.getInventory()).hasArtifactActive(item)){
            ((ArtifactSelectable) player.getInventory()).deselectArtifact();
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ModSounds.GENERIC_ITEM_EQUIP, 0.8F));
        }else {
            ((ArtifactSelectable) player.getInventory()).setActiveArtifact(new ArtifactItemStack(item,1));
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ModSounds.GENERIC_ITEM_EQUIP, 1.0F));
        }
    }
    public int getColorTitle();

    public default  int getOutlineColorTitle(){
        return 0xFF000000;
    }

    ArtifactSaga getSaga();
    default String  getArtifactId(){
        return ((Item)(this)).getTranslationKey();
    }
    default boolean getConditions(PlayerEntity player){
        return true;
    }

    void onActivateAbility(PlayerEntity player);

    boolean canStack();
    int maxStack();



    public static enum ArtifactSaga{
        OOT,
        MM,
        WW,
        TP,
        SS,
        MC,
        ST,

    }
    
}
