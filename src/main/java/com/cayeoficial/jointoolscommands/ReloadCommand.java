package com.cayeoficial.jointoolscommands;

import com.cayeoficial.JoinTools;
import com.cayeoficial.helpers.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private JoinTools plugin;
    public ReloadCommand(JoinTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        /*
        * Config File
        */
        FileConfiguration config = plugin.getConfig();

        /*
        * Check if it's executed by console
        */
        if(!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(config.getString("Messages.reload-message"));
            plugin.reloadConfig();
        } else {
            Player player = (Player) sender;
            if(player.hasPermission("JoinTools.admin")) {
                plugin.reloadConfig();
                player.sendMessage(Colorize.colorize(player, config.getString("Messages.reload-message")));
            } else {
                player.sendMessage(Colorize.colorize(player, config.getString("Messages.no-permission-error")));
            }

        }
        return true;
    }

}
