package net.rokiro.rokiromod.mixin;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.rokiro.rokiromod.item.ModItems;
import net.rokiro.rokiromod.util.ArtifactItem;
import net.rokiro.rokiromod.util.ArtifactItemStack;
import net.rokiro.rokiromod.util.ArtifactSelectable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public class ModPlayerInventoryMixin implements ArtifactSelectable {

    @Shadow @Final public PlayerEntity player;

    @Inject(method = "getMainHandStack", at = @At("HEAD"), cancellable = true)
    public void getMainItem(CallbackInfoReturnable<ItemStack> cir){
        if (hasArtifactActive()){
            if (cir.isCancellable()){
                cir.cancel();
                cir.setReturnValue(getActiveArtifact().asItemStack());
            }
        }

    }


    ArtifactItemStack artifactItemStack = null;

    @Override
    public void setActiveArtifact(ArtifactItemStack artifact) {
        artifactItemStack = artifact;
    }

    @Override
    public ArtifactItemStack getActiveArtifact() {
        return hasArtifactActive() ? artifactItemStack : null;
    }

    @Override
    public boolean hasArtifactActive() {
        return artifactItemStack != null;
    }

    @Override
    public boolean hasArtifactActive(ArtifactItem artifactItem) {
        if (hasArtifactActive()){
            if (((ArtifactSelectable) player.getInventory()).getActiveArtifact().getArtifactItem() == artifactItem){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deselectArtifact() {
        if  (((ArtifactSelectable) player.getInventory()).hasArtifactActive()){
            ((ArtifactSelectable) player.getInventory()).setActiveArtifact(null);
        }
    }
}
