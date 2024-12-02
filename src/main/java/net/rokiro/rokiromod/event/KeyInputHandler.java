package net.rokiro.rokiromod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.client.screen.EquipmentMenuScreen;
import net.rokiro.rokiromod.util.ArtifactAbilityUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static final String KEY_CATEGORY_TRIFORCE = "key.rokiros-mod.category.triforce";
    public static final String KEY_OPEN_SPECIAL_INVENTORY = "key.rokiros-mod.open_special_inventory";
    public static final String KEY_FIRST_ARTIFACT_SLOT = "key.rokiros-mod.use_first_artifact_slot";
    public static final String KEY_SECOND_ARTIFACT_SLOT = "key.rokiros-mod.use_second_artifact_slot";
    public static final String KEY_THIRD_ARTIFACT_SLOT = "key.rokiros-mod.use_third_artifact_slot";
    public static final String KEY_FOURTH_ARTIFACT_SLOT = "key.rokiros-mod.use_fourth_artifact_slot";

    public static KeyBinding openingSpInv;
    public static KeyBinding useFirstArtifactSlot;
    public static KeyBinding useSecondArtifactSlot;
    public static KeyBinding useThirdArtifactSlot;
    public static KeyBinding useFourthArtifactSlot;

    static int cooldownArtifactActivate = 5;
    static boolean canUseAbility = true;
    static boolean abilityKeyUp = true;
    static int tickCooldownArtifact = 0;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openingSpInv.wasPressed()){
                onOpeningSpingInv(client);
            }

            onPressArtifactKey();

        });
    }

    private static void checkArtifactCooldown(){

        if (canUseAbility){
            return;
        }

        tickCooldownArtifact++;

        if (tickCooldownArtifact % cooldownArtifactActivate == 0){
            tickCooldownArtifact = 0;
            canUseAbility = true;
        }

    }
    private static void onPressArtifactKey(){

        checkArtifactCooldown();

        if (useFirstArtifactSlot.isPressed()) {
            if (abilityKeyUp && canUseAbility){
                canUseAbility = false;
                ArtifactAbilityUtil.useArtifactAbility(0);
            }
            abilityKeyUp = false;
        }
        if (useSecondArtifactSlot.isPressed()) {
            if (abilityKeyUp && canUseAbility){
                canUseAbility = false;
                ArtifactAbilityUtil.useArtifactAbility(1);
            }
            abilityKeyUp = false;
        }
        if (useThirdArtifactSlot.isPressed()) {
            if (abilityKeyUp && canUseAbility){
                canUseAbility = false;
                ArtifactAbilityUtil.useArtifactAbility(2);
            }
            abilityKeyUp = false;
        }
        if (useFourthArtifactSlot.isPressed()) {
            if (abilityKeyUp && canUseAbility){
                canUseAbility = false;
                ArtifactAbilityUtil.useArtifactAbility(3);
            }
            abilityKeyUp = false;
        }

        if (!useFirstArtifactSlot.isPressed() && !useSecondArtifactSlot.isPressed()
                && !useThirdArtifactSlot.isPressed() && !useFourthArtifactSlot.isPressed()){
            abilityKeyUp = true;
        }
    }

    private static void onOpeningSpingInv(MinecraftClient client){
        client.setScreen(new EquipmentMenuScreen(client.currentScreen,true));
    }

    public static void register(){
        openingSpInv = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN_SPECIAL_INVENTORY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                KEY_CATEGORY_TRIFORCE
                ));

        useFirstArtifactSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_FIRST_ARTIFACT_SLOT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                KEY_CATEGORY_TRIFORCE
        ));
        useSecondArtifactSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SECOND_ARTIFACT_SLOT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                KEY_CATEGORY_TRIFORCE
        ));
        useThirdArtifactSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_THIRD_ARTIFACT_SLOT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                KEY_CATEGORY_TRIFORCE
        ));
        useFourthArtifactSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_FOURTH_ARTIFACT_SLOT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                KEY_CATEGORY_TRIFORCE
        ));

        registerKeyInputs();
    }

}
