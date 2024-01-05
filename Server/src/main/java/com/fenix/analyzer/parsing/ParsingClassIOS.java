package com.fenix.analyzer.parsing;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;

public class ParsingClassIOS {
    static String[] availableCountries = {
            "ae", "ag", "ai", "al", "am", "ao", "ar", "at", "au", "az", "bb", "be", "bf", "bg", "bh", "bj", "bm", "bn", "bo", "br",
            "bs", "bt", "bw", "by", "bz", "ca", "ch", "cl", "cn", "co", "cr", "cv", "cy", "cz", "de", "dk", "dm", "do",
            "dz", "ec", "ee", "eg", "es", "fi", "fj", "fm", "fr", "ga", "gb", "gd", "gh", "gm", "gr", "gt", "gw", "gy",
            "hk", "hn", "hr", "hu", "id", "ie", "il", "in", "is", "it", "jm", "jo", "jp", "ke", "kg", "kh", "kn", "kw",
            "kz", "la", "lb", "lc", "lk", "lt", "lu", "lv", "md", "mg", "ml", "mn", "mo", "mr", "mt", "mu", "mv",
            "mw", "mx", "my", "mz", "na", "ne", "ng", "ni", "nl", "no", "np", "nr", "nz", "om", "pa", "pe", "pg", "ph", "pk", "pl",
            "pt", "pw", "py", "qa", "ro", "ru", "sa", "sb", "sc", "se", "sg", "si", "sk", "sl", "sn", "sr", "st", "sv", "sz", "tc",
            "td", "th", "tn", "to", "tr", "tt", "tw", "tz", "ua", "ug", "us", "uy", "uz", "vc", "ve", "vn", "vu", "za", "zm", "zw"};


    private static void checkAppAvailability(String appID, String countryCode) {
        String appStoreURLForCountry = "https://apps.apple.com/" + countryCode + "/app/" + appID;
//        System.out.println("Перевірка доступності додатку в " + countryCode);

        try {
            Jsoup.connect(appStoreURLForCountry).get();
            System.out.println("Додаток доступний для встановлення в " + countryCode);
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == 404) {
                System.out.println("Додаток не доступний для встановлення в " + countryCode);
            } else {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkAvailableInAllCountries(String appId){
        for(String country : availableCountries){
            checkAppAvailability(appId, country);
        }
    }
}
