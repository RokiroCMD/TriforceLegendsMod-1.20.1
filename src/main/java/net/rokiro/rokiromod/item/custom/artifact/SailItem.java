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

public class SailItem extends Item implements ArtifactItem {

    public SailItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Un objeto esencial para navegar en"));
        tooltip.add(Text.literal("alta mar. Úsala para desplegar el"));
        tooltip.add(Text.literal("viento y avanzar rápidamente en tu"));
        tooltip.add(Text.literal("barco, permitiéndote explorar el"));
        tooltip.add(Text.literal("vasto océano."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFFf7e083;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF31a324;
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
