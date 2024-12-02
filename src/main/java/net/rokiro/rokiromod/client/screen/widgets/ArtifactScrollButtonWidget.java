package net.rokiro.rokiromod.client.screen.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.screen.ArtifactsMenuScreen;
import net.rokiro.rokiromod.sound.ModSounds;

public class ArtifactScrollButtonWidget extends ButtonWidget {

    public  static int texture_width = 107;
    public  static int texture_height = 18;
    int x;
    int y;
    MinecraftClient client;

    ScrollButtonType type;

    public ArtifactScrollButtonWidget(int x, int y, ButtonWidget.PressAction action, ScrollButtonType type) {
        super(x, y, (int)(texture_width * ArtifactsMenuScreen.scale), (int)(texture_height * ArtifactsMenuScreen.scale),
                ScreenTexts.EMPTY, action, DEFAULT_NARRATION_SUPPLIER);
        this.x = x;
        this.y = y;
        client = MinecraftClient.getInstance();
        this.type = type;

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.visible) {
            this.hovered = mouseX >= x && mouseY >= y &&
                    mouseX < x + width &&
                    mouseY < y + height;
            renderButton(context,mouseX,mouseY,delta);
        }
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        if (hovered){
            context.drawTexture(type.HOVERED_TEXTURE,x,y,0,0,width,height,width,height);
        } else{
            context.drawTexture(type.TEXTURE,x,y,0,0,width,height,width,height);
        }
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible) {
            if (this.isValidClickButton(button)) {
                boolean bl = this.clicked(mouseX, mouseY);
                if (bl) {
                    this.onClick(mouseX, mouseY);
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
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return this.active && this.visible &&
                mouseX >= (double)x &&
                mouseY >= (double)y &&
                mouseX < (double)(x + width) &&
                mouseY < (double)(y + height);
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(ModSounds.SELECT_EQUIPMENT_MENU, 0.95F));
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

    public enum ScrollButtonType{
        UP( new Identifier(RokirosMod.MOD_ID,"textures/gui/go_up_button.png"),  new Identifier(RokirosMod.MOD_ID,"textures/gui/go_up_button_hover.png")),
        DOWN(new Identifier(RokirosMod.MOD_ID,"textures/gui/go_down_button.png"),new Identifier(RokirosMod.MOD_ID,"textures/gui/go_down_button_hover.png"));

        Identifier TEXTURE;
        Identifier HOVERED_TEXTURE;

        ScrollButtonType(Identifier TEXTURE, Identifier HOVERED_TEXTURE) {
            this.TEXTURE = TEXTURE;
            this.HOVERED_TEXTURE = HOVERED_TEXTURE;
        }
    }

}
