package com.cadiducho.dev.BetterFood.main;

import java.util.HashMap;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Comandos implements CommandExecutor{
     public static BetterFood plugin;
     public static String command;
     /* TEST */
        private Scoreboard board;
        private Objective o;
        private final HashMap<OfflinePlayer, Score> scores = new HashMap<>();
        private boolean sc = false;
       



     @Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {        
		if (commandLabel.equalsIgnoreCase("betterfood")) {
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
					}
                                        else if (args[0].equalsIgnoreCase("reset") && player.hasPermission("betterfood.admin.reset")) {
                                            if (args.length != 1) {
                                            String argument = args[1];
                                            Player pReset = getServer().getPlayerExact(argument);
                                                if(pReset != null){
                                                plugin.reset(pReset);
                                                } else {
                                                    sender.sendMessage(ChatColor.RED + "This player is not online!");  
                                                }
                                            } else {
                                              sender.sendMessage(ChatColor.GREEN + "Use /BetterFood reset <player>!");  
                                            }    
                                        } else { sender.sendMessage(ChatColor.GREEN + "Use /BetterFood <setfood|reset|anotherArg> ");}
                              } else { sender.sendMessage(ChatColor.GREEN + "Use /BetterFood <setfood|reset|anotherArg> ");}


                                
			} else { sender.sendMessage(ChatColor.RED + "This command only can be executable by a player"); }
		}
                if (commandLabel.equalsIgnoreCase("salud") || commandLabel.equalsIgnoreCase("health")) {
                    if (sender instanceof Player) {
				Player player = (Player) sender;
                                        if (args[0].equalsIgnoreCase("help")) {
                                            menuAyuda(sender);
                                        }
                                        if (args[0].equalsIgnoreCase("ayuda")) {
                                            menuAyudaEs(sender);                                          
                                        }
                                if (args.length > 3){
                                    sender.sendMessage(ChatColor.GREEN + "Use /health help for command help");
				if (args.length <= 0){
                                    Stats.show(player);
                                }
                           }
                    } else { sender.sendMessage(ChatColor.RED + "This command only can be executable by a player"); }
                }
		
		return false;
	}
     
        private void menuAyuda(CommandSender sender) {
		sender.sendMessage(ChatColor.GREEN + "**" + ChatColor.YELLOW + "-------------{ " +
				ChatColor.GOLD +	"BetterFood Help" + ChatColor.YELLOW + " }------------" + ChatColor.GREEN + "**");
            sender.sendMessage(ChatColor.GREEN + "/health ayuda ► Ayuda en español/Help in Spanish");
            sender.sendMessage(ChatColor.GREEN + "/health ► View your health stats");
            if (sender.hasPermission("betterfood.admin")) {
            sender.sendMessage(ChatColor.GOLD + "[ADMIN]" + ChatColor.YELLOW + "/health reset <player> ► Reset someone stats");
            sender.sendMessage(ChatColor.GOLD +  "[ADMIN]" + ChatColor.YELLOW + "/health setfood <player> <int> ► Set someone stats");
            }
            sender.sendMessage(ChatColor.GREEN + "/health help ► Show this message");
        }
        private void menuAyudaEs(CommandSender sender) {
		sender.sendMessage(ChatColor.GREEN + "**" + ChatColor.YELLOW + "-------------{ " +
				ChatColor.GOLD +	"Ayuda de BetterFood" + ChatColor.YELLOW + " }------------" + ChatColor.GREEN + "**");
            sender.sendMessage(ChatColor.GREEN + "/salud help ► Help in English");
            sender.sendMessage(ChatColor.GREEN + "/salud ► Ver tus stats de salud");
            if (sender.hasPermission("betterfood.admin")) {
            sender.sendMessage(ChatColor.GOLD + "[ADMIN]" + ChatColor.YELLOW + "/salud reset <jugador> ► Reiniciar las stats de alguien");
            sender.sendMessage(ChatColor.GOLD +  "[ADMIN]" + ChatColor.YELLOW + "/salud setfood <jugador> <nºint> ► Cambiar las stats de alguien");
            }
            sender.sendMessage(ChatColor.GREEN + "/salud ayuda ► Ver este mensaje");
        }
}
                            /*   if (args[0].equalsIgnoreCase("sctest")) {
                                                            //Scoreboard 1.1Beta 
                                                            board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
               
                                                            o = board.registerNewObjective("test", "dummy");
                                                            o.setDisplayName("Health Stats");
                                                            o.setDisplaySlot(DisplaySlot.SIDEBAR);
                                                            int s = plugin.carbohidratos.get(player);
  
                                        }*/
                                     /*  if (args[0].equalsIgnoreCase("set") && player.hasPermission("betterfood.admin")) {
                                           if (args[1].equalsIgnoreCase("1")) {
                                              Player user = player.getServer().getPlayer(args[2]); 
                                              try {
                                              int a = Integer.parseInt(args[3]);
                                              proteinas.remove(user);
                                              proteinas.put(user, a);
                                              } catch (NumberFormatException e) {
					      sender.sendMessage(ChatColor.RED + args[3] + " is not a number!");
                                              }
                                           }
                                            //ToDo in BetterFood Beta.1.2
                                        } */