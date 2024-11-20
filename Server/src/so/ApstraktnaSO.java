/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import dbb.DBBroker;
import domen.ApstraktniDomenskiObjekat;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public abstract class ApstraktnaSO {
    protected abstract void validate(ApstraktniDomenskiObjekat ado) throws Exception;
    protected abstract void execute(ApstraktniDomenskiObjekat ado) throws Exception;

    public void templateExecute(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            validate(ado); 
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    } 
}
