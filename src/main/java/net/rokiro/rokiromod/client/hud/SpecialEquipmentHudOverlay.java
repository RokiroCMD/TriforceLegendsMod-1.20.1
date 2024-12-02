package net.rokiro.rokiromod.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.event.KeyInputHandler;
import net.rokiro.rokiromod.sound.ModSounds;
import net.rokiro.rokiromod.util.*;
import org.lwjgl.glfw.GLFW;
import software.bernie.geckolib.util.ClientUtils;

import java.util.ArrayList;
import java.util.List;

public class SpecialEquipmentHudOverlay implements HudRenderCallback {



    public static final Identifier SLOT = new Identifier(RokirosMod.MOD_ID,"textures/hud/special_equipment_slot.png");
    public static final Identifier ERROR_SLOT = new Identifier(RokirosMod.MOD_ID,"textures/hud/special_equipment_slot_error.png");
    public static final Identifier TRIGGER_SLOT = new Identifier(RokirosMod.MOD_ID,"textures/hud/special_equipment_slot_active.png");
    public static List<ArtifactSlotCaseElement> ARTIFACT_SLOTS = new ArrayList<>();
    public static boolean isShiftPressed = false;
    public static int TEXTURE_WIDTH = 24;
    public static int TEXTURE_HEIGHT = 24;


