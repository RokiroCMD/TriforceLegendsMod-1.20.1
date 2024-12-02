package net.rokiro.rokiromod.util;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.gen.Accessor;

public interface ArtifactSelectable {


    void setActiveArtifact(ArtifactItemStack artifact);
    ArtifactItemStack getActiveArtifact();
    boolean hasArtifactActive();
    boolean hasArtifactActive(ArtifactItem artifactItem);

    void deselectArtifact();
}
