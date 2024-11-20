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
public class Prostor extends ApstraktniDomenskiObjekat{
    
    private Long prostorID;
    private String adresa;
    private String tipProstora;

    public Prostor() {
    }

    public Prostor(Long prostorID, String adresa, String tipProstora) {
        this.prostorID = prostorID;
        this.adresa = adresa;
        this.tipProstora = tipProstora;
    }

    @Override
    public String toString() {
        return tipProstora+", "+adresa;
    }
    
    @Override
    public String nazivTabele() {
        return " Prostor ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public String koloneZaInsert() {
        return " (Adresa, TipProstora) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " ProstorID = "+prostorID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + adresa + "', '" + tipProstora + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Prostor p = new Prostor(rs.getLong("ProstorID"),
                    rs.getString("Adresa"), rs.getString("TipProstora"));

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    public Long getProstorID() {
        return prostorID;
    }

    public void setProstorID(Long prostorID) {
        this.prostorID = prostorID;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTipProstora() {
        return tipProstora;
    }

    public void setTipProstora(String tipProstora) {
        this.tipProstora = tipProstora;
    }
    
}
