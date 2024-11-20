/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.usluga;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Usluga;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author Lenovo
 */
public class SOUpdateUsluga extends ApstraktnaSO {
    
    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Usluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga!");
        }
        
        Usluga u = (Usluga) ado;
        
        ArrayList<Usluga> usluge = (ArrayList<Usluga>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Usluga usluga : usluge) {
            if (usluga.getNaziv().equals(u.getNaziv())) {
                if (usluga.getCena() == u.getCena() && usluga.getOpis().equals(u.getOpis())) {
                    throw new Exception("Niste promenili ni opis, ni cenu!");
                }
            }
        }
    }
    
    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
    
}
