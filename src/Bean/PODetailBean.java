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
public class PODetailBean {

    private String po_number;//
    private String item_id;//
    private double qtyApproved;//
    private double unit_price;//
    private double total_price;//
    private String remarks_detail;//

    Connection connection;
    ResultSet resultSet;
    Statement statement;

    public Boolean addPODetail(String item_id, double qtyApproved,
            double unitprice, double totalprice, String remarks_detail) {
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String querydet = "INSERT INTO hcdy_purchaseorderdetail VALUES (?,?,?,?,?,?) ";
            PreparedStatement st2 = db.getConnection().prepareStatement(querydet);
            st2.setString(1, po_number);
            st2.setString(2, item_id);
            st2.setDouble(3, qtyApproved);
            st2.setDouble(4, unitprice);
            st2.setDouble(5, totalprice);
            st2.setString(6, remarks_detail);
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
                prb.setItem_id(rs.getString("item_id"));
                prb.setQtyApproved(rs.getDouble("QtyApproved"));
                prb.setUnit_price(rs.getDouble("unit_price"));
                prb.setTotal_price(rs.getDouble("total_price"));
                prb.setRemarks_detail(rs.getString("remarks_detail"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return prb;
    }

    public Boolean updatePO(String item_id, double qtyApproved,
            double unitprice, double totalprice, String remarks_detail) {
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

    public Boolean insertPrice_QtyApproved(String item_id, String po_number, double qtyApproved,
            double unitprice) {
        Boolean po = false;
        double total_price;
        total_price = qtyApproved*unitprice;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement2 = db.getConnection().createStatement();
            String querydet = "UPDATE hcdy_purchaseorderdetail SET item_id = ('" + item_id + "'),"
                    + "QtyApproved = ('" + qtyApproved + "'), unit_price = ('" + unitprice + "'), "
                    + "total_price = ('" + total_price + "')"
                    + "WHERE PO_Number ='" + po_number + "' and item_id='"+item_id+"'";
            statement2.executeUpdate(querydet);
            po = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return po;
    }
    
    public boolean setPONumToDetail(String PONum, String PRNumber){
        boolean pod = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement2 = db.getConnection().createStatement();
            String querydet = "UPDATE hcdy_purchaseorderdetail SET PO_Number = ('" + PONum + "') where pr_number ='"+PRNumber+"'";
            statement2.executeUpdate(querydet);
            pod = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return pod;
    }

    public boolean moveToPO(String PRNumber) {
        boolean po = false;
        DatabaseConnection db = new DatabaseConnection();
        try {
            Statement statement2 = db.getConnection().createStatement();
            String querydet = "INSERT INTO hcdy_purchaseorderdetail (PR_Number, item_id, category_id, measure_id, remarks_item)"
                    + "        SELECT PR_Number, item_id, category_id, measureunit_id, remarks_item from hcdy_purchasereqdetail "
                    + "        WHERE PR_number='" + PRNumber + "' and (Select isFinal from hcdy_purchasereq where PR_number='" + PRNumber + "')";
            statement2.executeUpdate(querydet);
            po = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return po;

    }

    public boolean insertPONumber(String PONumber, String PRNumber) {
        boolean po = false;
        DatabaseConnection db = new DatabaseConnection();
        try {
            Statement statement2 = db.getConnection().createStatement();
            String querydet = "Update hcdy_purchaseorderdetail set PO_Number='" + PONumber + "' where PR_Number='" + PRNumber + "'";
            statement2.executeUpdate(querydet);
            po = true;
        } catch (SQLException ex) {
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

}
