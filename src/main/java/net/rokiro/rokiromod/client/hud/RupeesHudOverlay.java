package net.rokiro.rokiromod.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.util.TextGraphicUtils;

public class RupeesHudOverlay implements HudRenderCallback {

    public static final Identifier RUPEE = new Identifier(RokirosMod.MOD_ID,"textures/hud/rupee.png");

    public static int rupees = 0;
    public static int prevRupees = 0;
    public static boolean isAnimationActive = false;
    private static int age = 0;
    private static int pendingRupees = -1;

    float totalTickAnimationDelta = 0;
    float tickDelta = 0;
    int x = 0;
    int y = 0;
    String rupeeCountFormatted = "";
    TextRenderer textRenderer;
    int width = 0;
    int height = 0;
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null){
            width = client.getWindow().getScaledWidth();
            height = client.getWindow().getScaledHeight();

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
        BounceRupeesAnimation(drawContext,tickDelta);

    }

    public void BounceRupeesAnimation(DrawContext drawContext, float tickDelta){
        if (isAnimationActive){

            totalTickAnimationDelta += tickDelta;

            MatrixStack matrices = drawContext.getMatrices();

            matrices.push();

            float yMovement = MathHelper.clamp( MathHelper.sin( MathHelper.HALF_PI * (totalTickAnimationDelta/10f) ) * 3 , 0, 3f );

            matrices.translate(0f,-yMovement,0);

            TextGraphicUtils.drawOutilineText(drawContext,x*2 - 16 ,11,Text.literal(rupeeCountFormatted),
                    0xFF38ff82, 0xFF0a803d,
                    TextGraphicUtils.OutlineTextAlignment.RIGHT );

            if (yMovement == 0){
                isAnimationActive=false;
                totalTickAnimationDelta = 0f;
                if (pendingRupees > 0){
                    int tempRupees = pendingRupees;
                    pendingRupees = -1;
                    updateRupees(tempRupees);
                }
            }

            matrices.pop();

        } else {
            TextGraphicUtils.drawOutilineText(drawContext,x*2 - 16 ,11,Text.literal(rupeeCountFormatted),
                    0xFF29e355, 0xFF035722,
                    TextGraphicUtils.OutlineTextAlignment.RIGHT );
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
