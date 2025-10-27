package me.devallenalt.weteams.commands.subcommands;

import me.devallenalt.weteams.WeTeams;
import me.devallenalt.weteams.models.Team;
import org.bukkit.entity.Player;

public class BalanceCommand implements SubCommand {

    private final WeTeams plugin;

    public BalanceCommand(WeTeams plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player p, String[] args) {
        if (!plugin.getTeamManager().hasTeam(p)) {
            p.sendMessage(plugin.getPrefix() + "§cYou are not in a team.");
            return;
        }

        Team team = plugin.getTeamManager().getTeam(p);
        p.sendMessage(plugin.getPrefix() + "§bYour team balance: §a" + team.getBalance());
    }
}
