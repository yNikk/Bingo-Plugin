package eu.flowtex.bingo.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemBuilder {
    private ItemMeta itemMeta;
    private ItemStack itemStack;

    public ItemBuilder(final Material mat) {
        this.itemStack = new ItemStack(mat);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayname(final String s) {
        this.itemMeta.setDisplayName(s);
        return this;
    }

    public ItemBuilder setLocalizedName(final String s) {
        this.itemMeta.setLocalizedName(s);
        return this;
    }

    public ItemBuilder setLore(final String... s) {
        this.itemMeta.setLore((List) Arrays.asList(s));
        return this;
    }

    public ItemBuilder addLore(final String s) {
        ArrayList<String> lore = (ArrayList<String>) this.itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<String>();
        }
        lore.add(s);
        this.itemMeta.setLore((List) lore);
        return this;
    }

    public ItemBuilder addItemFlags(final ItemFlag... s) {
        this.itemMeta.addItemFlags(s);
        return this;
    }

    public ItemBuilder enchant(final Enchantment en, final int level) {
        this.itemStack.addUnsafeEnchantment(en, level);
        return this;
    }

    public ItemBuilder setDurability(short dur) {
        this.setDurability(dur);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        this.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        this.removeEnchantment(ench);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        try {
            SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
            itemMeta.setOwner(owner);
            itemStack.setItemMeta(itemMeta);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(ench, level, true);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        this.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder setInfinityDurability() {
        this.setDurability(Short.MAX_VALUE);
        return this;
    }

    public ItemBuilder removeLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        if (!lore.contains(line)) return this;
        lore.remove(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder removeLoreLine(int index) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        if (index < 0 || index > lore.size()) return this;
        lore.remove(index);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (itemMeta.hasLore()) lore = new ArrayList<>(itemMeta.getLore());
        lore.add(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLoreLine(String line, int pos) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>(itemMeta.getLore());
        lore.set(pos, line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta itemMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            itemMeta.setColor(color);
            itemStack.setItemMeta(itemMeta);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    @Override
    public String toString() {
        return "ItemBuilder{itemMeta=" + this.itemMeta + ", itemStack=" + this.itemStack + '}';
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }
}
