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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import konstante.Operacije;
import konstante.StatusOdgovora;
import sesija.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Lenovo
 */
public class Komunikacija {
    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }
    
    private Object posaljiZahtev(int operacija, Object parametar) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev(operacija, parametar);

        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        out.writeObject(kz);

        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        ServerskiOdgovor so = (ServerskiOdgovor) in.readObject();

        if (so.getStatusOdgovora().equals(StatusOdgovora.Error)) {
            throw so.getException();
        } else {
            return so.getOdgovor();
        }

    }

    public Administrator login(Administrator a) throws Exception {
        return (Administrator) posaljiZahtev(Operacije.LOGIN, a);
    }

    public void addKlijent(Klijent k) throws Exception {
        posaljiZahtev(Operacije.ADD_KLIJENT, k);
    }

    public ArrayList<Klijent> getAllKlijent() throws Exception {
        return (ArrayList<Klijent>) posaljiZahtev(Operacije.GET_ALL_KLIJENT, null);
    }

    public void deleteKlijent(Klijent k) throws Exception {
        posaljiZahtev(Operacije.DELETE_KLIJENT, k);
    }

    public void updateKlijent(Klijent k) throws Exception {
        posaljiZahtev(Operacije.UPDATE_KLIJENT, k);
    }

    public ArrayList<Usluga> getAllUsluga() throws Exception {
        return (ArrayList<Usluga>) posaljiZahtev(Operacije.GET_ALL_USLUGA, null);
    }

    public void deleteUsluga(Usluga u) throws Exception {
        posaljiZahtev(Operacije.DELETE_USLUGA, u);
    }

    public void addUsluga(Usluga u) throws Exception {
        posaljiZahtev(Operacije.ADD_USLUGA, u);
    }

    public void updateUsluga(Usluga u) throws Exception {
        posaljiZahtev(Operacije.UPDATE_USLUGA, u);
    }

    public ArrayList<Ugovor> getAllUgovor() throws Exception {
        return (ArrayList<Ugovor>) posaljiZahtev(Operacije.GET_ALL_UGOVOR, null);
    }

    public ArrayList<StavkaUgovora> getAllStavkaUgovora(Ugovor u) throws Exception {
        return (ArrayList<StavkaUgovora>) posaljiZahtev(Operacije.GET_ALL_STAVKA, u);
    }

    public void addProstor(Prostor p) throws Exception {
        posaljiZahtev(Operacije.ADD_PROSTOR, p);
    }

    public void deleteProstor(Prostor p) throws Exception {
        posaljiZahtev(Operacije.DELETE_PROSTOR, p);
    }

    public void addUgovor(Ugovor u) throws Exception {
        posaljiZahtev(Operacije.ADD_UGOVOR, u);
    }

    
}
