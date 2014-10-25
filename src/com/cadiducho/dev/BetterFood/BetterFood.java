package com.cadiducho.dev.BetterFood;

//Imports de Java
import com.cadiducho.dev.BetterFood.Commands.BetterFoodCMD;
import com.cadiducho.dev.BetterFood.Commands.HealthCMD;
import com.cadiducho.dev.BetterFood.configs.Config;
import com.cadiducho.dev.BetterFood.configs.Mensajes;
import java.util.HashMap;
import java.util.logging.Logger;

//Imports del plugin
import com.cadiducho.dev.BetterFood.listeners.PlayerConsumeListener;
import com.cadiducho.dev.BetterFood.listeners.PlayerDeathListener;
import com.cadiducho.dev.BetterFood.listeners.PlayerJoinListener;
import com.cadiducho.dev.BetterFood.listeners.PlayerRespawnListener;
import com.cadiducho.dev.BetterFood.util.Stats;
import com.cadiducho.dev.BetterFood.util.Timer;
import com.cadiducho.dev.BetterFood.util.Metodos;
import com.cadiducho.dev.BetterFood.util.Updater;
import com.cadiducho.dev.BetterFood.util.sql.MySQL;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.logging.Level;

//Imports de Bukkit
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class BetterFood extends JavaPlugin {

	public static final Logger log = Logger.getLogger("Minecraft");
	
        //Internal
	private final Metodos met = new Metodos(this);
        private final MySQL mysql = null;
        private final Connection connection = null;
        
	public Stats s = new Stats(this);
        BetterFood p = this;	
        //Listeners
	public PlayerJoinListener PlayerJoinListener = new PlayerJoinListener(this);
	public PlayerDeathListener PlayerDeathListener = new PlayerDeathListener(this);
	public PlayerConsumeListener PlayerConsumeListener = new PlayerConsumeListener(this);
	public PlayerRespawnListener PlayerRespawnListener = new PlayerRespawnListener(this);
	//Comandos
        private final HealthCMD healthCMD = new HealthCMD(this);
        private final BetterFoodCMD betterfoodCMD = new BetterFoodCMD(this);
        //HashMaps ¿?
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
            /*
             * Generar y cargar config.yml
             */
            debugLog("Mode Debug enabled", "Modo Debug activado");
            debugLog("Loading config module", "Cargando modulo de config");
            File file = new File(getDataFolder() + File.separator + "config.yml");
            if (!file.exists()){
                try {
                    getConfig().options().copyDefaults(true);
                    saveConfig();
                    log("Config file generated successfully");             
                } catch (Exception e) {
                    log("Config file can't be generated!");
                    debugLog("Cause: "+e.getMessage(), "Causa: "+e.getMessage());
                }
            }
            /*
             * Cargar y generar data.yml (or file name)
             */
            File dataFile = new File(getDataFolder() + File.separator + "data.yml");
		if (!dataFile.exists()) {
                    try {
                        reloadData();
			saveData();
			log("Data file generated successfully");
                    } catch (Exception e) {
                        this.getLogger().info("Data file cant be generated!");
                        debugLog("Cause: "+e.getMessage(), "Causa: "+e.getMessage());
                    }
		}
                
            if (Config.UPDATER = true) {
                Updater u = new Updater(66441, "acb5d9fcd0c7f710d50c2e893eccc537917e7417");
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
                /*
                 * Cargar y registrar comandos
                 */
                getCommand("health").setExecutor(healthCMD);
                getCommand("betterfood").setExecutor(betterfoodCMD);
                
		
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
        
        /*
	 * Metodos basicos para el data.yml si es que se usa
	 */
	private FileConfiguration data = null;
	private File dataFile = null;
        
	public FileConfiguration getData() {
	    if (data == null) {
	        this.reloadData();
	    }
	    return data;
	}
        
        public void saveData() {
	    if (data == null || dataFile == null) {
	    	return;
	    }
	    try {
	        getData().save(dataFile);
	    } catch (Exception ex) {
	        this.getLogger().log(Level.SEVERE, "Could not save config to " + dataFile, ex);
	    }
	}
        
	public void reloadData() {
	    if (dataFile == null) {
	    	dataFile = new File(getDataFolder(), "data.yml");
	    }
	    data = YamlConfiguration.loadConfiguration(dataFile);
	 
	    InputStream defStream = this.getResource("data.yml");
	    if (defStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defStream);
	        data.setDefaults(defConfig);
	    }
	}

        
        public Metodos getMetodos() {
            return this.met;
        }
        
        public MySQL getMySQL() {
            return this.mysql;
        }
        
        public Connection getConnection() {
            return this.connection;
        }
        
        public void log(String msg) {
            getLogger().log(Level.INFO, msg);
        }
        public boolean isDebug() {
            return this.getConfig().getBoolean("debug.enabled");
        }
        
        public void debugLog(String eng, String es) {
            if (isDebug()) {
                String msg;
                switch (this.getConfig().getString("debug.lang")){
                    case "es":
                    case "esp":
                        msg = es;
                        break;
                    case "eng":
                    default:
                        msg = eng;
                        break;
                }
                getLogger().log(Level.INFO, "[Debug] {0}", msg);
            }
        }


}