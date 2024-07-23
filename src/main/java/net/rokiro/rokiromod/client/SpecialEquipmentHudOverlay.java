package net.rokiro.rokiromod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.block.ModBlocks;
import net.rokiro.rokiromod.item.ModItems;
import software.bernie.geckolib.util.ClientUtils;

public class SpecialEquipmentHudOverlay implements HudRenderCallback {



    public static final Identifier SLOT = new Identifier(RokirosMod.MOD_ID,"textures/hud/special_equipment_slot.png");
    //public static final Identifier SLOT_BACK = new Identifier(RokirosMod.MOD_ID,"textures/hud/special_equipment_slot_back.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;

        MinecraftClient client = MinecraftClient.getInstance();


        if (client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = 0;
            y = 0;

            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            for (int i = 0 ; i < 4 ; i++){
                x = (i * 12) + 4;
                y = 4;

                if (i >= 2 && i <=3){

                    x -= 12;

                    if (i == 2){
                        y += 24;
                    }
                }

                if (i == 0 || i == 3){
                    y = 12 + 4;
                }

                if (i == 0){
                    x -= 2;
                } else if (i == 1){
                    y -= 2;
                } else if (i == 2){
                    y += 2;
                } else if (i == 3){
                    x += 2;
                }

                drawContext.setShaderColor(1f,1f,1f,1f);
                drawContext.drawTexture(SLOT,x,y,0,0,24,24,24,24);
                if (i ==0){
                drawContext.drawItem(new ItemStack(ModItems.TRIFORCE,1),x+4,y+4);
                } else if (i==1){
                    drawContext.drawItem(new ItemStack(ModItems.MAJORAS_MASK,1),x+4,y+4);
                } else if (i==2){
                    drawContext.drawItem(new ItemStack(ModItems.HYLIAN_METAL_DETECTOR,1),x+4,y+4);
                }else if (i==3){
                    drawContext.drawItem(new ItemStack(ModBlocks.DUNGEON_WOOD_LOCK,1),x+4,y+4);
                }
            }
        }
    }
}
