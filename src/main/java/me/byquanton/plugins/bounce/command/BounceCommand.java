package me.byquanton.plugins.bounce.command;

import me.byquanton.plugins.bounce.BouncePlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BounceCommand implements CommandExecutor {

    private final BouncePlugin plugin;

    public BounceCommand(BouncePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("bounce.use")){
            sender.sendMessage(ChatColor.RED+"You do not have permission to use this command");
            return true;
        }
        if(args.length == 0){
            if(sender.hasPermission("bounce.use")){
                if(sender instanceof Player player){
                    if(plugin.isProtectedPlayer(player)) {
                        plugin.removeProtectedPlayer(player);
                        sender.sendMessage(ChatColor.GREEN + "You are now unprotected");
                    }else{
                        plugin.addProtectedPlayer(player);
                        sender.sendMessage(ChatColor.GREEN + "You are now protected");
                    }
                }else{
                    sender.sendMessage(ChatColor.RED+ "Usage: /bounce <player>");
                }
            }
            return true;
        }
        if(args.length == 1){
            if(sender.hasPermission("bounce.use")){
                Player playerExact = plugin.getServer().getPlayerExact(args[0]);
                if(playerExact == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found");
                    return true;
                }
                if(plugin.isProtectedPlayer(playerExact)) {
                    plugin.removeProtectedPlayer(playerExact);
                    sender.sendMessage(ChatColor.GREEN + "Player "+playerExact.getName()+" is now unprotected");
                }else{
                    plugin.addProtectedPlayer(playerExact);
                    sender.sendMessage(ChatColor.GREEN + "Player "+playerExact.getName()+" is now protected");
                }
            }
        }
        return true;
    }
}
