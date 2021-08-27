package theforgtn;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        getLogger().info("AntiElytraLag enabled!");
    }
    @Override
    public void onDisable() {
        getLogger().info("AntiElytraLag disabled!");
    }
}
