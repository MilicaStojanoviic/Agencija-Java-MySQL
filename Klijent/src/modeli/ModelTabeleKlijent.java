/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Klijent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logika.Komunikacija;

/**
 *
 * @author Lenovo
 */
public class ModelTabeleKlijent extends AbstractTableModel implements  Runnable{
    private ArrayList<Klijent> lista;
    private String[] kolone = { "Ime", "Prezime", "Kontakt", "Tip klijenta"};
    private String parametar = "";

    public ModelTabeleKlijent() {
        try {
            lista = Komunikacija.getInstance().getAllKlijent();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleKlijent.class.getName()).log(Level.SEVERE, null, ex);
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
        Klijent k = lista.get(row);

        switch (column) {
            case 0:
                return k.getIme();
            case 1:
                return k.getPrezime();
            case 2:
                return k.getKontakt();
            case 3:
                return k.getTipKlijenta();

            default:
                return null;
        }
    }

    public Klijent getSelectedKlijent(int row) {
        return lista.get(row);
    }

    

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = Komunikacija.getInstance().getAllKlijent();
            if (!parametar.equals("")) {
                ArrayList<Klijent> novaLista = new ArrayList<>();
                for (Klijent k : lista) {
                    if (k.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || k.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(k);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
