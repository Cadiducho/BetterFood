/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cadiducho.dev.BetterFood.util;

import com.cadiducho.dev.BetterFood.BetterFood;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Administrador
 */
public class Metodos {
    public static BetterFood plugin;
	
    public Metodos(BetterFood instance) {
	plugin = instance;
    }
    
    public void menuAyuda(Player sender) {
		sender.sendMessage(ChatColor.GREEN + "**" + ChatColor.YELLOW + "--------------{ " +
                	ChatColor.GOLD +	"BetterFood Help" + ChatColor.YELLOW + " }------------" + ChatColor.GREEN + "**");
            sender.sendMessage(ChatColor.GREEN + "/health ayuda ► Ayuda en español/Help in Spanish");
            sender.sendMessage(ChatColor.GREEN + "/health ► View your health stats");
            if (sender.hasPermission("betterfood.admin")) {
            sender.sendMessage(ChatColor.GOLD + "[ADMIN]" + ChatColor.YELLOW + "/health reset <player> ► Reset someone stats");
            sender.sendMessage(ChatColor.GOLD +  "[ADMIN]" + ChatColor.YELLOW + "/health setfood <player> <int> ► Set someone stats");
            }
            sender.sendMessage(ChatColor.GREEN + "/health help ► Show this message");
        }
    public void menuAyudaEs(Player sender) {
		sender.sendMessage(ChatColor.GREEN + "**" + ChatColor.YELLOW + "--------------{ " +
				ChatColor.GOLD +	"Ayuda de BetterFood" + ChatColor.YELLOW + " }------------" + ChatColor.GREEN + "**");
            sender.sendMessage(ChatColor.GREEN + "/salud help ► Help in English");
            sender.sendMessage(ChatColor.GREEN + "/salud ► Ver tus stats de salud");
            if (sender.hasPermission("betterfood.admin")) {
            sender.sendMessage(ChatColor.GOLD + "[ADMIN]" + ChatColor.YELLOW + "/salud reset <jugador> ► Reiniciar las stats de alguien");
            sender.sendMessage(ChatColor.GOLD +  "[ADMIN]" + ChatColor.YELLOW + "/salud setfood <jugador> <nºint> ► Cambiar las stats de alguien");
            }
            sender.sendMessage(ChatColor.GREEN + "/salud ayuda ► Ver este mensaje");
    }
    /*public void reset(Player player) {
                        this.hidratacion.put(player, Stats.COMIENZO_HIDRATACION);
			this.carbohidratos.put(player, 0);
			this.proteinas.put(player, 0);
			this.vitaminas.put(player, 0);
    }*/
}
