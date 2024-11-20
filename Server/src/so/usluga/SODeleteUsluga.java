/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.usluga;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Usluga;
import so.ApstraktnaSO;

/**
 *
 * @author Lenovo
 */
public class SODeleteUsluga extends ApstraktnaSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Usluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
         DBBroker.getInstance().delete(ado);
    }
    
}
