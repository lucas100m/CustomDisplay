package com.daxton.customdisplay.config;

import com.daxton.customdisplay.CustomDisplay;
import com.daxton.customdisplay.api.config.AutoConfig;
import com.daxton.customdisplay.manager.ConfigMapManager;
import com.daxton.customdisplay.util.ConfigUtil;
import com.daxton.customdisplay.util.FolderConfigUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {

    CustomDisplay cd;

    public FileConfiguration config;
    //public FileConfiguration boos_bar_config;
    public FileConfiguration language;


    public ConfigManager(CustomDisplay plugin){
        cd = plugin;
        //new FolderConfigUtil();


        config = new AutoConfig("resource/config.yml","config.yml").get();
        language = new AutoConfig("resource/Language/"+config.getString("Language")+".yml","Language/"+config.getString("Language")+".yml").get();
        new AutoConfig("resource/Players/Default.yml","Players/Default.yml").get();
        new AutoConfig("resource/Players/Default.yml","Players/s99889989.yml").get();
        new AutoConfig("resource/Actions/ExampleAction.yml","Actions/ExampleAction.yml").get();
        new AutoConfig("resource/Character/ExampleCharacter.yml","Character/ExampleCharacter.yml").get();
        new AutoConfig("resource/Character/System/AttackNumber.yml","Character/System/AttackNumber.yml").get();
        new AutoConfig("resource/Character/System/Health.yml","Character/System/Health.yml").get();
        new AutoConfig().actionConfig();
        new AutoConfig().characterConfig();
        new AutoConfig().playersConfig();


        //config = ConfigMapManager.getFileConfigurationMap().get("config.yml");
        //boos_bar_config = ConfigMapManager.getFileConfigurationMap().get("BoosBarDisplay.yml");
        //language = ConfigMapManager.getFileConfigurationMap().get("Language_"+config.getString("Language")+".yml");

    }

}
