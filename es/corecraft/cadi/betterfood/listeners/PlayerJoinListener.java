package es.corecraft.cadi.betterfood.listeners;

import es.corecraft.cadi.betterfood.principal.Constantes;
import es.corecraft.cadi.betterfood.principal.BetterFood;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

	public static BetterFood plugin;
	
	public PlayerJoinListener(BetterFood i) {
		plugin = i;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		plugin.hidratacion.put(player, Constantes.COMIENZO_HIDRATACION);
		plugin.damageCause.put(player, null);
		plugin.carbohidratos.put(player, 0);
		plugin.cuentaCarbohidratos.put(player, Constantes.CUENTAATRAS_FALTA);
		plugin.proteinas.put(player, 0);
		plugin.cuentaProteinas.put(player, Constantes.CUENTAATRAS_FALTA);
		plugin.vitaminas.put(player, 0);
		plugin.cuentaVitaminas.put(player, Constantes.CUENTAATRAS_FALTA);
	}
	
}
