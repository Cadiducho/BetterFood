package es.corecraft.cadi.betterfood.principal;

//Imports de Java
import java.util.HashMap;
import java.util.logging.Logger;

//Imports del plugin
import es.corecraft.cadi.betterfood.listeners.PlayerConsumeListener;
import es.corecraft.cadi.betterfood.listeners.PlayerDeathListener;
import es.corecraft.cadi.betterfood.listeners.PlayerJoinListener;
import es.corecraft.cadi.betterfood.listeners.PlayerRespawnListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

//Imports de Bukkit
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
	
	public HashMap<Player, Integer> hidratacion = new HashMap<>();
	public HashMap<Player, Integer> carbohidratos = new HashMap<>();
	public HashMap<Player, Integer> cuentaCarbohidratos = new HashMap<>();
	public HashMap<Player, Integer> proteinas = new HashMap<>();
	public HashMap<Player, Integer> cuentaProteinas = new HashMap<>();
	public HashMap<Player, Integer> vitaminas = new HashMap<>();
	public HashMap<Player, Integer> cuentaVitaminas = new HashMap<>();
	public HashMap<Player, String> damageCause = new HashMap<>();
        
        private FileConfiguration customConfig = null;
	private File messagesFile = null;
	public void reloadCustomConfig() {
                
           if (messagesFile == null) {
                messagesFile = new File(getDataFolder(), "messages.yml");
		}
		customConfig = YamlConfiguration.loadConfiguration(messagesFile);

		// Saldra tal y como esta en el .jar
		InputStream defConfigStream = this.getResource("messages.yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
			customConfig.setDefaults(defConfig);
		}

	}
        
        //Mensajes y Config personalizada
	public FileConfiguration getMessages() {
		if (customConfig == null) {
			this.reloadCustomConfig();
		}
		return customConfig;
	}

	public void saveMessages() {
		if (customConfig == null || messagesFile == null) {
			return;
		}
		try {
			getMessages().save(messagesFile);
		} catch (IOException ex) {
			this.getLogger().log(Level.SEVERE,
					"No se pudo guardar la configuración para " + messagesFile, ex);
		}
	}
        
        private void SetupConfig() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
	}

	private void loadConfiguration() {

		getMessages().options().copyDefaults(true);
		saveMessages();
	}
	/* ------------------------------------------------------------*/
        /*                    Mensajes                                 */
        final public String DESHIDRATACION_NIVEL_2 = ChatColor.AQUA + "Estoy sediento, me pregunto por que...";
	final public String DESHIDRATACION_NIVEL_3 = ChatColor.AQUA + "Me siento muy exausto, necesito agua!";
	final public String DESHIDRATACION_NIVEL_4 = ChatColor.AQUA + "Agua... Agua...";
	final public String CARBOHIDRATOS_NIVEL_1 = ChatColor.YELLOW + "Me siento algo debil.";
	final public String CARBOHIDRATOS_NIVEL_2 = ChatColor.YELLOW + "Pierdo la energia muy rapidamente!";
	final public String CARBOHIDRATOS_NIVEL_3 = ChatColor.YELLOW + "Necesito...más...carbohidratos...";
	final public String CARBOHIDRATOS_MUCHOS_NIVEL_1 = ChatColor.YELLOW + "Genial, tengo mucha energia!.";
	final public String CARBOHIDRATOS_MUCHOS_NIVEL_2 = ChatColor.YELLOW + "Ayuda, no puedo parar!";
	final public String CARBOHIDRATOS_MUCHOS_NIVEL_3 = ChatColor.YELLOW + "Demasiados carbohidratos!!!";
	final public String VITAMINAS_NIVEL_1 = ChatColor.GREEN + "Me siento algo debil.";
	final public String VITAMINAS_NIVEL_2 = ChatColor.GREEN + "Creo que estoy enfermo.";
	final public String VITAMINAS_NIVEL_3 = ChatColor.GREEN + "Olvide tomar mis vitaminas...";
	final public String VITAMINAS_MUCHAS_NIVEL_1 = ChatColor.GREEN + "Me siento en forma superior!";
	final public String VITAMINAS_MUCHAS_NIVEL_2 = ChatColor.GREEN + "Ag... me estoy mareando";
	final public String VITAMINAS_MUCHAS_NIVEL_3 = ChatColor.GREEN + "Uf estoy enfermo, demasiadas vitaminas...";
	final public String PROTEINAS_NIVEL_1 = ChatColor.RED + "Me siento un poco débil";
	final public String PROTEINAS_NIVEL_2 = ChatColor.RED + "Ni siquiera puedo mover una pequeña roca!";
	final public String PROTEINAS_NIVEL_3 = ChatColor.RED + "Necesito proteinas rapidamente!";
	final public String PROTEINAS_MUCHAS_NIVEL_1 = ChatColor.RED + "Me siento en forma!";
	final public String PROTEINAS_MUCHAS_NIVEL_2 = ChatColor.RED + "Mi fuerza es cada vez enorme. Esto no puede ser bueno...";
	final public String PROTEINAS_MUCHAS_NIVEL_3 = ChatColor.RED + "Comi demasiadas proteinas...";
        


        /*------------------------------------------------------------------*/
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
		log.info("[BetterFood] Enabled/Activado!");
		
                SetupConfig();
		loadConfiguration();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(PlayerJoinListener, this);
		pm.registerEvents(PlayerDeathListener, this);
		pm.registerEvents(PlayerConsumeListener, this);
		pm.registerEvents(PlayerRespawnListener, this);
		
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
                
	}
	

        
	@Override
	public void onDisable() {
		log.info("[BetterFood] Disables/Desactivado!!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
     
		if (commandLabel.equalsIgnoreCase("salud") || (commandLabel.equalsIgnoreCase("health"))) {
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