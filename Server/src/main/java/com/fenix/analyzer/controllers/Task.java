package com.fenix.analyzer.controllers;

import com.fenix.analyzer.parsing.ParsingClassAndroid;
import com.fenix.analyzer.parsing.ParsingClassIOS;

public class Task implements Runnable{
    String link;

    public Task(String link) {
        this.link = link;
    }

    @Override
    public void run() {
        System.out.println("Parsing " + link);
        checkAvailableInAllCountries(link);
    }

    public void checkAvailableInAllCountries(String link){
        if (link.contains("play.google.com")) {
            ParsingClassAndroid android = new ParsingClassAndroid();
            android.checkAvailableInAllCountries(link);
        }
        else if(link.contains("id")){
            ParsingClassIOS ios = new ParsingClassIOS();
            ios.checkAvailableInAllCountries(link);
        }
        else{
            System.out.println("Incorrect link");
        }
    }
}
