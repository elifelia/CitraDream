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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Elifelia
 */
public class PODetailBean {
    
    private String po_number;//
    private String pr_number;
    private String item_id;//
    private String supplier_id;
    private String category_id;
    private String measure_id;
    private double qtyApproved;//
    private String remarks_detail;//
    private double unit_price;//
    private double total_price;//
    private Boolean status;
    private String department_id;
    private double QtyRest;
    
    
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    
    
    public Boolean addPODetail(String po_number, String pr_number, String item_id, 
            String supplier_id, String categoy_id, String measure_id, double qtyApproved,
            String remarks_detail, double unitprice,double totalprice, Boolean status,
             String department_id, double QtyRest){
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String querydet = "INSERT INTO hcdy_purchaseorderdetail VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement st2 = db.getConnection().prepareStatement(querydet);
            st2.setString(1, po_number);
            st2.setString(2, pr_number);
            st2.setString(3, item_id);
            st2.setString(4, supplier_id);
            st2.setString(5, categoy_id);
            st2.setString(6, measure_id);
            st2.setDouble(7, qtyApproved);
            st2.setString(8, remarks_detail);
            st2.setDouble(9, unitprice);
            st2.setDouble(10, totalprice);
            st2.setBoolean(11, status);
            st2.setString(12, department_id);
            st2.setDouble(13, QtyRest);
            pr = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return pr;
    }
    
    
    public PODetailBean cariPODetail(String po) {
        PODetailBean prb = new PODetailBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_purchaseorderdetail WHERE PR_number = '" + po + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                prb.setPo_number(rs.getString("PO_number"));
                prb.setPr_number(rs.getString("PR_Number"));
                prb.setItem_id(rs.getString("item_id"));
                prb.setSupplier_id(rs.getString("Supplier_id"));
                prb.setCategory_id(rs.getString("Category_id"));
                prb.setMeasure_id(rs.getString("Measure_id"));
                prb.setQtyApproved(rs.getDouble("QtyApproved"));
                prb.setRemarks_detail(rs.getString("remarks_item"));
                prb.setUnit_price(rs.getDouble("unit_price"));
                prb.setTotal_price(rs.getDouble("total_price"));
                prb.setStatus(rs.getBoolean("status"));
                prb.setDepartment_id(rs.getString("dept_id"));
                prb.setQtyRest(rs.getDouble("QtyRest"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return prb;
    }
    
    public Boolean updatePO(String po_number, String pr_number, String item_id, 
            String supplier_id, String categoy_id, String measure_id, double qtyApproved,
            String remarks_detail, double unitprice,double totalprice, Boolean status,
             String department_id) {
        Boolean po = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement2 = db.getConnection().createStatement();
            String querydet = "UPDATE hcdy_purchaseorderdetail SET item_id = ('" + item_id + "'),"
                    + "QtyApproved = ('" + qtyApproved + "'), unit_price = ('" + unitprice + "'), "
                    + "total_price = ('" + totalprice + "'), remarks_detail = ('" + remarks_detail + "') "
                    + "WHERE PO_Number ='" + po_number + "'";
            statement2.executeUpdate(querydet);
            po = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return po;
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

    public double getQtyApproved() {
        return qtyApproved;
    }

    public void setQtyApproved(double qtyApproved) {
        this.qtyApproved = qtyApproved;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getRemarks_detail() {
        return remarks_detail;
    }

    public void setRemarks_detail(String remarks_detail) {
        this.remarks_detail = remarks_detail;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getPr_number() {
        return pr_number;
    }

    public void setPr_number(String pr_number) {
        this.pr_number = pr_number;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getMeasure_id() {
        return measure_id;
    }

    public void setMeasure_id(String measure_id) {
        this.measure_id = measure_id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    
    public double countItemRest(String po_number, String item_id){
        double suspend=0;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement2 = db.getConnection().createStatement();
            String receive ="SELECT QtyReceived FROM hcdy_receivingdetail WHERE "
                + "PO_Number ='"+po_number+"' AND item_id='"+item_id+"'";
            double rec = Double.parseDouble(receive);
            String approved ="SELECT QtyApproved FROM hcdy_purchaseorderdetail WHERE PO_Number ='"+po_number+"'"
                + "AND item_id ='"+item_id+"'";
            double app = Double.parseDouble(approved);
            suspend = app - rec;
        
       
        } catch (SQLException ex) {
            Logger.getLogger(PODetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suspend;
    }
    
    

    public double getQtyRest() {
        return QtyRest;
    }

    public void setQtyRest(double QtyRest) {
        this.QtyRest = QtyRest;
    }
}

