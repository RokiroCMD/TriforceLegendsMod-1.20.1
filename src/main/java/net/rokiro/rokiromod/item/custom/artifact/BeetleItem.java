package net.rokiro.rokiromod.item.custom.artifact;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.rokiro.rokiromod.util.ArtifactItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BeetleItem extends Item implements ArtifactItem {
    public BeetleItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Dispositivo volador controlado a"));
        tooltip.add(Text.literal("distancia. Úsalo para explorar,"));
        tooltip.add(Text.literal("recoger objetos y activar "));
        tooltip.add(Text.literal("interruptores lejanos con "));
        tooltip.add(Text.literal("precisión"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFF76d43f;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF0b4008;
    }

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.SS;
    }

    @Override
    public String getArtifactId() {
        return getTranslationKey();
    }

    @Override
    public void onActivateAbility(PlayerEntity player) {

    }

    @Override
    public boolean canStack() {
        return false;
    }

    @Override
    public int maxStack() {
        return 1;
    }
}
