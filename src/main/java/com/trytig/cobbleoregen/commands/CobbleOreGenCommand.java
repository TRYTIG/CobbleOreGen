package com.trytig.cobbleoregen.commands;

import com.trytig.cobbleoregen.CobbleOreGen;
import com.trytig.cobbleoregen.events.CobbleGenerationEvent;
import com.trytig.cobbleoregen.utils.TextUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CobbleOreGenCommand implements CommandExecutor {
    CobbleOreGen plugin;

    public CobbleOreGenCommand(CobbleOreGen cobbleOreGen) {
        plugin = cobbleOreGen;
        plugin.getCommand("cobbleoregen").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            // Help command
            sender.sendMessage(TextUtils.translateColor("&cUsage: \"/cobbleoregen reload\""));
            return true;
        }

        if ("reload".equals(args[0])) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("cobbleoregen.reload")) {
                    // Player doesn't have the reload permission
                    sender.sendMessage(TextUtils.translateColor("&cYou do not have permission to execute this command."));
                    return true;
                }
            }
            CobbleGenerationEvent.reloadConfig(plugin);
            sender.sendMessage(TextUtils.translateColor("&aThe config has been reloaded"));
            return true;
        }

        sender.sendMessage(TextUtils.translateColor("&cInvalid usage, use \"/cobbleoregen\" for usage."));
        return true;
    }
}
