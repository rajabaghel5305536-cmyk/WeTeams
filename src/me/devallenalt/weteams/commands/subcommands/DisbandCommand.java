package me.devallenalt.weteams.commands.subcommands;

import me.devallenalt.weteams.WeTeams;
import me.devallenalt.weteams.models.Team;
import org.bukkit.entity.Player;

public class DisbandCommand implements SubCommand {

    private final WeTeams plugin;

    public DisbandCommand(WeTeams plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player p, String[] args) {
        if (!plugin.getTeamManager().hasTeam(p)) {
            p.sendMessage(plugin.getPrefix() + "§cYou are not in a team!");
            return;
        }

        Team team = plugin.getTeamManager().getTeam(p);
        if (team.getOwner() != p.getUniqueId()) {
            p.sendMessage(plugin.getPrefix() + "§cOnly the team owner can disband the team.");
            return;
        }

        // Disband the team
        plugin.getTeamManager().disbandTeam(p);
        p.sendMessage(plugin.getPrefix() + "§cYour team has been disbanded.");
    }
}
