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

public class SwiftSailItem extends Item implements ArtifactItem {
    public SwiftSailItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Una versión mejorada de la vela"));
        tooltip.add(Text.literal("que aumenta significativamente la"));
        tooltip.add(Text.literal("velocidad del barco. Permite navegar"));
        tooltip.add(Text.literal("más rápidamente y reducir el tiempo"));
        tooltip.add(Text.literal("de viaje en el océano."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFFf7e083;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFFc73934;
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
