package net.rokiro.rokiromod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.rokiro.rokiromod.client.screen.EspecialItemsInventoryScreen;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static final String KEY_CATEGORY_TRIFORCE = "key.rokiros-mod.category.triforce";
    public static final String KEY_OPEN_SPECIAL_INVENTORY = "key.rokiros-mod.open_special_inventory";

    public static KeyBinding openingSpInv;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openingSpInv.wasPressed()){
                onOpeningSpingInv(client);
            }
        });
    }

    private static void onOpeningSpingInv(MinecraftClient client){
        client.setScreen(new EspecialItemsInventoryScreen(client.currentScreen));
    }

    public static void register(){
        openingSpInv = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN_SPECIAL_INVENTORY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                KEY_CATEGORY_TRIFORCE
                ));

        registerKeyInputs();
    }

}
