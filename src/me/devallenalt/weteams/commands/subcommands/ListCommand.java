package me.devallenalt.weteams.commands.subcommands;

import me.devallenalt.weteams.WeTeams;
import org.bukkit.entity.Player;

public class ListCommand implements SubCommand {

    private final WeTeams plugin;

    public ListCommand(WeTeams plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player p, String[] args) {
        // For simplicity, let's just list the teams by their name
        p.sendMessage(plugin.getPrefix() + "§bTop Teams:");

        plugin.getTeamManager().getTeams().forEach((name, team) -> {
            p.sendMessage("§b" + team.getName() + " §7- §a" + team.getBalance() + " balance");
        });
    }
}
