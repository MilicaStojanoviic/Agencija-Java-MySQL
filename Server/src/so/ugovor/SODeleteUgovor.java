/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ugovor;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Ugovor;
import so.ApstraktnaSO;

/**
 *
 * @author Lenovo
 */
public class SODeleteUgovor extends ApstraktnaSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Ugovor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ugovor!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
