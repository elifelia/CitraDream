package Bean;

//import Controller.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Elifelia
 */
public class PurchaseRequestBean {

    private String pr_number;
    private String dept_id;
    private Date req_date;
    private String remarks_pr;
    private Date date;
    private String item_id;
    private String measureUnit_id;
    private Float QtyRequest;
    private String remarks_item;
    private String category_id;
    private String status;
    Boolean statusSave;

    Connection connection;
    ResultSet resultSet;
    Statement statement;

    public String getPr_number() {
        return pr_number;
    }

    public void setPr_number(String pr_number) {
        this.pr_number = pr_number;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public Date getReq_date() {
        return req_date;
    }

    public void setReq_date(Date req_date) {
        this.req_date = req_date;
    }

    public String getRemarks_pr() {
        return remarks_pr;
    }

    public void setRemarks_pr(String remarks_pr) {
        this.remarks_pr = remarks_pr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Float getQtyRequest() {
        return QtyRequest;
    }

    public void setQtyRequest(Float QtyRequest) {
        this.QtyRequest = QtyRequest;
    }

    public String getRemarks_item() {
        return remarks_item;
    }

    public void setRemarks_item(String remarks_item) {
        this.remarks_item = remarks_item;
    }

    public Boolean searchProduct(String itemName) {
        Boolean item = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_itemmaster WHERE item_name like %'" + itemName + "'% ";
            statement.executeUpdate(query);
            item = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return item;
    }

    public Boolean searchPR(String prNo) {
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_purchaseRequest WHERE pr_no like %'" + prNo + "'% ";
            statement.executeUpdate(query);
            pr = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return pr;
    }

    public Boolean addPR(String pr_number, String dept_id, Date req_date,
            String remarks_pr, Date date, String status) {
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String querypr = "INSERT INTO hcdy_purchasereq VALUES (?,?,?,?,?,?) ";
            PreparedStatement st = db.getConnection().prepareStatement(querypr);
            st.setString(1, pr_number);
            st.setString(2, dept_id);
            st.setDate(3, (java.sql.Date) req_date);
            st.setString(4, remarks_pr);
            st.setDate(5, (java.sql.Date) date);
            st.setString(6, status);
            st.executeUpdate();
            
            pr = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return pr;
    }
    
    public Boolean addPRdetail(String pr_number, String item_id , String category_id, String measureUnit_id,
            Float qtyRequest, String remarks_item) {
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String querydet = "INSERT INTO hcdy_purchasereqdetail VALUES (?,?,?,?,?,?)";
            PreparedStatement st2 = db.getConnection().prepareStatement(querydet);
            st2.setString(1, pr_number);
            st2.setString(2, item_id);
            st2.setString(3, category_id);
            st2.setString(4, measureUnit_id);
            st2.setFloat(5, qtyRequest);
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

    public PurchaseRequestBean cariPR(String pr) {
        PurchaseRequestBean prb = new PurchaseRequestBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_purchasereq WHERE PR_number = '" + pr + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                prb.setPr_number(rs.getString("PR_number"));
                prb.setDept_id(rs.getString("department_id"));
                prb.setDate(rs.getDate("Request_date"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return prb;
    }
    
    public PurchaseRequestBean cariPRdetail(String pr) {
        PurchaseRequestBean prb = new PurchaseRequestBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_purchasereqdetail WHERE PR_number = '" + pr + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                prb.setPr_number(rs.getString("PR_Number"));
                prb.setItem_id(rs.getString("item_id"));
                prb.setCategory_id(rs.getString("category_id"));
                prb.setMeasureUnit_id(rs.getString("measureUnit_id"));
                prb.setQtyRequest(rs.getFloat("QtyRequest"));
                prb.setRemarks_item(rs.getString("remarks_item"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return prb;
    }

    public Boolean updatePR(String pr_number, String dept_id, Date req_date,
            String remarks_pr, Date date, String status) {
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement = db.getConnection().createStatement();
            String query = "UPDATE hcdy_purchasereq SET Request_date = ('" + req_date + "'),"
                    + "remarks_PR = ('" + remarks_pr + "'), date = ('" + date + "'), status = ('" + status + "') "
                    + "WHERE PR_Number ='" + pr_number + "'";
            statement.executeUpdate(query);
            pr = true;
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return pr;
    }
    
    public Boolean updatePRdetail(String pr_number, String item_id , String category_id, String measureUnit_id,
            Float qtyRequest, String remarks_item) {
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement2 = db.getConnection().createStatement();
            String querydet = "UPDATE hcdy_purchasereqdetail SET item_id = ('" + item_id + "'),"
                    + "category_id = ('" + category_id + "'), measureUnit_id = ('" + measureUnit_id + "'), "
                    + "QtyOnHands = ('" + qtyRequest + "'), remarks_item = ('" + remarks_item + "') "
                    + "WHERE PR_Number ='" + pr_number + "'";
            statement2.executeUpdate(querydet);
            pr = true;
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return pr;
    }

    public static void main(String[] args) {
        PurchaseRequestBean pr = new PurchaseRequestBean();
        pr.addPR("001", "HK", new java.sql.Date(2015, 7, 7), "-", new java.sql.Date(2015, 7, 7),"");
        pr.addPRdetail("001", "001", "item_001", "ltr", new Float(3), "-");
        pr.addPRdetail("001", "002", "item_002", "pcs", new Float(2), "-");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

}
