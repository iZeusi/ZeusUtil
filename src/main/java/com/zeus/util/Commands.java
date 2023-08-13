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
        if (label.equalsIgnoreCase("broad")) {
            if (args.length > 0) {
                if (cH.hasPermission(sender, "broad")) {
                    cH.broadcast(String.join(" ", args));
                }
            }
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
