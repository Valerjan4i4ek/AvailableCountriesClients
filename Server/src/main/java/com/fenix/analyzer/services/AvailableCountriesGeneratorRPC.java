package com.fenix.analyzer.services;

import com.fenix.analyzer.models.AvailabilityInCountryModel;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.ArrayList;

@WebService
@SOAPBinding(style = Style.RPC)
public interface AvailableCountriesGeneratorRPC {
    @WebMethod
    ArrayList<AvailabilityInCountryModel> getInfoAboutAvailable (String link);
}
