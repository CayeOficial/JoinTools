package com.cayeoficial.listeners;

import com.cayeoficial.helpers.Colorize;
import com.cayeoficial.JoinTools;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getServer;

public class playerJoin implements Listener {
    private final JoinTools plugin;
    public playerJoin(JoinTools plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        /*
        * Variables
        */
        Player player = e.getPlayer();
        FileConfiguration config = plugin.getConfig();

        /*
        * Check if it should send a message when
        * a player join, and send it if it should.
        */
        if(config.getBoolean("Config.send-message-on-join")) {
            player.sendMessage(Colorize.colorize(player, config.getString("Config.join-message")));
        }

        /*
        * Check if it should send titles and send
        * if it should
        */
        if(config.getBoolean("Titles.send-title-on-join")) {
            player.sendTitle(Colorize.colorize(player, config.getString("Titles.title")), Colorize.colorize(player, config.getString("Titles.subtitle")));
        }

        /*
        * Check if it should execute a command
        * when someone joins
        */
        if(config.getBoolean("Commands.execute-command-on-join")) {
            getServer().dispatchCommand(getServer().getConsoleSender(), config.getString("Commands.command-to-execute").replaceAll("%player_name%", player.getName()));
        }

        /*
        * Execute sounds
        */
        if(config.getBoolean("Sounds.play-sound-on-join")) {
            player.playSound(player.getLocation(), Sound.valueOf(config.getString("Sounds.sound-name")), 2.0f, 1.0f);
        }

    }
}
