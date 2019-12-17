package pw.kaboom.extras.modules.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.CommandSender;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import pw.kaboom.extras.modules.server.ServerCommand;

public class PlayerCommand implements Listener {
	static HashMap<UUID, Long> commandMillisList = new HashMap<>();

	@EventHandler
	void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		final UUID playerUuid = event.getPlayer().getUniqueId();
		
		if (commandMillisList.get(playerUuid) != null) {
			final long millisDifference = System.currentTimeMillis() - commandMillisList.get(playerUuid);
	
			if (millisDifference < 75) {
				event.setCancelled(true);
			}
		}
		
		commandMillisList.put(playerUuid, System.currentTimeMillis());
		
		if (event.isCancelled()) {
			return;
		}
		
		final CommandSender sender = event.getPlayer();
		final String command = event.getMessage();
		final boolean isConsoleCommand = false;
		final String checkedCommand = ServerCommand.checkCommand(sender, command, isConsoleCommand);

		if (checkedCommand != null) {
			if (checkedCommand.equals("cancel")) {
				event.setCancelled(true);
			} else {
				event.setMessage(checkedCommand);
			}
		}
	}
}
