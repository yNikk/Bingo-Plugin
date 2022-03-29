package eu.flowtex.bingo.utils;

import org.bukkit.inventory.meta.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.enchantments.*;

public class ItemBuilder
{
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
        this.itemMeta.setLore((List)Arrays.asList(s));
        return this;
    }
    
    public ItemBuilder addLore(final String s) {
        ArrayList<String> lore = (ArrayList<String>)this.itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<String>();
        }
        lore.add(s);
        this.itemMeta.setLore((List)lore);
        return this;
    }
    
    public ItemBuilder setUnbreakable(final boolean s) {
        this.itemMeta.setUnbreakable(s);
        return this;
    }
    
    public ItemBuilder addItemFlags(final ItemFlag... s) {
        this.itemMeta.addItemFlags(s);
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
    
    public ItemBuilder enchant(final Enchantment en, final int level) {
        this.itemStack.addUnsafeEnchantment(en, level);
        return this;
    }
}
