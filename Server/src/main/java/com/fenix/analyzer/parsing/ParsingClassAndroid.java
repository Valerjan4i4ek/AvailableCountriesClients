package com.fenix.analyzer.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParsingClassAndroid {
    static String[] availableCountries = {
            "ae", "ag", "ai", "al", "am", "ao", "ar", "at", "au", "az", "bb", "be", "bf", "bg", "bh", "bj", "bm", "bn", "bo", "br",
            "bs", "bt", "bw", "by", "bz", "ca", "ch", "cl", "cn", "co", "cr", "cv", "cy", "cz", "de", "dk", "dm", "do",
            "dz", "ec", "ee", "eg", "es", "fi", "fj", "fm", "fr", "ga", "gb", "gd", "gh", "gm", "gn", "gq", "gr", "gt", "gw", "gy",
            "hk", "hn", "hr", "ht", "hu", "id", "ie", "il", "in", "is", "it", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "kn", "kw",
            "kz", "la", "lb", "lc", "li", "lk", "ls", "lt", "lu", "lv", "md", "mg", "mh", "ml", "mn", "mo", "mr", "mt", "mu", "mv",
            "mw", "mx", "my", "mz", "na", "ne", "ng", "ni", "nl", "no", "np", "nr", "nz", "om", "pa", "pe", "pg", "ph", "pk", "pl",
            "pt", "pw", "py", "qa", "ro", "ru", "sa", "sb", "sc", "se", "sg", "si", "sk", "sl", "sn", "sr", "st", "sv", "sz", "tc",
            "td", "th", "tn", "to", "tr", "tt", "tw", "tz", "ua", "ug", "us", "uy", "uz", "vc", "ve", "vn", "vu", "ws", "za", "zm", "zw"};


    public static boolean isAvailable(String playLink) throws IOException {
        Document doc = Jsoup.connect(playLink)
                .userAgent("Chrome/104.0.0.0")
                .referrer("http://www.google.com")
                .get();
//        userAgent("OPR/103.0.4928.47")

        boolean installButtonFound = false;
        for (Element button : doc.select("button")) {
//            System.out.println(button);
            if (button.text().contains("Install") || button.text().contains("Buy")) {
                installButtonFound = true;
                break;
            }
        }
        return installButtonFound;
    }


    public static List<String> countriesDB(){
        List<String> list = new ArrayList<>();
        Collections.addAll(list, availableCountries);
        return list;
    }

    public static String getAppLink(String link, String country){
        return link + "&gl=" + country;
//        String link = "https://play.google.com/store/apps/details";
//        String params = "id=" + appId + "&gl=" + country;
//        return link + "?" + params;
    }

    public String getAppLinkFromDB(String appId){
        String link = "https://play.google.com/store/apps/details";
        String params = "id=" + appId;
        return link + "?" + params;
    }
    public void checkAvailableInAllCountries(String appLink){
        List<String> list = countriesDB();
        String link;

        for(String country : list){
            link = getAppLink(appLink, country);
            try {
                if (isAvailable(link)) {
                    System.out.println("Доступно в обраному регіоні. " + country);
                } else {
                    System.out.println("Недоступно в обраному регіоні.  " + country);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
