package org.hqpots.bolt.selector;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.hqpots.bolt.utils.ItemStackBuilder;

public class ServerSelector implements Listener
{
	static List<String> serverSelectorLore = new ArrayList<>();
	static List<String> hardcoreFactionsLore = new ArrayList<>();
	static List<String> kitsLore = new ArrayList<>();
	static List<String> potsLore = new ArrayList<>();
	static List<String> none = new ArrayList<>();

	static
	{
		serverSelectorLore.add(ChatColor.translateAlternateColorCodes('&', "&6&lRIGHT CLICK &7to open the server selector"));

		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------------------------"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&cPlease use versions 1.7 or 1.8 to connect"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&6Discription:"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&7Fight in &eTeam &7battles with your &6friends"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&cDTR &7is &4important&7, Dont &cforget &7or you will go &5Raidable"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&7Make sure to not &dmiss &7the &3events"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&7such as &9KoTHs &7and &6Conquest"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&6&lCLICK &7to connect to &6&lHCFactions"));
		hardcoreFactionsLore.add(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------------------------"));

		kitsLore.add(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------------------------"));
		kitsLore.add(ChatColor.translateAlternateColorCodes('&', "&cPlease use versions 1.7 or 1.8 to connect"));
		kitsLore.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		kitsLore.add(ChatColor.translateAlternateColorCodes('&', "&6Discription:"));
		kitsLore.add(ChatColor.translateAlternateColorCodes('&', "&7This gamemode revolves around &6you &7and your &6faction"));
		kitsLore.add(ChatColor.translateAlternateColorCodes('&', "&7this mainly contains events such as &9KoTHS &7and &6Team Fights"));
		kitsLore.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		kitsLore.add(ChatColor.translateAlternateColorCodes('&', "&6&lCLICK &7to connect to &6&lKits"));
		kitsLore.add(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------------------------"));

		potsLore.add(ChatColor.translateAlternateColorCodes('&', "&7&m--------------------------------------------"));
		potsLore.add(ChatColor.translateAlternateColorCodes('&', "&cServer Down!"));
		potsLore.add(ChatColor.translateAlternateColorCodes('&', "&7&m--------------------------------------------"));

		none.add("");
	}

	/**
	 * TODO:
	 *
	 * Make code more efficient Update code
	 *
	 */

	public static ItemStack glass1 = ItemStackBuilder.get(Material.STAINED_GLASS_PANE, 1, (short) 0, "", none);
	public static ItemStack selector = ItemStackBuilder.get(Material.COMPASS, 1, (short) 0, "&6Server Selector", serverSelectorLore);
	public static ItemStack factions = ItemStackBuilder.get(Material.FISHING_ROD, 1, (short) 0, "&6HCFactions &c[Map 3]", hardcoreFactionsLore);
	public static ItemStack kitmap = ItemStackBuilder.get(Material.DIAMOND_HELMET, 1, (short) 0, "&6KitMap &c[Season 2]", kitsLore);
	public static ItemStack pots = ItemStackBuilder.get(Material.DIAMOND_SWORD, 1, (short) 0, "&6Practice &c[Beta]", potsLore);
	public static Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&8Server Selector"));

	/**
	 * TODO:
	 *
	 * Make code more efficient Update code
	 *
	 */

	public ServerSelector()
	{
		inv.setItem(0, glass1);
		inv.setItem(1, glass1);
		inv.setItem(2, glass1);
		inv.setItem(3, glass1);
		inv.setItem(4, glass1);
		inv.setItem(5, glass1);
		inv.setItem(6, glass1);
		inv.setItem(7, glass1);
		inv.setItem(8, glass1);
		inv.setItem(7, glass1);
		inv.setItem(9, glass1);
		inv.setItem(10, glass1);
		inv.setItem(12, glass1);
		inv.setItem(14, glass1);
		inv.setItem(16, glass1);
		inv.setItem(17, glass1);
		inv.setItem(18, glass1);
		inv.setItem(19, glass1);
		inv.setItem(20, glass1);
		inv.setItem(21, glass1);
		inv.setItem(22, glass1);
		inv.setItem(23, glass1);
		inv.setItem(24, glass1);
		inv.setItem(25, glass1);
		inv.setItem(26, glass1);

		inv.setItem(13, factions);
		inv.setItem(11, kitmap);
		inv.setItem(15, pots);
	}

	/**
	 * TODO: Redo this method
	 */

	@EventHandler
	public void InventoryClick(InventoryClickEvent e)
	{
		e.setCancelled(true);
		if ((!(e.getWhoClicked() instanceof Player)) || (e.getCurrentItem() == null)) { return; }
		if (e.getInventory().getType().equals(InventoryType.PLAYER))
		{
			e.setCancelled(false);
		}

		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();
		if (item.isSimilar(factions))
		{
			Bukkit.dispatchCommand(p, "play hcf");
			p.closeInventory();
		}
		else if (item.isSimilar(kitmap))
		{
			Bukkit.dispatchCommand(p, "play Kitmap");
			p.closeInventory();
		}
		else if (item.isSimilar(pots))
		{
			Bukkit.dispatchCommand(p, "play Practice");
			p.closeInventory();
		}
		else if (item.isSimilar(glass1))
		{
			p.closeInventory();
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		if ((!e.getAction().equals(Action.PHYSICAL)) && (e.getItem() != null) && (e.getItem().isSimilar(selector)))
		{
			Player p = e.getPlayer();
			p.openInventory(inv);
			e.setCancelled(true);
		}
	}
}