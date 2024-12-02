package net.rokiro.rokiromod.client.screen.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.hud.SpecialEquipmentHudOverlay;
import net.rokiro.rokiromod.client.screen.ArtifactsMenuScreen;
import net.rokiro.rokiromod.sound.ModSounds;
import net.rokiro.rokiromod.util.ArtifactItemStack;
import net.rokiro.rokiromod.util.TextGraphicUtils;

import java.awt.*;

public class EquipedSlotWidget extends ButtonWidget {

    public static final Identifier SLOT_HOVERED = new Identifier(RokirosMod.MOD_ID,"textures/hud/special_equipment_slot_active.png");

    Rectangle bounding_box;
    public ArtifactItemStack item;

    ArtifactsMenuScreen menuScreen;

    public EquipedSlotWidget(ArtifactsMenuScreen menuScreen,int x, int y, ButtonWidget.PressAction action) {
        super(x, y, (int)(24 * ArtifactsMenuScreen.scale), (int)(24 * ArtifactsMenuScreen.scale),
                ScreenTexts.EMPTY, action, DEFAULT_NARRATION_SUPPLIER);
        this.menuScreen = menuScreen;
        bounding_box = new Rectangle(
                getX() + ((int) (5 * ArtifactsMenuScreen.scale)),
                getY() + ((int) (5 * ArtifactsMenuScreen.scale)),
                width - ((int) (10 *ArtifactsMenuScreen.scale)),
                height - ((int) (10 *ArtifactsMenuScreen.scale)));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.visible) {
            this.hovered = mouseX >= this.bounding_box.x && mouseY >= this.bounding_box.y &&
                    mouseX < this.bounding_box.x + this.bounding_box.width &&
                    mouseY < this.bounding_box.y + this.bounding_box.height;
            this.renderButton(context, mouseX, mouseY, delta);
            //context.drawBorder(bounding_box.x,bounding_box.y, bounding_box.width, bounding_box.height, 0xFFFF0000);
        }
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        if (isHovered()){
            context.drawTexture(SLOT_HOVERED,getX(),getY(),0,0,width,height,width,height);
        } else {
            context.drawTexture(SpecialEquipmentHudOverlay.SLOT,getX(),getY(),0,0,width,height,width,height);
        }
        if (item!=null){

            MatrixStack itemMatrix = context.getMatrices();

            if (item.canStack()){

                itemMatrix.push();

                itemMatrix.translate(0f,0f,300f);

                TextGraphicUtils.drawItemQuantityText(context,(getX()+1+width/2),getY()+height,
                        TextGraphicUtils.OutlineTextAlignment.CENTER,1F,
                        item.getQuantity(), item.getMaxQuantity());



                itemMatrix.pop();
            }

            context.getMatrices().push();
            float itemX = getX() + width/2;
            float itemY = getY() + height/2;
            float itemScale = 1.5f;

            itemMatrix.scale(itemScale,itemScale,1f);
            itemMatrix.translate((-itemX + (itemX/itemScale)),-itemY + (itemY/itemScale),0f);

            context.drawItem(new ItemStack((Item)(item.getArtifactItem())), (int) ((itemX) - (width/2) / itemScale) +4 , (int) ((itemY) - (height/2) / itemScale) + 4);
            context.getMatrices().pop();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible) {
            if (this.isValidClickButton(button)) {
                boolean bl = this.clicked(mouseX, mouseY);
                if (bl) {
                    if (menuScreen.selectedItem != null){
                        if (alreadyEquippedArtifact(menuScreen.selectedItem)){
                            replaceEquippedArtifact(menuScreen.selectedItem);
                        }

                        this.item = menuScreen.selectedItem;
                        menuScreen.deselectSlot();

                    }
                    this.onClick(mouseX, mouseY);
                    this.playDownSound(MinecraftClient.getInstance().getSoundManager());
                    return true;
                }
            } else if (button == 1){
                boolean bl = this.clicked(mouseX, mouseY);
                if (bl) {
                    onRightClick();
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

    public void onRightClick(){
        if (this.item != null){
            this.item = null;
            playRightClickSound(MinecraftClient.getInstance().getSoundManager());
        }
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return this.active && this.visible &&
                mouseX >= (double)this.bounding_box.x &&
                mouseY >= (double)this.bounding_box.y &&
                mouseX < (double)(this.bounding_box.x + this.bounding_box.width) &&
                mouseY < (double)(this.bounding_box.y + this.bounding_box.height);
    }

    @Override
    public void playDownSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(ModSounds.SELECT_EQUIPMENT_MENU, 1.15F));
    }

    public void playRightClickSound(SoundManager soundManager) {
        soundManager.play(PositionedSoundInstance.master(ModSounds.GENERIC_ERROR, 1.25F));
    }

    public boolean alreadyEquippedArtifact(ArtifactItemStack equipedItem){
        for (ArtifactItemStack temp : menuScreen.equipedSlots.stream().map(equipedSlotWidget -> equipedSlotWidget.item).toList()){
            if (temp != null){
                if (temp.getArtifactItem().getArtifactId().equals(equipedItem.getArtifactItem().getArtifactId())) return true;
            }
        }
        return false;
    }

    public void replaceEquippedArtifact(ArtifactItemStack equipedItem){
        for (EquipedSlotWidget e : menuScreen.equipedSlots){
            if (e.item != null){
                if (e.item.getArtifactItem().getArtifactId().equals(equipedItem.getArtifactItem().getArtifactId())) {
                    e.item = null;
                }
            }
        }
    }

}
