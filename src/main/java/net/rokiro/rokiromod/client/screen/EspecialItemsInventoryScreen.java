package net.rokiro.rokiromod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.item.ModItems;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;


@Environment(EnvType.CLIENT)
public class EspecialItemsInventoryScreen extends Screen {

    private final Screen parent;
    boolean onAnimation = true;
    public static final Identifier EQUIPMENT_INVENTORY = new Identifier(RokirosMod.MOD_ID,"textures/gui/equipment_inventory.png");
    private static final int texture_width = 384;
    private static final int texture_height = 288;

    private TurnPageEquipmentWidget RIGHT_BUTTON_WIDGET;
    private TurnPageEquipmentWidget LEFT_BUTTON_WIDGET;
    int i = 0;
    int v = 0;

    int time = 0;
    int maxTimeAnimationEntry = 20;

    public EspecialItemsInventoryScreen(Screen parent) {
        super(Text.literal("EQUIPMENT_INVENTORY"));
        this.parent = parent;
        onAnimation = true;
    }

    @Override
    public void tick() {
        if (onAnimation){
            time++;
            if (time > maxTimeAnimationEntry){
                time = 0;
                onAnimation = false;
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        i = (this.width - texture_width) / 2;
        v = (this.height - texture_height) / 2;
        renderBackground(context);
        renderForeground(context, mouseX, mouseY,delta);
    }


    public void renderForeground(DrawContext context, int mouseX, int mouseY, float delta){

        if (!onAnimation){
            drawEntity(context, i + 140, v+220, 65, (float)(i + 140) - mouseX, (float)(v + 100) - mouseY, this.client.player);
            RIGHT_BUTTON_WIDGET.render(context,mouseX,mouseY,delta);
            LEFT_BUTTON_WIDGET.render(context, mouseX, mouseY, delta);
            drawRupeeBagIcon(context,mouseX,mouseY,delta);
        }
    }

    private void drawRupeeBagIcon(DrawContext context, int mouseX, int mouseY, float delta){

        //context.drawItem(new ItemStack(ModItems.MEDIUM_RUPEE_BAG),i,v);
        //context.drawTexture(new Identifier(RokirosMod.MOD_ID,"textures/item/medium_rupee_bag.png"),i+18,v+28,0,0,32,32,32,32);
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
    public void renderBackground(DrawContext context) {
        if (this.client.world != null) {

            int i = (this.width - texture_width) / 2;
            int v = (this.height - texture_height) / 2;
            context.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);

            context.getMatrices().push();
            RenderSystem.enableBlend();

            if (onAnimation){

                int yAnimation = v + (int) (MathHelper.cos((MathHelper.HALF_PI * time) / maxTimeAnimationEntry) * height);

                context.setShaderColor(1.0f,1.0f,1.0f, 1.0f);
                context.drawTexture(EQUIPMENT_INVENTORY,i,yAnimation,0,0,texture_width,texture_height,texture_width,texture_height);

            } else {
                context.drawTexture(EQUIPMENT_INVENTORY,i,v,0,0,texture_width,texture_height,texture_width,texture_height);

                String rawText = "Equipment";

                int textX = i + texture_width/2 - textRenderer.getWidth(rawText)/2;
                int textY = v + 10;
                context.drawText(textRenderer,Text.literal(rawText),textX,textY, Colors.BLACK,false);
            }

            context.getMatrices().pop();
        } else {
            this.renderBackgroundTexture(context);
        }
        context.setShaderColor(1.0f,1.0f,1.0f, 1.0f);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers);
    }



    @Override
    protected void init() {
        i = (this.width - texture_width) / 2;
        v = (this.height - texture_height) / 2;
        RIGHT_BUTTON_WIDGET = addDrawableChild(new TurnPageEquipmentWidget(i+texture_width - 8,v+(texture_height/2) - 16,(button -> {

        }), TurnPageEquipmentWidget.EquipmentButtonType.RIGHT));


        LEFT_BUTTON_WIDGET = addDrawableChild(new TurnPageEquipmentWidget(i - 22,v+(texture_height/2) - 16,(button -> {

        }), TurnPageEquipmentWidget.EquipmentButtonType.LEFT));
    }

    @Override
    public void close() {
        client.setScreen(parent);
    }



    @Environment(EnvType.CLIENT)
    public class TurnPageEquipmentWidget extends ButtonWidget {

        public static final Identifier RIGHT_BUTTON = new Identifier(RokirosMod.MOD_ID,"textures/gui/right_equipment_button.png");
        public static final Identifier RIGHT_BUTTON_HOVER = new Identifier(RokirosMod.MOD_ID,"textures/gui/right_equipment_button_hover.png");
        public static final Identifier LEFT_BUTTON = new Identifier(RokirosMod.MOD_ID,"textures/gui/left_equipment_button.png");
        public static final Identifier LEFT_BUTTON_HOVER = new Identifier(RokirosMod.MOD_ID,"textures/gui/left_equipment_button_hover.png");
        EquipmentButtonType type;
        enum EquipmentButtonType{
            RIGHT,
            LEFT
        }

        public TurnPageEquipmentWidget(int x, int y, ButtonWidget.PressAction action, EquipmentButtonType type) {
            super(x, y, 32, 32, ScreenTexts.EMPTY, action, DEFAULT_NARRATION_SUPPLIER);
            this.type = type;

        }

        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            renderButton(context,mouseX,mouseY,delta);
        }

        public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
            if (type == EquipmentButtonType.LEFT){
                if (isMouseOver(mouseX,mouseY)){
                    context.drawTexture(LEFT_BUTTON_HOVER, this.getX(), this.getY(), 0, 0, 32, 32,32,32);
                } else {
                    context.drawTexture(LEFT_BUTTON, this.getX(), this.getY(), 0, 0, 32, 32,32,32);
                }
            } else{
                if (isMouseOver(mouseX,mouseY)){
                    context.drawTexture(RIGHT_BUTTON_HOVER, this.getX(), this.getY(), 0, 0, 32, 32,32,32);
                }else {
                    context.drawTexture(RIGHT_BUTTON, this.getX(), this.getY(), 0, 0, 32, 32,32,32);
                }
            }
        }
    }
}


