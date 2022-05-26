package com.cayeoficial.helpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Colorize {
    public static String colorize(Player player, String configOption) {
        return ChatColor.translateAlternateColorCodes('&', configOption.replaceAll("%player_name%", player.getName()));
    }
}
