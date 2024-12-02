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

public class DekuLeafItem extends Item implements ArtifactItem {
    public DekuLeafItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Hoja mágica que permite planear"));
        tooltip.add(Text.literal("por el aire y crear ráfagas de"));
        tooltip.add(Text.literal("viento. Úsala para cruzar"));
        tooltip.add(Text.literal("grandes distancias y"));
        tooltip.add(Text.literal("activar objetos desde lejos."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFF169429;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF06401a;
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
