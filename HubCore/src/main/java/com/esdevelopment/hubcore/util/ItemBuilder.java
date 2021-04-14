package com.esdevelopment.hubcore.util;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
    }


    public ItemBuilder(Material material) {
        this(material, 1);
    }


    public ItemBuilder(Material material, int amount, short value) {
        itemStack = new ItemStack(material, amount, value);
    }

    public ItemBuilder setName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }


    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        itemStack.setItemMeta(leatherArmorMeta);
        return this;
    }


    public ItemStack create() {
        return itemStack;
    }
}