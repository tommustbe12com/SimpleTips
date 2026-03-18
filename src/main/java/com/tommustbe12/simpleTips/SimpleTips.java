package com.tommustbe12.simpleTips;

import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleTips extends JavaPlugin {

    private static SimpleTips instance;
    private TipManager tipManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        tipManager = new TipManager(this);
        tipManager.loadTips();

        getCommand("tip").setExecutor(new TipCommand(tipManager));

        new TipTask(tipManager).runTaskTimer(this, 20 * 30, 20 * getConfig().getInt("interval", 120));

        getLogger().info("SimpleTips enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleTips disabled.");
    }

    public static SimpleTips getInstance() {
        return instance;
    }
}