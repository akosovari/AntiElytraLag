package theforgtn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {
    // Startup logics
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        getLogger().info("AntiElytraLag enabled!");
    }
    // Shutdown logics
    @Override
    public void onDisable() {
        getLogger().info("AntiElytraLag disabled!");
    }
    // Debug commands
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ael")) {
            PluginDescriptionFile pdf = this.getDescription();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&3⌛&8] &7AntiElytraLag " + pdf.getVersion() + " is running."));

        }
        return false;
    }
}
