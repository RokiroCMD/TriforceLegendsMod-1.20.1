package net.rokiro.rokiromod.client.screen.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.screen.ArtifactsMenuScreen;
import net.rokiro.rokiromod.util.ArtifactItem;
import net.rokiro.rokiromod.util.TextGraphicUtils;

import java.util.ArrayList;
import java.util.List;

public class ArtifactDescriptorWidget  implements Drawable {

    public static final Identifier DESCRIPTOR_BG = new Identifier(RokirosMod.MOD_ID,"textures/gui/artifact_description_bg.png");
    public  static int texture_width = 77;
    public  static int texture_height = 42;
    int x;
    int y;

    int width;
    int height;

    MinecraftClient client;

    int xCenter;

    TextRenderer textRenderer;

    float totalTickDelta = 0;

    ArtifactsMenuScreen menuScreen;
    String title;
    int titleWidth;
    float descriptionScale = 0.58f;

    List<String> description = new ArrayList<>();
    boolean lineSkipped = false;

    public ArtifactDescriptorWidget(ArtifactsMenuScreen menuScreen,int x, int y) {
        this.menuScreen = menuScreen;
        setX(x);
        setY(y);
        client = MinecraftClient.getInstance();
        width = (int)(texture_width * ArtifactsMenuScreen.scale);
        height = (int)(texture_height * ArtifactsMenuScreen.scale);
        xCenter = getX() + (width/2);
        textRenderer = client.textRenderer;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {



        context.drawTexture(DESCRIPTOR_BG, x, y, 0,0,width,height,width,height);

        if (menuScreen.hovereddItem != null){


            totalTickDelta += delta;
            drawTitleArtifact(context);
            drawDescriptionArtifact(context);
        }
    }

    private void drawTitleArtifact(DrawContext context){

        title = menuScreen.hovereddItem.asItemStack().getName().getString();
        titleWidth = textRenderer.getWidth(title);
        int titleColor = (menuScreen.hovereddItem.getArtifactItem() instanceof ArtifactItem) ? ((ArtifactItem) menuScreen.hovereddItem.getArtifactItem()).getColorTitle() : 0xFFFFFFFF;
        int titleOutlineColor = (menuScreen.hovereddItem.getArtifactItem() instanceof ArtifactItem) ? ((ArtifactItem) menuScreen.hovereddItem.getArtifactItem()).getOutlineColorTitle() : 0xFF000000;

        TextGraphicUtils.drawOutilineText(context,xCenter,getY()+10, Text.literal(title), titleColor,titleOutlineColor, TextGraphicUtils.OutlineTextAlignment.CENTER,1f);

        //context.drawText(textRenderer, title, - titleWidth/2, getY() + 10,titleColor,false);



    }



    private void drawDescriptionArtifact(DrawContext context){

        generateArtifactTooltip();

        String tempDescription;

        context.getMatrices().push();
        context.getMatrices().scale(descriptionScale,descriptionScale,1);
        context.getMatrices().translate(-xCenter + (xCenter / descriptionScale) + 3,-getY() + (getY()/descriptionScale),1);

        for (int i = 0 ; i < description.size(); i++){
            tempDescription = description.get(i);
            int tempWidth = textRenderer.getWidth(tempDescription);
            context.drawText(textRenderer,tempDescription, (xCenter - (tempWidth/2)),(getY()+38)+(i * 10),0xFF7c838a, false);
        }

        context.getMatrices().pop();
    }

    void generateArtifactTooltip(){
        description = menuScreen.hovereddItem.asItemStack().getTooltip(null, TooltipContext.BASIC).stream()
                .filter(text -> {
                    if (!text.getString().equals(title)){

                        if (lineSkipped){
                            return false;
                        }

                        if (text.getString().isEmpty()){
                            this.lineSkipped = true;
                            return false;
                        }

                        return true;
                    }
                    return false;
                })
                .map(text -> text.getString()).toList();

        lineSkipped = false;
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
