package me.devallenalt.weteams.commands.subcommands;

import me.devallenalt.weteams.WeTeams;
import org.bukkit.entity.Player;

public class ChatCommand implements SubCommand {

    private final WeTeams plugin;

    public ChatCommand(WeTeams plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Player p, String[] args) {
        if (!plugin.getTeamManager().hasTeam(p)) {
            p.sendMessage(plugin.getPrefix() + "§cYou are not in a team.");
            return;
        }

        // Toggle team chat (this would require a boolean field to track if the chat is enabled
