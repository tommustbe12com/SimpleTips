package com.tommustbe12.simpleTips;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleTips extends JavaPlugin {

    private static SimpleTips instance;
    private TipManager tipManager;
    private TipTask task;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        tipManager = new TipManager(this);
        tipManager.loadTips();

        getCommand("tip").setExecutor(new TipCommand(tipManager));

        startTask();

        getLogger().info("SimpleTips enabled.");
    }

    public void startTask() {
        if (task != null) task.cancel();

        int interval = getConfig().getInt("interval", 120);
        task = new TipTask(tipManager);
        task.runTaskTimer(this, 20 * 5, 20L * interval);
    }

    public static SimpleTips getInstance() {
        return instance;
    }
}