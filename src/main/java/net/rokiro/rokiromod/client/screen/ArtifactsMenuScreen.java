package net.rokiro.rokiromod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.screen.widgets.*;
import net.rokiro.rokiromod.item.ModItems;
import net.rokiro.rokiromod.sound.ModSounds;
import net.rokiro.rokiromod.util.*;
import org.joml.Matrix4f;
import software.bernie.geckolib.util.ClientUtils;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ArtifactsMenuScreen extends Screen {

    private final Screen parent;

    public static final Identifier ARTIFACTS_MENU = new Identifier(RokirosMod.MOD_ID,"textures/gui/artifacts_inventory.png");
    public static final int original_width = 256;
    public static final int original_height = 192;
    public static int texture_width = 256;
    public static int texture_height = 192;
    int i = 0;
    int v = 0;
    public static float scale = 1.5f;

    private TurnPageWidget RIGHT_BUTTON_WIDGET;
    private TurnPageWidget LEFT_BUTTON_WIDGET;
    private ArtifactShowcaseWidget ARTIFACT_SHOWCASER;
    private ArtifactDescriptorWidget ARTIFACT_DESCRIPTOR;

    private ArtifactScrollButtonWidget ARTIFACT_UP_SCROLL_BUTTON;
    private ArtifactScrollButtonWidget ARTIFACT_DOWN_SCROLL_BUTTON;
    List<ItemCaseWidget> itemCases;
    public List<EquipedSlotWidget> equipedSlots;

    public ArtifactItemStack hovereddItem;
    public ArtifactItemStack selectedItem;
    public ItemCaseWidget selectedCase;

    float totalTickAnimationDelta = 0;
    float tickDelta = 0;
    boolean onCloseAnimation = false;
    public ArtifactsMenuScreen(Screen parent) {
        super(Text.literal("ARTIFACTS_MENU"));
        this.parent = parent;
    }

    private void initCases(){

        itemCases = new ArrayList<>();


        for (int n = 0; n < 5 ; n++){
            for (int m = 0 ; m < 4; m++){
                ItemCaseWidget tempWidget = new ItemCaseWidget(this,
                        (i + ((int)(116 * scale))) + (m * ((int)(27 * scale))),
                        (v + ((int)(37 * scale))) + (n * ((int)(27 * scale))),
                        button -> {});
                addDrawableChild(tempWidget);
                itemCases.add(tempWidget);
            }
        }
        LoadArtifactsInventory();

    }
    private void LoadArtifactsInventory(){
        for (int i = 0; i < ModItems.ARITFACTS_ITEMS.size(); i++){
            ArtifactItemStack tmpArtifact =  new ArtifactItemStack( (ArtifactItem) (ModItems.ARITFACTS_ITEMS.get(i)));
            tmpArtifact.setQuantity(!tmpArtifact.canStack() ? 1 :
                    ClientUtils.getClientPlayer().getRandom().nextBetween(0, tmpArtifact.getMaxQuantity()));
            if (i < itemCases.size()) {
                itemCases.get(i).setItem(tmpArtifact);
            } else {
                break;
            }
        }
    }
    private void initEquipedSlots(){
        equipedSlots = new ArrayList<>();

        equipedSlots.add( addDrawableChild(new EquipedSlotWidget(this,i+((int)(35 * scale)),v+((int)(140 * scale)), slot ->{
        })) );
        equipedSlots.add(addDrawableChild( new EquipedSlotWidget(this,i+((int)((36 + 13) * scale)) ,v+((int)((140 - 14) * scale)), slot ->{
        })));
        equipedSlots.add(addDrawableChild( new EquipedSlotWidget(this,i+((int)((36 + 13) * scale)) ,v+((int)((140 + 14) * scale)), slot ->{
        })));
        equipedSlots.add(addDrawableChild( new EquipedSlotWidget(this,i+((int)((36 + 24 + 3) * scale)) ,v+((int)(140 * scale)), slot ->{
        })));

        ArtifactsInventoryPlayer artifactEquipmentPlayer = new ArtifactsInventoryPlayer(client.player);

        int index = 0;
        for (ArtifactItemStack artifactItemStack : artifactEquipmentPlayer.getArtifactItems()){
            if (artifactItemStack != null){
                equipedSlots.get(index).item = artifactItemStack;
            } else {
                equipedSlots.get(index).item = null;
            }
            index++;
        }

    }
    private void initPageButtons(){
        RIGHT_BUTTON_WIDGET = addDrawableChild(new TurnPageWidget(i+texture_width - 8,v+(texture_height/2) - 16,(button -> {
        }), TurnPageWidget.EquipmentButtonType.RIGHT,client));


        LEFT_BUTTON_WIDGET = addDrawableChild(new TurnPageWidget(i - 22,v+(texture_height/2) - 16,(button -> {
            saveArtifactEquipment();
            client.setScreen(new EquipmentMenuScreen(parent,false));
        }), TurnPageWidget.EquipmentButtonType.LEFT,client));
    }
    private void initDescriptor(){
        ARTIFACT_DESCRIPTOR = new ArtifactDescriptorWidget(this, i+((int) (23 * scale)),v+((int) (82 *scale)));
        addDrawable(ARTIFACT_DESCRIPTOR);
    }
    private void initScrollButtons(){

        ARTIFACT_DOWN_SCROLL_BUTTON = new ArtifactScrollButtonWidget(i+((int)(115 * scale)),v+((int)(153 * scale)), button -> {
        }, ArtifactScrollButtonWidget.ScrollButtonType.DOWN);

        addDrawableChild(ARTIFACT_DOWN_SCROLL_BUTTON);
    }
    private void initShowcaser(){
        ARTIFACT_SHOWCASER = addDrawable(new ArtifactShowcaseWidget(this,
                i + (int)(34 * scale),
                v + (int)(25 * scale)));
    }
    @Override
    protected void init() {
        super.init();
        texture_width = (int) (original_width * scale);
        texture_height = (int) (original_height * scale);

        i = (this.width - texture_width) / 2;
        v = (this.height - texture_height) / 2;

        initCases();
        initEquipedSlots();
        initPageButtons();
        initShowcaser();
        initDescriptor();
        initScrollButtons();
        ARTIFACT_DOWN_SCROLL_BUTTON.active = false;

    }
/*
    private  void addItemAndCountById(String id, int index){
        String input = id;

        Pattern pattern = Pattern.compile("\\{count:(\\d+)\\}");
        Matcher matcher = pattern.matcher(input);

        Integer countValue = null;
        if (matcher.find()) {
            countValue = Integer.parseInt(matcher.group(1));
        }

        String result = input.replaceAll("\\{count:\\d+\\}", "");

        Item item = Registries.ITEM.get(new Identifier(result));

        if (item!= null){
            equipedSlots.get(index).item =new ItemStack(item, countValue != null ? countValue:1);
        }
    }*/

    @Override
    public void close() {
        if (!onCloseAnimation){
            onCloseAnimation = true;
            disableAll();
            saveArtifactEquipment();
            client.player.playSound(ModSounds.CLOSE_EQUIPMENT_MENU, SoundCategory.MASTER,5f,1f);
            //client.setScreen(parent);
        }
    }

    private void disableAll(){
        RIGHT_BUTTON_WIDGET.active = false;
        LEFT_BUTTON_WIDGET.active = false;

        for (EquipedSlotWidget e : equipedSlots){
            e.active = false;
        }

        for (ItemCaseWidget caseWidget :itemCases){
            caseWidget.active = false;
        }

        ARTIFACT_DOWN_SCROLL_BUTTON.active =false;

    }

    private boolean closeAnimation(DrawContext context) {
        if (onCloseAnimation){
            MatrixStack matrices = context.getMatrices();
            totalTickAnimationDelta += tickDelta;

            Matrix4f matrix = matrices.peek().getPositionMatrix();

            float yMovement = MathHelper.clamp(
                    MathHelper.sin( MathHelper.HALF_PI * (totalTickAnimationDelta/10f)) * (v + client.getWindow().getScaledHeight()),
                    0,
                    (client.getWindow().getScaledHeight())
            );

            if (yMovement >= (client.getWindow().getScaledHeight()) || totalTickAnimationDelta >= 15){
                totalTickAnimationDelta = 0;
                client.setScreen(parent);
                return true;
            }

            matrix.translate(0,yMovement,1f);
            context.setShaderColor(1.0f,1.0f,1.0f, 1.0f);
            context.drawTexture(ARTIFACTS_MENU,i,v,0,0,texture_width,texture_height,texture_width,texture_height);
            return true;
        }

        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        i = (this.width - texture_width) / 2;
        v = (this.height - texture_height) / 2;
        tickDelta = delta;
        renderBackground(context);
        renderForeground(context, mouseX, mouseY,delta);


    }

    @Override
    public void renderBackground(DrawContext context) {


        if (this.client.world != null) {

            context.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);

            RenderSystem.enableBlend();

            if (!closeAnimation(context)){
                context.getMatrices().push();

                context.drawTexture(ARTIFACTS_MENU,i,v,0,0,texture_width,texture_height,texture_width,texture_height);

                context.getMatrices().pop();

                String rawText = "Artifacts";

                int textX = i + texture_width/2 - textRenderer.getWidth(rawText)/2;
                int textY = v + 7;

                context.getMatrices().push();
                MatrixStack matrices = context.getMatrices();
                matrices.scale(2f,2f,1f);
                matrices.translate((-textX * 0.5) - (textRenderer.getWidth(rawText)/2) * 0.5 ,-textY * 0.5,1f);
                context.drawText(textRenderer,Text.literal(rawText),textX,textY, 0xFF66604c,false);

                context.getMatrices().pop();
            }
        } else {
            this.renderBackgroundTexture(context);
        }
        context.setShaderColor(1.0f,1.0f,1.0f, 1.0f);
    }

    public void renderForeground(DrawContext context, int mouseX, int mouseY, float delta){

        if (onCloseAnimation){
            return;
        }

        ARTIFACT_SHOWCASER.render(context,mouseX,mouseY,delta);

        equipedSlots.stream().forEach(selectedSlot ->{
            selectedSlot.render(context, mouseX, mouseY, delta);
        });

        itemCases.stream().forEach(itemCases -> {
            itemCases.render(context,mouseX,mouseY,delta);
        });

        ARTIFACT_DESCRIPTOR.render(context,mouseX,mouseY,delta);


        LEFT_BUTTON_WIDGET.renderButton(context,mouseX,mouseY,delta);
        RIGHT_BUTTON_WIDGET.renderButton(context,mouseX,mouseY,delta);


    }


    public void saveArtifactEquipment(){

        List<String> artifactList = equipedSlots.stream().map(slot ->{
            if (slot.item!= null) return (slot.item.getArtifactItem()).getArtifactId() + "{count:"+ slot.item.getQuantity()+"}";
            return "none";
        }).toList();

        ArtifactEquipmentData.clientSetArtifactsAllSlots(artifactList);

        ((ArtifactSelectable) client.player.getInventory()).deselectArtifact();
    }

    public void selectSlot(ItemCaseWidget caseWidget){

        if (selectedCase == null){
            caseWidget.isMarked = true;
            selectedCase = caseWidget;
            selectedItem = selectedCase.getItem();
        } else {
            if (!selectedCase.equals(caseWidget)){
                if (selectedCase != null){
                    selectedCase.isMarked = false;
                }
                caseWidget.isMarked = true;
                selectedCase = caseWidget;
                selectedItem = selectedCase.getItem();
            }
        }
    }


    public void deselectSlot() {
        if (selectedCase != null){
            selectedCase.isMarked = false;
            selectedItem = null;
            selectedCase = null;
        }
    }
}
