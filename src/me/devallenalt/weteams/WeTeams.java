package me.devallenalt.weteams;

import me.devallenalt.weteams.commands.TeamCommand;
import me.devallenalt.weteams.data.TeamManager;
import me.devallenalt.weteams.utils.ChatUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class WeTeams extends JavaPlugin {

    private static WeTeams instance;
    private TeamManager teamManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        teamManager = new TeamManager(this);
        getCommand("team").setExecutor(new TeamCommand(this));

        getLogger().info("WeTeams v1.0.0 enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("WeTeams disabled!");
    }

    public static WeTeams getInstance() {
        return instance;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public String getPrefix() {
        return ChatUtil.colorize(getConfig().getString("prefix"));
    }
}
