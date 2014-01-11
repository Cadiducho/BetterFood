package com.cadiducho.dev.BetterFood.configs;

import java.io.File;
import java.util.logging.Level;
import org.bukkit.configuration.file.YamlConfiguration;

import com.cadiducho.dev.BetterFood.main.BetterFood;


public class Mensajes {


        public static String DESHIDRATACION_NIVEL_1 = "Estoy sediento, me pregunto por que...";
        public static String DESHIDRATACION_NIVEL_2 = "Me siento muy exausto, necesito agua!";
        public static String DESHIDRATACION_NIVEL_3 = "Agua... Agua...";
        public static String CARBOHIDRATOS_NIVEL_1 = "Me siento algo debil."; //YELLOW
        public static String CARBOHIDRATOS_NIVEL_2 = "Pierdo la energia muy rapidamente!";
        public static String CARBOHIDRATOS_NIVEL_3 = "Necesito...más...carbohidratos...";
        public static String CARBOHIDRATOS_MUCHOS_NIVEL_1 = "Genial, tengo mucha energia!.";
        public static String CARBOHIDRATOS_MUCHOS_NIVEL_2 = "Ayuda, no puedo parar!";
        public static String CARBOHIDRATOS_MUCHOS_NIVEL_3 = "Demasiados carbohidratos!!!";
        public static String VITAMINAS_NIVEL_1 = "Me siento algo debil."; //GREEN
        public static String VITAMINAS_NIVEL_2 = "Creo que estoy enfermo.";
        public static String VITAMINAS_NIVEL_3 = "Olvide tomar mis vitaminas...";
        public static String VITAMINAS_MUCHAS_NIVEL_1 = "Me siento en forma superior!";
        public static String VITAMINAS_MUCHAS_NIVEL_2 = "Ag... me estoy mareando";
        public static String VITAMINAS_MUCHAS_NIVEL_3 = "Uf estoy enfermo, demasiadas vitaminas...";
        public static String PROTEINAS_NIVEL_1 = "Me siento un poco débil"; //RED
        public static String PROTEINAS_NIVEL_2 = "Ni siquiera puedo mover una pequeña roca!";
        public static String PROTEINAS_NIVEL_3 = "Necesito proteinas rapidamente!";
        public static String PROTEINAS_MUCHAS_NIVEL_1 = "Me siento en forma!";
        public static String PROTEINAS_MUCHAS_NIVEL_2 = "Mi fuerza es cada vez enorme. Esto no puede ser bueno...";
        public static String PROTEINAS_MUCHAS_NIVEL_3 = "Comi demasiadas proteinas...";

        public static String deathHydration = "%player% died of dehydration";
        public static String deathCarbohydrates = "%player% died due to a lack of carbohydrates";
        public static String deathCarbohydratesExtra = "%player% ate too much carbohydrates";
        public static String deathVitamins = "%player% didn't eat his veggies";
        public static String deathVitaminsExtra = "%player% ate too much veggies";
        public static String deathProteins = "%player% died due to a lack of proteins";
        public static String deathProteinsExtra = "%player% ate too much proteins";
        public static String deathGlucose = "%player% ate too much sugar";
        //File
        public static File mconfigFile = new File("plugins/BetterFood/messages.yml");


        
        public static void load() {
                YamlConfiguration m = YamlConfiguration.loadConfiguration(mconfigFile);
                deathHydration = m.getString("Death.Dehydration");
                deathCarbohydrates = m.getString("Death.Carbohydrates");
                deathCarbohydratesExtra = m.getString("Death.CarbohydratesExtra");
                deathVitamins = m.getString("Death.Vitamins");
                deathVitaminsExtra = m.getString("Death.VitaminsExtra");
                deathProteins = m.getString("Death.Proteins");
                deathProteinsExtra = m.getString("Death.ProteinsExtra");
                deathGlucose = m.getString("Death.Glucose");
                //Desidratación
                DESHIDRATACION_NIVEL_1 = m.getString("Dehydration.1");
                DESHIDRATACION_NIVEL_2 = m.getString("Dehydration.2");
                DESHIDRATACION_NIVEL_3 = m.getString("Dehydration.3");
               //Carbo
               CARBOHIDRATOS_NIVEL_1 = m.getString("Carbohydrate.short.1");
               CARBOHIDRATOS_NIVEL_2 = m.getString("Carbohydrate.short.2");
               CARBOHIDRATOS_NIVEL_3 = m.getString("Carbohydrate.short.3");
               CARBOHIDRATOS_MUCHOS_NIVEL_1 = m.getString("Carbohydrate.much.1");
               CARBOHIDRATOS_MUCHOS_NIVEL_2 = m.getString("Carbohydrate.much.2");
               CARBOHIDRATOS_MUCHOS_NIVEL_3 = m.getString("Carbohydrate.much.3");
                //Proteinas
               PROTEINAS_NIVEL_1 = m.getString("Proteins.short.1");
               PROTEINAS_NIVEL_2 = m.getString("Proteins.short.2");
               PROTEINAS_NIVEL_3 = m.getString("Proteins.short.3");
               PROTEINAS_MUCHAS_NIVEL_1 = m.getString("Proteins.much.1");
               PROTEINAS_MUCHAS_NIVEL_2 = m.getString("Proteins.much.2");
               PROTEINAS_MUCHAS_NIVEL_2 = m.getString("Proteins.much.3");
                //Vitaminas
               VITAMINAS_NIVEL_1 = m.getString("Vitamins.short.1");
               VITAMINAS_NIVEL_2 = m.getString("Vitamins.short.2");
               VITAMINAS_NIVEL_3 = m.getString("Vitamins.short.3");
               VITAMINAS_MUCHAS_NIVEL_1 = m.getString("Vitamins.much.1");
               VITAMINAS_MUCHAS_NIVEL_2 = m.getString("Vitamins.much.2");
               VITAMINAS_MUCHAS_NIVEL_3 = m.getString("Vitamins.much.2");
                //DeathEvent

                
        }
        
