package com.trytig.cobbleoregen;

import com.trytig.cobbleoregen.commands.CobbleOreGenCommand;
import com.trytig.cobbleoregen.events.CobbleGenerationEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class CobbleOreGen extends JavaPlugin {
    FileConfiguration configuration = this.getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Save config defaults
        saveDefaultConfig();

        // Register Events
        new CobbleGenerationEvent(this);

        // Register Commands
        new CobbleOreGenCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
