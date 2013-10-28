package es.corecraft.cadi.betterfood.listeners;

import es.corecraft.cadi.betterfood.principal.Constantes;
import es.corecraft.cadi.betterfood.principal.BetterFood;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

	public static BetterFood plugin;
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		
		Player player = e.getPlayer();
		
		plugin.hidratacion.remove(player);
		plugin.hidratacion.put(player, Constantes.COMIENZO_HIDRATACION);
		
		plugin.carbohidratos.remove(player);
		plugin.carbohidratos.put(player, 0);
		plugin.cuentaCarbohidratos.remove(player);
		plugin.cuentaCarbohidratos.put(player, Constantes.CUENTAATRAS_FALTA);

		plugin.proteinas.remove(player);
		plugin.proteinas.put(player, 0);
		plugin.cuentaProteinas.remove(player);
		plugin.cuentaProteinas.put(player, Constantes.CUENTAATRAS_FALTA);
		
		plugin.vitaminas.remove(player);
		plugin.vitaminas.put(player, 0);
		plugin.cuentaVitaminas.remove(player);
		plugin.cuentaVitaminas.put(player, Constantes.CUENTAATRAS_FALTA);
		
		plugin.damageCause.remove(player);
		plugin.damageCause.put(player, null);
		
	}
	
	public PlayerRespawnListener(BetterFood i) {
		plugin = i;
	}
	
}
