package net.rokiro.rokiromod.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.rokiro.rokiromod.RokirosMod;
import software.bernie.geckolib.util.ClientUtils;

public class FlashHudOverlay implements HudRenderCallback {

    public static float totalTickDelta = 0;
    float flashTime = 5;
    public static boolean active = false;
    MinecraftClient client;

    public FlashHudOverlay() {
        client = MinecraftClient.getInstance();
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

        if (active){
            totalTickDelta += tickDelta;

            int x = 0;
            int y = 0;

            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            RenderSystem.enableBlend();


            drawContext.fill(0,0,width,height,500,0xAAFFFFFF);
            if (totalTickDelta > flashTime){
                active = false;
                totalTickDelta = 0;
            }

        }
    }


    public static void activateFlash(){
        active = true;
        totalTickDelta = 0;
    }
}
