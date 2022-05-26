package com.cayeoficial;

import com.cayeoficial.jointoolscommands.ReloadCommand;
import com.cayeoficial.listeners.playerJoin;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class JoinTools extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
       getLogger().info("§aInitializing Join-Tools");
       getLogger().info("§aInitializing modules");
       try {
           createConfigurationFile();
           registerCommands();
           getServer().getPluginManager().registerEvents(this, this);
           registerEvents();
       } catch(Exception exception) {
          getLogger().warning("§cFailed to initialize the plugin.");
          getLogger().warning("§cThe stacktrace of the error is below, please include it when contacting support.");
          exception.printStackTrace();
       }

    }

    @Override
    public void onDisable() {
        getLogger().info("§cDisabling all the modules");
    }


    public void createConfigurationFile(){
        File config = new File(this.getDataFolder(),"config.yml");
        if(!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }

    public void registerCommands(){
        Objects.requireNonNull(this.getCommand("jtreload")).setExecutor(new ReloadCommand(this));
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new playerJoin(this), this);
    }
}

