/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public abstract class ApstraktniDomenskiObjekat implements Serializable{
    
    public abstract String nazivTabele();
    public abstract String alijas();
    public abstract String join();
    public abstract String koloneZaInsert();
    public abstract String vrednostZaPrimarniKljuc();
    public abstract String vrednostiZaInsert();
    public abstract String vrednostiZaUpdate();
    public abstract String uslov();
    public abstract ArrayList<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException;
}
