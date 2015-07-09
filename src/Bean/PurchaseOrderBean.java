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
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Elifelia
 */
public class PurchaseOrderBean {
    
    private String pr_number;//
    private String department_id;
    private Date delivery_date;//
    private String remarks;//
    private String status;//
    private String po_number;//
    private String item_id;//
    private Float qtyApproved;//
    private Float unit_price;//
    private Float total_price;//
    private String remarks_detail;//
    private String suppliers_id;//
    private String currency_id;//
    private Float grand_total;//
    
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    
    
    public Boolean addPO(String po_number, String pr_number, String suppliers_id, 
            String currency_id, Date delivery_date, Float grand_total, 
            String remarks, String status, String item_id, Float qtyApproved,
            Float unitprice,Float totalprice, String remarks_detail){
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "INSERT INTO hcdy_purchaseorder VALUES (?,?,?,?,?,?,?,?) ";
            String querydet = "INSERT INTO hcdy_purchaseorderdetail VALUES (?,?,?,?,?,?) ";
            PreparedStatement st = db.getConnection().prepareStatement(query);
            PreparedStatement st2 = db.getConnection().prepareStatement(querydet);
            st.executeUpdate();
            st.setString(1, po_number);
            st.setString(2, pr_number);
            st.setString(3, suppliers_id);
            st.setString(4, currency_id);
            st.setDate(5, (java.sql.Date) delivery_date);
            st.setFloat(6, grand_total);
            st.setString(7, remarks);
            st.setString(8, status);
            st2.setString(1, po_number);
            st2.setString(2, item_id);
            st2.setFloat(3, qtyApproved);
            st2.setFloat(4, unitprice);
            st2.setFloat(5, totalprice);
            st2.setString(6, remarks_detail);
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
            String currency_id, Date delivery_date, Float grand_total, 
            String remarks, String status, String item_id, Float qtyApproved,
            Float unitprice,Float totalprice, String remarks_detail) {
        Boolean po = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement = db.getConnection().createStatement();
            Statement statement2 = db.getConnection().createStatement();
            String query = "UPDATE hcdy_purchaseorder SET PR_Number = ('" + pr_number + "'),"
                    + "suppliers_id = ('" + suppliers_id + "'), currency_id = ('" + currency_id + "'), "
                    + "delivery_date = ('" + delivery_date + "'), grand_total = ('" + grand_total + "'),"
                    + "remarks = ('" + remarks + "'), status = ('" + status + "') "
                    + "WHERE PO_Number ='" + po_number + "'";
            String querydet = "UPDATE hcdy_purchaseorderdetail SET item_id = ('" + item_id + "'),"
                    + "QtyApproved = ('" + qtyApproved + "'), unit_price = ('" + unitprice + "'), "
                    + "total_price = ('" + totalprice + "'), remarks_detail = ('" + remarks_detail + "') "
                    + "WHERE PO_Number ='" + po_number + "'";
            statement.executeUpdate(query);
            statement2.executeUpdate(querydet);
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

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
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

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public Float getQtyApproved() {
        return qtyApproved;
    }

    public void setQtyApproved(Float qtyApproved) {
        this.qtyApproved = qtyApproved;
    }

    public Float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Float unit_price) {
        this.unit_price = unit_price;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public String getRemarks_detail() {
        return remarks_detail;
    }

    public void setRemarks_detail(String remarks_detail) {
        this.remarks_detail = remarks_detail;
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

    public Float getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(Float grand_total) {
        this.grand_total = grand_total;
    }
}

