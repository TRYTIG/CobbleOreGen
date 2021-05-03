package com.trytig.cobbleoregen.utils;

import org.bukkit.ChatColor;

public class TextUtils {
    public static String translateColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
