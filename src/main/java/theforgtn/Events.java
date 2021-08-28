package theforgtn;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;


public class Events implements Listener {
    // HashMap <= playerData
    private HashMap<String, Double> data = new HashMap<String, Double>();
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        // Main exclusions
        if(player.isGliding()) {
            // Chunk load section
            if (50 < player.getLocation().getChunk().getInhabitedTime()){
                data.put(player + "x", player.getLocation().getX());
                data.put(player + "y", player.getLocation().getY());
                data.put(player + "z", player.getLocation().getZ());
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Lassabban! Túl gyorsan haladsz!"));
                player.teleport(new Location(event.getPlayer().getWorld(), data.get(player + "x"), data.get(player + "y"), data.get(player + "z"), player.getLocation().getYaw(), player.getLocation().getPitch()));
            }
            // AntiCheat Section
            if (2.2F < data.get(player + "deltaXZ") && data.get(player + "deltaXZ") == ((double) Math.sqrt(Math.pow(event.getTo().getX() - event.getFrom().getX(), 2) + Math.pow(event.getTo().getZ() - event.getFrom().getZ(), 2)))) {
                player.teleport(new Location(event.getPlayer().getWorld(), data.get(player + "setback_x"), data.get(player + "setback_y"), data.get(player + "setback_z"), player.getLocation().getYaw(), player.getLocation().getPitch()));
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Lassabban! Túl gyorsan haladsz!"));
            } else if(data.get(player + "deltaXZ") < 2.2F){
                data.put(player + "setback_x", player.getLocation().getX());
                data.put(player + "setback_y", player.getLocation().getY());
                data.put(player + "setback_z", player.getLocation().getZ());
            }
            // Last tick data
            data.put(player + "deltaXZ", (double) Math.sqrt(Math.pow(event.getTo().getX() - event.getFrom().getX(), 2) + Math.pow(event.getTo().getZ() - event.getFrom().getZ(), 2)));

        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        data.put(player+"x", player.getLocation().getX());
        data.put(player+"y", player.getLocation().getY());
        data.put(player+"z", player.getLocation().getZ());
        data.put(player + "setback_x", player.getLocation().getX());
        data.put(player + "setback_y", player.getLocation().getY());
        data.put(player + "setback_z", player.getLocation().getZ());
        data.put(player + "deltaXZ", 0.0);
        data.put(player + "lastDeltaY", 0.0);
    }
}
