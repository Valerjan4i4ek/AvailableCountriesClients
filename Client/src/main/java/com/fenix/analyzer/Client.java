package com.fenix.analyzer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static final String UNIQUE_BINDING_NAME = "server.AvailableCountriesClients";
    static Scanner scanner = new Scanner(System.in);
    static Registry registry;
    static AvailableCountriesClients availableCountriesClients;
    static {
        try{
            registry = LocateRegistry.getRegistry("127.0.0.1", 2732);
            availableCountriesClients = (AvailableCountriesClients) registry.lookup(UNIQUE_BINDING_NAME);
        }
        catch (RemoteException | NotBoundException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        checkAvailableInAllCountries(userId);
    }

    public static void checkAvailableInAllCountries(int userId){
        try {
            availableCountriesClients.checkAvailableInAllCountries(userId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
