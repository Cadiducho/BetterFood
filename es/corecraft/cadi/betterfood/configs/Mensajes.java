package es.corecraft.cadi.betterfood.configs;


import java.io.File;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import es.corecraft.cadi.betterfood.principal.BetterFood;

/**
 *
 * @author Usuario
 */
public enum Mensajes {

 DESHIDRATACION_NIVEL_1("Estoy sediento, me pregunto por que..."), //aqua
 DESHIDRATACION_NIVEL_2("Me siento muy exausto, necesito agua!"),
 DESHIDRATACION_NIVEL_3("Agua... Agua..."),
 CARBOHIDRATOS_NIVEL_1("Me siento algo debil."), //YELLOW
 CARBOHIDRATOS_NIVEL_2("Pierdo la energia muy rapidamente!"),
 CARBOHIDRATOS_NIVEL_3("Necesito...más...carbohidratos..."),
 CARBOHIDRATOS_MUCHOS_NIVEL_1("Genial, tengo mucha energia!."),
 CARBOHIDRATOS_MUCHOS_NIVEL_2("Ayuda, no puedo parar!"),
 CARBOHIDRATOS_MUCHOS_NIVEL_3("Demasiados carbohidratos!!!"),
 VITAMINAS_NIVEL_1("Me siento algo debil."), //GREEN
 VITAMINAS_NIVEL_2("Creo que estoy enfermo."),
 VITAMINAS_NIVEL_3("Olvide tomar mis vitaminas..."),
 VITAMINAS_MUCHAS_NIVEL_1("Me siento en forma superior!"),
 VITAMINAS_MUCHAS_NIVEL_2("Ag... me estoy mareando"),
 VITAMINAS_MUCHAS_NIVEL_3("Uf estoy enfermo, demasiadas vitaminas..."),
 PROTEINAS_NIVEL_1("Me siento un poco débil"), //RED
 PROTEINAS_NIVEL_2("Ni siquiera puedo mover una pequeña roca!"),
 PROTEINAS_NIVEL_3("Necesito proteinas rapidamente!"),
 PROTEINAS_MUCHAS_NIVEL_1("Me siento en forma!"),
 PROTEINAS_MUCHAS_NIVEL_2("Mi fuerza es cada vez enorme. Esto no puede ser bueno..."),
 PROTEINAS_MUCHAS_NIVEL_3("Comi demasiadas proteinas...");

        
        private final String mvalue;
        public static YamlConfiguration mconfig = null;
        public static File mconfigFile = new File("plugins/BetterFood/messages.yml");
        
        private Mensajes(final String value) {
                this.mvalue = value;
        }
        
        public String getText() {
                String v = this.getValue();
                if (mconfig != null && mconfig.contains(this.name()))
                {
                        v = mconfig.getString(this.name());
                }
                v = ChatColor.translateAlternateColorCodes('&', v);
                return v;
        }
        
        public String getValue() {
                return this.mvalue;
        }
        
        public static void load() {
                if (!mconfigFile.exists()) createConfig();
                mconfig = YamlConfiguration.loadConfiguration(mconfigFile);
        }
        
        public static void createConfig() {
                YamlConfiguration newConfig = new YamlConfiguration();
                newConfig.options().header("Messages of BetterFood Plugin.");
                newConfig.options().header("For colors use http://minecraft.gamepedia.com/Formatting_codes");
                newConfig.options().copyHeader(true);
                for (Mensajes lang : Mensajes.values())
                {
                        String name = lang.name();
                        String value = lang.getValue();
                        newConfig.set(name, value);
                }
                try {
                        newConfig.save(mconfigFile);
                        BetterFood.log.log(Level.INFO, "mensajes cargados");
                } catch (Exception e) {
                        BetterFood.log.log(Level.WARNING, "Error while save the messages.yml: {0}", e.getMessage());
                }
        }
}
