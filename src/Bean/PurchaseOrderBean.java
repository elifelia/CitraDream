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
public class PurchaseOrderBean {
    
    private String pr_number;//
    private Date delivery_date;//
    private String remarks;//
    private String status;//
    private String po_number;//
    private String suppliers_id;//
    private String currency_id;//
    private double grand_total;//
    private Boolean isEmpty;
    
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    public double POTotal(String PRNumber){
        double amount = 0;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "sum(total_price) from  hcdy_purchaseorderdetail where pr_number='"+PRNumber+"'";
            PreparedStatement st = db.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                amount = rs.getDouble(1);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }

        return amount;
    }
    
    
    public Boolean addPO(String po_number, String pr_number, String suppliers_id, 
            String currency_id, Date delivery_date, 
            String remarks, double discount, double VAT, double total, boolean isEmpty){
        Boolean pr = false;
//        double reduction;
//        reduction = (POTotal(pr_number))-(POTotal(pr_number)*discount);
//        double total = reduction+(reduction*VAT);
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "INSERT INTO hcdy_purchaseorder VALUES (?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement st = db.getConnection().prepareStatement(query);
            st.executeUpdate();
            st.setString(1, po_number);
            st.setString(2, pr_number);
            st.setString(3, suppliers_id);
            st.setString(4, currency_id);
            st.setDate(5, (java.sql.Date) delivery_date);
            st.setDouble(6, total);
            st.setString(7, remarks);
            st.setDouble(8, discount);
            st.setDouble(9, VAT);
            st.setBoolean(10, isEmpty);
            pr = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return pr;
    }
    
    
    public PurchaseOrderBean cariPO(String po) {
        PurchaseOrderBean prb = new PurchaseOrderBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_purchasereq WHERE PR_number = '" + po + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                prb.setPo_number(rs.getString("PO_number"));
                prb.setPr_number(rs.getString("PR_number"));
                prb.setSuppliers_id(rs.getString("suppliers_id"));
                prb.setDelivery_date(rs.getDate("delivery_date"));
                prb.setRemarks(rs.getString("remarksq"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return prb;
    }
    
    public Boolean updatePO(String po_number, String pr_number, String suppliers_id, 
            String currency_id, Date delivery_date, double grand_total, 
            String remarks, String status) {
        Boolean po = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement = db.getConnection().createStatement();
            String query = "UPDATE hcdy_purchaseorder SET PR_Number = ('" + pr_number + "'),"
                    + "suppliers_id = ('" + suppliers_id + "'), currency_id = ('" + currency_id + "'), "
                    + "delivery_date = ('" + delivery_date + "'), grand_total = ('" + grand_total + "'),"
                    + "remarks = ('" + remarks + "'), status = ('" + status + "') "
                    + "WHERE PO_Number ='" + po_number + "'";
            statement.executeUpdate(query);
            po = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return po;
    }

    public String getPr_number() {
        return pr_number;
    }

    public void setPr_number(String pr_number) {
        this.pr_number = pr_number;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPo_number() {
        return po_number;
    }

    public void setPo_number(String po_number) {
        this.po_number = po_number;
    }

    public String getSuppliers_id() {
        return suppliers_id;
    }

    public void setSuppliers_id(String suppliers_id) {
        this.suppliers_id = suppliers_id;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public double getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(double grand_total) {
        this.grand_total = grand_total;
    }
    
    public String isEmpty() {
        String noPO = null;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String querydet = "SELECT PO_Number FROM hcdy_purchaseorder WHERE "
                    + "isEmpty = 1";
            Statement st = connect.createStatement();
            
            ResultSet rs = st.executeQuery(querydet);
            while (rs.next()) {                
                noPO = rs.getString("PO_Number");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return noPO;
    }

    public Boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    
    public String setPONumber() {
        DatabaseConnection db = new DatabaseConnection();
        String id = null;
        try {
            int num = 0;
            String query = "select count(po_number) from hcdy_purchaseorder";
            Statement st = db.getConnection().createStatement();
            java.sql.ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                num = Integer.parseInt(rs.getString(1));
            }
            num++;
            
            SimpleDateFormat format = new SimpleDateFormat("YYYY/MM");
            Date date = new Date();
            
            id = "PO-"+num+"/"+format.format(date)+"/ACCT";
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public void SetisEmpty(String prNumber) {
//        Boolean empty = true;
        int num = 0;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String querydet = "SELECT COUNT(item_id) FROM hcdy_purchaseorderdetail WHERE PR_Number = '" + prNumber + "'";
            Statement st = connect.createStatement();
            
            ResultSet rs = st.executeQuery(querydet);
            while (rs.next()) {                
                num = Integer.parseInt(rs.getString(1));
            }
            if (num == 0) {
                String query = "UPDATE hcdy_purchaseorder SET isEmpty = '?' WHERE PR_Number ='" + prNumber + "'";
                
                Connection connection = db.getConnection();
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setBoolean(1, true);
                pst.executeUpdate();
//                empty = true;
            } else {
                String query = "UPDATE hcdy_purchaseorder SET isEmpty = '?' WHERE PR_Number ='" + prNumber + "'";
                
                Connection connection = db.getConnection();
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setBoolean(1, false);
                pst.executeUpdate();
//                empty = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return empty;
    }
}

