package com.daxton.customdisplay.api.player;

import com.daxton.customdisplay.manager.ConfigMapManager;
import com.daxton.customdisplay.task.action.JudgmentAction;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerData {

    private Player player;

    private FileConfiguration fileConfiguration;

    private List<String> playerActionList;

    private List<String> onAttakList = new ArrayList<>();

    private List<String> onJoinList = new ArrayList<>();

    public PlayerData(Player player){
        this.player = player;
        setPlayerActionList();
        setActionList();
    }
    /**獲取動作列表**/
    public void setPlayerActionList() {
        for(String configName : ConfigMapManager.getFileConfigurationNameMap().values()){

            if(configName.contains("Players_"+player.getName()+".yml")){
                fileConfiguration = ConfigMapManager.getFileConfigurationMap().get("Players_"+player.getName()+".yml");
                break;
            }else{
                fileConfiguration = ConfigMapManager.getFileConfigurationMap().get("Players_Default.yml");
            }
        }
        playerActionList = fileConfiguration.getStringList("Action");
    }

    public void setActionList(){
        if(playerActionList.size() > 0){
            for(String actionString : playerActionList){
                if(actionString.toLowerCase().contains("~onattack")){
                    onAttakList.add(actionString);
                }
                if(actionString.toLowerCase().contains("~onjoin")){
                    onJoinList.add(actionString);
                }
            }
        }
    }

    public void runAction(String type, Event event){
        if(type.toLowerCase().contains("~onattack") && onAttakList.size() > 0){
            for(String actionString : onAttakList){
                EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) event;
                LivingEntity target = (LivingEntity) entityDamageByEntityEvent.getEntity();
                double damageNumber = entityDamageByEntityEvent.getFinalDamage();
                new JudgmentAction().execute(player,target,actionString,damageNumber,String.valueOf((int)(Math.random()*100000)));
            }
        }
        if(type.toLowerCase().contains("~onjoin") && onJoinList.size() > 0){
            for(String actionString : onJoinList){
                new JudgmentAction().execute(player,actionString,String.valueOf((int)(Math.random()*100000)));
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<String> getPlayerActionList() {
        return playerActionList;
    }
}
