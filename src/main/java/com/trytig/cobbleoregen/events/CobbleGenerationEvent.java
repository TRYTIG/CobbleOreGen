package com.trytig.cobbleoregen.events;

import com.trytig.cobbleoregen.CobbleOreGen;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CobbleGenerationEvent implements Listener {
    private static List<Material> ores = new ArrayList<>();
    private static Random random = new Random();

    public CobbleGenerationEvent(CobbleOreGen plugin) {
        // Reload config
        reloadConfig(plugin);

        // Register the event
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private final BlockFace[] blockFaces = new BlockFace[]{
            BlockFace.SELF,
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST
    };

    @EventHandler
    public void onFromTo(BlockFromToEvent event) {
        Material block = event.getBlock().getType();
        if (block.equals(Material.WATER) || block.equals(Material.LAVA)) {
            Block toBlock = event.getToBlock();
            if (toBlock.getType().equals(Material.AIR)) {
                Material inverseBlock = (block == Material.WATER ? Material.LAVA : Material.WATER);
                for (BlockFace blockFace : blockFaces) {
                    Block relativeBlock = toBlock.getRelative(blockFace, 1);
                    if (relativeBlock.getType().equals(inverseBlock)) {
                        event.setCancelled(true);
                        event.getToBlock().setType(ores.get(random.nextInt(ores.size())));
                    }
                }
            }
        }
    }

    public static void reloadConfig(CobbleOreGen plugin) {
        ores.clear();
        plugin.reloadConfig();
        FileConfiguration configuration = plugin.getConfig();
        for (String oreMaterialName : configuration.getStringList("ores")) {
            ores.add(Material.valueOf(oreMaterialName));
        }
    }
}