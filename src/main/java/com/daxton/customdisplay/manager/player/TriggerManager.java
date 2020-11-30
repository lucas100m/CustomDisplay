package com.daxton.customdisplay.manager.player;

import com.daxton.customdisplay.task.action.JudgmentAction;
import com.daxton.customdisplay.task.action.list.BossBar;
import com.daxton.customdisplay.task.action.list.HolographicNew;
import com.daxton.customdisplay.task.action.list.Loop;
import com.daxton.customdisplay.task.action.list.LoopOne;
import com.daxton.customdisplay.task.player.OnAttack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TriggerManager {



    /**玩家動作Map**/
    private static Map<String, JudgmentAction> playerActionTaskMap = new HashMap<>();

    /**AttackListener->onAttack**/
    private static Map<String, OnAttack> AttackListenerTaskMap = new HashMap<>();
    /**onAttack->JudgmentAction**/
    private static Map<String, JudgmentAction> onAttackTaskMap = new HashMap<>();
    /**JudgmentAction->HolographicNew**/
    private static Map<String, HolographicNew> holographicTaskMap = new HashMap<>();
    /**JudgmentAction->Loop**/
    private static Map<String, Loop> judgmentActionTaskMap = new HashMap<>();
    /**JudgmentAction->LoopOne**/
    private static Map<String, LoopOne> judgmentActionTaskLoopOneMap = new HashMap<>();
    /**JudgmentAction->BossBar**/
    private static Map<String, BossBar> judgment_BossBar_Map = new HashMap<>();
    /**Loop->JudgmentAction**/
    private static Map<String, JudgmentAction> loopTaskMap = new HashMap<>();







    /**玩家動作Map**/
    public static Map<String, JudgmentAction> getPlayerActionTaskMap() {
        return playerActionTaskMap;
    }

    /**AttackListener->onAttack**/
    public static Map<String, OnAttack> getAttackListenerTaskMap() {
        return AttackListenerTaskMap;
    }
    /**onAttack->JudgmentAction**/
    public static Map<String, JudgmentAction> getOnAttackTaskMap() {
        return onAttackTaskMap;
    }
    /**JudgmentAction->HolographicNew**/
    public static Map<String, HolographicNew> getHolographicTaskMap() {
        return holographicTaskMap;
    }
    /**Loop->JudgmentAction**/
    public static Map<String, JudgmentAction> getLoopTaskMap() {
        return loopTaskMap;
    }
    /**JudgmentAction->Loop**/
    public static Map<String, Loop> getJudgmentActionTaskMap() {
        return judgmentActionTaskMap;
    }
    /**JudgmentAction->BossBar**/
    public static Map<String, BossBar> getJudgment_BossBar_Map() {
        return judgment_BossBar_Map;
    }

    /**JudgmentAction->LoopOne**/
    public static Map<String, LoopOne> getJudgmentActionTaskLoopOneMap() {
        return judgmentActionTaskLoopOneMap;
    }
}
