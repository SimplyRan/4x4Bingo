package me.ranwolf.zerobingo.manager;

import lombok.Getter;
import lombok.Setter;
import me.ranwolf.zerobingo.ZeroBingo;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ItemsManager {

    ZeroBingo plugin = ZeroBingo.getInstance();
    @Getter @Setter private HashMap<Material , Integer> items;
    @Getter @Setter private ArrayList<Material> gotItems;
    private int howMuchItems = 16;

    public ItemsManager(){
        this.gotItems = new ArrayList<>();
        this.items = new HashMap<>();
        loadItems();
    }

    public void loadItems(){
        FileConfiguration config = plugin.getConfig();
        HashMap<Material , Integer> allItems = new HashMap<>();
        for (String item : config.getStringList("items")){
            allItems.put(tryToGetMatiral(item) ,tryGettingInt(item));
        }
        if (allItems.size() < 17){
            plugin.setGameManager(new GameManager());
            return;
        }
        ArrayList<Material> allMaterials = new ArrayList<>(allItems.keySet());
        for (int i = 0; i < howMuchItems; i++){

            Random random = new Random();
            int randomItem = random.nextInt(0 , allItems.size());
            Material item = allMaterials.get(randomItem);
            int points = allItems.get(item) ;
            if (items.containsKey(item)) {
                i--;
            }
            else {
                items.put(item , points);
            }
        }
    }

    public Material tryToGetMatiral(String item){
        int indexOfDiv = item.indexOf(',');
        Material material = Material.STONE;
        if (Material.getMaterial(item.substring(0 , indexOfDiv)) != null)
            material = Material.getMaterial(item.substring(0 , indexOfDiv));
        return material;

    }

    public int tryGettingInt(String item){
        int indexOfDiv = item.indexOf(',');
        Integer points = 1;
        try {
            points = Integer.parseInt(item.substring(indexOfDiv+1));
            // Disable this to allow all numbers!
            if (points > 3)
                points = 3;
        }catch (NumberFormatException e){
            e.getStackTrace();
        }
        return points;

    }


}
