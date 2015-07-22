package Bean;

//import Controller.Controller;
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
    private Boolean isFinal;
    private Boolean isEmpty;
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
            String remarks_pr, Date date, boolean isFinal, boolean isEmpty) {
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String querypr = "INSERT INTO hcdy_purchasereq VALUES (?,?,?,?,?,?,?) ";
            PreparedStatement st = db.getConnection().prepareStatement(querypr);
            st.setString(1, pr_number);
            st.setString(2, dept_id);
            st.setDate(3, (java.sql.Date) req_date);
            st.setString(4, remarks_pr);
            st.setDate(5, (java.sql.Date) date);
            st.setBoolean(6, isFinal);
            st.setBoolean(7, isEmpty);
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
                prb.setIsFinal(rs.getBoolean("isFinal"));
                prb.setReq_date(rs.getDate("Request_date"));
                prb.setRemarks_pr(rs.getString("remarks_PR"));
                prb.setIsEmpty(rs.getBoolean("isEmpty"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
        return prb;
    }
    
    public Boolean updatePR(String pr_number, String dept_id, Date req_date,
            String remarks_pr, Date date, Boolean isFinal, Boolean isEmpty) {
        Boolean pr = false;
        try {
            String sd = "UPDATE hcdy_purchasereq "
                    + "SET Request_date=?,"
                    + "remarks_pr=?,"
                    + "date=?,"
                    + "isFinal=?,"
                    + "isEmpty=?"
                    + "WHERE PR_Number=?";
            
            DatabaseConnection db = new DatabaseConnection();
            PreparedStatement statement = db.getConnection().prepareStatement(sd);
            statement.setDate(1, (java.sql.Date) req_date);
            statement.setString(2, remarks_pr);
            statement.setDate(3, (java.sql.Date)date);
            statement.setBoolean(4, isFinal);
            statement.setBoolean(5, isEmpty);
            statement.setString(6, pr_number);
            statement.executeUpdate();
            pr = true;
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRequestBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return pr;
    }

    public static void main(String[] args) {
//        PurchaseRequestBean pr = new PurchaseRequestBean();
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
//        
//        java.sql.Date date2 = new java.sql.Date(2015,07,11);
//        System.out.println(date.toString());
//        System.out.println(format.format(date));
//        System.out.println(date2.toString());
        
        java.util.Date utilDate = new java.util.Date();
        System.out.println(utilDate);
        java.sql.Date sql = new java.sql.Date(utilDate.getTime());
        System.out.println(sql);
        
    }
    public Boolean getIsFinal() {
        return isFinal;
    }
    
    public void setIsFinal(Boolean isFinal) {
        this.isFinal = isFinal;
    }
    
    public String setPRNumber(String department_id) {
        DatabaseConnection db = new DatabaseConnection();
        String id = null;
        try {
            int num = 0;
            String query = "select count(pr_number) from hcdy_purchasereq where department_id='" + department_id + "'";
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
                    id = "PR-" + num + "/" + format.format(date) + "/PM";
                    break;
                case "FO":
                    id = "PR-" + num + "/" + format.format(date) + "/FO";
                    break;
                case "HK":
                    id = "PR-" + num + "/" + format.format(date) + "/HK";
                    break;
                case "ACCT":
                    id = "PR-" + num + "/" + format.format(date) + "/AC";
                    break;
                case "S&M":
                    id = "PR-" + num + "/" + format.format(date) + "/SM";
                    break;
                case "A&G":
                    id = "PR-" + num + "/" + format.format(date) + "/AG";
                    break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public String getPRNumber(String department_id) {
        DatabaseConnection db = new DatabaseConnection();
        String id = null;
        try {
            int num = 0;
            String query = "select count(pr_number) from hcdy_purchasereq where department_id='" + department_id + "'";
            Statement st = db.getConnection().createStatement();
            java.sql.ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                num = Integer.parseInt(rs.getString(1));
            }
            
            SimpleDateFormat format = new SimpleDateFormat("YYYY/MM");
            Date date = new Date();
            switch (department_id) {
                case "POMEC":
                    id = "PR-" + num + "/" + format.format(date) + "/PM";
                    break;
                case "FO":
                    id = "PR-" + num + "/" + format.format(date) + "/FO";
                    break;
                case "HK":
                    id = "PR-" + num + "/" + format.format(date) + "/HK";
                    break;
                case "ACCT":
                    id = "PR-" + num + "/" + format.format(date) + "/AC";
                    break;
                case "S&M":
                    id = "PR-" + num + "/" + format.format(date) + "/SM";
                    break;
                case "A&G":
                    id = "PR-" + num + "/" + format.format(date) + "/AG";
                    break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public Boolean getIsEmpty() {
        return isEmpty;
    }
    
    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    
    public void SetisEmpty(String prNumber) {
//        Boolean empty = true;
        int num = 0;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String querydet = "SELECT COUNT(item_id) FROM hcdy_purchasereqdetail WHERE PR_Number = '" + prNumber + "'";
            Statement st = connect.createStatement();
            
            ResultSet rs = st.executeQuery(querydet);
            while (rs.next()) {                
                num = Integer.parseInt(rs.getString(1));
            }
            if (num == 0) {
                String query = "UPDATE hcdy_purchasereq SET isEmpty = '?' WHERE PR_Number ='" + prNumber + "'";
                
                Connection connection = db.getConnection();
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setBoolean(1, true);
                pst.executeUpdate();
//                empty = true;
            } else {
                String query = "UPDATE hcdy_purchasereq SET isEmpty = '?' WHERE PR_Number ='" + prNumber + "'";
                
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
    
    public String isEmpty(String dept_id) {
        String noPR = null;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String querydet = "SELECT PR_Number FROM hcdy_purchasereq WHERE "
                    + "isEmpty = 1 AND department_id ='" + dept_id + "'";
            Statement st = connect.createStatement();
            
            ResultSet rs = st.executeQuery(querydet);
            while (rs.next()) {                
                noPR = rs.getString("PR_Number");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return noPR;
    }
    public boolean setFinalTrue(String PR_Number){
        boolean success = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();
            String querydet = "UPDATE hcdy_purchasereq SET isFinal =? WHERE PR_Number =?";
            PreparedStatement pst = connect.prepareStatement(querydet);
            pst.setBoolean(1, true);
            pst.setString(2, PR_Number);
            pst.executeUpdate();
            success = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
}
