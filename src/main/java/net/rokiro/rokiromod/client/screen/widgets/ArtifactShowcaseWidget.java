package net.rokiro.rokiromod.client.screen.widgets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.screen.ArtifactsMenuScreen;
import net.rokiro.rokiromod.util.ArtifactItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class ArtifactShowcaseWidget implements Drawable{

    ArtifactItemStack item;
    public static final Identifier SHOWCASE_BG = new Identifier(RokirosMod.MOD_ID,"textures/gui/artifact_showcase_bg.png");
    public  static int texture_width = 56;
    public  static int texture_height = 56;
    int x;
    int y;

    int width;
    int height;

    MinecraftClient client;
    ArtifactsMenuScreen menuScreen;

    float totalTickDelta = 0;


    public ArtifactShowcaseWidget(ArtifactsMenuScreen menuScreen,int x, int y) {
        this.menuScreen = menuScreen;
        setX(x);
        setY(y);
        client = MinecraftClient.getInstance();
        width = (int)(texture_width * ArtifactsMenuScreen.scale);
        height = (int)(texture_height * ArtifactsMenuScreen.scale);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        context.drawTexture(SHOWCASE_BG,getX(),getY(),0,0,
                width,
                height,
                width,
                height);


        if (menuScreen.hovereddItem != null){
            item = menuScreen.hovereddItem;
            context.getMatrices().push();

            context.getMatrices().scale(8f,8f,1f);
            context.getMatrices().translate(-(x + width/2) + ((x + width/2)/8), -(y + height/2) + ((y + height/2)/8),0);

            totalTickDelta += delta;

            drawItem(client.player, client.player.getWorld(), item.asItemStack(),x + width/2,y + height/2, 1, 50, context);
            context.getMatrices().pop();
        }
    }

    private void drawItem(@Nullable LivingEntity entity, @Nullable World world, ItemStack stack, int x, int y, int seed, int z, DrawContext context) {
        if (!stack.isEmpty()) {

            MatrixStack matrices = context.getMatrices();

            BakedModel bakedModel = this.client.getItemRenderer().getModel(stack, world, entity, seed);

            matrices.push();

            matrices.translate((float)(x +0.5), (float)(y+3), (float)(150 + (bakedModel.hasDepth() ? z : 0)));
            //matrices.translate(0+4,0+6, (float)(150 + (bakedModel.hasDepth() ? z : 0)));


            try {
                matrices.multiplyPositionMatrix((new Matrix4f()).scaling(1.0F, -1.0F, 1.0F));
                matrices.scale(16.0F, 16.0F, 16.0F);
                boolean bl = !bakedModel.isSideLit();
                if (bl) {
                    DiffuseLighting.disableGuiDepthLighting();
                }


                float rotationAmount = (float) (totalTickDelta / 25F % 360);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotation(rotationAmount));

                this.client.getItemRenderer().renderItem(stack, ModelTransformationMode.GROUND, false, matrices, context.getVertexConsumers(), 15728880, OverlayTexture.DEFAULT_UV, bakedModel);
                context.draw();
                if (bl) {
                    DiffuseLighting.enableGuiDepthLighting();
                }
            } catch (Throwable var12) {
                CrashReport crashReport = CrashReport.create(var12, "Rendering item");
                CrashReportSection crashReportSection = crashReport.addElement("Item being rendered");
                crashReportSection.add("Item Type", () -> {
                    return String.valueOf(stack.getItem());
                });
                crashReportSection.add("Item Damage", () -> {
                    return String.valueOf(stack.getDamage());
                });
                crashReportSection.add("Item NBT", () -> {
                    return String.valueOf(stack.getNbt());
                });
                crashReportSection.add("Item Foil", () -> {
                    return String.valueOf(stack.hasGlint());
                });
                throw new CrashException(crashReport);
            }

            matrices.pop();
        }
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
}
