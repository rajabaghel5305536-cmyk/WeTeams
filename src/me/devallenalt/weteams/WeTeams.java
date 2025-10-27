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

        // Register Commands
        registerCommands();

        getlogger().info("WeTeam v1.0.0 enabled!");
    }

    private void registerCommands() {
        // Register each subcommand under /team
        getCommand("team").setExecutor(new TeamCommand(this));
    {

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
