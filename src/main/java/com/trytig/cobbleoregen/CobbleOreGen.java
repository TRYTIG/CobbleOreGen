package com.trytig.cobbleoregen;

import com.trytig.cobbleoregen.events.CobbleGenerationEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CobbleOreGen extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Register Events
        new CobbleGenerationEvent(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
