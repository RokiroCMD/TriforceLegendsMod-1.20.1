package net.rokiro.rokiromod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ModStatus;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.effects.ModEffects;
import net.rokiro.rokiromod.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ModEntityMixin implements IEntityDataSaver {


    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (this.persistentData == null){
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> ci){
        if (persistentData != null){
            nbt.put(RokirosMod.MOD_ID+".data", persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo ci){
        if (nbt.contains(RokirosMod.MOD_ID+".data", NbtElement.COMPOUND_TYPE)){
            persistentData = nbt.getCompound(RokirosMod.MOD_ID+".data");
        }
    }

    @Inject(method = "changeLookDirection", at = @At(value = "HEAD"), cancellable = true)
    public void onchangeLookDirection(double cursorDeltaX, double cursorDeltaY, CallbackInfo ci){
        if (ci.isCancellable()){

            if ((Object) this instanceof LivingEntity){
                LivingEntity livingEntity =  (LivingEntity) ((Object) this);
                if (livingEntity.hasStatusEffect(ModEffects.NUT_STUNNED_EFFECT)) ci.cancel();
            }
        }
    }

}
