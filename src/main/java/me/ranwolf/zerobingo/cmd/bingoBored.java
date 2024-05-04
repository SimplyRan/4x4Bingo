package me.ranwolf.zerobingo.cmd;

import me.ranwolf.zerobingo.ZeroBingo;
import me.ranwolf.zerobingo.manager.PlayerManager;
import me.ranwolf.zerobingo.manager.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class bingoBored implements CommandExecutor {

    ZeroBingo plugin = ZeroBingo.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("You have to be player to open this bingo bored!");
            return true;
        }
        Player player = (Player) sender;

        if (!plugin.getGameManager().isGameOn()){
            sender.sendMessage("Can't open bingo bored when game not active!");
            return true;
        }
        if (plugin.getGameManager().isTeamMode()){
            teamBingoInv(player);
            return true;
        }
        openBingoInv(player);





        return true;
    }

    public void openBingoInv(Player player){
        if (plugin.getGameManager().isTeamMode()){
            //TODO bingo for team
            return;
        }
        soloBingoInv(player);
    }

    public void teamBingoInv(Player player){
        Inventory bingoInv = Bukkit.createInventory(null , 54 , ChatColor.YELLOW + "Bingo bored");
        TeamManager teamManager = plugin.getGameManager().getTeams().get(plugin.getGameManager().getPlayerTeam(player));
        HashMap<Material , Integer> teamItems = teamManager.getTeamItems().getItems();
        for (int i = 0; i < bingoInv.getSize(); i++){
            bingoInv.setItem(i , getItem("border"));
        }
        int[] indexToPut = {13, 14 ,15 ,16 ,22 ,23 ,24 ,25 ,31 ,32 , 33,34 ,40 ,41 ,42 ,43};
        int i = 0;
        for (Material item : teamItems.keySet()){
            ItemStack itemNow = new ItemStack(item);
            ItemMeta itemNowMeta = itemNow.getItemMeta();
            if (teamManager.getTeamItems().getGotItems().contains(item)) {
                itemNowMeta.setDisplayName(ChatColor.GREEN + item.name().toLowerCase().replace("_", " ") + ", " + teamItems.get(item));
            }
            else itemNowMeta.setDisplayName(ChatColor.RED + item.name().toLowerCase().replace("_" ," ") + ", " + teamItems.get(item));
            itemNow.setItemMeta(itemNowMeta);
            bingoInv.setItem(indexToPut[i] , itemNow);
            i++;
        }

        //Team Points item
        ItemStack teamItem = new ItemStack(Material.BOOK);
        ItemMeta teamItemMeta = teamItem.getItemMeta();
        teamItemMeta.setDisplayName(ChatColor.AQUA + "Team "+ plugin.getGameManager().getPlayerTeam(player) + " points:");
        teamItem.setItemMeta(teamItemMeta);

        ItemStack points = new ItemStack(Material.PAPER);
        ItemMeta pointsMeta = points.getItemMeta();
        pointsMeta.setDisplayName(ChatColor.AQUA + String.valueOf(teamManager.getPoints()));
        points.setItemMeta(pointsMeta);

        bingoInv.setItem(19 , teamItem);
        bingoInv.setItem(28 , points);

        player.openInventory(bingoInv);
    }




    public void soloBingoInv(Player player){
        Inventory bingoInv = Bukkit.createInventory(null , 54 , ChatColor.YELLOW + "Bingo bored");
        PlayerManager playerManager = plugin.getGameManager().getPlayers().get(player.getUniqueId());
        HashMap<Material , Integer> playerItems = playerManager.getPlayerItems().getItems();
        for (int i = 0; i < bingoInv.getSize(); i++){
            bingoInv.setItem(i , getItem("border"));
        }
        int[] indexToPut = {13, 14 ,15 ,16 ,22 ,23 ,24 ,25 ,31 ,32 , 33,34 ,40 ,41 ,42 ,43};
        int i = 0;
        for (Material item : playerItems.keySet()){
            ItemStack itemNow = new ItemStack(item);
            ItemMeta itemNowMeta = itemNow.getItemMeta();
            if (playerManager.getPlayerItems().getGotItems().contains(item)) {
                itemNowMeta.setDisplayName(ChatColor.GREEN + item.name().toLowerCase().replace("_", " ") + ", " + playerItems.get(item));
            }
            else itemNowMeta.setDisplayName(ChatColor.RED + item.name().toLowerCase().replace("_" ," ") + ", " + playerItems.get(item));
            itemNow.setItemMeta(itemNowMeta);
            bingoInv.setItem(indexToPut[i] , itemNow);
            i++;

        }
        //Player Skull
        ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) playerSkull.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.setDisplayName(ChatColor.AQUA + player.getDisplayName() + " points:");
        playerSkull.setItemMeta(skullMeta);

        ItemStack points = new ItemStack(Material.PAPER);
        ItemMeta pointsMeta = points.getItemMeta();
        pointsMeta.setDisplayName(ChatColor.AQUA + String.valueOf(playerManager.getPoints()));
        points.setItemMeta(pointsMeta);

        bingoInv.setItem(19 , playerSkull);
        bingoInv.setItem(28 , points);

        player.openInventory(bingoInv);
    }

    public ItemStack getItem(String name){
        ItemStack item;
        if (name.equals("border")){
            item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName("");
            item.setItemMeta(itemMeta);
        }
        else item = new ItemStack(Material.BARRIER);
        return item;
    }
}
