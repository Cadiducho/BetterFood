package com.cadiducho.dev.BetterFood.main;

//Imports de Java
import com.cadiducho.dev.BetterFood.configs.Config;
import com.cadiducho.dev.BetterFood.configs.Mensajes;
import java.util.HashMap;
import java.util.logging.Logger;

//Imports del plugin
import com.cadiducho.dev.BetterFood.listeners.PlayerConsumeListener;
import com.cadiducho.dev.BetterFood.listeners.PlayerDeathListener;
import com.cadiducho.dev.BetterFood.listeners.PlayerJoinListener;
import com.cadiducho.dev.BetterFood.listeners.PlayerRespawnListener;
import java.util.logging.Level;

//Imports de Bukkit
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class BetterFood extends JavaPlugin {

	public static final Logger log = Logger.getLogger("Minecraft");
	
	
	public Stats s = new Stats(this);
	
	
	public PlayerJoinListener PlayerJoinListener = new PlayerJoinListener(this);
	public PlayerDeathListener PlayerDeathListener = new PlayerDeathListener(this);
	public PlayerConsumeListener PlayerConsumeListener = new PlayerConsumeListener(this);
	public PlayerRespawnListener PlayerRespawnListener = new PlayerRespawnListener(this);
	
	public HashMap<Player, Integer> hidratacion = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> carbohidratos = new HashMap<>();
	public HashMap<Player, Integer> cuentaCarbohidratos = new HashMap<>();
	public HashMap<Player, Integer> proteinas = new HashMap<>();
	public HashMap<Player, Integer> cuentaProteinas = new HashMap<>();
	public HashMap<Player, Integer> vitaminas = new HashMap<>();
	public HashMap<Player, Integer> cuentaVitaminas = new HashMap<>();
	public HashMap<Player, String> damageCause = new HashMap<>();
        
        public Updater updater;


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
        private static int value;
        public void reset(Player player) {
		value = 0;
		proteinas.remove(player);
		proteinas.put(player, value);
		vitaminas.remove(player);
		vitaminas.put(player, value);
		carbohidratos.remove(player);
		carbohidratos.put(player, value);
		hidratacion.remove(player);
		hidratacion.put(player, Constantes.COMIENZO_HIDRATACION);
	}
	@Override
	public void onEnable() {
		log.info("[BetterFood] Enabled!");
		
                if (!Config.configFile.exists()) { 
                    Config.save();
                }
               if (!Mensajes.mconfigFile.exists()) {
                   Mensajes.createConfig();
               }
                Config.load();
                Mensajes.load();
          	PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(PlayerJoinListener, this);
		manager.registerEvents(PlayerDeathListener, this);
		manager.registerEvents(PlayerConsumeListener, this);
		manager.registerEvents(PlayerRespawnListener, this);
		
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Timer(this), 20, 20);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			this.hidratacion.put(player, Constantes.COMIENZO_HIDRATACION);
			this.damageCause.put(player, null);
			this.carbohidratos.put(player, 0);
			this.cuentaCarbohidratos.put(player, Constantes.CUENTAATRAS_FALTA);
			this.proteinas.put(player, 0);
			this.cuentaProteinas.put(player, Constantes.CUENTAATRAS_FALTA);
			this.vitaminas.put(player, 0);
			this.cuentaVitaminas.put(player, Constantes.CUENTAATRAS_FALTA);
		}
                /*if (Config.UPDATER_CHECKER = true) {
                    Updater uc = new Updater(this, "http://dev.bukkit.org/bukkit-plugins/betterfood/files.rss");
                    if (uc.checkUpdate()) {
                    log.log(Level.INFO, "[BetterFood] A new update is available: {0}", uc.getVersion());
                    log.log(Level.INFO, "Get it from: {0}", uc.getLink());
                    }
                }*/

	}
	

        
	@Override
	public void onDisable() {
		log.info("[BetterFood] Disables/Desactivado!!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
     
		if (commandLabel.equalsIgnoreCase("salud") || (commandLabel.equalsIgnoreCase("health")) || (commandLabel.equalsIgnoreCase("betterfood"))) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("setfood") && player.hasPermission("betterfood.admin")) {
                                            Player user = player.getServer().getPlayer(args[2]);
                                            
						user.setFoodLevel(Integer.parseInt(args[1]));
					} else { sender.sendMessage(ChatColor.GREEN + "Use /health setfood <int> <player>");}
                                        if (args[0].equalsIgnoreCase("reset") && player.hasPermission("betterfood.admin") &&
							args.length >= 2) {
						reset(getServer().getPlayer(args[1]));  
                                               
					} else { sender.sendMessage(ChatColor.GREEN + "Use /health reset <player>");}
                                        if (args[0].equalsIgnoreCase("help")) {
                                            menuAyuda(sender);
                                        }
                                        if (args[0].equalsIgnoreCase("ayuda")) {
                                            menuAyudaEs(sender);                                          
                                        }
                                       /* if (args[0].equalsIgnoreCase("set") && player.hasPermission("betterfood.admin")) {
                                            
                                        } *///To Do 
				} if (args.length > 3){
                                    sender.sendMessage(ChatColor.GREEN + "Use /health help for command help");
				} if (args.length <= 0){
                                    Stats.show(player);
                                }
                                
			} else { sender.sendMessage(ChatColor.RED + "This command only can be executable by a player"); }
		}
		
		return false;
	}
	
}