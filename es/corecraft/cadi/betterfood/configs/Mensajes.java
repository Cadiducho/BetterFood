
package es.corecraft.cadi.betterfood.configs;

import es.corecraft.cadi.betterfood.principal.BetterFood;
import java.io.File;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Mensajes {
         DESHIDRATACION_NIVEL_2("Estoy sediento, me pregunto por que..."), //AQUA
	 DESHIDRATACION_NIVEL_3("Me siento muy exausto, necesito agua!"),
	 DESHIDRATACION_NIVEL_4("Agua... Agua..."),
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
         
                private final String value;
                public static YamlConfiguration config = null;
                public static File configFile = new File("plugins/BetterFood/messages.yml");
                
                private Mensajes(final String value) {
                    this.value = value;
                }
                
                public String getText() {
                    String v = this.getValue();
                    if (config != null && config.contains(this.name()))
                    {
                        v = config.getString(this.name());
                    }
                    v = ChatColor.translateAlternateColorCodes('&', v);
                    return v;
                }
                
                public String getValue() {
                    return this.value;
                }
                
                public static void load() {
                    if (!configFile.exists()) createConfig();
                    config = YamlConfiguration.loadConfiguration(configFile);
                }
                public static void createConfig() {
                    YamlConfiguration newConfig = new YamlConfiguration();
                    newConfig.options().header("Messages of BetterFood plugin");
                    newConfig.options().copyHeader(true);
                    for (Mensajes lang : Mensajes.values())
                    {
                        String name = lang.name();
                        String value = lang.getValue();
                        newConfig.set(name, value);
                    }
                    try {
                        newConfig.save(configFile);
                    } catch (Exception e) {
                        BetterFood.log.log(Level.WARNING, "Error while save the messages.yml: {0}", e.getMessage());
                              
                    }
                }
                
}
