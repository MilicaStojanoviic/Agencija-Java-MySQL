/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.prostor;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Prostor;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author Lenovo
 */
public class SOAddProstor extends ApstraktnaSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Prostor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Prostor!");
        }

        Prostor p = (Prostor) ado;

        ArrayList<Prostor> prostori = (ArrayList<Prostor>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Prostor prostor : prostori) {
            if (prostor.getAdresa().equals(p.getAdresa())) {
                throw new Exception("Vec postoji prostor s tom adresom!");
            }
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
