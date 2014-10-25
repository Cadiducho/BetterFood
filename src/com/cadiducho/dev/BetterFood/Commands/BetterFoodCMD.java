package com.cadiducho.dev.BetterFood.Commands;

import com.cadiducho.dev.BetterFood.BetterFood;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BetterFoodCMD implements CommandExecutor {
    
    public static BetterFood plugin;
    
    public BetterFoodCMD(BetterFood instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) { 
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("setfood") && player.hasPermission("betterfood.admin.setfood")) {
                    if (args.length != 1) {
                        Player user = player.getServer().getPlayer(args[2]);
                        user.setFoodLevel(Integer.parseInt(args[1]));
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "Use /BetterFood setfood <foodLevel> <player>!");  
                    }
                } else if (args[0].equalsIgnoreCase("reset") && player.hasPermission("betterfood.admin.reset")) {
                    if (args.length != 1) {
                        Player pReset = plugin.getServer().getPlayer(args[1]);
                        if(pReset != null){
                            //plugin.reset(plugin.getServer().getPlayer(args[1]));
                        } else {
                            sender.sendMessage(ChatColor.RED + "This player is not online!");  
                        }
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "Use /BetterFood reset <player>!");  
                    }    
                } else { sender.sendMessage(ChatColor.GREEN + "Use /BetterFood <setfood|reset|anotherArg> ");}
            } else { sender.sendMessage(ChatColor.GREEN + "Use /BetterFood <setfood|reset|anotherArg> ");}     
	} else { sender.sendMessage(ChatColor.RED + "This command only can be executable by a player"); }
        return true;
    }
    
}
