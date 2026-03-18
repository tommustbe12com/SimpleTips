package com.tommustbe12.simpleTips;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TipManager {

    private final SimpleTips plugin;
    private final List<String> tips = new ArrayList<>();

    public TipManager(SimpleTips plugin) {
        this.plugin = plugin;
    }

    public void loadTips() {
        tips.clear();
        tips.addAll(plugin.getConfig().getStringList("tips"));
    }

    public void saveTips() {
        plugin.getConfig().set("tips", tips);
        plugin.saveConfig();
    }

    public List<String> getTips() {
        return tips;
    }

    public void addTip(String tip) {
        tips.add(tip);
        saveTips();
    }

    public void removeTip(int index) {
        if (index >= 0 && index < tips.size()) {
            tips.remove(index);
            saveTips();
        }
    }

    public String getFormattedTip(Player player) {
        if (tips.isEmpty()) return null;

        String tip = tips.get((int) (Math.random() * tips.size()));
        tip = ChatColor.translateAlternateColorCodes('&', tip);

        if (plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            tip = PlaceholderAPI.setPlaceholders(player, tip);
        }

        return tip;
    }
}