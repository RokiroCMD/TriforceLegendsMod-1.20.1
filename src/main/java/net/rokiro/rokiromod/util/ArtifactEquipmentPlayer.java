package net.rokiro.rokiromod.util;

import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class ArtifactEquipmentPlayer {

    List<ArtifactItemStack> artifactItems = new ArrayList<>();

    public ArtifactEquipmentPlayer(PlayerEntity player) {
        List<String> strArtifacts = ArtifactEquipmentData.clientGetArtifactEquipment(player);
        strArtifacts.stream().forEach(s -> {
            if (s.equals("none")) artifactItems.add(null) ;
            else artifactItems.add(ArtifactEquipmentData.getArtifactItemStackById(s));
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
