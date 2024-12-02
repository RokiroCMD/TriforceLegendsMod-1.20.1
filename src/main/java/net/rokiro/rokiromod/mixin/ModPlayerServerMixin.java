package net.rokiro.rokiromod.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.util.ArtifactEquipmentData;
import net.rokiro.rokiromod.util.RupeesData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ModPlayerServerMixin {

    @Shadow public ServerPlayNetworkHandler networkHandler;
    int age = 1;
    boolean hasSpawned = false;

    @Inject(method = "tick",  at = @At("TAIL"))
    public void onTickChange(CallbackInfo ci){
        if (this.networkHandler.isConnectionOpen()){
            if (this.networkHandler.getPlayer() != null){
                if (!hasSpawned) {
                    age++;
                    if (age % 5 == 0) {
                        try {
                            RupeesData.getRupees(this.networkHandler.getPlayer());
                            age = 0;
                            ArtifactEquipmentData.syncArtifactEquipment(this.networkHandler.getPlayer(),
                                    ArtifactEquipmentData.getArtifactEquipment(this.networkHandler.getPlayer()));
                            hasSpawned = true;
                        } catch(Exception e){
                            RokirosMod.LOGGER.info("Error at loading RupeeInformation");
                        }
                    }
                }
            }
        }
    }


}
