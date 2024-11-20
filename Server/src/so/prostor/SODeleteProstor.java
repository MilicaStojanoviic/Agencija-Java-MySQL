/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.prostor;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.Prostor;
import so.ApstraktnaSO;

/**
 *
 * @author Lenovo
 */
public class SODeleteProstor extends ApstraktnaSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Prostor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Prostor!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
