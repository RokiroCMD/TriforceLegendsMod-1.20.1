package net.rokiro.rokiromod.item.custom.artifact;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.rokiro.rokiromod.RokirosMod;
import net.rokiro.rokiromod.item.client.MajorasMaskRenderer;
import net.rokiro.rokiromod.util.ArtifactItem;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MajorasMaskItem extends ArmorItem implements GeoItem, ArtifactItem {

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private  final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public MajorasMaskItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public TypedActionResult<ItemStack> equipAndSwap(Item item, World world, PlayerEntity user, Hand hand) {
        //RokirosMod.LOGGER.info("Jalando");
        return super.equipAndSwap(item, world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Máscara maldita con una apariencia"));
        tooltip.add(Text.literal("distintiva y siniestra. Contiene"));
        tooltip.add(Text.literal("un espíritu maligno que tiene el"));
        tooltip.add(Text.literal("poder de poseer a su portador y"));
        tooltip.add(Text.literal("manipular el tiempo."));
        super.appendTooltip(stack, world, tooltip, context);
    }



    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private MajorasMaskRenderer renderer;

            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity
                    ,ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {

                if (this.renderer == null) this.renderer = new MajorasMaskRenderer();

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",0, this::predicate));
    }

    private PlayState predicate(AnimationState state){
        state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public int getColorTitle() {
        return 0xFF832fba;
    }

    @Override
    public ArtifactSaga getSaga() {
        return ArtifactSaga.MM;
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
        return 0xFF2f0963;
    }
}
