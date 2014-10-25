package com.cadiducho.dev.BetterFood.listeners;

import com.cadiducho.dev.BetterFood.configs.Mensajes;
import com.cadiducho.dev.BetterFood.util.Stats;
import com.cadiducho.dev.BetterFood.BetterFood;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
	
        public static BetterFood plugin;
        
        public PlayerDeathListener(BetterFood i) {
                plugin = i;
        }
        
        @EventHandler
        public void onPlayerDeath(PlayerDeathEvent e) {
                Player player = (Player)e.getEntity();
                String playerName = player.getName(); // You really need to change this to a map of strings, not Players!
                String damageCause = plugin.damageCause.get(playerName);                                     
                        if (damageCause != null && damageCause.equalsIgnoreCase("dehydration")) {
                                e.setDeathMessage(Mensajes.deathHydration.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("carbohydrates")) {
                                e.setDeathMessage(Mensajes.deathCarbohydrates.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("carbohydratesExtra")) {
                                e.setDeathMessage(Mensajes.deathCarbohydratesExtra.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("vitamins")) {
                                e.setDeathMessage(Mensajes.deathVitamins.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("vitaminsExtra")) {
                                e.setDeathMessage(Mensajes.deathVitaminsExtra.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("proteins")) {
                                e.setDeathMessage(Mensajes.deathProteins.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        } else if (damageCause != null && damageCause.equalsIgnoreCase("proteinsExtra")) {
                                e.setDeathMessage(Mensajes.deathProteinsExtra.replaceAll("%player%", player.getName()));
                                plugin.damageCause.remove(player);
                                plugin.damageCause.put(player, null);
                        }
                        
                
                
                plugin.hidratacion.remove(e.getEntity());
                plugin.hidratacion.put(e.getEntity(), Stats.COMIENZO_HIDRATACION);
                
                plugin.carbohidratos.remove(e.getEntity());
                plugin.carbohidratos.put(e.getEntity(), 0);
                plugin.cuentaCarbohidratos.remove(e.getEntity());
                plugin.cuentaCarbohidratos.put(e.getEntity(), Stats.CUENTAATRAS_FALTA);

                plugin.proteinas.remove(e.getEntity());
                plugin.proteinas.put(e.getEntity(), 0);
                plugin.cuentaProteinas.remove(e.getEntity());
                plugin.cuentaProteinas.put(e.getEntity(), Stats.CUENTAATRAS_FALTA);
                
                plugin.vitaminas.remove(e.getEntity());
                plugin.vitaminas.put(e.getEntity(), 0);
                plugin.cuentaVitaminas.remove(e.getEntity());
                plugin.cuentaVitaminas.put(e.getEntity(), Stats.CUENTAATRAS_FALTA);
                
                plugin.damageCause.remove(e.getEntity());
                plugin.damageCause.put(e.getEntity(), null);
        }
        
        public static void setDeathMessageHydration(String s) {
                Mensajes.deathHydration = s;
        }
        
        public static void setDeathMessageCarbohydrate(String s) {
                Mensajes.deathCarbohydrates = s;
        }
        
        public static void setDeathMessageCarbohydrateTooMuch(String s) {
                Mensajes.deathCarbohydratesExtra = s;
        }
        
        public static void setDeathMessageVitamins(String s) {
                Mensajes.deathVitamins = s;
        }
        
        public static void setDeathMessageVitaminsExtra(String s) {
                Mensajes.deathVitaminsExtra = s;
        }
        
        public static void setDeathMessageProteins(String s) {
                Mensajes.deathProteins = s;
        }
        
        public void setDeathMessageProteinsExtra(String s) {
                Mensajes.deathProteinsExtra = s;
        }

}