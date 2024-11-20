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
public class SOGetAllKlijent extends ApstraktnaSO{
    
    private ArrayList<Klijent> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> klijenti = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Klijent>) (ArrayList<?>) klijenti;
    }
    
    public ArrayList<Klijent> getLista() {
        return lista;
    }
    
}
