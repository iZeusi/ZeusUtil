package com.zeus.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    CommandHelper cH;

    public Commands() {
        cH = new CommandHelper();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (label.toLowerCase()) {
            case "broad":
                if (args.length > 0) {
                    if (cH.hasPermission(sender, "broad")) {
                        cH.broadcast(String.join(" ", args));
                    }
                }
                break;
            case "key":
                if (cH.hasPermission(sender, "key")) {
                    if (args.length == 2) {
                        try {
                            int amount = Integer.parseInt(args[1]);
                            cH.chatPrefix(sender, cH.key(args[0], amount));
                        } catch (NumberFormatException nfe) {
                            cH.chatPrefix(sender, "&cIncorrect usage: Amount must be a Number");
                        }
                    } else cH.chatPrefix(sender, "&cIncorrect usage: /Key <Player> <Amount>");
                } else cH.chatPrefix(sender, "&cNo permission!");
                break;
        }
        if (sender instanceof Player) {
            Player p = (Player) sender;
            switch (label.toLowerCase()) {
                case "ranks":
                case "voteranks":
                case "rank":
                    cH.ranks(p);
                    break;
                case "rules":
                case "rule":
                    cH.rules(p);
                    break;
                case "rare":
                case "rares":
                    cH.rares(p);
                    break;
            }
        }
        return true;
    }
}
