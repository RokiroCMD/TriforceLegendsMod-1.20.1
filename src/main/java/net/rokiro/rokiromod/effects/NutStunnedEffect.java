package net.rokiro.rokiromod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

public class NutStunnedEffect extends StatusEffect {
    protected NutStunnedEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFF0e519e);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

            if (!entity.getWorld().isClient()){
                if ((entity instanceof MobEntity)){
                    MobEntity mob = (MobEntity) entity;
                    if (!mob.isAiDisabled()){
                        mob.setAiDisabled(true);
                    }
                }
            }
    }


    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
            if (!entity.getWorld().isClient()){
                if ((entity instanceof MobEntity)){
                    MobEntity mob = (MobEntity) entity;
                    mob.setAiDisabled(false);
            }
        }
    }
}
