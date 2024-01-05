package com.fenix.analyzer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AvailableCountriesClients extends Remote {
    void checkAvailableInAllCountries(int userId) throws RemoteException;
}
