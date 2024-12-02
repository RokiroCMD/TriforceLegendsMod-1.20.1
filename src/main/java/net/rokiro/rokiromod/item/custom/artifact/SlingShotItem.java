package net.rokiro.rokiromod.item.custom.artifact;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.rokiro.rokiromod.util.ArtifactItem;
import net.rokiro.rokiromod.util.ArtifactItemStack;
import net.rokiro.rokiromod.util.ArtifactSelectable;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SlingShotItem extends Item implements ArtifactItem {
    public SlingShotItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Herramienta de disparo confiable"));
        tooltip.add(Text.literal("que usa semillas Deku como munición."));
        tooltip.add(Text.literal("Perfecta para atacar enemigos lejanos"));
        tooltip.add(Text.literal("y activar interruptores a distancia."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFF961520;
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
        ArtifactItem.equipArtifactItem(player, this);
    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public int maxStack() {
        return 60;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF450303;
    }
}
