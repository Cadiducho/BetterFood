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
import static com.cadiducho.dev.BetterFood.main.Comandos.plugin;
import java.io.File;
import java.util.List;

//Imports de Bukkit
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class BetterFood extends JavaPlugin {

	public static final Logger log = Logger.getLogger("Minecraft");
	
	public File data_file = new File("plugins/BetterFood/data", "stats.yml");
        public FileConfiguration dataStats = YamlConfiguration.loadConfiguration(data_file);
        
	public Stats s = new Stats(this);
        BetterFood p = this;	
        
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

	@Override
	public void onEnable() {
            if (Config.UPDATER = true) {
                Update u = new Update(66441, "acb5d9fcd0c7f710d50c2e893eccc537917e7417");
            }
		log.info("[BetterFood] Plugin have been enabled!");
		
                if (!Config.configFile.exists()) { 
                    Config.save();
                }
               if (!Mensajes.mconfigFile.exists()) {
                   Mensajes.save();
               }
                Config.load();
                Mensajes.load();
          	PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(PlayerJoinListener, this);
		manager.registerEvents(PlayerDeathListener, this);
		manager.registerEvents(PlayerConsumeListener, this);
		manager.registerEvents(PlayerRespawnListener, this);
                
                getCommand("health").setExecutor(new Comandos());
                getCommand("betterfood").setExecutor(new Comandos());
                
		
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Timer(this), 20, 20);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			this.hidratacion.put(player, Stats.COMIENZO_HIDRATACION);
			this.damageCause.put(player, null);
			this.carbohidratos.put(player, 0);
			this.cuentaCarbohidratos.put(player, Stats.CUENTAATRAS_FALTA);
			this.proteinas.put(player, 0);
			this.cuentaProteinas.put(player, Stats.CUENTAATRAS_FALTA);
			this.vitaminas.put(player, 0);
			this.cuentaVitaminas.put(player, Stats.CUENTAATRAS_FALTA);
		}

                       

	}
	

        
	@Override
	public void onDisable() {
              log.info("[BetterFood] Plugin have been disabled!!");
              /* ToDo in BetterFood Beta.1.2 */	
              /*
                List<String> hdata = dataStats.getStringList("hidratación"); 
                for (Player pl : hidratacion.keySet()){
                   hdata.add(pl.getName() + ":" + hidratacion.get(pl).intValue());
                }
                List<String> cdata = dataStats.getStringList("carbohidratos"); 
                for (Player pl : carbohidratos.keySet()){
                   cdata.add(pl.getName() + ":" + carbohidratos.get(pl).intValue());
                }
                List<String> vdata = dataStats.getStringList("vitaminas"); 
                for (Player pl : vitaminas.keySet()){
                   vdata.add(pl.getName() + ":" + vitaminas.get(pl).intValue());
                }
                List<String> pdata = dataStats.getStringList("proteinas"); 
                for (Player pl : proteinas.keySet()){
                   pdata.add(pl.getName() + ":" + proteinas.get(pl).intValue());
                }
          */
	}
                    

        public void reset(Player player) {
                        this.hidratacion.put(player, Stats.COMIENZO_HIDRATACION);
			this.carbohidratos.put(player, 0);
			this.proteinas.put(player, 0);
			this.vitaminas.put(player, 0);
	}

}