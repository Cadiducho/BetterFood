package es.corecraft.cadi.betterfood.principal;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Timer implements Runnable {

	public static BetterFood plugin;
	Random ran = new Random();
	
	public Timer(BetterFood i) {
		plugin = i;
	}
	
	@Override
	public void run() {
	
            /* Strings de la config (messages.yml)
             *  Cargar y leer todos los mensajes
             */
            String prot1 = plugin.getMessages().getString("afaw");
            String prot2 = plugin.getMessages().getString("asfsvg");
            String prot3 = plugin.getMessages().getString("prot");
            
		try {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.getGameMode().getValue() != 1) {
					
					double total = plugin.carbohidratos.get(player) + plugin.proteinas.get(player) +
							plugin.vitaminas.get(player);
					int value;
					
					if (total >= 9) {
						
						/*
						 * Proteinas Pocas
						 */
						if (plugin.proteinas.get(player) / total < 0.1) {
							
							value = plugin.cuentaProteinas.get(player);
							value -= 1;
							plugin.cuentaProteinas.remove(player);
							plugin.cuentaProteinas.put(player, value);
							
							if (value == 119) {
								player.sendMessage(prot1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(6) + 6) * 20, 0));
								}
							}
							if (value == 90) {
								player.sendMessage(prot2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(12) + 6) * 20, 1));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(12) + 2) * 20, 3));
								}
							}
							if (value == 60) {
								player.sendMessage(prot3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (ran.nextInt(18) + 6) * 20, 2));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(18) + 6) * 20, 4));
								}
								if (ran.nextInt(2) == 0) {
									plugin.damageCause.remove(player);
									plugin.damageCause.put(player, "proteinas");
									player.damage(1);
									if (!(player.isDead())) {
										plugin.damageCause.remove(player);
										plugin.damageCause.put(player, null);
									}
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "proteinas");
								player.damage(999999);
							}
							
						}
						/*
						 * Proteinas Muchas
						 */
						else if (plugin.proteinas.get(player) / total > 0.65) {
							
							value = plugin.cuentaProteinas.get(player);
							value -= 1;
							plugin.cuentaProteinas.remove(player);
							plugin.cuentaProteinas.put(player, value);
							
							if (value == 119) {
								player.sendMessage(plugin.PROTEINAS_MUCHAS_NIVEL_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (ran.nextInt(6) + 6) * 20, 0));
								}
							}
							if (value == 90) {
								player.sendMessage(plugin.PROTEINAS_MUCHAS_NIVEL_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (ran.nextInt(12) + 6) * 20, 1));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(12) + 2) * 20, 0));
								}
							}
							if (value == 60) {
								player.sendMessage(plugin.PROTEINAS_MUCHAS_NIVEL_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (ran.nextInt(18) + 6) * 20, 2));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (ran.nextInt(18) + 6) * 20, 0));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(6) + 2) * 20, 0));
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "proteinasExtra");
								player.damage(999999);
							}
							
						} else if (plugin.cuentaProteinas.get(player) < Constantes.CUENTAATRAS_FALTA) {
							plugin.cuentaProteinas.remove(player);
							plugin.cuentaProteinas.put(player, Constantes.CUENTAATRAS_FALTA);
						}
						
						/*
						 * Vitaminas Pocas
						 */
						if (plugin.vitaminas.get(player) / total < 0.1) {
							
							value = plugin.cuentaVitaminas.get(player);
							value -= 1;
							plugin.cuentaVitaminas.remove(player);
							plugin.cuentaVitaminas.put(player, value);
							
							if (value == 119) {
								player.sendMessage(plugin.VITAMINAS_NIVEL_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(6) + 6) * 20, 2));
								}
							}
							if (value == 90) {
								player.sendMessage(plugin.VITAMINAS_NIVEL_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(12) + 6) * 20, 3));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(12) + 6) * 20, 1));
								}
							}
							if (value == 60) {
								player.sendMessage(plugin.VITAMINAS_NIVEL_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (ran.nextInt(18) + 6) * 20, 4));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(18) + 6) * 20, 2));
								}
								if (ran.nextInt(2) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(12) + 6) * 20, 0));
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "vitaminas");
								player.damage(999999);
							}
							
						}
						/*
						 * Muchas Vitaminas
						 */
						else if (plugin.vitaminas.get(player) / total > 0.65) {
							
							value = plugin.cuentaVitaminas.get(player);
							value -= 1;
							plugin.cuentaVitaminas.remove(player);
							plugin.cuentaVitaminas.put(player, value);
							
							if (value == 119) {
								player.sendMessage(plugin.VITAMINAS_MUCHAS_NIVEL_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, (ran.nextInt(8) + 2) * 20, 0));
								}
							}
							if (value == 90) {
								player.sendMessage(plugin.VITAMINAS_MUCHAS_NIVEL_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, (ran.nextInt(8) + 2) * 20, 0));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(12) + 2) * 20, 0));
								}
							}
							if (value == 60) {
								player.sendMessage(plugin.VITAMINAS_MUCHAS_NIVEL_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, (ran.nextInt(8) + 2) * 20, 0));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (ran.nextInt(16) + 2) * 20, 1));
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "vitaminasExtra");
								player.damage(999999);
							}
							
						} else if (plugin.cuentaVitaminas.get(player) < Constantes.CUENTAATRAS_FALTA) {
							plugin.cuentaVitaminas.remove(player);
							plugin.cuentaVitaminas.put(player, Constantes.CUENTAATRAS_FALTA);
						}
						
						/*
						 * Pocos Carbohidratos
						 */
						if (plugin.carbohidratos.get(player) / total < 0.1) {
							
							value = plugin.cuentaCarbohidratos.get(player);
							value -= 1;
							plugin.cuentaCarbohidratos.remove(player);
							plugin.cuentaCarbohidratos.put(player, value);
							
							if (value == 119) {
								player.sendMessage(plugin.CARBOHIDRATOS_NIVEL_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(6) + 6) * 20, 0));
								}
							}
							if (value == 90) {
								player.sendMessage(plugin.CARBOHIDRATOS_NIVEL_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(12) + 6) * 20, 1));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(12) + 6) * 20, 1));
								}
							}
							if (value == 60) {
								player.sendMessage(plugin.CARBOHIDRATOS_NIVEL_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (ran.nextInt(18) + 6) * 20, 1));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (ran.nextInt(18) + 6) * 20, 1));
								}
								if (ran.nextInt(2) == 0) {
									plugin.damageCause.remove(player);
									plugin.damageCause.put(player, "carbohidratos");
									player.damage(1);
									if (!(player.isDead())) {
										plugin.damageCause.remove(player);
										plugin.damageCause.put(player, null);
									}
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "carbohidratos");
								player.damage(999999);
							}
							
						}
						/*
						 * Muchos carbohidratos
						 */
						else if (plugin.carbohidratos.get(player) / total > 0.65) {
							
							value = plugin.cuentaCarbohidratos.get(player);
							value -= 1;
							plugin.cuentaCarbohidratos.remove(player);
							plugin.cuentaCarbohidratos.put(player, value);
							
							if (value == 119) {
								player.sendMessage(plugin.CARBOHIDRATOS_MUCHOS_NIVEL_1);
							}
							if (value <= 120 && value > 90) {
								if (ran.nextInt(10) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (ran.nextInt(8) + 2) * 20, 2));
								}
							}
							if (value == 90) {
								player.sendMessage(plugin.CARBOHIDRATOS_MUCHOS_NIVEL_2);
							}
							if (value <= 90 && value > 60) {
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (ran.nextInt(8) + 2) * 20, 3));
								}
								if (ran.nextInt(8) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (ran.nextInt(8) + 2) * 20, 3));
								}
							}
							if (value == 60) {
								player.sendMessage(plugin.CARBOHIDRATOS_MUCHOS_NIVEL_3);
							}
							if (value <= 60) {
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (ran.nextInt(8) + 2) * 20, 5));
								}
								if (ran.nextInt(6) == 0) {
									player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (ran.nextInt(8) + 2) * 20, 5));
								}
								if (ran.nextInt(2) == 0) {
									plugin.damageCause.remove(player);
									plugin.damageCause.put(player, "carbohidratosExtra");
									player.damage(1);
									if (!(player.isDead())) {
										plugin.damageCause.remove(player);
										plugin.damageCause.put(player, null);
									}
								}
							}
							if (value <= 0) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, "carbohidratosExtra");
								player.damage(999999);
							}
							
						} else if (plugin.cuentaCarbohidratos.get(player) < Constantes.CUENTAATRAS_FALTA) {
							plugin.cuentaCarbohidratos.remove(player);
							plugin.cuentaCarbohidratos.put(player, Constantes.CUENTAATRAS_FALTA);
						}
					}
					
					/*
					 * Hidratacion
					 */
					value = plugin.hidratacion.get(player);
					plugin.hidratacion.remove(player);
					value--;
					
					if (value > 0) {
						plugin.hidratacion.put(player, value);
					} else {
						plugin.damageCause.remove(player);
						plugin.damageCause.put(player, "deshidratacion");
						player.damage(999999);
					}
					
					if (value <= 120 && value > 90) {
						if (value == 120) {
							player.sendMessage(plugin.DESHIDRATACION_NIVEL_2);
						}
						if (ran.nextInt(10) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(6) + 6) * 20, 0));
						}
					} else if (value <= 90 && value > 60) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 22, 0));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 22, 0));
						if (value == 90) {
							player.sendMessage(plugin.DESHIDRATACION_NIVEL_3);
						}
						if (ran.nextInt(8) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(12) + 6) * 20, 1));
						}
					} else  if (value <= 60) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 22, 1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 22, 1));
						if (value == 60) {
							player.sendMessage(plugin.DESHIDRATACION_NIVEL_4);
						}
						if (ran.nextInt(2) == 0) {
							plugin.damageCause.remove(player);
							plugin.damageCause.put(player, "deshidratacion");
							player.damage(1);
							if (!(player.isDead())) {
								plugin.damageCause.remove(player);
								plugin.damageCause.put(player, null);
							}
						}
						if (ran.nextInt(6) == 0) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (ran.nextInt(18) + 6) * 20, 2));
						}
						
					}
				}
			}
		} catch (Exception e) { }
		
	}

}
