/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Lenovo
 */
public class KlijentskiZahtev implements Serializable{
    private int operacija;
    private Object parametar;

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(int operation, Object data) {
        this.operacija = operation;
        this.parametar = data;
    }

    public Object getParametar() {
        return parametar;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }
}
