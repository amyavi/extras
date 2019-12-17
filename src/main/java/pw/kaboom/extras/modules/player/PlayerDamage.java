package pw.kaboom.extras.modules.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

import org.bukkit.inventory.ItemStack;

public class PlayerDamage implements Listener {
	@EventHandler
	void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntityType() == EntityType.PLAYER) {
			if (event.getCause() == DamageCause.VOID &&
				event.getDamage() == Float.MAX_VALUE) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	void onPlayerDeath(PlayerDeathEvent event) {
		final Player player = event.getEntity();
		final AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			onlinePlayer.sendMessage(event.getDeathMessage());
		}

		if (!event.getKeepInventory()) {
			player.getInventory().clear();
			
			for (ItemStack item : event.getDrops()) {
				player.getWorld().dropItemNaturally(player.getLocation(), item);
			}
		}

		if (event.getDroppedExp() > 0) {
			ExperienceOrb xp = player.getWorld().spawn(player.getLocation(), ExperienceOrb.class);
			xp.setExperience(event.getDroppedExp());
		}

		try {
			maxHealth.setBaseValue(20);
			player.setHealth(20);

			if (player.getBedSpawnLocation() != null) {
				player.teleportAsync(player.getBedSpawnLocation());
			} else {
				final World world = Bukkit.getWorld("world");
				final Location spawnLocation = world.getSpawnLocation();
	
				for (double y = spawnLocation.getY(); y <= 256; y++) {
					final Location yLocation = new Location(world, spawnLocation.getX(), y, spawnLocation.getZ());
					final Block coordBlock = world.getBlockAt(yLocation);
	
					if (!coordBlock.getType().isSolid() &&
						!coordBlock.getRelative(BlockFace.UP).getType().isSolid()) {
						player.teleportAsync(yLocation);
						break;
					}
				}
			}
		} catch (Exception exception) {
			maxHealth.setBaseValue(Double.POSITIVE_INFINITY);
			player.setHealth(20);
			maxHealth.setBaseValue(20);
		}

		player.setExp(event.getNewExp());
		player.setLevel(event.getNewLevel());
		player.setFoodLevel(20);
		player.setFireTicks(0);
		player.setRemainingAir(player.getMaximumAir());
		player.getActivePotionEffects().clear();

		event.setCancelled(true);
	}
}
