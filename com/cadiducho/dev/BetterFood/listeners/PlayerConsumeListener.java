package com.cadiducho.dev.BetterFood.listeners;

import com.cadiducho.dev.BetterFood.main.Stats;
import com.cadiducho.dev.BetterFood.main.BetterFood;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerConsumeListener implements Listener {
	
	public static BetterFood plugin;
	int value;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getTypeId() == 92) {
				value = plugin.carbohidratos.get(e.getPlayer());
				value += 2;
				plugin.carbohidratos.remove(e.getPlayer());
				plugin.carbohidratos.put(e.getPlayer(), value);
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent e) {
		
		Player player = e.getPlayer();
		
		if (e.getItem().getType() == Material.GOLDEN_CARROT || e.getItem().getType() == Material.GOLDEN_APPLE) {
			value = 0;
			plugin.proteinas.remove(e.getPlayer());
			plugin.proteinas.put(e.getPlayer(), value);
			plugin.vitaminas.remove(e.getPlayer());
			plugin.vitaminas.put(e.getPlayer(), value);
			plugin.carbohidratos.remove(e.getPlayer());
			plugin.carbohidratos.put(e.getPlayer(), value);
		}

		if (e.getItem().getType() == Material.APPLE || e.getItem().getType() == Material.MELON) {
			value = plugin.carbohidratos.get(e.getPlayer());
			value += 1;
			plugin.carbohidratos.remove(e.getPlayer());
			plugin.carbohidratos.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.COOKIE) {
			value = plugin.carbohidratos.get(e.getPlayer());
			value += 2;
			plugin.carbohidratos.remove(e.getPlayer());
			plugin.carbohidratos.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.PUMPKIN_PIE) {
			value = plugin.vitaminas.get(e.getPlayer());
			value += 2;
			plugin.vitaminas.remove(e.getPlayer());
			plugin.vitaminas.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.CARROT_ITEM || e.getItem().getType() == Material.APPLE ||
				e.getItem().getType() == Material.MUSHROOM_SOUP || e.getItem().getType() == Material.MELON) {
			value = plugin.vitaminas.get(e.getPlayer());
			value += 2;
			plugin.vitaminas.remove(e.getPlayer());
			plugin.vitaminas.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.ROTTEN_FLESH || e.getItem().getType() == Material.SPIDER_EYE) {
			value = plugin.proteinas.get(e.getPlayer());
			value += 1;
			plugin.proteinas.remove(e.getPlayer());
			plugin.proteinas.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.COOKED_BEEF || e.getItem().getType() == Material.COOKED_CHICKEN ||
				e.getItem().getType() == Material.COOKED_FISH || e.getItem().getType() == Material.GRILLED_PORK ||
				e.getItem().getType() == Material.RAW_BEEF || e.getItem().getType() == Material.RAW_CHICKEN ||
				e.getItem().getType() == Material.RAW_FISH || e.getItem().getType() == Material.PORK) {
			value = plugin.proteinas.get(e.getPlayer());
			value += 2;
			plugin.proteinas.remove(e.getPlayer());
			plugin.proteinas.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.COOKIE || e.getItem().getType() == Material.POISONOUS_POTATO) {
			value = plugin.carbohidratos.get(e.getPlayer());
			value += 1;
			plugin.carbohidratos.remove(e.getPlayer());
			plugin.carbohidratos.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.BAKED_POTATO || e.getItem().getType() == Material.BREAD ||
				e.getItem().getType() == Material.POTATO) {
			value = plugin.carbohidratos.get(e.getPlayer());
			value += 2;
			plugin.carbohidratos.remove(e.getPlayer());
			plugin.carbohidratos.put(e.getPlayer(), value);
		}
		
		if (e.getItem().getType() == Material.MILK_BUCKET) {
			plugin.hidratacion.remove(player);
			plugin.hidratacion.put(player, Stats.COMIENZO_HIDRATACION);
			e.getPlayer().removePotionEffect(PotionEffectType.CONFUSION);
		}
		
		if (e.getItem().getType() == Material.POTION) {
			int variable = plugin.hidratacion.get(player);
			plugin.hidratacion.remove(player);
			if (variable < 120) {
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
			}
			variable += 600;
			if (variable >= Stats.COMIENZO_HIDRATACION) {
				variable = Stats.COMIENZO_HIDRATACION;
			}
			plugin.hidratacion.put(player, variable);
		}
		
		if (e.getItem().getType() == Material.MUSHROOM_SOUP) {
			int variable;
                        variable = plugin.hidratacion.get(player);
			plugin.hidratacion.remove(player);
			if (variable < 120) {
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
				e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
			}
			variable += 400;
			if (variable >= Stats.COMIENZO_HIDRATACION) {
				variable = Stats.COMIENZO_HIDRATACION;
			}
			plugin.hidratacion.put(player, variable);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW);
			e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
		}
		
	}
	
	public PlayerConsumeListener(BetterFood i) {
		plugin = i;
	}

}
