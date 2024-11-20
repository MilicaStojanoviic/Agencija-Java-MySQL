/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klijent;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Klijent;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author Lenovo
 */
public class SOUpdateKlijent extends ApstraktnaSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }

        Klijent k = (Klijent) ado;

        ArrayList<Klijent> klijenti = (ArrayList<Klijent>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Klijent klijent : klijenti) {
            if (!klijent.getKlijentID().equals(k.getKlijentID())) {
                if (klijent.getKontakt().equals(k.getKontakt())) {
                    throw new Exception("Vec postoji klijent s tim kontaktom!");
                }
            }
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
    
}
