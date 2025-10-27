package me.devallenalt.weteams.commands.subcommands;

import me.devallenalt.weteams.WeTeams;
import me.devallenalt.weteams.models.Team;
import org.bukkit.entity.Player;

public class InviteCommand implements SubCommand {

    private final WeTeams plugin;

    public InviteCommand(WeTeams plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(plugin.getPrefix() + "§cUsage: /team invite <player_name>");
            return;
        }

        String targetName = args[1];
        Player target = plugin.getServer().getPlayer(targetName);
        
        if (target == null) {
            p.sendMessage(plugin.getPrefix() + "§cPlayer not found!");
            return;
        }

        if (!plugin.getTeamManager().hasTeam(p)) {
            p.sendMessage(plugin.getPrefix() + "§cYou must be in a team to invite others.");
            return;
        }

        Team team = plugin.getTeamManager().getTeam(p);

        if (team.getOwner() != p.getUniqueId()) {
            p.sendMessage(plugin.getPrefix() + "§cOnly the team owner can invite players.");
            return;
        }

        team.addMember(target);
        target.sendMessage(plugin.getPrefix() + "§aYou have been invited to join team " + team.getName() + "!");
        p.sendMessage(plugin.getPrefix() + "§aYou invited " + targetName + " to your team.");
    }
}
