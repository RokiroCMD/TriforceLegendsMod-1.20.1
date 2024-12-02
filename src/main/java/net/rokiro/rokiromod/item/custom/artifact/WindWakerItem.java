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

public class WindWakerItem extends Item implements ArtifactItem {
    public WindWakerItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Un instrumento mágico que controla"));
        tooltip.add(Text.literal("el viento. Toca melodías para"));
        tooltip.add(Text.literal("cambiar la dirección del viento,"));
        tooltip.add(Text.literal("lo que te permitirá navegar más"));
        tooltip.add(Text.literal("fácilmente por el océano y resolver"));
        tooltip.add(Text.literal("acertijos."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFFdcfcfb;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFFa0adad;
    }

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.WW;
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
        return 0;
    }
}
