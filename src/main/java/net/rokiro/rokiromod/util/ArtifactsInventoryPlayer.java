package net.rokiro.rokiromod.util;

import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class ArtifactsInventoryPlayer {

    List<ArtifactItemStack> artifactItems = new ArrayList<>();

    public ArtifactsInventoryPlayer(PlayerEntity player) {
        List<String> strArtifacts = ArtifactEquipmentData.clientGetArtifactEquipment(player);
        strArtifacts.stream().forEach(s -> {
            if (s.equals("none")) artifactItems.add(null) ;
            else artifactItems.add(ArtifactEquipmentData.getArtifactItemStackByIdInSlots(s));
        } );
    }

    public List<ArtifactItemStack> getArtifactItems() {
        return artifactItems;
    }

    @Override
    public String toString() {
        return artifactItems.stream().map(artifactItemStack -> {
            if (artifactItemStack == null) return  "none";
            else return artifactItemStack.getArtifactItem().getArtifactId() + artifactItemStack.quantity;
        }).toList().toString();
    }
}
