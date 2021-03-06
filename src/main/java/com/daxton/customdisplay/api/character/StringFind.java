package com.daxton.customdisplay.api.character;

import com.daxton.customdisplay.CustomDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringFind {

    private CustomDisplay cd = CustomDisplay.getCustomDisplay();
    /**動作名稱**/
    private String actionName = "";
    /**觸發動作名稱**/
    private String triggerActionName = "";

    public StringFind(){

    }

    /**丟入整個動作 返回動作第一個關鍵字**/
    public String getAction(String string){
        String lastString = "";
        List<String> stringList = getStringList(string);
        if(stringList.size() > 0){
            String[] strings = stringList.toArray(new String[stringList.size()]);
            lastString = strings[0];
        }
        return lastString;
    }


    /**找尋動作內的觸發動作名稱**/
    public String findActionName(String firstString){
        List<String> stringList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(firstString,"[;] ");
        while (stringTokenizer.hasMoreElements()){
            stringList.add(stringTokenizer.nextToken());
        }
        if(stringList.size() > 0){
            for(String string : stringList){
                if(string.toLowerCase().contains("action")){
                    actionName = string;
                }
                if(string.toLowerCase().contains("action=") || string.toLowerCase().contains("a=")){
                    String[] strings = string.split("=");
                    if(strings.length == 2){
                        triggerActionName = strings[1];
                    }
                }
            }
        }

        return triggerActionName;
    }

    /**丟入字串按照[;]轉成List**/
    public List<String> getStringList(String string){
        List<String> stringList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(string,"[;] ");
        while(stringTokenizer.hasMoreElements()){
            stringList.add(stringTokenizer.nextToken());
        }
        return stringList;
    }
    /**丟入字串按照[;] 轉成List，Message專用**/
    public List<String> getStringMessageList(String string){
        List<String> stringList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(string,"[;]");
        while(stringTokenizer.hasMoreElements()){
            stringList.add(stringTokenizer.nextToken());
        }
        return stringList;
    }

}
