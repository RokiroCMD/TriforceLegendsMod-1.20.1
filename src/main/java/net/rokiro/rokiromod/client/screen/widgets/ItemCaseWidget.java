package net.rokiro.rokiromod.client.screen.widgets;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.screen.ArtifactsMenuScreen;
import net.rokiro.rokiromod.sound.ModSounds;
import net.rokiro.rokiromod.util.ArtifactItemStack;
import net.rokiro.rokiromod.util.TextGraphicUtils;

@Environment(EnvType.CLIENT)
public class ItemCaseWidget extends ButtonWidget {

    public static final Identifier ITEM_CASE = new Identifier(RokirosMod.MOD_ID,"textures/gui/item_case_artifacts_menu.png");
    public static final Identifier SELECTED_CASE = new Identifier(RokirosMod.MOD_ID,"textures/gui/selected_case_menu.png");
    public static final Identifier MARKED_CASE = new Identifier(RokirosMod.MOD_ID,"textures/gui/marked_case_menu.png");

    private ArtifactItemStack item;
    boolean hasMouseEntered = false;
    boolean hasMoseLeft = false;
    private MinecraftClient client;
    public boolean isMarked = false;
    ArtifactsMenuScreen menuScreen;

    public ItemCaseWidget(ArtifactsMenuScreen menuScreen,int x, int y, ButtonWidget.PressAction action) {
        super(x, y, (int)(24* ArtifactsMenuScreen.scale), (int)(24*ArtifactsMenuScreen.scale), ScreenTexts.EMPTY, action, DEFAULT_NARRATION_SUPPLIER);
        this.menuScreen = menuScreen;
        this.client = MinecraftClient.getInstance();
    }

    public ItemCaseWidget(ArtifactsMenuScreen menuScreen,int x, int y, ButtonWidget.PressAction action, ArtifactItemStack item) {
        super(x, y, (int)(24*ArtifactsMenuScreen.scale), (int)(24*ArtifactsMenuScreen.scale), ScreenTexts.EMPTY, action, DEFAULT_NARRATION_SUPPLIER);
        this.menuScreen = menuScreen;
        this.item = item;
        this.client = MinecraftClient.getInstance();
    }
    public ItemCaseWidget(ArtifactsMenuScreen menuScreen,int x, int y, ButtonWidget.PressAction action, ArtifactItemStack item, int count) {
        super(x, y, (int)(24*ArtifactsMenuScreen.scale),  (int)(24*ArtifactsMenuScreen.scale), ScreenTexts.EMPTY, action, DEFAULT_NARRATION_SUPPLIER);
        this.menuScreen = menuScreen;
        this.item = item;
        this.client = MinecraftClient.getInstance();
    }


    public void setItem(ArtifactItemStack itemStack) {
        this.item = itemStack;
    }

    public ArtifactItemStack getItem() {
        return item;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible) {
            if (this.isValidClickButton(button)) {
                boolean bl = this.clicked(mouseX, mouseY);
                if (bl) {
                    this.onClick(mouseX, mouseY);
                    if (item!=null){
                        menuScreen.selectSlot(this);
                    } else {
                        menuScreen.deselectSlot();
                    }
                    this.playDownSound(MinecraftClient.getInstance().getSoundManager());
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        if (item!=null){
            soundManager.play(PositionedSoundInstance.master(ModSounds.SELECT_EQUIPMENT_MENU, 1.0F));
        } else {
            soundManager.play(PositionedSoundInstance.master(ModSounds.GENERIC_ERROR, 1.0F));
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context,mouseX,mouseY,delta);
    }



    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTexture(ITEM_CASE, this.getX(), this.getY(), 0, 0, getWidth(), getHeight(),getWidth(),getHeight());
        if (item!=null){

            MatrixStack itemMatrix = context.getMatrices();


            if ((item.canStack())){

                itemMatrix.push();

                itemMatrix.translate(0f,0f,300f);

                TextGraphicUtils.drawItemQuantityText(context,getX()+width-1,getY()+height-3,
                        TextGraphicUtils.OutlineTextAlignment.RIGHT,1f,
                        item.getQuantity(), item.getMaxQuantity());


                itemMatrix.pop();
            }

            itemMatrix.push();

            if (isMouseOver(mouseX,mouseY)){
                itemMatrix.scale(2.25f,2.25f,1f);
                itemMatrix.translate(-getX()  + (getX()/2.25),-getY() + (getY()/2.25) -1,0f);
                context.drawItem(item.asItemStack(),getX(),getY());
            }else {
                itemMatrix.scale(2f,2f,1f);
                itemMatrix.translate((-getX())/2,-getY()/2,0f);
                context.drawItem(item.asItemStack(),getX()+1,getY());
            }

            itemMatrix.pop();
            if (isMarked){
                MatrixStack matrices = context.getMatrices();

                matrices.push();

                matrices.translate(-6f,-6f,0f);

                context.drawTexture(MARKED_CASE, this.getX(), this.getY(), 0, 0,
                        (int)(32 * ArtifactsMenuScreen.scale),
                        (int)(32 * ArtifactsMenuScreen.scale),
                        (int)(32 * ArtifactsMenuScreen.scale),
                        (int)(32 * ArtifactsMenuScreen.scale));

                matrices.pop();
            }

        }

        if (isMouseOver(mouseX,mouseY)){
            if (!hasMouseEntered){
                client.player.playSound(ModSounds.CURSOR_EQUIPMENT_MENU, SoundCategory.MASTER,5f, 1f);
                if (item !=null){
                    menuScreen.hovereddItem = item;
                } else {
                    menuScreen.hovereddItem = null;
                }
                hasMouseEntered=true;
            }

            if (!isMarked){
                MatrixStack matrices = context.getMatrices();

                matrices.push();

                matrices.translate(-6f,-6f,300f);

                context.drawTexture(SELECTED_CASE, this.getX(), this.getY(), 0, 0,
                        (int)(32 * ArtifactsMenuScreen.scale),
                        (int)(32 * ArtifactsMenuScreen.scale),
                        (int)(32 * ArtifactsMenuScreen.scale),
                        (int)(32 * ArtifactsMenuScreen.scale));

                matrices.pop();
            }

        }else {
            if (hasMouseEntered){
                hasMouseEntered = false;

            }
        }
    }


}