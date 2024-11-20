/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import konstante.StatusOdgovora;

/**
 *
 * @author Lenovo
 */
public class ServerskiOdgovor implements Serializable{
    private Object odgovor;
    private Exception exc;
    private StatusOdgovora statusOdgovora;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object data, Exception exc, StatusOdgovora responseStatus) {
        this.odgovor = data;
        this.exc = exc;
        this.statusOdgovora = responseStatus;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getException() {
        return exc;
    }

    public void setException(Exception exc) {
        this.exc = exc;
    }

    public StatusOdgovora getStatusOdgovora() {
        return statusOdgovora;
    }

    public void setStatusOdgovora(StatusOdgovora statusOdgovora) {
        this.statusOdgovora = statusOdgovora;
    }
}
