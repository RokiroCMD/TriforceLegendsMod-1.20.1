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

public class BombchuItem extends Item implements ArtifactItem {
    public BombchuItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Un explosivo en miniatura que "));
        tooltip.add(Text.literal("se desliza por el suelo. Se"));
        tooltip.add(Text.literal("desplaza en línea recta hasta"));
        tooltip.add(Text.literal("alcanzar su objetivo o una"));
        tooltip.add(Text.literal("pared, detonando al impacto."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFFebc21e;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF4c68c7;
    }

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.OOT;
    }

    @Override
    public void onActivateAbility(PlayerEntity player) {

    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public int maxStack() {
        return 30;
    }
}
