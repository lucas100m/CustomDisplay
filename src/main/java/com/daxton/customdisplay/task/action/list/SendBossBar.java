package com.daxton.customdisplay.task.action.list;

import com.daxton.customdisplay.CustomDisplay;
import com.daxton.customdisplay.manager.BBDMapManager;
import com.daxton.customdisplay.util.ArithmeticUtil;
import com.daxton.customdisplay.util.NumberUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.script.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.boss.BarColor.BLUE;
import static org.bukkit.boss.BarStyle.SEGMENTED_10;

public class SendBossBar {

    private CustomDisplay cd = CustomDisplay.getCustomDisplay();

    private BukkitRunnable bukkitRunnable;

    public BossBar bossBar;

    private double maxHealth;

    private Player player;

    public org.bukkit.boss.BossBar getBossBar() {
        return bossBar;
    }

    public SendBossBar(Entity entity, LivingEntity target){
        player = (Player) entity;
        UUID targetUUID = target.getUniqueId();
        UUID uuid = player.getUniqueId();
        UUID objectUUID = BBDMapManager.getTargetAttackBossBarMap().get(targetUUID);
        if(objectUUID == null){
            BBDMapManager.getTargetAttackBossBarMap().put(targetUUID,uuid);
        }
        maxHealth = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double nowHealth = target.getHealth();
        double progress = nowHealth/maxHealth;
        String changeNowHealth = new NumberUtil(nowHealth,"#.0").getDecimalString();

        bossBar = Bukkit.createBossBar(target.getName()+changeNowHealth+"/"+maxHealth, Enum.valueOf(BarColor.class , "BLUE"), Enum.valueOf(BarStyle.class , "SOLID"));
        bossBar.setProgress(progress);
        bossBar.addPlayer(player);
    bukkitRunnable = new BukkitRunnable() {
        int tickRun;
        @Override
        public void run() {
            tickRun = tickRun + 10;
            double nowHealth = target.getHealth();
            double progress = nowHealth/maxHealth;
            String changeNowHealth = new NumberUtil(nowHealth,"#.0").getDecimalString();
            bossBar.setProgress(progress);
            bossBar.setTitle(target.getName()+changeNowHealth+"/"+maxHealth);
            if(tickRun > 200){
                bossBar.removePlayer(player);
                cancel();
                BBDMapManager.getAttackBossBarMap().remove(uuid);
            }
        }
    };
    bukkitRunnable.runTaskTimer(cd,1,10);

    }

    public BukkitRunnable getBukkitRunnable() {
        return bukkitRunnable;
    }

    public Player getPlayer() {
        return player;
    }

}
