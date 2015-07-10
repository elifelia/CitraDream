package Bean;

//import Controller.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
    private Boolean status;
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
                prb.setDate(rs.getDate("date"));
                prb.setStatus(rs.getBoolean("status"));
                prb.setReq_date(rs.getDate("Request_date"));
                prb.setRemarks_pr(rs.getString("remarks_PR"));
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
//        pr.addPR("001", "HK", new java.sql.Date(2015, 7, 7), "-", new java.sql.Date(2015, 7, 7),"");
//        pr.addPRdetail("001", "001", "item_001", "ltr", new Float(3), "-");
//        pr.addPRdetail("001", "002", "item_002", "pcs", new Float(2), "-");
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    
    public String setPRNumber(String department_id) {
        DatabaseConnection db = new DatabaseConnection();
        String id = null;
        try {
            int num = 0;
            String query = "select count(pr_number) from hcdy_purchasereq where department_id='"+department_id+"'";
            Statement st = db.getConnection().createStatement();
            java.sql.ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                num = Integer.parseInt(rs.getString(1));
            }
            num++;
            
            SimpleDateFormat format = new SimpleDateFormat("YYYY/MM");
            Date date = new Date();
            switch (department_id) {
                case "POMEC":
                    id = "PR-"+num+"/"+format.format(date)+"/PM";
                    break;
                case "FO":
                    id = "PR-"+num+"/"+format.format(date)+"/FO";
                    break;
                case "HK":
                    id = "PR-"+num+"/"+format.format(date)+"/HK";
                    break;
                case "ACCT":
                    id = "PR-"+num+"/"+format.format(date)+"/AC";
                    break;
                case "S&M":
                    id = "PR-"+num+"/"+format.format(date)+"/SM";
                    break;
                case "A&G":
                    id = "PR-"+num+"/"+format.format(date)+"/AG";
                    break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
