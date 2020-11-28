package com.makoopay.powersplugin.powersplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;


public class PowerEvent implements Listener {
    
    

    private final HashMap<UUID, Integer> points = new HashMap<>();
    

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();


        // add the player to the hashmap if missing
        points.putIfAbsent(event.getPlayer().getUniqueId(), 0);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("FiceMC", "Point Counter");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "§f§l   FicePoints   §f");

        Team team = scoreboard.registerNewTeam("point_counter");

        team.addEntry(ChatColor.WHITE.toString());

        team.setPrefix(ChatColor.RED + "Points: " + ChatColor.WHITE);

        team.setSuffix( String.valueOf( points.get( event.getPlayer().getUniqueId() ) ) );

        objective.getScore(ChatColor.WHITE.toString()).setScore(1);

        player.setScoreboard(scoreboard);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {

            if (p.getInventory().getItemInMainHand().getType() == Material.WOOD_SWORD) {

                p.sendMessage(ChatColor.GREEN + " +1 Points ");

                event.getPlayer().spawnParticle(Particle.LAVA, event.getPlayer().getLocation(), 10);

                Scoreboard scoreboard = p.getScoreboard();

                Team team = scoreboard.getTeam("point_counter");

                points.put(
                        event.getPlayer().getUniqueId(),
                        points.get( event.getPlayer().getUniqueId() ) + 1
                );

                team.setSuffix( String.valueOf( points.get( event.getPlayer().getUniqueId() ) ) );


            }

        }
    }
}
