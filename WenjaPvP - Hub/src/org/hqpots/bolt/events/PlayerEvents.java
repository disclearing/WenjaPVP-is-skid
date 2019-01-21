package org.hqpots.bolt.events;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.hqpots.bolt.Bolt;
import org.hqpots.bolt.selector.ServerSelector;
import org.hqpots.bolt.utils.ItemStackBuilder;
import org.hqpots.bolt.utils.ServerInfo;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;

public class PlayerEvents implements Listener
{

	static List<String> clayLore = new ArrayList<>();
	static List<String> enderpearlLore = new ArrayList<>();

	static
	{
		clayLore.add(ChatColor.translateAlternateColorCodes('&', "&a&lRIGHT Click&7 to toggle visable players on and off"));
		enderpearlLore.add(ChatColor.translateAlternateColorCodes('&', "&a&lRIGHT CLICK&7 to travel"));
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		for (Item item : this.epItems)
		{
			if (item.getPassenger().equals(p))
			{
				item.eject();
			}
		}
		p.getInventory().clear();
		p.setWalkSpeed(0.5F);
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getInventory().setItem(4, ServerSelector.selector);
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event)
	{
		event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event)
	{
		if ((event.getEntity() instanceof Player))
		{
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerJoinEvent2(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------"));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lStore: &7store.wenjapvp.net"));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lForum: &7www.wenjapvp.net"));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lTwitter: &7@WenjaPvP"));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m------------------------"));
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if (!event.getPlayer().isOp())
		{
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if (!event.getPlayer().isOp())
		{
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void bucketFill(PlayerBucketEmptyEvent event)
	{
		if (!event.getPlayer().isOp())
		{
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void bucketEmpty(PlayerBucketFillEvent event)
	{
		if (!event.getPlayer().isOp())
		{
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e)
	{
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerPickItem(PlayerPickupItemEvent e)
	{
		e.setCancelled(true);
		e.getItem().remove();
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e)
	{
		e.setQuitMessage(null);
	}

	@EventHandler
	public void onJoindsa(PlayerJoinEvent e)
	{
		e.setJoinMessage(null);
	}

	@EventHandler
	public void onPlayerShitHeadJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		if (player.getName().equalsIgnoreCase("Levice"))
		{
			player.setBanned(true);
		}
		else if (player.getName().equalsIgnoreCase("MilkSales"))
		{
			player.setBanned(true);
		}
	}

	private ArrayList<String> usingClock = new ArrayList();

	@EventHandler
	public void onPlayerJoin2(PlayerJoinEvent e)
	{
		ItemStack i = new ItemStack(Material.CLAY_BALL);
		ItemMeta iMeta = i.getItemMeta();
		i.setItemMeta(iMeta);
		for (Player p : Bukkit.getServer().getOnlinePlayers())
		{
			if (this.usingClock.contains(p.getName()))
			{
				p.hidePlayer(e.getPlayer());
			}
			else
			{
				p.showPlayer(e.getPlayer());
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		Action a = e.getAction();

		ItemStack is = e.getItem();
		if ((a == Action.PHYSICAL) || (is == null) || (is.getType() == Material.AIR)) { return; }
		if ((is.getType() == Material.CLAY_BALL) && (is.hasItemMeta()))
		{
			if (this.usingClock.contains(e.getPlayer().getName()))
			{
				this.usingClock.remove(e.getPlayer().getName());
				for (Player p : Bukkit.getServer().getOnlinePlayers())
				{
					e.getPlayer().showPlayer(p);
				}
			}
			else
			{
				this.usingClock.add(e.getPlayer().getName());
				for (Player p : Bukkit.getServer().getOnlinePlayers())
				{
					e.getPlayer().hidePlayer(p);
				}
			}
		}
	}

	public static void sendPlayerToServer(Player player, ServerInfo server)
	{
		sendPlayerToServer(player, server.name);
	}

	public static void sendPlayerToServer(Player player, String server)
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		player.sendPluginMessage(Bolt.getInstance(), "BungeeCord", out.toByteArray());
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e)
	{
		e.setCancelled(true);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e)
	{
		e.setCancelled(true);
	}

	@EventHandler
	public void onTrhowEnder(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) { return; }
		if ((!event.hasItem()) || (!event.getItem().getType().equals(Material.ENDER_PEARL)) || ((!action.equals(Action.RIGHT_CLICK_AIR)) && (!action.equals(Action.RIGHT_CLICK_BLOCK)))) { return; }
		event.setCancelled(true);
		event.setUseItemInHand(Event.Result.DENY);
		event.setUseInteractedBlock(Event.Result.DENY);
		Item ep = player.getWorld().dropItem(player.getLocation().add(0.0D, 0.5D, 0.0D), ItemStackBuilder.get(Material.ENDER_PEARL, 1, (short) 0, UUID.randomUUID().toString(), null));
		ep.setPickupDelay(10000);
		ep.setVelocity(player.getLocation().getDirection().normalize().multiply(2.0F));
		ep.setPassenger(player);
		player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
		deleteItemWhenNeeded(ep);
		player.updateInventory();
	}

	Set<Item> epItems = new HashSet();

	public void deleteItemWhenNeeded(final Item item)
	{
		new BukkitRunnable()
		{
			public void run()
			{
				if (item.isDead())
				{
					cancel();
				}
				if ((item.getVelocity().getX() == 0.0D) || (item.getVelocity().getY() == 0.0D) || (item.getVelocity().getZ() == 0.0D))
				{
					Player p = (Player) item.getPassenger();
					PlayerEvents.this.epItems.remove(item);
					item.remove();
					if (p != null)
					{
						p.teleport(p.getLocation().add(0.0D, 0.5D, 0.0D));
					}
					cancel();
				}
			}
		}.runTaskTimer(Bolt.getInstance(), 2L, 1L);
	}

	@EventHandler
	public void handleEntityDamageByEntityEvent(EntityDamageByEntityEvent event)
	{
		if ((event.getDamager() instanceof Player))
		{
			Player damager = (Player) event.getDamager();
			if ((event.getEntity() instanceof Player))
			{
				Player damagee = (Player) event.getEntity();
				damager.hidePlayer(damagee);
				damager.sendMessage(ChatColor.AQUA + "Pop...");
			}
		}
	}
}
