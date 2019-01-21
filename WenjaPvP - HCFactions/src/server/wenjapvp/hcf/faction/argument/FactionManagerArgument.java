package server.wenjapvp.hcf.faction.argument;

import com.doctordark.util.command.CommandArgument;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import server.wenjapvp.hcf.HCF;
import server.wenjapvp.hcf.faction.struct.Role;
import server.wenjapvp.hcf.faction.type.Faction;
import server.wenjapvp.hcf.faction.type.PlayerFaction;

import java.util.Arrays;

/**
 * Faction argument used to accept invitations from {@link Faction}s.
 */
public class FactionManagerArgument extends CommandArgument implements Listener {

    public Inventory factionManager;
    private final HCF plugin;

    public FactionManagerArgument(HCF plugin) {
        super("manage", "Manage your faction using a GUI");
        this.plugin = plugin;
    }

    @Override
    public String getUsage(String label) {
        return '/' + label;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;
        PlayerFaction playerFaction = HCF.getPlugin().getFactionManager().getPlayerFaction(player);
        this.factionManager = Bukkit.createInventory(null, 36, "Administración de Faction");

        if (playerFaction == null) {
            player.sendMessage(ChatColor.RED + "You don't have a faction");
            return true;
        }


        if (playerFaction.getMember(player).getRole() == Role.LEADER) {
            player.openInventory(factionManager);

            for (Player p : playerFaction.getOnlinePlayers()) {
                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                ItemMeta meta = skull.getItemMeta();
                meta.setLore((Arrays.asList(new String[]{ChatColor.translateAlternateColorCodes('&',"&eHaz click &6&lDERECHO &epara &6&lDEMOTEAR&e a este jugador."),
                        (ChatColor.translateAlternateColorCodes('&',"&eHaz click &6&lIZQUIERDO &epara &6&lPROMOTEAR&e a este jugador.")),ChatColor.translateAlternateColorCodes('&',"&eHaz click &6&lMEDIO &epara hacer &6&lLEADER &ea este jugador.")})));
                meta.setDisplayName(p.getName());
                skull.setItemMeta(meta);
                factionManager.addItem(skull);
            }
        } else {
            player.sendMessage(ChatColor.RED + "You are not a leader.");
        }
        return false;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if (inventory.getName().equals("Administración de Faction")) {

            if (clicked.getType() == Material.SKULL_ITEM) {
                if (event.getClick() == ClickType.LEFT) {
                    Bukkit.dispatchCommand(player, "f promote " + clicked.getItemMeta().getDisplayName());
                    event.setCancelled(true);
                }
                if (event.getClick() == ClickType.MIDDLE){
                    Bukkit.dispatchCommand(player, "f leader " + clicked.getItemMeta().getDisplayName());
                    event.setCancelled(true);
                }
                if (event.getClick() == ClickType.RIGHT) {
                    Bukkit.dispatchCommand(player, "f demote " + clicked.getItemMeta().getDisplayName());
                    event.setCancelled(true);
                }
            }
        }
    }
}