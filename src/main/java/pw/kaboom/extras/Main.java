package pw.kaboom.extras;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashSet;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.bukkit.Color;
import org.bukkit.Material;

import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	int onlineCount = 0;
	File spawnSchematic = new File("worlds/world/spawn.schematic");
	HashSet<String> consoleCommandBlacklist = new HashSet<String>(Arrays.asList(new String[] {
		"essentials:action",
		"essentials:adventure",
		"essentials:adventuremode",
		"essentials:afk",
		"essentials:amsg",
		"essentials:away",
		"essentials:ban",
		"essentials:banip",
		"essentials:bc",
		"essentials:bcast",
		"essentials:bcastw",
		"essentials:bcw",
		"essentials:broadcast",
		"essentials:broadcastworld",
		"essentials:ci",
		"essentials:clean",
		"essentials:clear",
		"essentials:clearinvent",
		"essentials:clearinventory",
		"essentials:creative",
		"essentials:creativemode",
		"essentials:describe",
		"essentials:eaction",
		"essentials:eadventure",
		"essentials:eadventuremode",
		"essentials:eafk",
		"essentials:eat",
		"essentials:eamsg",
		"essentials:eaway",
		"essentials:eban",
		"essentials:ebanip",
		"essentials:ebc",
		"essentials:ebcast",
		"essentials:ebcastw",
		"essentials:ebcw",
		"essentials:ebroadcast",
		"essentials:ebroadcastworld",
		"essentials:eci",
		"essentials:eco",
		"essentials:economy",
		"essentials:eclean",
		"essentials:eclear",
		"essentials:eclearinvent",
		"essentials:eclearinventory",
		"essentials:ecreative",
		"essentials:ecreativemode",
		"essentials:edescribe",
		"essentials:eeat",
		"essentials:eeco",
		"essentials:eeconomy",
		"essentials:eemail",
		"essentials:efeed",
		"essentials:egamemode",
		"essentials:egm",
		"essentials:egma",
		"essentials:egmc",
		"essentials:egms",
		"essentials:egmsp",
		"essentials:egmt",
		"essentials:eheal",
		"essentials:ehelpop",
		"essentials:ejail",
		"essentials:ekick",
		"essentials:ekill",
		"essentials:email",
		"essentials:eme",
		"essentials:ememo",
		"essentials:emute",
		"essentials:emsg",
		"essentials:enuke",
		"essentials:epardon",
		"essentials:epardonip",
		"essentials:epm",
		"essentials:eshoutworld",
		"essentials:esilence",
		"essentials:esudo",
		"essentials:esurvival",
		"essentials:esurvivalmode",
		"essentials:etele",
		"essentials:eteleport",
		"essentials:etell",
		"essentials:etempban",
		"essentials:etjail",
		"essentials:etogglejail",
		"essentials:etp",
		"essentials:etp2p",
		"essentials:etpaall",
		"essentials:etpall",
		"essentials:etppos",
		"essentials:etptoggle",
		"essentials:eunban",
		"essentials:eunbanip",
		"essentials:ev",
		"essentials:evanish",
		"essentials:ewarp",
		"essentials:ewarps",
		"essentials:ewhisper",
		"essentials:feed",
		"essentials:gamemode",
		"essentials:gm",
		"essentials:gma",
		"essentials:gmc",
		"essentials:gms",
		"essentials:gmsp",
		"essentials:gmt",
		"essentials:heal",
		"essentials:helpop",
		"essentials:jail",
		"essentials:kick",
		"essentials:kill",
		"essentials:m",
		"essentials:mail",
		"essentials:me",
		"essentials:memo",
		"essentials:mute",
		"essentials:msg",
		"essentials:nuke",
		"essentials:pardon",
		"essentials:pardonip",
		"essentials:pm",
		"essentials:shoutworld",
		"essentials:silence",
		"essentials:sp",
		"essentials:spec",
		"essentials:spectator",
		"essentials:sudo",
		"essentials:survival",
		"essentials:survivalmode",
		"essentials:t",
		"essentials:tele",
		"essentials:teleport",
		"essentials:tell",
		"essentials:tempban",
		"essentials:tjail",
		"essentials:togglejail",
		"essentials:tp",
		"essentials:tp2p",
		"essentials:tpaall",
		"essentials:tpall",
		"essentials:tppos",
		"essentials:tptoggle",
		"essentials:unban",
		"essentials:unbanip",
		"essentials:v",
		"essentials:vanish",
		"essentials:w",
		"essentials:warp",
		"essentials:warps",
		"essentials:whisper",
		"extras:cc",
		"extras:clearchat",
		"minecraft:clear",
		"minecraft:effect",
		"minecraft:execute",
		"minecraft:gamemode",
		"minecraft:gamerule",
		"minecraft:kill",
		"minecraft:me",
		"minecraft:say",
		"minecraft:spreadplayers",
		"minecraft:tell",
		"minecraft:tellraw",
		"minecraft:title",
		"minecraft:tp",
		"action",
		"adventure",
		"adventuremode",
		"afk",
		"amsg",
		"away",
		"ban",
		"banip",
		"bc",
		"bcast",
		"bcastw",
		"bcw",
		"broadcast",
		"broadcastworld",
		"cc",
		"ci",
		"clean",
		"clear",
		"clearchat",
		"clearinvent",
		"clearinventory",
		"creative",
		"creativemode",
		"describe",
		"eaction",
		"eadventure",
		"eadventuremode",
		"eafk",
		"eamsg",
		"eat",
		"eaway",
		"eban",
		"ebanip",
		"ebc",
		"ebcast",
		"ebcastw",
		"ebcw",
		"ebroadcast",
		"ebroadcastworld",
		"eci",
		"eclean",
		"eclear",
		"eclearinvent",
		"eclearinventory",
		"ecreativemode",
		"eco",
		"economy",
		"edescribe",
		"eeat",
		"eeco",
		"eeconomy",
		"eecreative",
		"eemail",
		"efeed",
		"effect",
		"egamemode",
		"egm",
		"egma",
		"egmc",
		"egms",
		"egmsp",
		"egmt",
		"eheal",
		"ehelpop",
		"ejail",
		"ekick",
		"ekill",
		"email",
		"eme",
		"ememo",
		"emute",
		"emsg",
		"enuke",
		"epardon",
		"epardonip",
		"epm",
		"eshoutworld",
		"esilence",
		"esudo",
		"esurvival",
		"esurvivalmode",
		"etele",
		"eteleport",
		"etell",
		"etempban",
		"etjail",
		"etogglejail",
		"etp",
		"etp2p",
		"etpaall",
		"etpall",
		"etppos",
		"etptoggle",
		"eunban",
		"eunbanip",
		"ev",
		"evanish",
		"ewarp",
		"ewarps",
		"ewhisper",
		"execute",
		"feed",
		"gamemode",
		"gamerule",
		"gm",
		"gma",
		"gmc",
		"gms",
		"gmsp",
		"gmt",
		"heal",
		"helpop",
		"jail",
		"kill",
		"m",
		"mail",
		"me",
		"memo",
		"mute",
		"msg",
		"nuke",
		"paper:paper",
		"paper",
		"pardon",
		"pardonip",
		"pm",
		"shoutworld",
		"say",
		"silence",
		"sp",
		"spec",
		"spectator",
		"spigot:spigot",
		"spigot",
		"spreadplayers",
		"sudo",
		"survival",
		"survivalmode",
		"t",
		"tele",
		"teleport",
		"tell",
		"tempban",
		"title",
		"tjail",
		"togglejail",
		"tp",
		"tp2p",
		"tpaall",
		"tpall",
		"tppos",
		"tptoggle",
		"unban",
		"unbanip",
		"v",
		"vanish",
		"w",
		"warp",
		"warps",
		"whisper",
	}));

	public void onEnable() {
		this.getCommand("clearchat").setExecutor(new CommandClearChat());
		this.getCommand("console").setExecutor(new CommandConsole());
		this.getCommand("destroyentities").setExecutor(new CommandDestroyEntities());
		this.getCommand("discord").setExecutor(new CommandDiscord());
		this.getCommand("enchantall").setExecutor(new CommandEnchantAll());
		this.getCommand("jumpscare").setExecutor(new CommandJumpscare());
		this.getCommand("prefix").setExecutor(new CommandPrefix(this));
		this.getCommand("skin").setExecutor(new CommandSkin());
		this.getCommand("spawn").setExecutor(new CommandSpawn());
		this.getCommand("tellraw").setExecutor(new CommandTellraw());
		this.getCommand("unloadchunks").setExecutor(new CommandUnloadChunks());
		this.getCommand("username").setExecutor(new CommandUsername(this));

		new PasteSpawn(this).runTaskTimer(this, 0, 200);
		new Tick().runTaskTimer(this, 0, 1);
		this.getServer().getPluginManager().registerEvents(new Events(this), this);
	}

	public void getSkin(String name, Player player) {
		try {
			URL nameurl = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
			HttpURLConnection nameconnection = (HttpURLConnection) nameurl.openConnection();

			if (nameconnection.getResponseCode() == 200) {
				InputStreamReader namestream = new InputStreamReader(nameconnection.getInputStream());
				String uuid = new JsonParser().parse(namestream).getAsJsonObject().get("id").getAsString();
				URL uuidurl = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
				HttpURLConnection uuidconnection = (HttpURLConnection) uuidurl.openConnection();

				if (uuidconnection.getResponseCode() == 200) {
					InputStreamReader uuidstream = new InputStreamReader(uuidconnection.getInputStream());
					JsonObject response = new JsonParser().parse(uuidstream).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
					String texture = response.get("value").getAsString();
					String signature = response.get("signature").getAsString();

					PlayerProfile textureprofile = player.getPlayerProfile();
					textureprofile.setProperty(new ProfileProperty("textures", texture, signature));
					player.setPlayerProfile(textureprofile);
				}
				uuidconnection.disconnect();
			}
			nameconnection.disconnect();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void editMeta(ItemStack item) {
		if (item.getType() == Material.LINGERING_POTION ||
		item.getType() == Material.POTION ||
		item.getType() == Material.SPLASH_POTION) {
			PotionMeta potion = (PotionMeta) item.getItemMeta();
			potion.setColor(Color.WHITE);
		} else if (item.getType() == Material.LEATHER_BOOTS ||
		item.getType() == Material.LEATHER_CHESTPLATE ||
		item.getType() == Material.LEATHER_HELMET ||
		item.getType() == Material.LEATHER_LEGGINGS) {
			LeatherArmorMeta armor = (LeatherArmorMeta) item.getItemMeta();
			armor.setColor(Color.fromRGB(160,101,64));
		}
	}
}
