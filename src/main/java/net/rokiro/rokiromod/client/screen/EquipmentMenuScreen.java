package net.rokiro.rokiromod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.screen.widgets.TurnPageWidget;
import net.rokiro.rokiromod.item.ModItems;
import net.rokiro.rokiromod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;


@Environment(EnvType.CLIENT)
public class EquipmentMenuScreen extends Screen {

    private final Screen parent;
    boolean onStartAnimation = true;
    boolean onCloseAnimation = false;
    public static final Identifier EQUIPMENT_MENU = new Identifier(RokirosMod.MOD_ID,"textures/gui/equipment_inventory.png");
    private static final int texture_width = 384;
    private static final int texture_height = 288;

    private TurnPageWidget RIGHT_BUTTON_WIDGET;
    private TurnPageWidget LEFT_BUTTON_WIDGET;
    int i = 0;
    int v = 0;

    int time = 0;
    int maxTimeAnimationEntry = 40;

    float totalTickAnimationDelta = 0;
    float tickDelta = 0;



    public EquipmentMenuScreen(Screen parent, boolean animate) {
        super(Text.literal("EQUIPMENT_MENU"));
        this.parent = parent;
        if (animate){
            onStartAnimation = true;
        } else {
            onStartAnimation = false;
        }
    }

    @Override
    public void tick() {
        if (onStartAnimation){
            time++;
            if (time > maxTimeAnimationEntry){
                time = 0;
                onStartAnimation = false;
            }
        }

    }


    @Override
    protected void init() {
        i = (this.width - texture_width) / 2;
        v = (this.height - texture_height) / 2;
        if (onStartAnimation){
            client.player.playSound(ModSounds.OPEN_EQUIPMENT_MENU, SoundCategory.MASTER,5f,1f);
        }


        RIGHT_BUTTON_WIDGET = addDrawableChild(new TurnPageWidget(i+texture_width - 8,v+(texture_height/2) - 16,(button -> {
            client.setScreen(new ArtifactsMenuScreen(parent));
        }), TurnPageWidget.EquipmentButtonType.RIGHT,client));


        LEFT_BUTTON_WIDGET = addDrawableChild(new TurnPageWidget(i - 22,v+(texture_height/2) - 16,(button -> {

        }), TurnPageWidget.EquipmentButtonType.LEFT,client));
    }

    @Override
    public void close() {
        if (onStartAnimation){

        } else {
            if (!onCloseAnimation){
                onCloseAnimation = true;
                disableAll();
                client.player.playSound(ModSounds.CLOSE_EQUIPMENT_MENU, SoundCategory.MASTER,5f,1f);
            }
            //client.setScreen(parent);
        }
    }

    private void disableAll(){
        RIGHT_BUTTON_WIDGET.active =false;
        LEFT_BUTTON_WIDGET.active =false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        i = (this.width - texture_width) / 2;
        v = (this.height - texture_height) / 2;
        tickDelta = delta;
        renderBackground(context);
        renderForeground(context, mouseX, mouseY,delta);


    }


