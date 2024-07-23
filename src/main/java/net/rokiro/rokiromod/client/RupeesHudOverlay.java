package net.rokiro.rokiromod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.jna.platform.win32.OaIdl;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.impl.client.indigo.renderer.helper.TextureHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.Font;
import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;

public class RupeesHudOverlay implements HudRenderCallback {

    public static final Identifier RUPEE = new Identifier(RokirosMod.MOD_ID,"textures/hud/rupee.png");

    public static int rupees = 0;
    public static int prevRupees = 0;
    public static boolean isAnimationActive = false;
    private static int age = 0;
    private static int pendingRupees = -1;
    private int maxAgeAnimation = 60;
    private int bounceDuration = 30;
    int x = 0;
    int y = 0;
    String rupeeCountFormatted = "";
    TextRenderer textRenderer;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width /2;
            y = height;


            RenderSystem.setShader(GameRenderer::getPositionTexProgram);

            DrawRupeesIcon(drawContext, tickDelta);
            DrawRupeesText(drawContext, tickDelta);


            drawContext.setShaderColor(1f,1f,1f,1f);

        }

    }
    public void DrawRupeesIcon(DrawContext drawContext, float tickDelta){
        drawContext.setShaderColor(1f,1f,1f,1f);
            drawContext.drawTexture(RUPEE,x*2 - 15,2,0,0,13,20,13,20);
    }
    public void DrawRupeesText(DrawContext drawContext, float tickDelta){
        textRenderer = MinecraftClient.getInstance().textRenderer;
        rupeeCountFormatted = ""+rupees;
        int pixelLength = textRenderer.getWidth(rupeeCountFormatted);

        BounceRupeesAnimation(drawContext,tickDelta, pixelLength);

    }

    public void BounceRupeesAnimation(DrawContext drawContext, float tickDelta,  int pixelLength){
        if (isAnimationActive){

            drawContext.setShaderColor(0.65f,1f,0.6f,1f);

            if (age >= bounceDuration/2 && age < bounceDuration){
                drawContext.drawTextWithShadow(textRenderer, Text.literal(rupeeCountFormatted ),x*2 - 16 -pixelLength,7, Colors.WHITE);
            } else if (age >= bounceDuration){
                drawContext.drawTextWithShadow(textRenderer, Text.literal(rupeeCountFormatted ),x*2 - 16 -pixelLength,6, Colors.WHITE);
            } else{
                drawContext.setShaderColor(1f,1f,1f,1f);
                drawContext.drawTextWithShadow(textRenderer, Text.literal(rupeeCountFormatted ),x*2 - 16 -pixelLength,8, Colors.WHITE);
            }

            age++;
            if (age >= maxAgeAnimation){
                age=0;
                isAnimationActive=false;
                if (pendingRupees > 0){
                    int tempRupees = pendingRupees;
                    pendingRupees = -1;
                    updateRupees(tempRupees);
                }
            }
        } else {
            drawContext.setShaderColor(0.1f,1f,0.2f,1f);
            drawContext.drawTextWithShadow(textRenderer, Text.literal(rupeeCountFormatted ),x*2 - 16 -pixelLength,8, Colors.WHITE);
        }
    }

    public static void updateRupees(int count){
        if (!isAnimationActive){
            isAnimationActive = true;
            prevRupees = rupees;
            rupees = count;
        } else {
            pendingRupees = count;
        }

    }


}
