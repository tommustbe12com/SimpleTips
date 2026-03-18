package com.tommustbe12.simpleTips;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.List;

public class TipCommand implements CommandExecutor {

    private final TipManager manager;

    public TipCommand(TipManager manager) {
        this.manager = manager;
    }

    private String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(color("&8&m--------------------------------"));
            sender.sendMessage(color("&b&lSimpleTips &7- Commands"));
            sender.sendMessage(color("&7/tip list"));
            sender.sendMessage(color("&7/tip add <message>"));
            sender.sendMessage(color("&7/tip remove <id>"));
            sender.sendMessage(color("&7/tip toggleops"));
            sender.sendMessage(color("&8&m--------------------------------"));
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "list":
                List<String> tips = manager.getTips();

                sender.sendMessage(color("&8&m-------- &bTips &8&m--------"));
                if (tips.isEmpty()) {
                    sender.sendMessage(color("&cNo tips found."));
                    return true;
                }

                for (int i = 0; i < tips.size(); i++) {
                    sender.sendMessage(color("&7" + i + ": &f" + tips.get(i)));
                }
                return true;

            case "add":
                if (!sender.hasPermission("simpletips.manage")) {
                    sender.sendMessage(color("&cNo permission."));
                    return true;
                }

                if (args.length < 2) {
                    sender.sendMessage(color("&cUsage: /tip add <message>"));
                    return true;
                }

                String msg = String.join(" ", args).substring(4);
                manager.addTip(msg);
                sender.sendMessage(color("&aTip added."));
                return true;

            case "remove":
                if (!sender.hasPermission("simpletips.manage")) {
                    sender.sendMessage(color("&cNo permission."));
                    return true;
                }

                if (args.length != 2) {
                    sender.sendMessage(color("&cUsage: /tip remove <id>"));
                    return true;
                }

                try {
                    int id = Integer.parseInt(args[1]);
                    manager.removeTip(id);
                    sender.sendMessage(color("&aTip removed."));
                } catch (Exception e) {
                    sender.sendMessage(color("&cInvalid ID."));
                }
                return true;

            case "toggleops":
                if (!sender.hasPermission("simpletips.manage")) {
                    sender.sendMessage(color("&cNo permission."));
                    return true;
                }

                boolean current = SimpleTips.getInstance().getConfig().getBoolean("ops-enabled", true);
                SimpleTips.getInstance().getConfig().set("ops-enabled", !current);
                SimpleTips.getInstance().saveConfig();

                sender.sendMessage(color("&aOps tips toggled: " + (!current)));
                return true;

            default:
                sender.sendMessage(color("&cUnknown subcommand."));
                return true;
        }
    }
}