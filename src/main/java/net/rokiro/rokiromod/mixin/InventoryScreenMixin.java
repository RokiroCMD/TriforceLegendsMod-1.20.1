package net.rokiro.rokiromod.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.RupeesHudOverlay;
import net.rokiro.rokiromod.client.SpecialEquipmentHudOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

    private static final Identifier SPECIAL_INVENTORY_ICON = new Identifier(RokirosMod.MOD_ID,"textures/hud/special_inventory_icon.png");

    @Inject(at = @At("TAIL"), method = "drawForeground")
    private void drawForeground(DrawContext context, int mouseX, int mouseY, CallbackInfo info) {
        context.drawTexture(SPECIAL_INVENTORY_ICON,8 *16,16*4 -4,0,0,20,20,20,20);
    }


}
