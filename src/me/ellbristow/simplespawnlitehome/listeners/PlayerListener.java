package me.ellbristow.simplespawnlitehome.listeners;

import me.ellbristow.simplespawnlitehome.SimpleSpawnLiteHome;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerListener implements Listener {
    
    private SimpleSpawnLiteHome plugin;
    
    public PlayerListener(SimpleSpawnLiteHome instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        event.setRespawnLocation(plugin.getHomeLoc(player));
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.isCancelled()) return;
        
        Player player = event.getPlayer();

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.BED_BLOCK)) {
            if (plugin.getSetting("SetHomeWithBeds")) {
                plugin.setBedLoc(player);
                player.sendMessage(ChatColor.GOLD + "Your home has been set to this bed location!");
            }
        }
    }
    
}
