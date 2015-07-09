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
public class ReceivingBean {
    private String receipt_number;
    private String po_reference;
    private Date receivingDate;
    private String item_ref;
    private Float qtyReceived;
    private Float qtyOnHold;

    Connection connection;
    ResultSet resultSet;
    Statement statement;
    
    public String getReceipt_number() {
        return receipt_number;
    }

    public void setReceipt_number(String receipt_number) {
        this.receipt_number = receipt_number;
    }

    public String getPo_reference() {
        return po_reference;
    }

    public void setPo_reference(String po_reference) {
        this.po_reference = po_reference;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public String getItem_ref() {
        return item_ref;
    }

    public void setItem_ref(String item_ref) {
        this.item_ref = item_ref;
    }

    public Float getQtyReceived() {
        return qtyReceived;
    }

    public void setQtyReceived(Float qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public Float getQtyOnHold() {
        return qtyOnHold;
    }

    public void setQtyOnHold(Float qtyOnHold) {
        this.qtyOnHold = qtyOnHold;
    }
    
    public Boolean addPO(String receipt_number, String po_reference, Date receivingDate, String item_ref, 
            Float qtyReceived, Float qtyOnHold){
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "INSERT INTO hcdy_receiving VALUES (?,?,?) ";
            String querydet ="INSERT INTO hcdy_receivingdetail VALUES (?,?,?,?,?)";
            PreparedStatement st = db.getConnection().prepareStatement(query);
            PreparedStatement st2 = db.getConnection().prepareStatement(querydet);
            st.executeUpdate();
            st2.executeUpdate();
            st.setString(1, receipt_number);
            st.setString(2, po_reference);
            st.setDate(3, (java.sql.Date) receivingDate);
            st2.setString(1, receipt_number);
            st2.setString(2, po_reference);
            st2.setString(3, item_ref);
            st2.setFloat(4, qtyReceived);
            st2.setFloat(4, qtyOnHold);
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
            String query = "SELECT * FROM hcdy_receiving WHERE receipt_number = '" + rr + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                prb.setReceipt_number(rs.getString("receipt_number"));
                prb.setPo_reference(rs.getString("PO_reference"));
                prb.setReceivingDate(rs.getDate("receivingDate"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return prb;
    }
    
    public Boolean updateRR(String receipt_number, String po_reference, Date receivingDate, String item_ref, 
            Float qtyReceived, Float qtyOnHold) {
        Boolean rr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement = db.getConnection().createStatement();
            Statement statement2 = db.getConnection().createStatement();
            String query = "UPDATE hcdy_receiving SET PO_reference = ('" + po_reference + "'),"
                    + "receivingDate = ('" + receivingDate + "') "
                    + "WHERE receipt_number ='" + receipt_number + "'";
            String querydet = "UPDATE hcdy_receivingdetail SET po_ref = ('" + po_reference + "'),"
                    + "item_ref = ('" + item_ref + "'), QtyReceived = ('" + qtyReceived + "'), "
                    + "QtyOnHold = ('" + qtyOnHold + "') "
                    + "WHERE rr_number ='" + receipt_number + "'";
            statement.executeUpdate(query);
            statement2.executeUpdate(querydet);
            rr = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return rr;
    }
    
    public static void main(String[] args) {
        ReceivingBean rb = new ReceivingBean();
        rb.addPO(null, null, null, null, Float.NaN, Float.NaN);
    }
    
}