    public void renderForeground(DrawContext context, int mouseX, int mouseY, float delta){

        if (!onStartAnimation && !onCloseAnimation){
            drawEntity(context, i + 140, v+220, 65,
                    (float)(i + 140) - mouseX, (float)(v + 100) - mouseY,
                    this.client.player);

            RIGHT_BUTTON_WIDGET.render(context,mouseX,mouseY,delta);
            LEFT_BUTTON_WIDGET.render(context, mouseX, mouseY, delta);
            //drawRupeeBagIcon(context,mouseX,mouseY,delta);

        }
    }
    @Override
    public void renderBackground(DrawContext context) {
        if (this.client.world != null) {

            context.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);


            context.getMatrices().push();
            RenderSystem.enableBlend();

            // Start animation check and movement
             if (!startAnimation(context)){
                 // Close animation check and movement
                 if (!closeAnimation(context)){
                     //Stand animation
                     context.drawTexture(EQUIPMENT_MENU,i,v,0,0,texture_width,texture_height,texture_width,texture_height);

                     String rawText = "Equipment";

                     int textX = i + texture_width/2 - textRenderer.getWidth(rawText)/2;
                     int textY = v + 15;
                     MatrixStack matrices = context.getMatrices();
                     matrices.scale(2f,2f,1f);
                     matrices.translate((-textX * 0.5) - (textRenderer.getWidth(rawText)/2) * 0.5 ,-textY * 0.5,1f);
                     context.drawText(textRenderer,Text.literal(rawText),textX,textY, 0xFF66604c,false);
                 }
            }

            context.getMatrices().pop();
        } else {
            this.renderBackgroundTexture(context);
        }
        context.setShaderColor(1.0f,1.0f,1.0f, 1.0f);
    }

    private void drawRupeeBagIcon(DrawContext context, int mouseX, int mouseY, float delta){

        /* Drawing rupeeBag on FirstSlot
           * Placeholder obviously */

        context.getMatrices().push();
        context.getMatrices().scale(2f,2f,2f);
        context.drawItem(new ItemStack(ModItems.MEDIUM_RUPEE_BAG),(i+18)/2,(v+28)/2);
        context.getMatrices().pop();

    }

    public static void drawEntity(DrawContext context, int x, int y, int size, float mouseX, float mouseY, LivingEntity entity) {
        float f = (float)Math.atan((double)(mouseX / 40.0F));
        float g = (float)Math.atan((double)(mouseY / 40.0F));
        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf2 = (new Quaternionf()).rotateX(g * 20.0F * 0.017453292F);
        quaternionf.mul(quaternionf2);
        float h = entity.bodyYaw;
        float i = entity.getYaw();
        float j = entity.getPitch();
        float k = entity.prevHeadYaw;
        float l = entity.headYaw;
        entity.bodyYaw = 180.0F + f * 20.0F;
        entity.setYaw(180.0F + f * 40.0F);
        entity.setPitch(-g * 20.0F);
        entity.headYaw = entity.getYaw();
        entity.prevHeadYaw = entity.getYaw();
        drawEntity(context, x, y, size, quaternionf, quaternionf2, entity);
        entity.bodyYaw = h;
        entity.setYaw(i);
        entity.setPitch(j);
        entity.prevHeadYaw = k;
        entity.headYaw = l;
    }
    public static void drawEntity(DrawContext context, int x, int y, int size, Quaternionf quaternionf, @Nullable Quaternionf quaternionf2, LivingEntity entity) {
        context.getMatrices().push();
        context.getMatrices().translate((double)x, (double)y, 50.0);
        context.getMatrices().multiplyPositionMatrix((new Matrix4f()).scaling((float)size, (float)size, (float)(-size)));
        context.getMatrices().multiply(quaternionf);
        DiffuseLighting.method_34742();
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        if (quaternionf2 != null) {
            quaternionf2.conjugate();
            entityRenderDispatcher.setRotation(quaternionf2);
        }

        entityRenderDispatcher.setRenderShadows(false);
        RenderSystem.runAsFancy(() -> {
            entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, context.getMatrices(), context.getVertexConsumers(), 15728880);
        });
        context.draw();
        entityRenderDispatcher.setRenderShadows(true);
        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers);
    }


    public boolean startAnimation(DrawContext context){
        if (onStartAnimation){

            MatrixStack matrices = context.getMatrices();
            totalTickAnimationDelta += tickDelta;

            Matrix4f matrix = matrices.peek().getPositionMatrix();

            float yMovement = MathHelper.clamp(
                    MathHelper.cos( MathHelper.HALF_PI * (totalTickAnimationDelta/10f)) * (v + client.getWindow().getScaledHeight()),
                    0,
                    (client.getWindow().getScaledHeight())
            );

            if (yMovement == 0){
                onStartAnimation =false;
                totalTickAnimationDelta =0;
            }

            matrix.translate(0,yMovement,1f);

            context.setShaderColor(1.0f,1.0f,1.0f, 1.0f);
            context.drawTexture(EQUIPMENT_MENU,i,v,0,0,texture_width,texture_height,texture_width,texture_height);
            return true;
        }

        return false;
    }

    private boolean closeAnimation(DrawContext context) {
        if (onCloseAnimation){

            MatrixStack matrices = context.getMatrices();
            totalTickAnimationDelta += tickDelta;

            Matrix4f matrix = matrices.peek().getPositionMatrix();

            float yMovement = MathHelper.clamp(
                    MathHelper.sin( MathHelper.HALF_PI * (totalTickAnimationDelta/10f)) * (v + client.getWindow().getScaledHeight()),
                    0,
                    (client.getWindow().getScaledHeight())
            );

            if (yMovement == (client.getWindow().getScaledHeight()) || totalTickAnimationDelta >= 15){
                totalTickAnimationDelta = 0;
                client.setScreen(parent);
                return true;
            }

            matrix.translate(0,yMovement,1f);

            //int yAnimation = v + (int) (MathHelper.cos((MathHelper.HALF_PI * time) / maxTimeAnimationEntry) * height);
            context.setShaderColor(1.0f,1.0f,1.0f, 1.0f);
            context.drawTexture(EQUIPMENT_MENU,i,v,0,0,texture_width,texture_height,texture_width,texture_height);
            return true;
        }

        return false;
    }



}


