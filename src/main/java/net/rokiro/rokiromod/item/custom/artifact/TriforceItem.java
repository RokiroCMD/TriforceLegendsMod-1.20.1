package net.rokiro.rokiromod.item.custom.artifact;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.util.ArtifactItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TriforceItem extends Item implements ArtifactItem {
    public TriforceItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Artefacto mítico creado por las"));
        tooltip.add(Text.literal("diosas doradas de Nayru, Farore"));
        tooltip.add(Text.literal("y Din, contiene la trifuerza del"));
        tooltip.add(Text.literal("valor, la sabiduría y la fuerza"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() { return 0xFFFFFFFF;}

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.OOT;
    }

    @Override
    public String getArtifactId() {
        return this.getTranslationKey();
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
        return 1;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFFf5d442;
    }
}
