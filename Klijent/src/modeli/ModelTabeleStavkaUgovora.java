/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.StavkaUgovora;
import domen.Ugovor;
import domen.Usluga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logika.Komunikacija;

/**
 *
 * @author Lenovo
 */
public class ModelTabeleStavkaUgovora extends AbstractTableModel {
    private ArrayList<StavkaUgovora> lista;
    private String[] kolone = {"Rb", "Usluga", "Trajanje"};
    private int rb = 0;

    public ModelTabeleStavkaUgovora() {
        lista = new ArrayList<>();
    }

    public ModelTabeleStavkaUgovora(Ugovor u) {
        try {
            lista = Komunikacija.getInstance().getAllStavkaUgovora(u);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleStavkaUgovora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaUgovora st = lista.get(row);

        switch (column) {
            case 0:
                return st.getRbStavke();
            case 1:
                return st.getUsluga().getNaziv()+", "+st.getUsluga().getCena()+"din. ";
            case 2:
                return st.getTrajanje()+" god.";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaUgovora st) {
        rb = lista.size();
        st.setRbStavke(++rb);
        lista.add(st);
        fireTableDataChanged();
    }

    public boolean postojiUsluga(Usluga u) {
        for (StavkaUgovora StavkaUgovora : lista) {
            if (StavkaUgovora.getUsluga().getUslugaID().equals(u.getUslugaID())) {
                return true;
            }
        }
        return false;
    }

    public void obrisiStavku(int row) {
        lista.remove(row);
        
        rb = 0;
        for (StavkaUgovora StavkaUgovora : lista) {
            StavkaUgovora.setRbStavke(++rb);
        }
        
        fireTableDataChanged();
    }


    public ArrayList<StavkaUgovora> getLista() {
        return lista;
    }
}
