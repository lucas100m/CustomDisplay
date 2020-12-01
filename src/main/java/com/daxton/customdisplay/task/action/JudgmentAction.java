package com.daxton.customdisplay.task.action;

import com.daxton.customdisplay.CustomDisplay;
import com.daxton.customdisplay.api.character.StringFind;
import com.daxton.customdisplay.task.action.list.*;
import com.daxton.customdisplay.manager.player.TriggerManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class JudgmentAction {

    private CustomDisplay cd = CustomDisplay.getCustomDisplay();

    private BukkitRunnable bukkitRunnable;

    int delay = 0;

    public JudgmentAction(){

    }


    public void execute(Player player, LivingEntity target, String firstString, double damageNumber,String taskID){

        if (firstString.toLowerCase().contains("delay ")) {
            String[] slt = firstString.split(" ");
            if (slt.length == 2) {
                delay = delay + Integer.valueOf(slt[1]);
            }
        }
        bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                /**動作第一個關鍵字**/
                String judgMent = new StringFind().getAction(firstString);

                /**Action的相關判斷**/
                if (judgMent.toLowerCase().contains("action")) {
                    if(TriggerManager.getJudgment_Action_Map().get(taskID) == null){
                        TriggerManager.getJudgment_Action_Map().put(taskID,new Action());
                    }
                    if(TriggerManager.getJudgment_Action_Map().get(taskID) != null){
                        TriggerManager.getJudgment_Action_Map().get(taskID).setAction(player,target,firstString,damageNumber,taskID);
                    }

                }

                /**HolographicDisplays的相關判斷**/
                if(judgMent.toLowerCase().contains("createhd") || judgMent.toLowerCase().contains("addlinehd") || judgMent.toLowerCase().contains("removelinehd") || judgMent.toLowerCase().contains("teleporthd") || judgMent.toLowerCase().contains("deletehd")){
                    if(TriggerManager.getJudgment_Holographic_Map().get(taskID) == null){
                        TriggerManager.getJudgment_Holographic_Map().put(taskID,new HolographicNew());
                    }
                    if(TriggerManager.getJudgment_Holographic_Map().get(taskID) != null){
                        TriggerManager.getJudgment_Holographic_Map().get(taskID).setHD(player,target,firstString,damageNumber,taskID);
                    }
                }
                /**Loop的相關判斷**/
                if(judgMent.toLowerCase().contains("loop")){
                    if(TriggerManager.getJudgment_Loop_Map().get(taskID) == null){
                        TriggerManager.getJudgment_Loop_Map().put(taskID,new Loop());
                    }
                    if(TriggerManager.getJudgment_Loop_Map().get(taskID) != null){
                        TriggerManager.getJudgment_Loop_Map().get(taskID).onLoop(player,target,firstString,damageNumber,taskID);
                    }
                }

                /**Title的相關判斷**/
                if(judgMent.toLowerCase().contains("title")){
                    new Title(player,firstString).sendTitle();
                }
                /**Sound的相關判斷**/
                if (judgMent.toLowerCase().contains("sound")) {
                    new Sound(player, firstString).playSound();
                }

                if(judgMent.toLowerCase().contains("boosbar")){
                    if(TriggerManager.getJudgment_BossBar_Map().get(taskID) == null){
                        TriggerManager.getJudgment_BossBar_Map().put(taskID,new SendBossBar());
                    }
                    if(TriggerManager.getJudgment_BossBar_Map().get(taskID) != null){
                        TriggerManager.getJudgment_BossBar_Map().get(taskID).set(player,target,firstString,taskID);
                    }
                }


            }
        };
        bukkitRunnable.runTaskLater(cd, delay);
    }

    /**只有玩家**/
    public void execute(Player player, String firstString,String taskID){

        if (firstString.toLowerCase().contains("delay ")) {
            String[] slt = firstString.split(" ");
            if (slt.length == 2) {
                delay = delay + Integer.valueOf(slt[1]);
            }
        }
        bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                /**動作第一個關鍵字**/
                String judgMent = new StringFind().getAction(firstString);
                /**Action的相關判斷**/
                /**Action的相關判斷**/
                if (judgMent.toLowerCase().contains("action")) {

                    if(TriggerManager.getJudgment_Action_Map().get(taskID) == null){
                        TriggerManager.getJudgment_Action_Map().put(taskID,new Action());
                    }
                    if(TriggerManager.getJudgment_Action_Map().get(taskID) != null){
                        TriggerManager.getJudgment_Action_Map().get(taskID).setAction(player,firstString,taskID);
                    }
                }
                /**LoopOne的相關判斷**/
                if(judgMent.toLowerCase().contains("loop")){
                    if(firstString.toLowerCase().contains("unlimited")){
                        if(TriggerManager.getJudgment_LoopOne_Map().get(taskID) == null){
                            TriggerManager.getJudgment_LoopOne_Map().put(taskID,new LoopOne());
                            TriggerManager.getJudgment_LoopOne_Map().get(taskID).onLoop(player,firstString,taskID);
                        }
                    }else {
                        new LoopOne().onLoop(player,firstString,taskID);
                    }
                }
                /**Title的相關判斷**/
                if(judgMent.toLowerCase().contains("title")){
                    new Title(player,firstString).sendTitle();
                }
                /**Sound的相關判斷**/
                if (judgMent.toLowerCase().contains("sound")) {
                    new Sound(player, firstString).playSound();
                }
                /**ActionBar的相關判斷**/
                if(judgMent.toLowerCase().contains("actionbar")){
                    new ActionBar(player,firstString).sendActionBar();
                }

            }
        };
        bukkitRunnable.runTaskLater(cd, delay);

    }

    public void executeOneTwo(Player player,LivingEntity target, String firstString,String taskID){

        if (firstString.toLowerCase().contains("delay ")) {
            String[] slt = firstString.split(" ");
            if (slt.length == 2) {
                delay = delay + Integer.valueOf(slt[1]);
            }
        }
        bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                /**動作第一個關鍵字**/
                String judgMent = new StringFind().getAction(firstString);
                /**Action的相關判斷**/
                /**Action的相關判斷**/
                if (judgMent.toLowerCase().contains("action")) {
                    if(TriggerManager.getJudgment_Action_Map().get(taskID) == null){
                        TriggerManager.getJudgment_Action_Map().put(taskID,new Action());
                    }
                    if(TriggerManager.getJudgment_Action_Map().get(taskID) != null){
                        TriggerManager.getJudgment_Action_Map().get(taskID).setAction(player,target,firstString,taskID);
                    }

                }
                /**LoopOne的相關判斷**/
                if(judgMent.toLowerCase().contains("loop")){
                    if(firstString.toLowerCase().contains("unlimited")){
                        if(TriggerManager.getJudgment_LoopOne_Map().get(taskID) == null){
                            TriggerManager.getJudgment_LoopOne_Map().put(taskID,new LoopOne());
                            TriggerManager.getJudgment_LoopOne_Map().get(taskID).onLoop(player,firstString,taskID);
                        }
                    }else {
                        new LoopOne().onLoop(player,firstString,taskID);
                    }
                }
                /**Title的相關判斷**/
                if(judgMent.toLowerCase().contains("title")){
                    new Title(player,firstString).sendTitle();
                }
                /**Sound的相關判斷**/
                if (judgMent.toLowerCase().contains("sound")) {
                    new Sound(player, firstString).playSound();
                }
                /**ActionBar的相關判斷**/
                if(judgMent.toLowerCase().contains("actionbar")){
                    new ActionBar(player,firstString).sendActionBar();
                }

            }
        };
        bukkitRunnable.runTaskLater(cd, delay);

    }

    public BukkitRunnable getBukkitRunnable() {
        return bukkitRunnable;
    }
}
