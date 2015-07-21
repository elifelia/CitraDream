/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

//import Controller.Controller;
import java.sql.Connection;
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
public class ItemBean {
    private String ItemID;
    private String ItemName;
    private String categoryID;
    private String measure;
    private String itemDesc;
    private double qtyOnHand;
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
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
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
    
    
    public ItemBean cariItem(String item) {
        ItemBean ib = new ItemBean();
//        ub = null;
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_itemmaster WHERE item_name like '%" + item + "%'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                if (null != resultSet) {
                    ib.setItemID(resultSet.getString("item_id"));
                    ib.setItemName(resultSet.getString("item_name"));
                    ib.setCategoryID(resultSet.getString("category_id"));
                    ib.setMeasure(resultSet.getString("measureUnit_id"));
                    ib.setItemDesc(resultSet.getString("item_desc"));
                    ib.setQtyOnHand(resultSet.getDouble("QtyOnHand"));
                    
                }

            }

        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return ib;
    }
    
     public ItemBean cariItemID(String id) {
        ItemBean ib = new ItemBean();
//        ub = null;
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_itemmaster WHERE item_id = '" + id + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                if (null != resultSet) {
                    ib.setItemID(resultSet.getString("item_id"));
                    ib.setItemName(resultSet.getString("item_name"));
                    ib.setCategoryID(resultSet.getString("category_id"));
                    ib.setMeasure(resultSet.getString("measureUnit_id"));
                    ib.setItemDesc(resultSet.getString("item_desc"));
                    ib.setQtyOnHand(resultSet.getDouble("QtyOnHand"));
                    
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return ib;
    }
    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public double getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(double qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

}
