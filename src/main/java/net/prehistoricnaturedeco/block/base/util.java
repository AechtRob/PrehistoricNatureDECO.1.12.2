package net.prehistoricnaturedeco.block.base;

import net.lepidodendron.block.BlockAraucarioxylonLogPetrified;
import net.lepidodendron.item.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class util {
    
    public static int getOreID(ItemStack itemStack) {
        int enumUsed = 0;
        if (itemStack.getItem() == Items.IRON_NUGGET) {
            enumUsed = 1;
        }
        else if (itemStack.getItem() == Items.GOLD_NUGGET) {
            enumUsed = 2;
        }
        else if (itemStack.getItem() == new ItemStack(ItemZircon.block, 1).getItem()) {
            enumUsed = 3;
        }
        else if (itemStack.getItem() == Items.EMERALD) {
            enumUsed = 4;
        }
        else if (itemStack.getItem() == Items.DIAMOND) {
            enumUsed = 5;
        }
        else if (itemStack.getItem() == new ItemStack(ItemBalticAmberChunk.block, 1).getItem()) {
            enumUsed = 6;
        }
        else if (itemStack.getItem() == new ItemStack(ItemDominicanAmberChunk.block, 1).getItem()) {
            enumUsed = 7;
        }
        else if (itemStack.getItem() == Items.QUARTZ) {
            enumUsed = 8;
        }
        else if (itemStack.getItem() == (new ItemStack(Items.DYE, 1, 4)).getItem()) {
            if (itemStack.getMetadata() == 4) {
                enumUsed = 9;
            }
        }
        else if (itemStack.getItem() == Items.COAL) {
            enumUsed = 10;
        }
        else if (itemStack.getItem() == new ItemStack(BlockAraucarioxylonLogPetrified.block, 1).getItem()){
            enumUsed = 11;
        }
        else if (itemStack.getItem() == new ItemStack(Blocks.REDSTONE_TORCH, 1).getItem()){
            enumUsed = 12;
        }
        else if (itemStack.getItem() == new ItemStack(ItemAnthracite.block, 1).getItem()) {
            enumUsed = 13;
        }
        else if (itemStack.getItem() == new ItemStack(ItemSalt.block, 1).getItem()) {
            enumUsed = 14;
        }
        else if (itemStack.getItem() == new ItemStack(ItemSulphur.block, 1).getItem()) {
            enumUsed = 15;
        }
        return enumUsed;
    }

    public static ItemStack getItemToDrop(int variant) {
        EntityItem entityToSpawn = null;
        if (variant == 1) {
            return new ItemStack(Items.IRON_NUGGET, (int) (1));
        }
        else if (variant == 2) {
            return new ItemStack(Items.GOLD_NUGGET, (int) (1));
        }
        else if (variant == 3) {
            return new ItemStack(ItemZircon.block, (int) (1));
        }
        else if (variant == 4) {
            return new ItemStack(Items.EMERALD, (int) (1));
        }
        else if (variant == 5) {
            return new ItemStack(Items.DIAMOND, (int) (1));
        }
        else if (variant == 6) {
            return new ItemStack(ItemBalticAmberChunk.block, (int) (1));
        }
        else if (variant == 7) {
            return new ItemStack(ItemDominicanAmberChunk.block, (int) (1));
        }
        else if (variant == 8) {
            return new ItemStack(Items.QUARTZ, (int) (1));
        }
        else if (variant == 9) {
            return new ItemStack(Items.DYE, 1, 4);
        }
        else if (variant == 10) {
            return new ItemStack(Items.COAL, (int) (1));
        }
        else if (variant == 11) {
            return new ItemStack(BlockAraucarioxylonLogPetrified.block, (int) (1));
        }
        else if (variant == 12) {
            return new ItemStack(Blocks.REDSTONE_TORCH, (int) (1));
        }
        else if (variant == 13) {
            return new ItemStack(ItemAnthracite.block, (int) (1));
        }
        else if (variant == 14) {
            return new ItemStack(ItemSalt.block, (int) (1));
        }
        else if (variant == 15) {
            return new ItemStack(ItemSulphur.block, (int) (1));
        }
        return ItemStack.EMPTY;
    }
}
