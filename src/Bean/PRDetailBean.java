/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Elifelia
 */
public class PRDetailBean {
    
    private String pr_number; //
    private String item_id; //
    private String measureUnit_id; //
    private double QtyRequest; //
    private String remarks_item; //
    private String category_id; //

    public String getPr_number() {
        return pr_number;
    }

    public void setPr_number(String pr_number) {
        this.pr_number = pr_number;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getMeasureUnit_id() {
        return measureUnit_id;
    }

    public void setMeasureUnit_id(String measureUnit_id) {
        this.measureUnit_id = measureUnit_id;
    }

    public double getQtyRequest() {
        return QtyRequest;
    }

    public void setQtyRequest(double QtyRequest) {
        this.QtyRequest = QtyRequest;
    }

    public String getRemarks_item() {
        return remarks_item;
    }

    public void setRemarks_item(String remarks_item) {
        this.remarks_item = remarks_item;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
 
    public Boolean addPRdetail(String pr_number, String item_id , String category_id, String measureUnit_id,
            double qtyRequest, String remarks_item) {
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String querydet = "INSERT INTO hcdy_purchasereqdetail VALUES (?,?,?,?,?,?)";
            PreparedStatement st2 = db.getConnection().prepareStatement(querydet);
            st2.setString(1, pr_number);
            st2.setString(2, item_id);
            st2.setString(3, category_id);
            st2.setString(4, measureUnit_id);
            st2.setDouble(5, qtyRequest);
            st2.setString(6, remarks_item);
            st2.executeUpdate();

            pr = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return pr;
    }
    
    public PRDetailBean cariPRdetail(String pr, String item_id) {
        PRDetailBean prb = new PRDetailBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_purchasereqdetail WHERE PR_number = '" + pr + "' AND item_id = '"+item_id+"'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                prb.setPr_number(rs.getString("PR_Number"));
                prb.setItem_id(rs.getString("item_id"));
                prb.setCategory_id(rs.getString("category_id"));
                prb.setMeasureUnit_id(rs.getString("measureUnit_id"));
                prb.setQtyRequest(rs.getDouble("QtyRequest"));
                prb.setRemarks_item(rs.getString("remarks_item"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return prb;
    }
    
    public PRDetailBean tampilPRDetail(String pr) {
        PRDetailBean prb = new PRDetailBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_purchasereqdetail WHERE PR_number = '" + pr + "' ";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                prb.setPr_number(rs.getString("PR_Number"));
                prb.setItem_id(rs.getString("item_id"));
                prb.setCategory_id(rs.getString("category_id"));
                prb.setMeasureUnit_id(rs.getString("measureUnit_id"));
                prb.setQtyRequest(rs.getDouble("QtyRequest"));
                prb.setRemarks_item(rs.getString("remarks_item"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return prb;
    }
    
}
