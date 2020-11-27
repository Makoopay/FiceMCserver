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
    
    
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("FiceMC", "Point Counter");
        objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        objective.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "§f§l   FicePoints   §f");

        Team team = scoreboard.registerNewTeam("point_counter");

        team.addEntry(ChatColor.WHITE.toString());

        team.setPrefix(ChatColor.RED + "Points: " + ChatColor.WHITE);

        team.setSuffix("0");

        objective.getScore(ChatColor.WHITE.toString()).setScore(1);

        player.setScoreboard(scoreboard);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();


        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {

            if (p.getItemInHand().getType() == Material.YELLOW_FLOWER) {

                p.sendMessage(ChatColor.GREEN + " +1 Points ");

                event.getPlayer().spawnParticle(Particle.LAVA, event.getPlayer().getLocation(), 10);

                Scoreboard scoreboard = p.getScoreboard();

                Team team = scoreboard.getTeam("point_counter");

                int points = Integer.parseInt(team.getSuffix());

                team.setSuffix(Integer.toString(points + 1));

                p.getInventory().remove(p.getItemInHand());



            }
        }
    }

}
