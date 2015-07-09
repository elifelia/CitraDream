/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

//import Controller.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Elifelia
 */
public class MeasureBean {

    private String measureID;
    private String measureName;
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    public String getMeasureID() {
        return measureID;
    }

    public void setMeasureID(String measureID) {
        this.measureID = measureID;
    }

    public String getMeasureName() {
        return measureName;
    }

    public void setMeasureName(String measureName) {
        this.measureName = measureName;
    }

    public Boolean addMeasure(String MeasureID, String MeasureName) {
        Boolean measure = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "INSERT INTO hcdy_measure VALUES('" + MeasureID + "','" + MeasureName + "') ";
            statement.executeUpdate(query);
            measure = true;
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return measure;
    }

    public MeasureBean cariMeasure(String mea) {
        MeasureBean mb = new MeasureBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_measureunit WHERE measureUnit_id like '%" + mea + "%'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                mb.setMeasureID(rs.getString("measureUnit_id"));
                mb.setMeasureName(rs.getString("measureUnit_name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mb;
    }
}
