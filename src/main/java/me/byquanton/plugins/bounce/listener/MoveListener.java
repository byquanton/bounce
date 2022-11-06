package me.byquanton.plugins.bounce.listener;

import me.byquanton.plugins.bounce.BouncePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {
    private final BouncePlugin plugin;
    public MoveListener(BouncePlugin bouncePlugin) {
        this.plugin = bouncePlugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(plugin.isProtectedPlayer(event.getPlayer())){
            event.getPlayer().getNearbyEntities(2,2,2).forEach(entity -> {
                if(entity instanceof Player player){
                    if(!player.hasPermission("bounce.bypass")){
                        player.setVelocity(player.getLocation().getDirection().multiply(-1).setY(0));
                    }
                }
            });
        }

        if(event.getPlayer().hasPermission("bounce.bypass")) {
            return;
        }
        event.getTo().getNearbyEntities(2,2,2).stream().
            filter(e -> {
                if (e instanceof Player player) {
                    if(!player.hasPermission("bounce.bypass")){
                        return plugin.isProtectedPlayer(player)&&!event.getPlayer().equals(player);
                    }
                    return false;
                }else{
                    return false;
                }
            }).forEach(entity -> {
                double distance_to = entity.getLocation().distance(event.getTo());
                double distance_from = entity.getLocation().distance(event.getFrom());
                if(distance_to < distance_from) {
                    event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(-1).setY(0));
                }
        });
    }
}
