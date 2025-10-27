package me.devallenalt.weteams.utils;

import org.bukkit.ChatColor;

public class ChatUtil {
    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
