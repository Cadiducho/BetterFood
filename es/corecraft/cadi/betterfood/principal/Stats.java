package es.corecraft.cadi.betterfood.principal;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Stats {

	public static BetterFood plugin;
	
	public static void show(Player player) {
	
		double total = plugin.carbohidratos.get(player) + plugin.vitaminas.get(player) + plugin.proteinas.get(player);
		int count = 0;
		double partCarbo = 20.0, partProtein = 20.0, partVitamin = 20.0;
		String hydrationStats = "", carbohydratesStats = "", proteinsStats = "", vitaminsStats = "";
		
		partCarbo = Constantes.STAT_BAR_SIZE * (plugin.carbohidratos.get(player) / total);
		partProtein = Constantes.STAT_BAR_SIZE * (plugin.proteinas.get(player) / total);
		partVitamin = Constantes.STAT_BAR_SIZE * (plugin.vitaminas.get(player) / total);
		
                String hidratacion = plugin.getConfig().getString("Hydration");
                String carbo = plugin.getConfig().getString("Carbohydrates");
                String proteinas = plugin.getConfig().getString("Protein");
                String vitaminas = plugin.getConfig().getString("Vitamin");
                
                String title = plugin.getConfig().getString("Title");
                
                
		for (int i = 1; i <= Constantes.STAT_BAR_SIZE; i++) {
			if (i <= partCarbo) {
				carbohydratesStats += "#";
			} else {
				carbohydratesStats += "-";
			}
		}
		for (int j = 1; j <= Constantes.STAT_BAR_SIZE; j++) {
			if (j <= partProtein) {
				proteinsStats += "#";
			} else {
				proteinsStats += "-";
			}
		}
		for (int k = 1; k <= Constantes.STAT_BAR_SIZE; k++) {
			if (k <= partVitamin) {
				vitaminsStats += "#";
			} else {
				vitaminsStats += "-";
			}
		}
		
		for (int m = 1; m <= plugin.hidratacion.get(player) / Constantes.STAT_BAR_HIDRATACION; m++) {
			hydrationStats += "#";
			count++;
		}
		for (int n = count; n < Constantes.STAT_BAR_SIZE; n++) {
			hydrationStats += "-";
		}

		player.sendMessage(ChatColor.GREEN + "**" + ChatColor.YELLOW + "-------------{ " +
				ChatColor.GOLD +	title + ChatColor.YELLOW + " }------------" + ChatColor.GREEN + "**");
		player.sendMessage(ChatColor.AQUA + "* [" + hydrationStats + "] " + hidratacion );
		player.sendMessage(ChatColor.YELLOW + "* [" + carbohydratesStats + "] " + carbo + " (" + (int) (partCarbo * 5) + "%)");
		player.sendMessage(ChatColor.RED + "* [" + proteinsStats + "] " + proteinas + " (" + (int) (partProtein * 5) + "%)");
		player.sendMessage(ChatColor.GREEN + "* [" + vitaminsStats + "] " + vitaminas + " (" + (int) (partVitamin * 5) + "%)");
		player.sendMessage(ChatColor.GREEN + "**" + ChatColor.YELLOW + "--------------------" + ChatColor.GOLD + "*" + 
				ChatColor.YELLOW + "-------------------" + ChatColor.GREEN + "**");
		
	}
	
	public Stats(BetterFood i) {
		plugin = i;
	}
	
}
