package com.daxton.customdisplay.command;

import com.daxton.customdisplay.CustomDisplay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomDisplayCommand implements CommandExecutor, TabCompleter{
// ,TabCompleter
    private CustomDisplay cd = CustomDisplay.getCustomDisplay();

    private String[] subCommands = {"help", "reload"};

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        //sender.sendMessage(args.length > 0 ? args[0] : "nothing");
        if(!sender.isOp()){
            sender.sendMessage(cd.getConfigManager().isOP);
            return false;
        }
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("reload")){
                cd.load();
                sender.sendMessage(cd.getConfigManager().reload);
                return true;
            }
            if(args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(cd.getConfigManager().help);
                for(String msg : cd.getConfigManager().helpinfo){
                    sender.sendMessage(msg);
                }
                return true;
            }
        }
        sender.sendMessage(cd.getConfigManager().help);
        for(String msg : cd.getConfigManager().helpinfo){
            sender.sendMessage(msg);
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        //如果不是能夠補全的長度，則返回空列表
        if (args.length > 1) return new ArrayList<>();

        //如果此時僅輸入了命令"cd"，則直接返回所有的子命令
        if (args.length == 0) return Arrays.asList(subCommands);

        //篩選所有可能的補全列表，並返回
        return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }

}
