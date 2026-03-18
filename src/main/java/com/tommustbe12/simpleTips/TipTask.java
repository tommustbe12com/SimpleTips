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
        boolean opsEnabled = SimpleTips.getInstance().getConfig().getBoolean("ops-enabled", true);
        String mode = SimpleTips.getInstance().getConfig().getString("display", "chat");

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!opsEnabled && player.isOp()) continue;

            String tip = manager.getFormattedTip(player);
            if (tip == null) return;

            switch (mode.toLowerCase()) {
                case "actionbar":
                    player.sendActionBar(tip);
                    break;

                case "title":
                    player.sendTitle(tip, "", 10, 60, 10);
                    break;

                case "subtitle":
                    player.sendTitle("", tip, 10, 60, 10);
                    break;

                default:
                    player.sendMessage(tip);
                    break;
            }
        }
    }
}