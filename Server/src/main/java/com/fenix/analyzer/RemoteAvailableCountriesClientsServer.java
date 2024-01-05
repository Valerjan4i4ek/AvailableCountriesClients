package com.fenix.analyzer;

import com.fenix.analyzer.controllers.Generator;
import com.fenix.analyzer.controllers.Processor;

import java.rmi.RemoteException;
import java.util.List;

public class RemoteAvailableCountriesClientsServer implements AvailableCountriesClients {
    Processor processor;
    Generator generator;
    public RemoteAvailableCountriesClientsServer() {
        generator = new Generator();
        processor = new Processor();
        new Thread(processor).start();
    }

    @Override
    public void checkAvailableInAllCountries(int userId) throws RemoteException {
        List<String> list = generator.returnLinkOrAppId(userId);

        for(String link : list){
            processor.addLink(link);
        }
    }
}
