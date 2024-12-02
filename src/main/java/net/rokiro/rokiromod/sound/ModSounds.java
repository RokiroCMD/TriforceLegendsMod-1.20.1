package net.rokiro.rokiromod.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;

public class ModSounds {


    public static final SoundEvent BOKOBLIN_AMBIENT = registerSoundEvent("bokoblin_ambient");
    public static final SoundEvent BOKOBLIN_ATTACK = registerSoundEvent("bokoblin_attack");
    public static final SoundEvent BOKOBLIN_DEAD = registerSoundEvent("bokoblin_dead");
    public static final SoundEvent BOKOBLIN_HIT = registerSoundEvent("bokoblin_hit");
    public static final SoundEvent SKULLKID_LAUGH = registerSoundEvent("skullkid_laugh");
    public static final SoundEvent SKULLKID_SCREAM = registerSoundEvent("skullkid_scream");

    public static final SoundEvent RUPEE_BOUNCE = registerSoundEvent("rupee_bounce");
    public static final SoundEvent RUPEE_OBTAINED = registerSoundEvent("rupee_obtained");
    public static final SoundEvent OPEN_EQUIPMENT_MENU = registerSoundEvent("open_equipment_menu");
    public static final SoundEvent CLOSE_EQUIPMENT_MENU = registerSoundEvent("close_equipment_menu");
    public static final SoundEvent CURSOR_EQUIPMENT_MENU = registerSoundEvent("cursor_equipment_menu");
    public static final SoundEvent SELECT_EQUIPMENT_MENU = registerSoundEvent("select_equipment_menu");
    public static final SoundEvent TURN_RIGHT_EQUIPMENT_MENU = registerSoundEvent("tright_equipment_menu");
    public static final SoundEvent TURN_LEFT_EQUIPMENT_MENU = registerSoundEvent("tleft_equipment_menu");
    public static final SoundEvent GENERIC_ERROR = registerSoundEvent("generic_error");
    public static final SoundEvent DEKU_NUT_THROW = registerSoundEvent("deku_nut_throw");
    public static final SoundEvent GENERIC_ITEM_EQUIP = registerSoundEvent("generic_item_equip");

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(RokirosMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT,id, SoundEvent.of(id));

    }
    public static void registerSound(){
        RokirosMod.LOGGER.info("Registering sounds for " + RokirosMod.MOD_ID);
    }

}
