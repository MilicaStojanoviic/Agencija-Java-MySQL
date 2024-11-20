/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Ugovor extends ApstraktniDomenskiObjekat{
    
    private Long ugovorID;
    private Date datumPotpisivanja;
    private Date datumStupanjaNaSnagu;
    private String napomene;
    private Klijent klijent;
    private Prostor prostor;
    private Administrator administrator;
    private ArrayList<StavkaUgovora> stavkeUgovora;

    public Ugovor() {
    }

    public Ugovor(Long ugovorID, Date datumPotpisivanja, Date datumStupanjaNaSnagu, String napomene, Klijent klijent, Prostor prostor, Administrator administrator, ArrayList<StavkaUgovora> stavkeUgovora) {
        this.ugovorID = ugovorID;
        this.datumPotpisivanja = datumPotpisivanja;
        this.datumStupanjaNaSnagu = datumStupanjaNaSnagu;
        this.napomene = napomene;
        this.klijent = klijent;
        this.prostor = prostor;
        this.administrator = administrator;
        this.stavkeUgovora = stavkeUgovora;
    }
    

    @Override
    public String nazivTabele() {
        return " Ugovor ";
    }

    @Override
    public String alijas() {
        return " ug ";
    }

    @Override
    public String join() {
        return " JOIN KLIJENT K ON (K.KLIJENTID = UG.KLIJENTID) "
                + "JOIN PROSTOR P ON (P.PROSTORID = UG.PROSTORID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = UG.ADMINISTRATORID) ";
    }

    @Override
    public String koloneZaInsert() {
        return " (datumPotpisivanja, datumStupanjaNaSnagu, napomene, klijentID, prostorID, administratorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " ugovorID = " + ugovorID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Date(datumPotpisivanja.getTime()) + "', " + new Date(datumStupanjaNaSnagu.getTime()) + ", "
                + "'" + napomene + "', " + klijent.getKlijentID() + ", " + prostor.getProstorID()
                + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumPotpisivanja= '" + new Date(datumPotpisivanja.getTime()) + "', " 
                + "datumStupanjaNaSnagu= '" + new Date(datumStupanjaNaSnagu.getTime()) + "', "
                + "'" + napomene + "', " 
                + "prostorID = " + prostor.getProstorID()+ " ";
    }

    @Override
    public String uslov() {
        return "";
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


            lista.add(ug);
        }

        rs.close();
        return lista;
    }

    public Long getUgovorID() {
        return ugovorID;
    }

    public void setUgovorID(Long ugovorID) {
        this.ugovorID = ugovorID;
    }

    public Date getDatumPotpisivanja() {
        return datumPotpisivanja;
    }

    public void setDatumPotpisivanja(Date datumPotpisivanja) {
        this.datumPotpisivanja = datumPotpisivanja;
    }

    public Date getDatumStupanjaNaSnagu() {
        return datumStupanjaNaSnagu;
    }

    public void setDatumStupanjaNaSnagu(Date datumStupanjaNaSnagu) {
        this.datumStupanjaNaSnagu = datumStupanjaNaSnagu;
    }

    public String getNapomene() {
        return napomene;
    }

    public void setNapomene(String napomene) {
        this.napomene = napomene;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Prostor getProstor() {
        return prostor;
    }

    public void setProstor(Prostor prostor) {
        this.prostor = prostor;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<StavkaUgovora> getStavkeUgovora() {
        return stavkeUgovora;
    }

    public void setStavkeUgovora(ArrayList<StavkaUgovora> stavkeUgovora) {
        this.stavkeUgovora = stavkeUgovora;
    }
    
}
