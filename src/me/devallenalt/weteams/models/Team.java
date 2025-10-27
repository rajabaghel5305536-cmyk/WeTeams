package me.devallenalt.weteams.models;

import org.bukkit.entity.Player;
import java.util.*;

public class Team {
    private final String name;
    private UUID owner;
    private final Set<UUID> members = new HashSet<>();
    private double balance;
    private String homeWorld;
    private double homeX, homeY, homeZ;

    public Team(String name, UUID owner) {
        this.name = name;
        this.owner = owner;
        this.members.add(owner);
    }

    public String getName() { return name; }
    public UUID getOwner() { return owner; }
    public Set<UUID> getMembers() { return members; }

    public void addMember(Player p) { members.add(p.getUniqueId()); }
    public void removeMember(Player p) { members.remove(p.getUniqueId()); }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public void setHome(String world, double x, double y, double z) {
        this.homeWorld = world;
        this.homeX = x;
        this.homeY = y;
        this.homeZ = z;
    }
}
