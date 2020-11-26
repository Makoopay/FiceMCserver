package com.makoopay.powersplugin.powersplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PowerEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (p.getItemInHand().getType() == Material.WOOD_SWORD) {
                p.sendMessage(ChatColor.GREEN + " +1 Points ");


                event.getPlayer().spawnParticle(Particle.LAVA, event.getPlayer().getLocation(), 10);
            }
        }
    }
}
