package com.fenix.analyzer.models;

public class AvailabilityInCountryModel {
    private String country;
    private boolean available;

    public AvailabilityInCountryModel(String country, boolean available) {
        this.country = country;
        this.available = available;
    }

    public String getCountry() {
        return country;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Availability: " +
                "country='" + country + '\'' +
                ", available=" + available;
    }
}
