package me.ranwolf.zerobingo.manager;

import lombok.Getter;
import lombok.Setter;
import me.ranwolf.zerobingo.ZeroBingo;

import java.util.ArrayList;
import java.util.UUID;


public class TeamManager {

    ZeroBingo plugin = ZeroBingo.getInstance();
    @Getter @Setter int teamNumber;
    @Getter @Setter ArrayList<UUID> teamPlayers;
    @Getter @Setter ItemsManager teamItems;
    @Getter @Setter private int points;
    @Getter @Setter boolean[] rows;
    @Getter @Setter boolean[] coloms;

    public TeamManager(int teamNumber){
        rows = new boolean[]{false , false , false , false};
        coloms = new boolean[]{false , false , false , false};
        this.points = 0;
        this.teamNumber = teamNumber;
        this.teamPlayers = new ArrayList<>();
        this.teamItems = new ItemsManager();
    }




}
