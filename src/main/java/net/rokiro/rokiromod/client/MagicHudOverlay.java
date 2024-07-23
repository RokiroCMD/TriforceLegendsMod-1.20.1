package net.rokiro.rokiromod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;

public class MagicHudOverlay implements HudRenderCallback {

    public static final Identifier START = new Identifier(RokirosMod.MOD_ID,"textures/hud/magic_bar_start.png");
    public static final Identifier MIDDLE = new Identifier(RokirosMod.MOD_ID,"textures/hud/magic_bar_middle.png");
    public static final Identifier END = new Identifier(RokirosMod.MOD_ID,"textures/hud/magic_bar_end.png");

    public static final int DEFUALT_MAX_MAGIC = 3;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;

        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = 0;
            y = 0;

            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            x = (2 * 12) + 8;
            y = 2;

            drawContext.setShaderColor(1f,1f,1f,1f);
            drawContext.drawTexture(START,x,y,0,0,12,12,12,12);

            for (int i = 0 ; i < DEFUALT_MAX_MAGIC ; i++){
                x+= 12;
                drawContext.drawTexture(MIDDLE,x,y,0,0,12,12,12,12);
            }

            x+= 12;

            drawContext.drawTexture(END,x,y,0,0,12,12,12,12);

        }
    }
}
