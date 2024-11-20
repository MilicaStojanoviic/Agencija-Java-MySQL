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
public class Usluga extends ApstraktniDomenskiObjekat{
    
    private Long uslugaID;
    private String naziv;
    private double cena;
    private String opis;

    public Usluga() {
    }

    public Usluga(Long uslugaID, String naziv, double cena, String opis) {
        this.uslugaID = uslugaID;
        this.naziv = naziv;
        this.cena = cena;
        this.opis = opis;
    }

    @Override
    public String toString() {
        return naziv+ " (Cena usluge na mesečnom nivou: "+cena+"din)";
    }

    @Override
    public String nazivTabele() {
        return " Usluga ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv, Cena, Opis) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " UslugaID = " + uslugaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + cena + "', " + "'" + opis +  "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " cena = '" + cena + "', opis = '" + opis + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Usluga u = new Usluga(rs.getLong("UslugaID"),
                    rs.getString("naziv"), rs.getDouble("cena"),
                    rs.getString("opis"));

            lista.add(u);
        }

        rs.close();
        return lista;
    }

    public Long getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(Long uslugaID) {
        this.uslugaID = uslugaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
}
