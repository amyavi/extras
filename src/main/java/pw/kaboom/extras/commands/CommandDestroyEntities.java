package pw.kaboom.extras.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class CommandDestroyEntities implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		int entityCount = 0;
		int worldCount = 0;

		for (World world : Bukkit.getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if (entity.getType() != EntityType.PLAYER) {
					entity.remove();
					entityCount++;
				}
			}
			worldCount++;
		}
		sender.sendMessage("Successfully destroyed " + entityCount + " entities in " + worldCount + " worlds");
		return true;
	}
}
