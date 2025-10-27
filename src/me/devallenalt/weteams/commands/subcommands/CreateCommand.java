package me.devallenalt.weteams.commands.subcommands;

import me.devallenalt.weteams.WeTeams;
import me.devallenalt.weteams.gui.ConfirmCreateGUI;
import org.bukkit.entity.Player;

public class CreateCommand implements SubCommand {

    private final WeTeams plugin;

    public CreateCommand(WeTeams plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(plugin.getPrefix() + "§cUsage: /team create <name>");
            return;
        }

        String teamName = args[1];

        if (plugin.getTeamManager().hasTeam(p)) {
            p.sendMessage(plugin.getPrefix() + "§cYou already have a team!");
            return;
        }

        // Open the confirmation GUI for team creation
        new ConfirmCreateGUI(plugin).open(p, teamName);
    }
}
