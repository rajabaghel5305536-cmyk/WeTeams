package me.devallenalt.weteams.gui;

import me.devallenalt.weteams.WeTeams;
import me.devallenalt.weteams.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ConfirmCreateGUI implements Listener {

    private final WeTeams plugin;

    public ConfirmCreateGUI(WeTeams plugin) {
        this.plugin = plugin;
    }

    public void open(Player player, String teamName) {
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.colorize("&8[&bTeam Creation&8]"));

        // Create "Confirm" button
        ItemStack confirmItem = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirmMeta = confirmItem.getItemMeta();
        assert confirmMeta != null;
        confirmMeta.setDisplayName(ChatUtil.colorize("&aConfirm"));
        confirmItem.setItemMeta(confirmMeta);

        // Create "Cancel" button
        ItemStack cancelItem = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelMeta = cancelItem.getItemMeta();
        assert cancelMeta != null;
        cancelMeta.setDisplayName(ChatUtil.colorize("&cCancel"));
        cancelItem.setItemMeta(cancelMeta);

        // Place the items in the inventory
        inv.setItem(3, confirmItem);
        inv.setItem(5, cancelItem);

        // Open the inventory for the player
        player.openInventory(inv);

        // Store team name temporarily for later use in event handling
        plugin.getServer().getScheduler().runTask(plugin, () -> player.getPersistentDataContainer().set(plugin.getNamespacedKey("temp_team_name"), String.class, teamName));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Inventory inv = event.getInventory();
            if (!inv.getName().equals(ChatUtil.colorize("&8[&bTeam Creation&8]"))) return;

            event.setCancelled(true);  // Prevent item pick-up or movement

            // Get the team name from persistent data
            String teamName = player.getPersistentDataContainer().get(plugin.getNamespacedKey("temp_team_name"), String.class);
            if (teamName == null) return;  // No team name, return

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                if (event.getCurrentItem().getType() == Material.GREEN_WOOL) {
                    // Confirm the creation of the team
                    plugin.getTeamManager().createTeam(player, teamName);
                    player.sendMessage(plugin.getPrefix() + "§aTeam §b" + teamName + " §acreated successfully!");
                } else if (event.getCurrentItem().getType() == Material.RED_WOOL) {
                    // Cancel the creation
                    player.sendMessage(plugin.getPrefix() + "§cTeam creation cancelled.");
                }
                player.closeInventory();  // Close the inventory after selection
            }
        }
    }
}
