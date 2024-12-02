package net.rokiro.rokiromod.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArtifactItemStack {

    ArtifactItem item;
    ItemStack itemStack;
    int quantity;
    int maxQuantity;
    boolean canStack;

    public ArtifactItemStack(ArtifactItem item, Integer quantity) {
        this.item = item;
        if (quantity == null){
            this.quantity = 1;
        } else {
            this.quantity = quantity;
        }
        maxQuantity = item.maxStack();
        canStack = item.canStack();
        itemStack = new ItemStack(asItem());
    }

    public ArtifactItemStack(ArtifactItem item) {
        this.item = item;
        maxQuantity = item.maxStack();
        canStack = item.canStack();
        if (item.canStack()){
            this.quantity = 1;
        } else {
            this.quantity = 0;
        }
        itemStack = new ItemStack(asItem());
    }

    public ArtifactItem getArtifactItem() {
        return item;
    }

    public Item asItem(){
        return (Item) (item);
    }

    public ItemStack asItemStack(){
        return itemStack;
    }

    public void setItem(ArtifactItem item) {
        this.item = item;
        maxQuantity = item.maxStack();
        canStack = item.canStack();
        if (!item.canStack()){
            this.quantity = 1;
        } else {
            this.quantity = 0;
        }
        itemStack = new ItemStack(asItem());
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public boolean canStack() {
        return canStack;
    }



    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
