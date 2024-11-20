/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ugovor;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import domen.StavkaUgovora;
import domen.Ugovor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.ApstraktnaSO;

/**
 *
 * @author Lenovo
 */
public class SOAddUgovor extends ApstraktnaSO{

    @Override
    protected void validate(ApstraktniDomenskiObjekat ado) throws Exception {
        if (!(ado instanceof Ugovor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Ugovor!");
        }

        Ugovor u = (Ugovor) ado;

        if (!u.getDatumStupanjaNaSnagu().after(new Date())) {
            throw new Exception("Datum i vreme stupanja ugovora na snagu mora biti posle danasnjeg datuma!");
        }

        if (u.getStavkeUgovora().isEmpty()) {
            throw new Exception("Ugovor mora imati barem jednu stavku!");
        }
    }

    @Override
    protected void execute(ApstraktniDomenskiObjekat ado) throws Exception {
        // vracamo ps sa generisanim kljucem
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        // uzimamo taj kljuc
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long ugovorID = tableKeys.getLong(1);

        // setujemo ga za nas ugovor
        Ugovor u = (Ugovor) ado;
        u.setUgovorID(ugovorID);

        // dodajemo redom stavku po stavku
        // nakon sto setujemo da potice iz naseg Ugovora
        for (StavkaUgovora stavkaUgovora : u.getStavkeUgovora()) {
            stavkaUgovora.setUgovor(u);
            DBBroker.getInstance().insert(stavkaUgovora);
        }
    }
    
}
