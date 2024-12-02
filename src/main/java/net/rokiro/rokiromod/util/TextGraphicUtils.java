package net.rokiro.rokiromod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class TextGraphicUtils {


    public static void drawOutilineText(DrawContext drawContext, int x, int y, Text text, int textColor, int outlineColor, OutlineTextAlignment textAlignment, float scale) {

        TextRenderer textRenderer =MinecraftClient.getInstance().textRenderer;

        MatrixStack matrices = drawContext.getMatrices();

        matrices.push();


        int xPos = x;
        int yPos = y - 3;

            matrices.scale(scale,scale,1f);
            switch (textAlignment){
                case CENTER -> {
                    matrices.translate(-xPos + ((xPos - (textRenderer.getWidth(text)/2f  *scale)) / scale), -yPos + (yPos/scale), 0f);
                }
                case RIGHT -> {
                    matrices.translate(-xPos + ((xPos - (textRenderer.getWidth(text)  *scale)) / scale), -yPos + (yPos/scale), 0f);
                }default -> {
                    matrices.translate(-xPos + (xPos/ scale) , -yPos + (yPos/scale), 0f);
                }
            }


        drawContext.drawText(textRenderer,text,xPos-1,yPos-1,outlineColor, false);
        drawContext.drawText(textRenderer,text,xPos+1,yPos+1,outlineColor, false);
        drawContext.drawText(textRenderer,text,xPos-1,yPos+1,outlineColor, false);
        drawContext.drawText(textRenderer,text,xPos+1,yPos-1,outlineColor, false);
        drawContext.drawText(textRenderer,text,xPos+1,yPos,outlineColor, false);
        drawContext.drawText(textRenderer,text,xPos-1,yPos,outlineColor, false);
        drawContext.drawText(textRenderer,text,xPos,yPos+1,outlineColor, false);
        drawContext.drawText(textRenderer,text,xPos,yPos-1,outlineColor, false);

        drawContext.drawText(textRenderer,text,xPos,yPos,textColor, false);

        matrices.pop();
    }


    public static void drawOutilineText(DrawContext drawContext, int x, int y, Text text, int textColor, int outlineColor) {
        drawOutilineText(drawContext, x,y,text,textColor,outlineColor,OutlineTextAlignment.LEFT);
    }

    public static void drawOutilineText(DrawContext drawContext, int x, int y, Text text, int textColor, int outlineColor, OutlineTextAlignment textAlignment) {
        drawOutilineText(drawContext, x,y,text,textColor,outlineColor,textAlignment,1f);
    }

    public static void drawOutilineText(DrawContext drawContext, int x, int y, Text text, int textColor, int outlineColor, float scale) {
        drawOutilineText(drawContext, x,y,text,textColor,outlineColor,OutlineTextAlignment.LEFT, scale);
    }

    public static void drawItemQuantityText(DrawContext drawContext, int x, int y, OutlineTextAlignment textAlignment, float scale, int quantity, int maxCount){
        drawOutilineText(drawContext,x,y, Text.literal(quantity < 10 ?
                "0"+ quantity :
                ""+quantity),
                getColorItemQuantity(quantity,maxCount),
                getOutlineColorItemQuantity(quantity,maxCount),
                textAlignment,scale);

    }

    private static int getColorItemQuantity(int quantity, int maxCount){
        if (quantity >= maxCount){
            return 0xFF15d128;
        } else {
            if ( quantity > 0){
                return 0xFFFFFFFF;
            } else {
                return 0xFFd11d32;
            }
        }
    }

    private static int getOutlineColorItemQuantity(int quantity, int maxCount){
        if (quantity >= maxCount){
            return 0xFF074222;
        } else {
            if ( quantity > 0){
                return 0xFF000000;
            } else {
                return 0xFF2e0202;
            }
        }
    }


    public enum OutlineTextAlignment{
        LEFT,
        CENTER,
        RIGHT
    }

}
