package com.tommustbe12.simpleTips;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TipTask extends BukkitRunnable {

    private final TipManager manager;

    public TipTask(TipManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        String tip = manager.getRandomTip();
        if (tip == null) return;

        boolean opsEnabled = SimpleTips.getInstance().getConfig().getBoolean("ops-enabled", true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!opsEnabled && player.isOp()) continue;

            player.sendMessage(tip);
        }
    }
}