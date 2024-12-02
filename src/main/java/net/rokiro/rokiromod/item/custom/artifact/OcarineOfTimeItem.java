package net.rokiro.rokiromod.item.custom.artifact;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.option.Perspective;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.rokiro.rokiromod.client.screen.EquipmentMenuScreen;
import net.rokiro.rokiromod.client.screen.OcarinePlayScreen;
import net.rokiro.rokiromod.util.ArtifactItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OcarineOfTimeItem extends Item implements ArtifactItem {
    public OcarineOfTimeItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Un instrumento legendario con el"));
        tooltip.add(Text.literal("poder de controlar el tiempo. Toca"));
        tooltip.add(Text.literal("melodías sagradas para abrir puertas,"));
        tooltip.add(Text.literal("manipular el flujo temporal y acceder"));
        tooltip.add(Text.literal("a reinos ocultos."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getColorTitle() {
        return 0xFF4b78eb;
    }

    @Override
    public int getOutlineColorTitle() {
        return 0xFF192875;
    }

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.OOT;
    }


    @Override
    public boolean getConditions(PlayerEntity player) {
        if (player.isOnGround()){
            return true;
        }
        return  false;
    }

    @Override
    public void onActivateAbility(PlayerEntity player) {
        MinecraftClient.getInstance().options.setPerspective(Perspective.THIRD_PERSON_FRONT);
        MinecraftClient.getInstance().setScreen(new OcarinePlayScreen(MinecraftClient.getInstance().currentScreen));
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
