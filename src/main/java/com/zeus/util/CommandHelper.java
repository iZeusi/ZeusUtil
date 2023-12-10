package com.zeus.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandHelper {

    String prefix = "&7[&eE&6M&7]&r ";

    public boolean hasPermission(CommandSender p, String perm) {
        return p.isOp() || p.hasPermission("zu.admin") || p.hasPermission("zu." + perm);
    }

    public void broadcast(String message) {
        Bukkit.broadcastMessage(colorize(prefix + message));
    }

    public void rares(Player p) {
        sendCenteredMessage(p, "&7]&r&7&m----------------&r&7[ &r&e&l&nRares &r&7]&r&7&m----------------&r&7[");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&aFull list of Rares: &ehttps://shorturl.at/sBMP6&r");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&7]&r&7&m----------------------------------------------&r&7[");
    }

    public void rules(Player p) {
        sendCenteredMessage(p, "&7]&r&7&m----------------&r&7[ &r&e&l&nRules &r&7]&r&7&m----------------&r&7[");
        chatNoPrefix(p, "");
        chatNoPrefix(p, "&c - &fThe use of hacked clients, or \"hacks\" are not allowed.");
        chatNoPrefix(p, "&c - &fDo not spam chat with multiple messages.");
        chatNoPrefix(p, "&c - &fEnglish is the language to be used in global chat.");
        chatNoPrefix(p, "&c - &fWe do not condone racism, sexism, homophobia, or any other type or prejudice or discrimination.");
        chatNoPrefix(p, "");
        chatNoPrefix(p, "&aPlease view our full rules list at: &ehttps://shorturl.at/aILZ6&r");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&7]&r&7&m----------------------------------------------&r&7[");
    }

    public void ranks(Player p) {
        sendCenteredMessage(p, "&7]&r&7&m----------------&r&7[ &r&e&l&nRanks &r&7]&r&7&m----------------&r&7[");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&eRank Name &7| &bVote Count &7| &aHomes &7| &cClaim Blocks p/h");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&ePeasant &7| &b0 Votes &7| &a1 Homes &7| &c100 p/h");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eFarmer &7| &b3 Votes &7| &a2 Homes &7| &c110 p/h");
        sendCenteredMessage(p, "&7Unlocks: &a/Workbench");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eCommoner &7| &b9 Votes &7| &a3 Homes &7| &c125 p/h");
        sendCenteredMessage(p, "&7Unlocks: &a/Enderchest");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eSquire &7| &b27 Votes &7| &a4 Homes &7| &c150 p/h");
        sendCenteredMessage(p, "&7Unlocks: &aShop Plot");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eKnight &7| &b48 Votes &7| &a5 Homes &7| &c175 p/h");
        sendCenteredMessage(p, "&7Unlocks: &a/Back");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eCommander &7| &b90 Votes &7| &a6 Homes &7| &c200 p/h");
        sendCenteredMessage(p, "&7Unlocks: &aSilk Touch Spawners");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eLord &7| &b150 Votes &7| &a7 Homes &7| &c250 p/h");
        sendCenteredMessage(p, "&7Unlocks: &a/Spawner [Type]");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eBaron &7| &b300 Votes &7| &a8 Homes &7| &c350 p/h");
        sendCenteredMessage(p, "&7Unlocks: &a/Feed&7, &a/Condense&7, &a/Top");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eEmperor &7| &b660 Votes &7| &a9 Homes &7| &c500 p/h");
        sendCenteredMessage(p, "&7Unlocks: &a/Repair Hand &7(15 Min Cooldown)");
        sendCenteredMessage(p, "");
        sendCenteredMessage(p, "&eDeity &7| &b999 Votes &7| &a10 Homes &7| &c750 p/h");
        sendCenteredMessage(p, "&7Unlocks: &a/Repair All");
        chatNoPrefix(p, "");
        sendCenteredMessage(p, "&7]&r&7&m----------------------------------------------&r&7[");
    }

    public void chatNoPrefix(Player p, String msg) {
        p.sendMessage(colorize(msg + "&r"));
    }

    public String colorize(String msg) {
        return translateHexColorCodes(
                "&#",
                "",
                org.bukkit.ChatColor.translateAlternateColorCodes('&', msg));
    }

    public String translateHexColorCodes(String startTag, String endTag, String message)
    {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            char COLOR_CHAR = ChatColor.COLOR_CHAR;
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    private final int CENTER_PX = 154;

    public void sendCenteredMessage(Player player, String message) {
        if(message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
            }else if(previousCode){
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb + message);
    }
}
