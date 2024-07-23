package net.rokiro.rokiromod.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    HYLIAN_SET(ToolMaterials.DIAMOND.getMiningLevel(), 1100, 10.0f, 3.0f, 15, () -> Ingredient.ofItems(ModItems.HYLIAN_STEEL)),
    WW_MASTER_SWORD(ToolMaterials.NETHERITE.getMiningLevel(), 2500,10,5.0f, 15, () -> Ingredient.ofItems(ModItems.TRIFORCE));


    private int miningLevel;
    private int itemDurability;
    private float miningSpeed;
    private float attackDamage;
    private int enchantabilty;
    private final Supplier<Ingredient> repairIngredients;

    ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantabilty, Supplier<Ingredient> repairIngredients) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantabilty = enchantabilty;
        this.repairIngredients = repairIngredients;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantabilty;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredients.get();
    }
}
