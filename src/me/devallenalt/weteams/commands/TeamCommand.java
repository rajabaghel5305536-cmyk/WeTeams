package me.devallenalt.weteams.commands;

import me.devallenalt.weteams.WeTeams;
import me.devallenalt.weteams.commands.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TeamCommand implements CommandExecutor {

    private final WeTeams plugin;
    private final Map<String, SubCommand> subCommands = new HashMap<>();

    public TeamCommand(WeTeams plugin) {
        this.plugin = plugin;

        // Register all subcommands
        subCommands.put("create", new CreateCommand(plugin));
        subCommands.put("disband", new DisbandCommand(plugin));
        subCommands.put("join", new JoinCommand(plugin));
        subCommands.put("invite", new InviteCommand(plugin));
        subCommands.put("kick", new KickCommand(plugin));
        subCommands.put("bal", new BalanceCommand(plugin));
        subCommands.put("list", new ListCommand(plugin));
        subCommands.put("chat", new ChatCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getPrefix() + "§cThis command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            // Show the main help if no arguments
            showHelp(player);
            return true;
        }

        String subCommandName = args[0].toLowerCase();

        if (subCommands.containsKey(subCommandName)) {
            SubCommand subCommand = subCommands.get(subCommandName);
            subCommand.execute(player, args);
        } else {
            player.sendMessage(plugin.getPrefix() + "§cUnknown command. Type /team for help.");
        }

        return true;
    }

    private void showHelp(Player player) {
        player.sendMessage(plugin.getPrefix() + "§bTeam Commands:");
        player.sendMessage("§7/team create <name> - Create a new team");
        player.sendMessage("§7/team disband - Disband your team");
        player.sendMessage("§7/team join <name> - Join a team");
        player.sendMessage("§7/team invite <player> - Invite a player to your team");
        player.sendMessage("§7/team kick <player> - Kick a player from your team");
        player.sendMessage("§7/team bal - Show team balance");
        player.sendMessage("§7/team list - Show the top teams");
        player.sendMessage("§7/team chat - Toggle team chat");
    }
}
