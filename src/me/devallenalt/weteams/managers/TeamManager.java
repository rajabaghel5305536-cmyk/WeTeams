package me.devallenalt.weteams.data;

import me.devallenalt.weteams.models.Team;
import org.bukkit.entity.Player;
import java.util.*;

public class TeamManager {
    private final Map<String, Team> teams = new HashMap<>();
    private final Map<UUID, String> playerTeams = new HashMap<>();

    public TeamManager(Object plugin) {}

    public boolean hasTeam(Player p) {
        return playerTeams.containsKey(p.getUniqueId());
    }

    public Team getTeam(Player p) {
        return teams.get(playerTeams.get(p.getUniqueId()));
    }

    public void createTeam(Player owner, String name) {
        Team t = new Team(name, owner.getUniqueId());
        teams.put(name, t);
        playerTeams.put(owner.getUniqueId(), name);
    }

    public void disbandTeam(Player p) {
        Team team = getTeam(p);
        if (team != null) {
            for (UUID member : team.getMembers()) {
                playerTeams.remove(member);
            }
            teams.remove(team.getName());
        }
    }
}
