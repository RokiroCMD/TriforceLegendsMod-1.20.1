package net.rokiro.rokiromod.client.screen.widgets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.sound.ModSounds;

@Environment(EnvType.CLIENT)
public class TurnPageWidget extends ButtonWidget {

    public static final Identifier RIGHT_BUTTON = new Identifier(RokirosMod.MOD_ID, "textures/gui/right_equipment_button.png");
    public static final Identifier RIGHT_BUTTON_HOVER = new Identifier(RokirosMod.MOD_ID, "textures/gui/right_equipment_button_hover.png");
    public static final Identifier LEFT_BUTTON = new Identifier(RokirosMod.MOD_ID, "textures/gui/left_equipment_button.png");
    public static final Identifier LEFT_BUTTON_HOVER = new Identifier(RokirosMod.MOD_ID, "textures/gui/left_equipment_button_hover.png");
    EquipmentButtonType type;

    public enum EquipmentButtonType {
        RIGHT,
        LEFT
    }

    boolean hasMouseEntered = false;

    MinecraftClient client;

    public TurnPageWidget(int x, int y, PressAction action, EquipmentButtonType type, MinecraftClient client) {
        super(x, y, 32, 32, ScreenTexts.EMPTY, action, DEFAULT_NARRATION_SUPPLIER);
        this.type = type;
        this.client = client;

    }

    @Override
    public void onPress() {
        super.onPress();
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
    public void playDownSound(SoundManager soundManager) {
        if (type == EquipmentButtonType.LEFT) {
            soundManager.play(PositionedSoundInstance.master(ModSounds.TURN_LEFT_EQUIPMENT_MENU, 1.0F));
        } else {
            soundManager.play(PositionedSoundInstance.master(ModSounds.TURN_RIGHT_EQUIPMENT_MENU, 1.0F));
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderButton(context, mouseX, mouseY, delta);
    }

    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        if (type == EquipmentButtonType.LEFT) {
            if (isMouseOver(mouseX, mouseY)) {
                context.drawTexture(LEFT_BUTTON_HOVER, this.getX(), this.getY(), 0, 0, 32, 32, 32, 32);
                if (!hasMouseEntered) {
                    client.player.playSound(ModSounds.CURSOR_EQUIPMENT_MENU, SoundCategory.MASTER, 5f, 1f);
                    hasMouseEntered = true;
                }
            } else {
                if (hasMouseEntered) {
                    hasMouseEntered = false;
                }
                context.drawTexture(LEFT_BUTTON, this.getX(), this.getY(), 0, 0, 32, 32, 32, 32);
            }
        } else {
            if (isMouseOver(mouseX, mouseY)) {
                if (!hasMouseEntered) {
                    client.player.playSound(ModSounds.CURSOR_EQUIPMENT_MENU, SoundCategory.MASTER, 5f, 1f);
                    hasMouseEntered = true;
                }
                context.drawTexture(RIGHT_BUTTON_HOVER, this.getX(), this.getY(), 0, 0, 32, 32, 32, 32);
            } else {
                if (hasMouseEntered) {
                    hasMouseEntered = false;
                }
                context.drawTexture(RIGHT_BUTTON, this.getX(), this.getY(), 0, 0, 32, 32, 32, 32);
            }
        }
    }
}
