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
public class Klijent extends ApstraktniDomenskiObjekat{

    private Long klijentID;
    private String ime;
    private String prezime;
    private String kontakt;
    private String tipKlijenta;

    public Klijent() {
    }

    public Klijent(Long klijentID, String ime, String prezime, String kontakt, String tipKlijenta) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt = kontakt;
        this.tipKlijenta = tipKlijenta;
    }

    @Override
    public String toString() {
        return ime+" "+prezime+", "+tipKlijenta;
    }
    
    @Override
    public String nazivTabele() {
        return " klijent ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Kontakt, TipKlijenta) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " KlijentID = " + klijentID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + kontakt + "', '" + tipKlijenta + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " kontakt = '" + kontakt + "', tipKlijenta = '" + tipKlijenta + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Klijent k = new Klijent(rs.getLong("KlijentID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Kontakt"), rs.getString("TipKlijenta"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    public Long getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Long klijentID) {
        this.klijentID = klijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getTipKlijenta() {
        return tipKlijenta;
    }

    public void setTipKlijenta(String tipKlijenta) {
        this.tipKlijenta = tipKlijenta;
    }
    
}
