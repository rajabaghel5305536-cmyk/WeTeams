package me.devallenalt.weteams.commands;

import me.devallenalt.weteams.WeTeams;
import me.devallenalt.weteams.commands.subcommands.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeamCommand implements CommandExecutor {

    private final HashMap<String, SubCommand> subCommands = new HashMap<>();

    public TeamCommand(WeTeams plugin) {
        subCommands.put("create", new CreateCommand(plugin));
        subCommands.put("disband", new DisbandCommand(plugin));
        // Add others similarly
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Players only!");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage("§b/team help §7for commands.");
            return true;
        }

        SubCommand sub = subCommands.get(args[0].toLowerCase());
        if (sub == null) {
            p.sendMessage("§cUnknown subcommand!");
            return true;
        }

        sub.execute(p, args);
        return true;
    }
}
