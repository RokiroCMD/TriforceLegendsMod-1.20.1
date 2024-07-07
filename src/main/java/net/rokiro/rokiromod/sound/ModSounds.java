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

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(RokirosMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT,id, SoundEvent.of(id));

    }
    public static void registerSound(){
        RokirosMod.LOGGER.info("Registering sounds for " + RokirosMod.MOD_ID);
    }

}
