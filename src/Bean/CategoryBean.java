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
import javax.swing.JOptionPane;

/**
 *
 * @author Elifelia
 */
public class CategoryBean {

    private String CategoryID;
    private String CategoryName;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public Boolean addCategory(String categoryID, String categoryName) {
        Boolean category = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "INSERT INTO hcdy_itemcat VALUES('" + categoryID + "','" + categoryName + "') ";
            statement.executeUpdate(query);
            category = true;
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return category;
    }

    public CategoryBean cariCategory(String category) {
        CategoryBean cb = new CategoryBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_itemcat WHERE category_name like '%" + category + "%'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                cb.setCategoryID(rs.getString("category_id"));
                cb.setCategoryName(rs.getString("category_name"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return cb;
    }

    public Boolean updateCategory(String categoryID, String categoryName) {
        Boolean category = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "UPDATE hcdy_itemcat SET category_name "
                    + "=('" + categoryName + "') WHERE category_id ='" + categoryID + "'";
            statement.executeUpdate(query);
            category = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return category;
    }
}