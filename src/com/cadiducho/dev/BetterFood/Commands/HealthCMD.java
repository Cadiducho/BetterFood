package com.cadiducho.dev.BetterFood.Commands;

import com.cadiducho.dev.BetterFood.BetterFood;
import com.cadiducho.dev.BetterFood.util.Stats;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HealthCMD implements CommandExecutor {
    
    public static BetterFood plugin;
    
    public HealthCMD(BetterFood instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) { 
        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch(args.length){
                case 0:
                    Stats.show(player);
                    break;
                case 1:
                    switch(args[0].toString()){
                        case "help":
                            plugin.getMetodos().menuAyuda(player);
                            break;
                        case "ayuda":
                            plugin.getMetodos().menuAyudaEs(player);
                            break;
                        default:
                            sender.sendMessage(ChatColor.GREEN + "Use /health <help|ayuda> for commands help");
                            break; 
                    }
                    break;
                default:
                    sender.sendMessage(ChatColor.GREEN + "Use /health for stats, or /health help for commands help");
                    break;
                }
            } else { sender.sendMessage(ChatColor.RED + "This command only can be executable by a player"); }
        return true;
    }
    
    
}
