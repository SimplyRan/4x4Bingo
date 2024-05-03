package me.ranwolf.zerobingo.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class disableTakingItems implements Listener {


    @EventHandler
    public void invClickEvent(InventoryClickEvent e){
        if (e.getView().getTitle().equals(ChatColor.YELLOW + "Bingo bored")){
            e.setCancelled(true);
        }
    }


}
