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

public class SimpleBowItem extends Item implements ArtifactItem {
    public SimpleBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Un arco básico que permite disparar"));
        tooltip.add(Text.literal("flechas. Ideal para atacar enemigos"));
        tooltip.add(Text.literal("a distancia y activar mecanismos "));
        tooltip.add(Text.literal("lejanos. Requiere flechas como "));
        tooltip.add(Text.literal("munición."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFFab1a1a;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF2896b5;
    }

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.SS;
    }

    @Override
    public void onActivateAbility(PlayerEntity player) {
        ArtifactItem.equipArtifactItem(player, this);
    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public int maxStack() {
        return 25;
    }
}
