package com.cadiducho.dev.BetterFood.listeners;

import com.cadiducho.dev.BetterFood.main.Constantes;
import com.cadiducho.dev.BetterFood.main.BetterFood;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
	
        
        public static BetterFood plugin;
        public static String deathHydration = "%player% died of dehydration";
        public static String deathCarbohydrates = "%player% died due to a lack of carbohydrates";
        public static String deathCarbohydratesExtra = "%player% ate too much carbohydrates";
        public static String deathVitamins = "%player% didn't eat his veggies";
        public static String deathVitaminsExtra = "%player% ate too much veggies";
        public static String deathProteins = "%player% died due to a lack of proteins";
        public static String deathProteinsExtra = "%player% ate too much proteins";
        public static String deathGlucose = "%player% ate too much sugar";
        
        public PlayerDeathListener(BetterFood i) {
                plugin = i;
        }
        
        @EventHandler
        public void onPlayerDeath(PlayerDeathEvent e) {
                Player player = (Player)e.getEntity();
                String playerName = player.getName(); // You really need to change this to a map of strings, not Players!
                String damageCause = plugin.damageCause.get(playerName);                                     
                        if (damageCause != null && damageCause.equalsIgnoreCase("dehydration")) {
                                e.setDeathMessage(deathHydration.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("carbohydrates")) {
                                e.setDeathMessage(deathCarbohydrates.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("carbohydratesExtra")) {
                                e.setDeathMessage(deathCarbohydratesExtra.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("vitamins")) {
                                e.setDeathMessage(deathVitamins.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("vitaminsExtra")) {
                                e.setDeathMessage(deathVitaminsExtra.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("proteins")) {
                                e.setDeathMessage(deathProteins.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("proteinsExtra")) {
                                e.setDeathMessage(deathProteinsExtra.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        }
                        
                
                
                plugin.hidratacion.remove(e.getEntity());
                plugin.hidratacion.put(e.getEntity(), Constantes.COMIENZO_HIDRATACION);
                
                plugin.carbohidratos.remove(e.getEntity());
                plugin.carbohidratos.put(e.getEntity(), 0);
                plugin.cuentaCarbohidratos.remove(e.getEntity());
                plugin.cuentaCarbohidratos.put(e.getEntity(), Constantes.CUENTAATRAS_FALTA);

                plugin.proteinas.remove(e.getEntity());
                plugin.proteinas.put(e.getEntity(), 0);
                plugin.cuentaProteinas.remove(e.getEntity());
                plugin.cuentaProteinas.put(e.getEntity(), Constantes.CUENTAATRAS_FALTA);
                
                plugin.vitaminas.remove(e.getEntity());
                plugin.vitaminas.put(e.getEntity(), 0);
                plugin.cuentaVitaminas.remove(e.getEntity());
                plugin.cuentaVitaminas.put(e.getEntity(), Constantes.CUENTAATRAS_FALTA);
                
                plugin.damageCause.remove(e.getEntity());
                plugin.damageCause.put(e.getEntity(), null);
        }
        
        public static void setDeathMessageHydration(String s) {
                deathHydration = s;
        }
        
        public static void setDeathMessageCarbohydrate(String s) {
                deathCarbohydrates = s;
        }
        
        public static void setDeathMessageCarbohydrateTooMuch(String s) {
                deathCarbohydratesExtra = s;
        }
        
        public static void setDeathMessageVitamins(String s) {
                deathVitamins = s;
        }
        
        public static void setDeathMessageVitaminsExtra(String s) {
                deathVitaminsExtra = s;
        }
        
        public static void setDeathMessageProteins(String s) {
                deathProteins = s;
        }
        
        public void setDeathMessageProteinsExtra(String s) {
                deathProteinsExtra = s;
        }

}