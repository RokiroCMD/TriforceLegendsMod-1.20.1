package net.rokiro.rokiromod.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

    @Inject(at = @At("TAIL"), method = "drawForeground")
    private void drawForeground(DrawContext context, int mouseX, int mouseY, CallbackInfo info) {
    }


}