    public SpecialEquipmentHudOverlay() {
        ARTIFACT_SLOTS = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (int i = 0 ; i < 4 ; i++) {

            x = (i * (TEXTURE_WIDTH / 2)) + 4;
            y = 4;

            if (i >= 2 && i <= 3) {

                x -= (TEXTURE_WIDTH / 2);

                if (i == 2) {
                    y += TEXTURE_HEIGHT;
                }
            }

            if (i == 0 || i == 3) {
                y = (TEXTURE_WIDTH / 2) + 4;
            }

            if (i == 0) {
                x -= 2;
            } else if (i == 1) {
                y -= 2;
            } else if (i == 2) {
                y += 2;
            } else if (i == 3) {
                x += 2;
            }
            ARTIFACT_SLOTS.add(new ArtifactSlotCaseElement(i, x + TEXTURE_WIDTH/2,y + TEXTURE_HEIGHT/2));
        }
    }

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null){

            isShiftPressed = InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT);

            for (ArtifactSlotCaseElement artifactSlot :ARTIFACT_SLOTS){
                drawContext.setShaderColor(1f,1f,1f,1f);
                artifactSlot.render(drawContext, tickDelta);
            }

        }
    }


    public static void animateActivateSlot(int slotIndex) {

        ARTIFACT_SLOTS.get(slotIndex).applyTriggerAnim();
    }
    public static void animateErrorSlot(int slotIndex){
        ARTIFACT_SLOTS.get(slotIndex).applyErrorAnim();
    }

    public static void updateArtifactEquipment(){

        ArtifactEquipmentPlayer artifactEquipmentPlayer = new ArtifactEquipmentPlayer(ClientUtils.getClientPlayer());

        int index = 0;
        for (ArtifactItemStack artifactItemStack : artifactEquipmentPlayer.getArtifactItems()){
            if (artifactItemStack != null){
                ARTIFACT_SLOTS.get(index).setArtifactItemStack(artifactItemStack);
            } else {
                ARTIFACT_SLOTS.get(index).setArtifactItemStack(null);
            }
            index++;
        }

    }

    class ArtifactSlotCaseElement {

        private ArtifactItemStack artifactItemStack;

        int x;
        int y;

        int index;

        private int TEXTURE_WIDTH = 24;
        private int TEXTURE_HEIGHT = 24;
        float totalTickDelta;
        boolean errorAnimation;
        boolean triggerAnimation;
        float errorTimeAnimation = 10f;
        float triggerTimeAnimation = 10f;

        float shrinkScaleValue = 0.15f;

        public ArtifactSlotCaseElement(int index ,int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
        public ArtifactSlotCaseElement(int index, int x, int y, ArtifactItemStack artifactItemStack) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.artifactItemStack = artifactItemStack;
        }

        private void drawItem(DrawContext drawContext){
            if (artifactItemStack != null){
                drawContext.drawItem(artifactItemStack.asItemStack(),x+4,y+4);
                MatrixStack itemMatrix = drawContext.getMatrices();

                if (artifactItemStack.canStack()){

                    itemMatrix.push();
                    itemMatrix.translate(0f,0f,300f);

                    TextGraphicUtils.drawItemQuantityText(drawContext,x + TEXTURE_WIDTH / 2,y + TEXTURE_HEIGHT - 2,
                            TextGraphicUtils.OutlineTextAlignment.CENTER,0.8f,
                            artifactItemStack.getQuantity(), artifactItemStack.getMaxQuantity());

                    itemMatrix.pop();
                }
            }
        }

        public void render(DrawContext drawContext, float tickDelta){

            drawBackgroundCase(drawContext,tickDelta);
            //drawItem(drawContext);

            if (isShiftPressed){

                drawContext.getMatrices().push();


                Text key = Text.literal("");
                switch (index){
                    case 0-> key  = KeyInputHandler.useFirstArtifactSlot.getBoundKeyLocalizedText();
                    case 1-> key  = KeyInputHandler.useSecondArtifactSlot.getBoundKeyLocalizedText();
                    case 2-> key  = KeyInputHandler.useThirdArtifactSlot.getBoundKeyLocalizedText();
                    case 3-> key  = KeyInputHandler.useFourthArtifactSlot.getBoundKeyLocalizedText();
                }

                float scale = MinecraftClient.getInstance().textRenderer.getWidth(key) < TEXTURE_WIDTH ? 1.0f : 0.55f;

                float yOffset = 0f;

                if (scale<1f){
                    if (index==0){
                        yOffset = (MinecraftClient.getInstance().textRenderer.fontHeight *scale) /2;
                    }
                    if (index == 3){
                        yOffset = (-MinecraftClient.getInstance().textRenderer.fontHeight * scale) /2;
                    }
                }

                drawContext.getMatrices().translate(0.5f,
                        yOffset,
                        301f);

                TextGraphicUtils.drawOutilineText(drawContext,x,y ,key,
                        0xFFe5e5d3, 0xff4d4132,
                        TextGraphicUtils.OutlineTextAlignment.CENTER, scale);
                drawContext.getMatrices().pop();
            }

        }



        public void drawBackgroundCase(DrawContext drawContext, float tickDelta){
            if (playingErrorAnimation(drawContext,tickDelta));
            else if (playingTriggerAnimation(drawContext,tickDelta));
            else {
                drawContext.getMatrices().push();
                drawContext.getMatrices().translate(
                        -x + (x - (TEXTURE_WIDTH/2f)),
                        -y + (y - (TEXTURE_HEIGHT/2f)),
                        0f);
                drawItem(drawContext);
                drawContext.drawTexture(SLOT,x,y,0,0,TEXTURE_WIDTH,TEXTURE_HEIGHT,TEXTURE_WIDTH,TEXTURE_HEIGHT);
                drawContext.getMatrices().pop();
            }

        }

        private boolean playingErrorAnimation(DrawContext drawContext, float tickDelta){
            if (errorAnimation){
                totalTickDelta += tickDelta / 10;
                drawContext.getMatrices().push();
                float resultScale = 1f - MathHelper.sin((totalTickDelta / errorTimeAnimation) * MathHelper.PI)  * shrinkScaleValue;
                drawContext.getMatrices().translate(
                        -x + ((x - (TEXTURE_WIDTH/2f  * resultScale)) / resultScale),
                        -y + ((y - (TEXTURE_HEIGHT/2f  * resultScale)) / resultScale),
                        0f);
                drawContext.getMatrices().scale(resultScale,resultScale,1f);
                drawContext.drawTexture(ERROR_SLOT,x ,y ,0,0,TEXTURE_WIDTH,TEXTURE_HEIGHT,TEXTURE_WIDTH,TEXTURE_HEIGHT);
                drawItem(drawContext);
                drawContext.getMatrices().pop();
                if ( totalTickDelta >= errorTimeAnimation){
                    errorAnimation = false;
                    totalTickDelta = 0;
                }
                return true;
            }
            return false;
        }

        private boolean playingTriggerAnimation(DrawContext drawContext, float tickDelta){
            if (triggerAnimation){
                totalTickDelta += tickDelta / 10;
                drawContext.getMatrices().push();
                float resultScale = 1f - MathHelper.sin((totalTickDelta / triggerTimeAnimation) * MathHelper.PI)  * shrinkScaleValue;
                drawContext.getMatrices().translate(
                        -x + ((x - (TEXTURE_WIDTH/2f  * resultScale)) / resultScale),
                        -y + ((y - (TEXTURE_HEIGHT/2f  * resultScale)) / resultScale),
                        0f);
                drawContext.getMatrices().scale(resultScale,resultScale,1f);
                drawContext.drawTexture(TRIGGER_SLOT,x ,y ,0,0,TEXTURE_WIDTH,TEXTURE_HEIGHT,TEXTURE_WIDTH,TEXTURE_HEIGHT);
                drawItem(drawContext);
                drawContext.getMatrices().pop();
                if ( totalTickDelta >= triggerTimeAnimation){
                    triggerAnimation = false;
                    totalTickDelta = 0;
                }
                return true;
            }
            return false;
        }

        public ArtifactItemStack getArtifactItemStack() {
            return artifactItemStack;
        }

        public void setArtifactItemStack(ArtifactItemStack artifactItemStack) {
            this.artifactItemStack = artifactItemStack;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void applyErrorAnim() {
            errorAnimation = true;
            triggerAnimation = false;
            totalTickDelta = 0;
        }

        public void applyTriggerAnim() {
            triggerAnimation = true;
            errorAnimation = false;
            totalTickDelta = 0;
        }
    }


}
