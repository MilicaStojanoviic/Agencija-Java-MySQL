/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import domen.Administrator;
import domen.Klijent;
import domen.Prostor;
import domen.StavkaUgovora;
import domen.Ugovor;
import domen.Usluga;
import so.klijent.SOAddKlijent;
import so.klijent.SODeleteKlijent;
import so.klijent.SOGetAllKlijent;
import so.klijent.SOUpdateKlijent;
import so.login.SOLogin;
import so.prostor.SOAddProstor;
import so.prostor.SODeleteProstor;
import so.stavkaUgovora.SOGetAllStavkaUgovora;
import so.ugovor.SOAddUgovor;
import so.ugovor.SOGetAllUgovor;
import so.usluga.SOAddUsluga;
import so.usluga.SODeleteUsluga;
import so.usluga.SOGetAllUsluga;
import so.usluga.SOUpdateUsluga;

/**
 *
 * @author Lenovo
 */
public class Kontroler {
    private static Kontroler instance;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addKlijent(Klijent klijent) throws Exception {
        (new SOAddKlijent()).templateExecute(klijent);
    }

    public Object getAllKlijent() throws Exception {
        SOGetAllKlijent so = new SOGetAllKlijent();
        so.templateExecute(new Klijent());
        return so.getLista();
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        (new SODeleteKlijent()).templateExecute(klijent);
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        (new SOUpdateKlijent()).templateExecute(klijent);
    }

    public Object getAllUsluga() throws Exception {
        SOGetAllUsluga so=new SOGetAllUsluga();
        so.templateExecute(new Usluga());
        return so.getLista();
    }

    public void deleteUsluga(Usluga usluga) throws Exception {
        (new SODeleteUsluga()).templateExecute(usluga);
    }

    public void addUsluga(Usluga usluga) throws Exception {
        (new SOAddUsluga()).templateExecute(usluga);
    }

    public void updateUsluga(Usluga usluga) throws Exception {
        (new SOUpdateUsluga()).templateExecute(usluga);
    }

    public Object getAllUgovor() throws Exception {
        SOGetAllUgovor so=new SOGetAllUgovor();
        so.templateExecute(new Ugovor());
        return so.getLista();
    }

    public Object getAllStavkaUgovora(Ugovor u) throws Exception {
        SOGetAllStavkaUgovora so = new SOGetAllStavkaUgovora();
        
        StavkaUgovora st = new StavkaUgovora();
        st.setUgovor(u);
        
        so.templateExecute(st);
        return so.getLista();
    }

    public void addProstor(Prostor prostor) throws Exception {
        (new SOAddProstor()).templateExecute(prostor);
    }

    public void deleteProstor(Prostor prostor) throws Exception {
        (new SODeleteProstor()).templateExecute(prostor);
    }

    public void addUgovor(Ugovor ugovor) throws Exception {
        (new SOAddUgovor()).templateExecute(ugovor);
    }
    
}
