package me.byquanton.plugins.bounce;

import me.byquanton.plugins.bounce.command.BounceCommand;
import me.byquanton.plugins.bounce.listener.MoveListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class BouncePlugin extends JavaPlugin {

    private final List<Player> protectedPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MoveListener(this), this);
        getCommand("bounce").setExecutor(new BounceCommand(this));
    }

    @Override
    public void onDisable() {

    }

    public void addProtectedPlayer(Player player) {
        protectedPlayers.add(player);
    }

    public void removeProtectedPlayer(Player player) {
        protectedPlayers.remove(player);
    }

    public boolean isProtectedPlayer(Player player) {
        return protectedPlayers.contains(player);
    }

    public void clearProtectedPlayers() {
        protectedPlayers.clear();
    }

    public List<Player> getProtectedPlayers() {
        return protectedPlayers;
    }
}