        public static void save() {
                YamlConfiguration mconfig = new YamlConfiguration();
                mconfig.set("Death.Dehydration", deathHydration);  
                mconfig.set("Death.Carbohydrates", deathCarbohydrates);
                mconfig.set("Death.CarbohydratesExtra", deathCarbohydratesExtra);
                mconfig.set("Death.Vitamins", deathVitamins);
                mconfig.set("Death.VitaminsExtra", deathVitaminsExtra);
                mconfig.set("Death.Proteins", deathProteins);
                mconfig.set("Death.ProteinsExtra", deathProteinsExtra);
                mconfig.set("Death.Glucose", deathGlucose);
                
                mconfig.set("Dehydration.1", DESHIDRATACION_NIVEL_1);
                mconfig.set("Dehydration.2", DESHIDRATACION_NIVEL_2);
                mconfig.set("Dehydration.3", DESHIDRATACION_NIVEL_3);
                
                mconfig.set("Carbohydrate.short.1", CARBOHIDRATOS_NIVEL_1);
                mconfig.set("Carbohydrate.short.2", CARBOHIDRATOS_NIVEL_2);
                mconfig.set("Carbohydrate.short.3", CARBOHIDRATOS_NIVEL_3);
                mconfig.set("Carbohydrate.much.1", CARBOHIDRATOS_MUCHOS_NIVEL_1);
                mconfig.set("Carbohydrate.much.2", CARBOHIDRATOS_MUCHOS_NIVEL_2);
                mconfig.set("Carbohydrate.much.3", CARBOHIDRATOS_MUCHOS_NIVEL_3);
                
                mconfig.set("Proteins.short.1", PROTEINAS_NIVEL_1);
                mconfig.set("Proteins.short.2", PROTEINAS_NIVEL_2);
                mconfig.set("Proteins.short.3", PROTEINAS_NIVEL_3);
                mconfig.set("Proteins.much.1", PROTEINAS_MUCHAS_NIVEL_1);
                mconfig.set("Proteins.much.2", PROTEINAS_MUCHAS_NIVEL_2);
                mconfig.set("Proteins.much.3", PROTEINAS_MUCHAS_NIVEL_3);
                
                mconfig.set("Vitamins.short.1", VITAMINAS_NIVEL_1);
                mconfig.set("Vitamins.short.2", VITAMINAS_NIVEL_2);
                mconfig.set("Vitamins.short.3", VITAMINAS_NIVEL_3);
                mconfig.set("Vitamins.much.1", VITAMINAS_MUCHAS_NIVEL_1);
                mconfig.set("Vitamins.much.2", VITAMINAS_MUCHAS_NIVEL_2);
                mconfig.set("Vitamins.much.3", VITAMINAS_MUCHAS_NIVEL_3);


                try {
                        mconfig.save(mconfigFile);
                        
                } catch (Exception e) {
                        BetterFood.log.log(Level.WARNING, "Error while saving Messages.yml: {0}!", e.getMessage());
                }
        }
}