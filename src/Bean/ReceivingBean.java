/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Elifelia
 */
public class ReceivingBean {
    private String RR_Number;
    private Date receivingDate;
    private double grand_total;
    private String user;
    private Boolean isEmpty;
    

    Connection connection;
    ResultSet resultSet;
    Statement statement;
    
    public String setRRNumber() {
        DatabaseConnection db = new DatabaseConnection();
        String id = null;
        try {
            int num = 0;
            String query = "select count(rr_number) from hcdy_receiving";
            Statement st = db.getConnection().createStatement();
            java.sql.ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                num = Integer.parseInt(rs.getString(1));
            }
            num++;
            
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            Date date = new Date();
            
            id = "RR-"+num+"/"+format.format(date)+"/ACCT";
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    
    public Boolean addRR(String RR_Number, Date receivingDate, 
            double grand_total, String user, Boolean isEmpty){
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "INSERT INTO hcdy_receiving VALUES (?,?,?,?,?) ";
            PreparedStatement st = db.getConnection().prepareStatement(query);
            st.executeUpdate();
            st.setString(1, RR_Number);
            st.setDate(2, (java.sql.Date) receivingDate);
            st.setDouble(3, grand_total);
            st.setString(4, user);
            st.setBoolean(5, isEmpty);
            
            
            pr = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return pr;
    }
    
    
    public ReceivingBean cariRR(String rr) {
        ReceivingBean prb = new ReceivingBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_receiving WHERE RR_Number = '" + rr + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                prb.setRR_Number(rs.getString("RR_Number"));
                prb.setReceivingDate(rs.getDate("receivingDate"));
                prb.setGrand_total(rs.getDouble("grand_total"));
                prb.setUser(rs.getString("user"));
                prb.setIsEmpty(rs.getBoolean("isEmpty"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return prb;
    }
    
    
    public Boolean updateRR(String RR_Number, Date receivingDate, 
            double grand_total, String user, Boolean isEmpty) {
        Boolean pr = false;
        try {
            String sd = "UPDATE hcdy_receiving "
                    + "SET RR_Number=?,"
                    + "receivingDate=?,"
                    + "grand_total=?,"
                    + "user=?,"
                    + "isEmpty=?"
                    + "WHERE RR_Number=?";
            
            DatabaseConnection db = new DatabaseConnection();
            PreparedStatement statement = db.getConnection().prepareStatement(sd);
            statement.setString(1, RR_Number);
            statement.setDate(2, (java.sql.Date) receivingDate);
            statement.setDouble(3, grand_total);
            statement.setString(4, user);
            statement.setBoolean(5, isEmpty);
            statement.executeUpdate();
            pr = true;
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return pr;
    }
    
    public static void main(String[] args) {
//        ReceivingBean rb = new ReceivingBean();
//        rb.addPO(null, null, null, null, null, grand_total, null);
    }

    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(double grand_total) {
        this.grand_total = grand_total;
    }

    public Boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    
    public String isEmpty(String user) {
        String noRR = null;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String querydet = "SELECT RR_Number FROM hcdy_receiving WHERE "
                    + "isEmpty = 1 AND user = '"+user+"'";
            Statement st = connect.createStatement();
            
            ResultSet rs = st.executeQuery(querydet);
            while (rs.next()) {                
                noRR = rs.getString("RR_Number");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return noRR;
    }

    public String getRR_Number() {
        return RR_Number;
    }

    public void setRR_Number(String RR_Number) {
        this.RR_Number = RR_Number;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }
}
