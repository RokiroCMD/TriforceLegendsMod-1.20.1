package net.rokiro.rokiromod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;

public class OcarinePlayScreen extends Screen {
    public static final Identifier OCARINE_A_BUTTON = new Identifier(RokirosMod.MOD_ID,"textures/gui/ocarine/ocarine_a_button.png");
    public static final Identifier DIALOG_BOX = new Identifier(RokirosMod.MOD_ID,"textures/gui/ocarine/dialog_box.png");
    public static final Identifier OCARINE_UP_BUTTON = new Identifier(RokirosMod.MOD_ID,"textures/gui/ocarine/ocarine_up_button.png");
    public static final Identifier OCARINE_DOWN_BUTTON = new Identifier(RokirosMod.MOD_ID,"textures/gui/ocarine/ocarine_down_button.png");
    public static final Identifier OCARINE_LEFT_BUTTON = new Identifier(RokirosMod.MOD_ID,"textures/gui/ocarine/ocarine_left_button.png");
    public static final Identifier OCARINE_RIGHT_BUTTON = new Identifier(RokirosMod.MOD_ID,"textures/gui/ocarine/ocarine_right_button.png");
    public static final Identifier OCARINE_CHORD_DECO = new Identifier(RokirosMod.MOD_ID,"textures/gui/ocarine/chord_deco.png");

    public OcarinePlayScreen(Screen parent) {
        super(Text.literal("Ocarine play screen"));
        this.parent = parent;
    }
    private final Screen parent;
    private final int BACKGROUND_WIDTH = 128;
    private final int BACKGROUND_HEIGHT = 48;
    float scale = 1.5f;

    int i = 0;
    int v = 0;

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    protected void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void close() {
        MinecraftClient.getInstance().options.setPerspective(Perspective.FIRST_PERSON);
        super.close();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        RenderSystem.enableBlend();
        i = (this.width /2);
        v = ( (this.height - BACKGROUND_HEIGHT) /2 ) + (this.height /4);

        MatrixStack matrices = context.getMatrices();

        matrices.push();

        matrices.scale(scale,scale,1f);
        matrices.translate(
                -i + ((i - (BACKGROUND_WIDTH/2f  * scale)) / scale),
                (-v + (v /scale)) ,
                0f);

        DrawBackground(context,mouseX,mouseY,delta);
        Draw(context, mouseX, mouseY, delta);
        matrices.pop();
    }

    public void DrawBackground(DrawContext context, int mouseX, int mouseY, float delta){
        context.drawTexture(DIALOG_BOX, i,v, 0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,BACKGROUND_WIDTH,BACKGROUND_HEIGHT);
    }
    public void Draw(DrawContext context, int mouseX, int mouseY, float delta){
        for (int c = 0; c < 4 ; c++){
            context.drawHorizontalLine(i+7, i+116,v+(12 +(c * 8)),0xFF8c6c3f);
        }
        context.drawTexture(OCARINE_CHORD_DECO, i+6,v+8, 0,0, 16,32,16,32);

    }
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        RokirosMod.LOGGER.info(""+keyCode);
        return super.keyPressed(keyCode, scanCode, modifiers);
    }


}
