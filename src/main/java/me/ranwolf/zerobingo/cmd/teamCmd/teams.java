package me.ranwolf.zerobingo.cmd.teamCmd;

import me.ranwolf.zerobingo.ZeroBingo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class teams implements CommandExecutor , Listener {

    ZeroBingo plugin = ZeroBingo.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            sender.sendMessage("U Must be a player to use this command");


        if (plugin.getGameManager().isGameOn()){
            if (plugin.getGameManager().isTeamMode()){
                openTeamsInv((Player) sender);
            }
            else sender.sendMessage("Team Mode isn't on!");
        }
        else sender.sendMessage("Game isn't on!");

        return true;
    }


    public void openTeamsInv(Player player){
        Inventory teamInv = Bukkit.createInventory(null , 9 , ChatColor.YELLOW + "Teams");
        for (int i = 0; i < plugin.getGameManager().getHowMuchTeams(); i++){
            ItemStack teamItem = new ItemStack(Material.BOOK);
            ItemMeta teamMeta = teamItem.getItemMeta();
            teamMeta.setDisplayName(ChatColor.YELLOW + "Team " + (i+1));
            teamItem.setItemMeta(teamMeta);
            teamInv.setItem(i , teamItem);
        }
        player.openInventory(teamInv);
    }

    @EventHandler
    public void clickOnTeamItem(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(ChatColor.YELLOW + "Teams")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().getType().equals(Material.BOOK)){
                openTeamInv(player , Integer.parseInt(e.getCurrentItem().getItemMeta().getDisplayName().substring(7)));
            }
        }

        if (e.getView().getTitle().contains(ChatColor.YELLOW + "Team")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null)
                return;
            if (e.getCurrentItem().getType().equals(Material.FEATHER)){
                openTeamsInv(player);
            }

        }
    }

    public void openTeamInv(Player player , int team){
        Inventory teamInv = Bukkit.createInventory(null , 54 , ChatColor.YELLOW + "Team " + team);
        int skullNow = 0;
        for (UUID teamPlayer : plugin.getGameManager().getTeams().get(team).getTeamPlayers()){
            ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) playerSkull.getItemMeta();
            skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(teamPlayer));
            playerSkull.setItemMeta(skullMeta);
            teamInv.setItem(skullNow , playerSkull);
            skullNow++;
        }

        ItemStack gettingBack = new ItemStack(Material.FEATHER);
        ItemMeta gettingBackMeta = gettingBack.getItemMeta();
        gettingBackMeta.setDisplayName(ChatColor.YELLOW + "Go back");
        gettingBack.setItemMeta(gettingBackMeta);

        teamInv.setItem(53 , gettingBack);
        player.openInventory(teamInv);
    }
}
