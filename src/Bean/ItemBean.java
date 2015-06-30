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

/**
 *
 * @author Elifelia
 */
public class ItemBean {
    private String ItemID;
    private String ItemName;
    private String categoryID;
    private String measure;
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }
    
    public Boolean addItem(String ItemID, String ItemName, String category, String measure){
        Boolean item = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "INSERT INTO hcdy_itemmaster VALUES('"+ItemID+"','"+ItemName+"','"+category+"','"+measure+"') ";
            statement.executeUpdate(query);
            item = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return item;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
    
    public Boolean ambilCategory(String category){
        Boolean cat = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_category ";
            statement.executeUpdate(query);
            
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return cat;
    }
    
    public String[] ambilMeasure(){
        String[] measureArray = null;
        ItemBean ib = new ItemBean();
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "SELECT measureunit_name FROM hcdy_measureunit ";
            resultSet = statement.executeQuery(query);
            int i =0;            
            while (resultSet.next()) {
                            
                if (null != resultSet) {
                    String measure = resultSet.getString("measure");
                    measureArray[i++]= measure;
                }

            }

        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return measureArray;
    }
}
