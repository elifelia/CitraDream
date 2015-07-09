/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;
//
//import Controller.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Elifelia
 */
public class CurrencyBean {

    private String currencyID;
    private String currencyName;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public String getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(String currencyID) {
        this.currencyID = currencyID;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Boolean addCurrency(String currencyID, String currencyName) {
        Boolean currency = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "INSERT INTO hcdy_currency VALUES('" + currencyID + "','" + currencyName + "') ";
            statement.executeUpdate(query);
            currency = true;

        } catch (SQLException ex) {
            Logger.getLogger(CurrencyBean.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return currency;
    }

    public CurrencyBean cariCurrency(String curr) {
        CurrencyBean cb = new CurrencyBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_currency WHERE currency_id like '%" + curr + "%'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                cb.setCurrencyID(rs.getString("currency_id"));
                cb.setCurrencyName(rs.getString("currency_name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CurrencyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cb;
    }

    public Boolean updateCurrency(String currID, String currencyName) {
        Boolean currency = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "UPDATE hcdy_currency SET currency_name = ('" + currencyName + "')"
                    + "WHERE currency_id ='" + currID + "'";
            statement.executeUpdate(query);
            currency = true;

        } catch (SQLException ex) {
            Logger.getLogger(CurrencyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return currency;
    }
}
