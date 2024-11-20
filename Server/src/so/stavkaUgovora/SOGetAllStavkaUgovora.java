/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkaUgovora;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.StavkaUgovora;
import java.util.ArrayList;
import so.ApstraktnaSO;

/**
 *
 * @author Lenovo
 */
public class SOGetAllStavkaUgovora extends ApstraktnaSO{
    
    private ArrayList<StavkaUgovora> lista;

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof StavkaUgovora)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaUgovora!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        ArrayList<ApstraktniDomenskiObjekat> stavkeUgovora = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaUgovora>) (ArrayList<?>) stavkeUgovora;
    }
    
    public ArrayList<StavkaUgovora> getLista() {
        return lista;
    }
    
}
