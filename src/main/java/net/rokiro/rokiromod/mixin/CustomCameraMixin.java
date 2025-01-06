package net.rokiro.rokiromod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CustomCameraMixin {

    @Shadow protected abstract void setPos(Vec3d pos);

    @Shadow private float cameraY;

    @Shadow private float lastCameraY;

    @Shadow protected abstract void setPos(double x, double y, double z);

    @Shadow protected abstract void setRotation(float yaw, float pitch);

    @Shadow private float yaw;


    float timeTicks = 0;
    @Inject(method = "update", at = @At("TAIL"), cancellable = true)
    public void modifyCamera(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null){
            if (thirdPerson && !client.options.getPerspective().isFrontView()) {

                Vec3d playerPos = client.player.getEyePos();
                double distance = 1.5f;
                double yHeight = -1.5;
                double resultCameraDistance =  Math.cos(Math.toRadians( Math.abs((double) client.player.getPitch())));
                double resultCameraHeight =  Math.sin(Math.toRadians( client.player.getPitch()));

                distance *= resultCameraDistance;
                yHeight += 1.8 * resultCameraHeight;

                timeTicks += tickDelta;

                yHeight += Math.sin(Math.PI * timeTicks/50) *0.05;

                Vec3d offset = new Vec3d(-1.425F, yHeight, -distance).rotateY(-(float) Math.toRadians(client.player.getYaw()));


                double xCamera = MathHelper.lerp((double)tickDelta, focusedEntity.prevX + offset.x, playerPos.add(offset).x);
                double yCamera = MathHelper.lerp((double)tickDelta, focusedEntity.prevY + 1.62 + offset.y, playerPos.add(offset).getY()) + (double)MathHelper.lerp(tickDelta, this.lastCameraY, this.cameraY);
                double zCamera = MathHelper.lerp((double)tickDelta, focusedEntity.prevZ + offset.z, playerPos.add(offset).z);

                //client.player.sendMessage(Text.literal("" + (focusedEntity.getYaw(tickDelta))));
                this.setRotation(focusedEntity.getYaw(tickDelta), focusedEntity.getPitch(tickDelta));
                this.setPos(xCamera, yCamera, zCamera);

                ci.cancel();
            }
        }
    }
}
