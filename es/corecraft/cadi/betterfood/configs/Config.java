package es.corecraft.cadi.betterfood.configs;

import java.io.File;
import java.util.logging.Level;
import org.bukkit.configuration.file.YamlConfiguration;

import es.corecraft.cadi.betterfood.principal.BetterFood;


public class Config {

        public static String TITLE = "Health Stats";
        public static String HIDRATACION = "Hydratation";
        public static String CARBO = "Carbohydrates";
        public static String PROTEINAS = "Proteins";
        public static String VITAMINAS = "Vitamins";
        //File
        public static File configFile = new File("plugins/BetterFood/config.yml");


        
        public static void load() {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
                
                TITLE = config.getString("Menu.Title");
                HIDRATACION = config.getString("Menu.Hydratation");
                CARBO = config.getString("Menu.Carbohydrates");
                PROTEINAS = config.getString("Menu.Protein");
                VITAMINAS = config.getString("Menu.Vitamin");
        }
        
        public static void save() {
                YamlConfiguration config = new YamlConfiguration();
                config.set("Menu.Title", TITLE);
                config.set("Menu.Hydratation", HIDRATACION);
                config.set("Menu.Carbohydrates", CARBO);
                config.set("Menu.Protein", PROTEINAS);
                config.set("Menu.Vitamin", VITAMINAS);
                try {
                        config.save(configFile);
                        BetterFood.log.log(Level.INFO, "config cargados");
                } catch (Exception e) {
                        BetterFood.log.log(Level.WARNING, "Error while saving the Config!");
                }
        }
}