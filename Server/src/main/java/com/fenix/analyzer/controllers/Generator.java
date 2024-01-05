package com.fenix.analyzer.controllers;

import com.fenix.analyzer.db.AvailableCountriesFromDB;
import com.fenix.analyzer.parsing.ParsingClassAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Generator {
    AvailableCountriesFromDB sql = new AvailableCountriesFromDB();

    public List<String> returnLinkOrAppId(int userId){
        Map<String, String> map = sql.getAppIdAndPlatform(userId);
        List<String> list = new ArrayList<>();
        String link;
        ParsingClassAndroid android;

        for(Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getValue().equals("Android")){
                android = new ParsingClassAndroid();
                link = android.getAppLinkFromDB(entry.getKey());
                list.add(link);
            } else if (entry.getValue().equals("iOS")) {
                list.add(entry.getKey());
            }
        }

        for(String s : list){
            System.out.println(s);
        }
        System.out.println("-------------------------------------------");

        return list;
    }

}
