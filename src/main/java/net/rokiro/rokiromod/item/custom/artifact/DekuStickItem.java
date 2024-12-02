package net.rokiro.rokiromod.item.custom.artifact;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.rokiro.rokiromod.util.ArtifactItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DekuStickItem extends Item implements ArtifactItem {
    public DekuStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Son utiles para encender antorchas"));
        tooltip.add(Text.literal("y resolver ciertos acertijos, aunque"));
        tooltip.add(Text.literal("son frágiles y se rompen después de"));
        tooltip.add(Text.literal("un solo uso como arma"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFF804330;
    }

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
        ArtifactItem.equipArtifactItem(player, this);
    }

    @Override
    public boolean canStack() {
        return true;
    }

    @Override
    public int maxStack() {
        return 16;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF4d1e18;
    }
}
