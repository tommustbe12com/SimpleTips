package com.tommustbe12.simpleTips;

import org.bukkit.ChatColor;

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

    public String getRandomTip() {
        if (tips.isEmpty()) return null;
        return ChatColor.translateAlternateColorCodes('&',
                tips.get((int) (Math.random() * tips.size()))
        );
    }
}