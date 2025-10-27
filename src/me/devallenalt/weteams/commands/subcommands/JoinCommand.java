package me.devallenalt.weteams.commands.subcommands;

import me.devallenalt.weteams.WeTeams;
import me.devallenalt.weteams.models.Team;
import org.bukkit.entity.Player;

public class JoinCommand implements SubCommand {

    private final WeTeams plugin;

    public JoinCommand(WeTeams plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(plugin.getPrefix() + "§cUsage: /team join <team_name>");
            return;
        }

        String teamName = args[1];
        Team team = plugin.getTeamManager().getTeamByName(teamName);
        
        if (team == null) {
            p.sendMessage(plugin.getPrefix() + "§cTeam not found!");
            return;
        }

        if (plugin.getTeamManager().hasTeam(p)) {
            p.sendMessage(plugin.getPrefix() + "§cYou are already in a team!");
            return;
        }

        // Add player to team
        team.addMember(p);
        p.sendMessage(plugin.getPrefix() + "§aYou have joined team " + teamName + "!");
    }
}
