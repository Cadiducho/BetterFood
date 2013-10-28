package es.corecraft.cadi.betterfood.listeners;

import es.corecraft.cadi.betterfood.principal.Constantes;
import es.corecraft.cadi.betterfood.principal.BetterFood;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
	
	public static BetterFood plugin;
	public static String muerteDeshidratacion = "%player% murio por deshidratacion";
	public static String muerteCarbohidratos = "%player% murio por falta de carbohidratos";
	public static String muerteCarbohidratosExtra = "%player% comio demasiados carbohidratos";
	public static String deathVitaminas = "%player% no comio vitaminas!";
	public static String deathVitaminasExtra = "%player% tomo demasiadas verduras";
	public static String deathProteinas = "A %player% le faltaron proteinas";
	public static String deathProteinasExtra = "%player% tomo demasiadas proteinas";
	public static String deathGlucose = "A %player% le pasaron factura los dulces!";
	
	public PlayerDeathListener(BetterFood i) {
		plugin = i;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();			
			if (plugin.damageCause.get(player).equalsIgnoreCase("dehydration")) {
				e.setDeathMessage(muerteDeshidratacion.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("carbohydrates")) {
				e.setDeathMessage(muerteCarbohidratos.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("carbohydratesExtra")) {
				e.setDeathMessage(muerteCarbohidratosExtra.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("vitamins")) {
				e.setDeathMessage(deathVitaminas.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("vitaminsExtra")) {
				e.setDeathMessage(deathVitaminasExtra.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("proteins")) {
				e.setDeathMessage(deathProteinas.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			} else if (plugin.damageCause.get(player).equalsIgnoreCase("proteinsExtra")) {
				e.setDeathMessage(deathProteinasExtra.replaceAll("%player%", player.getName()));
				plugin.damageCause.remove(player);
				plugin.damageCause.put(player, null);
			}
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
		muerteDeshidratacion = s;
	}
	
	public static void setDeathMessageCarbohydrate(String s) {
		muerteCarbohidratos = s;
	}
	
	public static void setDeathMessageCarbohydrateTooMuch(String s) {
		muerteCarbohidratosExtra = s;
	}
	
	public static void setDeathMessageVitamins(String s) {
		deathVitaminas = s;
	}
	
	public static void setDeathMessageVitaminsExtra(String s) {
		deathVitaminasExtra = s;
	}
	
	public static void setDeathMessageProteins(String s) {
		deathProteinas = s;
	}
	
	public void setDeathMessageProteinsExtra(String s) {
		deathProteinasExtra = s;
	}

}