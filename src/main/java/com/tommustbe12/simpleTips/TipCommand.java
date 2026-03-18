package com.tommustbe12.simpleTips;

import org.bukkit.ChatColor;
import org.bukkit.command.*;

import java.util.List;

public class TipCommand implements CommandExecutor {

    private final TipManager manager;

    public TipCommand(TipManager manager) {
        this.manager = manager;
    }

    private String c(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(c("&8&m--------------------------------"));
            sender.sendMessage(c("&b&lSimpleTips &7- Commands"));
            sender.sendMessage(c("&7/tip list"));
            sender.sendMessage(c("&7/tip add <msg>"));
            sender.sendMessage(c("&7/tip remove <id>"));
            sender.sendMessage(c("&7/tip interval <seconds>"));
            sender.sendMessage(c("&7/tip display <chat/actionbar/title/subtitle>"));
            sender.sendMessage(c("&8&m--------------------------------"));
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "interval":
                if (!sender.hasPermission("simpletips.manage")) return true;

                if (args.length != 2) {
                    sender.sendMessage(c("&cUsage: /tip interval <seconds>"));
                    return true;
                }

                try {
                    int sec = Integer.parseInt(args[1]);
                    SimpleTips.getInstance().getConfig().set("interval", sec);
                    SimpleTips.getInstance().saveConfig();
                    SimpleTips.getInstance().startTask();

                    sender.sendMessage(c("&aInterval set to " + sec + " seconds."));
                } catch (Exception e) {
                    sender.sendMessage(c("&cInvalid number."));
                }
                return true;

            case "display":
                if (!sender.hasPermission("simpletips.manage")) return true;

                if (args.length != 2) {
                    sender.sendMessage(c("&cUsage: /tip display <type>"));
                    return true;
                }

                String type = args[1].toLowerCase();

                if (!type.equals("chat") && !type.equals("actionbar") &&
                        !type.equals("title") && !type.equals("subtitle")) {
                    sender.sendMessage(c("&cInvalid type."));
                    return true;
                }

                SimpleTips.getInstance().getConfig().set("display", type);
                SimpleTips.getInstance().saveConfig();

                sender.sendMessage(c("&aDisplay mode set to " + type));
                return true;

            case "list":
                sender.sendMessage(c("&8&m------------Tips----------------"));
                List<String> tips = manager.getTips();
                for (int i = 0; i < tips.size(); i++) {
                    sender.sendMessage(c("&7" + i + ": &f" + tips.get(i)));
                }
                return true;

            case "add":
                if (!sender.hasPermission("simpletips.manage")) return true;

                if (args.length < 2) return true;

                String msg = String.join(" ", args).substring(4);
                manager.addTip(msg);
                sender.sendMessage(c("&aTip added."));
                return true;

            case "remove":
                if (!sender.hasPermission("simpletips.manage")) return true;

                try {
                    int id = Integer.parseInt(args[1]);
                    manager.removeTip(id);
                    sender.sendMessage(c("&aTip removed."));
                } catch (Exception e) {
                    sender.sendMessage(c("&cInvalid ID."));
                }
                return true;
        }

        return true;
    }
}