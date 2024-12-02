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

public class BoomerangItem extends Item implements ArtifactItem {
    public BoomerangItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Herramienta arrojadiza que"));
        tooltip.add(Text.literal("regresa a tu mano. Úsalo para"));
        tooltip.add(Text.literal("aturdir enemigos, recoger objetos"));
        tooltip.add(Text.literal("a distancia y activar interruptores"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFFdb1a1a;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFFa1571f;
    }

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.OOT;
    }

    @Override
    public void onActivateAbility(PlayerEntity player) {
        ArtifactItem.equipArtifactItem(player, this);
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
