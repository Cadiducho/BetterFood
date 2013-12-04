package com.cadiducho.dev.BetterFood.configs;

import java.io.File;
import java.util.logging.Level;
import org.bukkit.configuration.file.YamlConfiguration;

import com.cadiducho.dev.BetterFood.main.BetterFood;


public class Config {

        public static String TITLE = "Health Stats";
        public static String HIDRATACION = "Hydratation";
        public static String CARBO = "Carbohydrates";
        public static String PROTEINAS = "Proteins";
        public static String VITAMINAS = "Vitamins";
        public static boolean UPDATER_CHECKER = true;
        //File
        public static File configFile = new File("plugins/BetterFood/config.yml");


        
        public static void load() {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
                
                TITLE = config.getString("Menu.Title");
                HIDRATACION = config.getString("Menu.Hydratation");
                CARBO = config.getString("Menu.Carbohydrates");
                PROTEINAS = config.getString("Menu.Protein");
                VITAMINAS = config.getString("Menu.Vitamin");
                UPDATER_CHECKER = config.getBoolean("UpdaterChecker");
        }
        
        public static void save() {
                YamlConfiguration config = new YamlConfiguration();
                config.set("Menu.Title", TITLE);
                config.set("Menu.Hydratation", HIDRATACION);
                config.set("Menu.Carbohydrates", CARBO);
                config.set("Menu.Protein", PROTEINAS);
                config.set("Menu.Vitamin", VITAMINAS);
                config.set("UpdaterChecker", UPDATER_CHECKER);

                try {
                        config.save(configFile);
                        BetterFood.log.log(Level.INFO, "config cargados");
                } catch (Exception e) {
                        BetterFood.log.log(Level.WARNING, "Error while saving the Config: {0}!", e.getMessage());
                }
        }
}