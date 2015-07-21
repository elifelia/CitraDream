/*
 * To change this license header, choose License Headers in
Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Elifelia
 */
public class RRDetailBean {
    
    private String RR_Number;
    private String PO_Number;
    private String item_id;
    private double qtyReceived;
    private double qtyOnHold;
    private String suppliers_id;
    private String department_id;
    private Boolean isEmpty;
    
    public Boolean addRRdetail(String RR_Number, 
            String PO_Number, String item_id, double qtyReceived, 
            double qtyOnHold, String supplier_id, String department_id) {
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String querydet = "INSERT INTO hcdy_purchasereqdetail VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st2 = db.getConnection().prepareStatement(querydet);
            st2.setString(1, RR_Number);
            st2.setString(2, PO_Number);
            st2.setString(3, item_id);
            st2.setDouble(4, qtyReceived);
            st2.setDouble(5, qtyOnHold);
            st2.setString(6, supplier_id);
            st2.setString(7, department_id);
//            st2.setString(7, remarks_item);
            st2.executeUpdate();

            pr = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return pr;
    }
    
    public RRDetailBean cariRRdetail(String rr, String po) {
        RRDetailBean prb = new RRDetailBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_receivingdetail WHERE RR_number"
                    + " = '" + rr + "' AND PO_Number = '"+po+"'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                prb.setRR_Number(rs.getString("RR_Number"));
                prb.setPO_Number(rs.getString("PO_Number"));
                prb.setItem_id(rs.getString("item_id"));
                prb.setQtyReceived(rs.getDouble("QtyReceived"));
                prb.setQtyOnHold(rs.getDouble("QtyOnHold"));
                prb.setSuppliers_id(rs.getString("suppliers_id"));
                prb.setDepartment_id(rs.getString("department_id"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return prb;
    }
    
    public RRDetailBean tampilRRDetail(String rr) {
        RRDetailBean prb = new RRDetailBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_receivingdetail WHERE RR_number = '" + rr + "' ";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                prb.setRR_Number(rs.getString("RR_Number"));
                prb.setPO_Number(rs.getString("PO_Number"));
                prb.setItem_id(rs.getString("item_id"));
                prb.setQtyReceived(rs.getDouble("QtyReceived"));
                prb.setQtyOnHold(rs.getDouble("QtyOnHold"));
                prb.setSuppliers_id(rs.getString("suppliers_id"));
                prb.setDepartment_id(rs.getString("department_id"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return prb;
    }

    public String getRR_Number() {
        return RR_Number;
    }

    public void setRR_Number(String RR_Number) {
        this.RR_Number = RR_Number;
    }

    public String getPO_Number() {
        return PO_Number;
    }

    public void setPO_Number(String PO_Number) {
        this.PO_Number = PO_Number;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public double getQtyReceived() {
        return qtyReceived;
    }

    public void setQtyReceived(double qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public double getQtyOnHold() {
        return qtyOnHold;
    }

    public void setQtyOnHold(double qtyOnHold) {
        this.qtyOnHold = qtyOnHold;
    }

    public String getSuppliers_id() {
        return suppliers_id;
    }

    public void setSuppliers_id(String suppliers_id) {
        this.suppliers_id = suppliers_id;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public Boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public double countItemOnhold(String po_number, String item_id){
        String receive ="SELECT QtyReceived FROM hcdy_receivingdetail WHERE "
                + "PO_Number ='"+po_number+"' AND item_id='"+item_id+"'";
        double rec = Double.parseDouble(receive);
        String onHold ="SELECT QtyOnHold FROM hcdy_receivingdetail WHERE PO_Number ='"+po_number+"'"
                + "AND item_id ='"+item_id+"'";
        double onH = Double.parseDouble(onHold);
        double holdItem = onH + rec;
        
       return holdItem;
    }

}
