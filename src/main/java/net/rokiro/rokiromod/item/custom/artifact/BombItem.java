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

public class BombItem extends Item implements ArtifactItem {
    public BombItem(Settings settings) {
        super(settings);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Un explosivo potente. Lánzala "));
        tooltip.add(Text.literal("o colócala para destruir "));
        tooltip.add(Text.literal("obstáculos y dañar enemigos."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFF1b284f;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF01041f;
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
        return 20;
    }
}
