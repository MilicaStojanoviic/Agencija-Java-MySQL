/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Administrator;
import domen.Klijent;
import domen.Prostor;
import domen.StavkaUgovora;
import domen.Ugovor;
import domen.Usluga;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import konstante.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import konstante.StatusOdgovora;
import logika.Kontroler;

/**
 *
 * @author Lenovo
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket socket;

    ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev kz = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor so = obradiZahtev(kz);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(so);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor obradiZahtev(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor(null, null, StatusOdgovora.Success);
        try {
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    Administrator administrator = (Administrator) kz.getParametar();
                    Administrator ulogovani = Kontroler.getInstance().login(administrator);
                    so.setOdgovor(ulogovani);
                    break;
                case Operacije.ADD_KLIJENT:
                    Kontroler.getInstance().addKlijent((Klijent) kz.getParametar());
                    break;
                case Operacije.GET_ALL_KLIJENT:
                    so.setOdgovor(Kontroler.getInstance().getAllKlijent());
                    break;
                case Operacije.DELETE_KLIJENT:
                    Kontroler.getInstance().deleteKlijent((Klijent) kz.getParametar());
                    break;
                case Operacije.UPDATE_KLIJENT:
                    Kontroler.getInstance().updateKlijent((Klijent) kz.getParametar());
                    break;
                case Operacije.GET_ALL_USLUGA:
                    so.setOdgovor(Kontroler.getInstance().getAllUsluga());
                    break;
                case Operacije.DELETE_USLUGA:
                    Kontroler.getInstance().deleteUsluga((Usluga) kz.getParametar());
                    break;
                case Operacije.ADD_USLUGA:
                    Kontroler.getInstance().addUsluga((Usluga) kz.getParametar());
                    break;
                case Operacije.UPDATE_USLUGA:
                    Kontroler.getInstance().updateUsluga((Usluga) kz.getParametar());
                    break;
                case Operacije.GET_ALL_UGOVOR:
                    so.setOdgovor(Kontroler.getInstance().getAllUgovor());
                    break;
                case Operacije.GET_ALL_STAVKA:
                    so.setOdgovor(Kontroler.getInstance().getAllStavkaUgovora((Ugovor)kz.getParametar()));
                    break;
                case Operacije.ADD_PROSTOR:
                    Kontroler.getInstance().addProstor((Prostor) kz.getParametar());
                    break;
                case Operacije.DELETE_PROSTOR:
                    Kontroler.getInstance().deleteProstor((Prostor) kz.getParametar());
                    break;
                case Operacije.ADD_UGOVOR:
                    Kontroler.getInstance().addUgovor((Ugovor) kz.getParametar());
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            so.setStatusOdgovora(StatusOdgovora.Error);
            so.setException(e);
        }
        return so;
    }
}
