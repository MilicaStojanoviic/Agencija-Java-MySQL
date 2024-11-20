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
import java.util.Date;
import so.ApstraktnaSO;

/**
 *
 * @author Lenovo
 */
public class SOUpdateUgovor extends ApstraktnaSO{

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
        // updatujemo Ugovor
        DBBroker.getInstance().update(ado);

        Ugovor t = (Ugovor) ado;
        // obrisemo stare stavke
        // sledeca linija koda izrvsava naredbu
        // DELETE FROM STAVKAUgovora WHERE UgovorID = nasID
        // cime se brisu SVE stavke naseg Ugovora ODJEDNOM !!!
        DBBroker.getInstance().delete(t.getStavkeUgovora().get(0));

        // ovo nije najoptimalnije, da se brise sve, pa dodaje novo
        // vec da imamo listu dodatih, izmenjenih, obrisanih
        // pa neke da dodajemo, neke da menjamo, neke da brisemo
        // dodamo nove
        for (StavkaUgovora stavkaUgovora : t.getStavkeUgovora()) {
            DBBroker.getInstance().insert(stavkaUgovora);
        }
    }
    
}
