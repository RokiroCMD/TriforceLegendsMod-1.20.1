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

public class GoddessLyreItem extends Item implements ArtifactItem {
    public GoddessLyreItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Un instrumento sagrado que permite"));
        tooltip.add(Text.literal("invocar magia antigua. Úsala para"));
        tooltip.add(Text.literal("desvelar secretos, activar símbolos"));
        tooltip.add(Text.literal("místicos y guiar tu camino en la aventura."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFFfff945;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF9c5c17;
    }

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.SS;
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
