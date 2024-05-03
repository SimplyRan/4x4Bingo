package me.ranwolf.zerobingo.manager;

import lombok.Getter;
import lombok.Setter;
import me.ranwolf.zerobingo.ZeroBingo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sun.reflect.generics.tree.VoidDescriptor;

import java.util.UUID;

public class PlayerManager {

    ZeroBingo plugin = ZeroBingo.getInstance();
    @Getter private UUID uuid;
    @Getter @Setter private ItemsManager playerItems;
    @Getter @Setter private int points;

    public PlayerManager(UUID uuid){
        this.uuid = uuid;
        this.playerItems = new ItemsManager();
        this.points = 0;
    }




}
