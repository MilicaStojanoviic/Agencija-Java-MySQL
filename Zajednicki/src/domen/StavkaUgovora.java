/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class StavkaUgovora extends ApstraktniDomenskiObjekat{

    private Ugovor ugovor;
    private int rbStavke;
    private int trajanje;
    private Usluga usluga;

    public StavkaUgovora() {
    }

    public StavkaUgovora(Ugovor ugovor, int rbStavke, int trajanje, Usluga usluga) {
        this.ugovor = ugovor;
        this.rbStavke = rbStavke;
        this.trajanje = trajanje;
        this.usluga = usluga;
    }
    
    @Override
    public String nazivTabele() {
        return " StavkaUgovora ";
    }

    @Override
    public String alijas() {
        return " st ";
    }

    @Override
    public String join() {
        return " JOIN USLUGA U ON (U.USLUGAID = ST.USLUGAID) "
                + "JOIN UGOVOR UG ON (UG.UGOVORID = ST.UGOVORID) "
                + "JOIN KLIJENT K ON (K.KLIJENTID = UG.KLIJENTID) "
                + "JOIN PROSTOR P ON (P.PROSTORID = UG.PROSTORID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = UG.ADMINISTRATORID) ";
    }

    @Override
    public String koloneZaInsert() {
        return " (ugovorID, rbStavke, trajanje, uslugaID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + ugovor.getUgovorID() + ", " + rbStavke + ", "
                + " " + trajanje + ", " + usluga.getUslugaID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " WHERE U.UGOVORID = " + ugovor.getUgovorID();
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Prostor p = new Prostor(rs.getLong("ProstorID"),
                    rs.getString("Adresa"), rs.getString("TipProstora"));

            Klijent k = new Klijent(rs.getLong("KlijentID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Kontakt"), rs.getString("TipKlijenta"));

            Ugovor ug = new Ugovor(rs.getLong("ugovorID"), rs.getDate("datumPotpisivanja"),
                    rs.getDate("datumStupanjaNaSnagu"), rs.getString("napomene"), k, p, a, null);

            Usluga u = new Usluga(rs.getLong("UslugaID"),
                    rs.getString("naziv"), rs.getDouble("cena"),
                    rs.getString("opis"));

            StavkaUgovora st = new StavkaUgovora(ug, rs.getInt("rbStavke"),
                    rs.getInt("Trajanje"), u);

            lista.add(st);
        }

        rs.close();
        return lista;
    }

    public Ugovor getUgovor() {
        return ugovor;
    }

    public void setUgovor(Ugovor ugovor) {
        this.ugovor = ugovor;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }
    
}
