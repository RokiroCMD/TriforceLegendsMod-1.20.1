package net.rokiro.rokiromod.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rokiro.rokiromod.RokirosMod;

public class ModEffects {


    public static final StatusEffect NUT_STUNNED_EFFECT = new NutStunnedEffect();

    public static void registerStatusEffects(){
        Registry.register(Registries.STATUS_EFFECT, new Identifier(RokirosMod.MOD_ID, "nut_stunned"), NUT_STUNNED_EFFECT);
    }

}
