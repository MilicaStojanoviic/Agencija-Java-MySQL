/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Ugovor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logika.Komunikacija;

/**
 *
 * @author Lenovo
 */
public class ModelTabeleUgovor extends AbstractTableModel implements Runnable{

    private ArrayList<Ugovor> lista;
    private String[] kolone = { "Klijent", "Prostor", "Datum stupanja na snagu"};
    private String parametar = "";

    public ModelTabeleUgovor() {
        try {
            lista = Komunikacija.getInstance().getAllUgovor();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleUgovor.class.getName()).log(Level.SEVERE, null, ex);
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
        Ugovor u = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        switch (column) {
            case 0:
                return u.getKlijent();
            case 1:
                return u.getProstor();
            case 2:
                return sdf.format(u.getDatumStupanjaNaSnagu());
            default:
                return null;
        }
    }

    public Ugovor getSelectedUgovor(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleUgovor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = Komunikacija.getInstance().getAllUgovor();
            if (!parametar.equals("")) {
                ArrayList<Ugovor> novaLista = new ArrayList<>();
                for (Ugovor u : lista) {
                    if (u.getKlijent().getIme().toLowerCase().contains(parametar.toLowerCase())
                            || u.getKlijent().getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(u);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
