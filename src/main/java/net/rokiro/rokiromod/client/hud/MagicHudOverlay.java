package net.rokiro.rokiromod.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.rokiro.rokiromod.RokirosMod;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MagicHudOverlay implements HudRenderCallback {

    public static final Identifier START = new Identifier(RokirosMod.MOD_ID,"textures/hud/magic_bar_start.png");
    public static final Identifier MIDDLE = new Identifier(RokirosMod.MOD_ID,"textures/hud/magic_bar_middle.png");
    public static final Identifier END = new Identifier(RokirosMod.MOD_ID,"textures/hud/magic_bar_end.png");

    public static final int DEFUALT_MAX_MAGIC = 3;
    float totalTickDelta = 0;

    float scaleFactor = 1.4f;

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

    private  void TestingShaders( DrawContext drawContext, float tickDelta){

        MinecraftClient client = MinecraftClient.getInstance();

        MatrixStack matrices = drawContext.getMatrices();

        matrices.push();

        totalTickDelta += tickDelta;

        float xMovement = MathHelper.sin(totalTickDelta/20f) * 50;
        float yMovement = MathHelper.cos(totalTickDelta/20f) * 50 ;

        float scaleValue = (MathHelper.sin(totalTickDelta/30f) /2 * scaleFactor) + scaleFactor;

        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tesselator = Tessellator.getInstance();
        BufferBuilder buffer = tesselator.getBuffer();

        buffer.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_COLOR);

        matrix.scale(scaleValue, scaleValue, 1f);
        matrix.translate((client.getWindow().getScaledWidth()/ 2 )  / scaleValue +(xMovement / scaleValue), (client.getWindow().getScaledHeight() / 2) / scaleValue + (yMovement /scaleValue),1f);
        matrix.translateLocal(-15f * scaleValue,-20f  * scaleValue,1f);

        buffer.vertex(matrix, 15,0,1).color(0xFFbd3c63).next();
        buffer.vertex(matrix, 0,20,1).color(0xFF801731 ).next();
        buffer.vertex(matrix, 30,20,1).color(0xFF801731).next();
        buffer.vertex(matrix, 15,40,1).color(0xFFbd3c63).next();

        RenderSystem.setShader(GameRenderer::getPositionColorProgram );
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);


        tesselator.draw();

        matrices.pop();
    }

}
