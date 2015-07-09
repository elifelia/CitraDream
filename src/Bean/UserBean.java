/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

//import Controller.Controller;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Elifelia
 */
public class UserBean {

    private String userID;
    private String pass;
    private String user_name;
    private String dept_id;
    private Boolean PurchaseReq;
    private Boolean PurchaseReqView;
    private Boolean PurchaseOrd;
    private Boolean PurchaseOrdView;
    private Boolean Receiving;
    private Boolean ReceivingView;
    private Boolean PurchaseOrdInactive;
    private Boolean masterData;

//    
    public UserBean cariUser(String userID, String pass) {
        UserBean ub = new UserBean();
        DatabaseConnection db = new DatabaseConnection();
        try {

            Statement statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_userdata WHERE user_name ='" + userID + "' AND user_pass ='" + pass + "' ";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ub.setUserID(resultSet.getString("user_id"));
                ub.setUser_name(resultSet.getString("user_name"));
                ub.setPass(resultSet.getString("user_pass"));
                ub.setDept_id(resultSet.getString("dept_id"));
                ub.setPurchaseReq(resultSet.getBoolean("bool_pfForm"));
                ub.setPurchaseReqView(resultSet.getBoolean("bool_pfView"));
                ub.setPurchaseOrd(resultSet.getBoolean("bool_poForm"));
                ub.setPurchaseOrdView(resultSet.getBoolean("bool_poView"));
                ub.setPurchaseOrdInactive(resultSet.getBoolean("bool_poInactive"));
                ub.setReceiving(resultSet.getBoolean("bool_rrForm"));
                ub.setReceivingView(resultSet.getBoolean("bool_rrView"));
                ub.setMasterData(resultSet.getBoolean("bool_masterData"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return ub;
    }

    public Boolean login(UserBean ub) {
        return ub.getUserID() != null;

    }
    public UserBean cariUsername(String username) {
        UserBean ub = new UserBean();
        DatabaseConnection db = new DatabaseConnection();
        try {

            Statement statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_userdata WHERE user_name like '%" + username + "%'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ub.setUserID(resultSet.getString("user_id"));
                ub.setUser_name(resultSet.getString("user_name"));
                ub.setPass(resultSet.getString("user_pass"));
                ub.setDept_id(resultSet.getString("dept_id"));
                ub.setPurchaseReq(resultSet.getBoolean("bool_pfForm"));
                ub.setPurchaseReqView(resultSet.getBoolean("bool_pfView"));
                ub.setPurchaseOrd(resultSet.getBoolean("bool_poForm"));
                ub.setPurchaseOrdView(resultSet.getBoolean("bool_poView"));
                ub.setPurchaseOrdInactive(resultSet.getBoolean("bool_poInactive"));
                ub.setReceiving(resultSet.getBoolean("bool_rrForm"));
                ub.setReceivingView(resultSet.getBoolean("bool_rrView"));
                ub.setMasterData(resultSet.getBoolean("bool_masterData"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return ub;
    }

    public Boolean simpanUser(String userID, String user, String pass, String deptID, Boolean prf, Boolean prv, Boolean pof,
            Boolean pov, Boolean poi, Boolean rrf, Boolean rrv, Boolean master) {
        boolean simpanStatus = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "INSERT INTO hcdy_userdata VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, userID);
            statement.setString(2, user);
            statement.setString(3, pass);
            statement.setString(4, deptID);
            statement.setBoolean(5, prf);
            statement.setBoolean(6, prv);
            statement.setBoolean(7, pof);
            statement.setBoolean(8, pov);
            statement.setBoolean(9, poi);
            statement.setBoolean(10, rrf);
            statement.setBoolean(11, rrv);
            statement.setBoolean(12, poi);
            statement.executeUpdate();
            simpanStatus = true;

        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return simpanStatus;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getPurchaseReq() {
        return PurchaseReq;
    }

    public void setPurchaseReq(Boolean PurchaseReq) {
        this.PurchaseReq = PurchaseReq;
    }

    public Boolean getPurchaseReqView() {
        return PurchaseReqView;
    }

    public void setPurchaseReqView(Boolean PurchaseReqView) {
        this.PurchaseReqView = PurchaseReqView;
    }

    public Boolean getPurchaseOrd() {
        return PurchaseOrd;
    }

    public void setPurchaseOrd(Boolean PurchaseOrd) {
        this.PurchaseOrd = PurchaseOrd;
    }

    public Boolean getPurchaseOrdView() {
        return PurchaseOrdView;
    }

    public void setPurchaseOrdView(Boolean PurchaseOrdView) {
        this.PurchaseOrdView = PurchaseOrdView;
    }

    public Boolean getReceiving() {
        return Receiving;
    }

    public void setReceiving(Boolean Receiving) {
        this.Receiving = Receiving;
    }

    public Boolean getReceivingView() {
        return ReceivingView;
    }

    public void setReceivingView(Boolean ReceivingView) {
        this.ReceivingView = ReceivingView;
    }

    public Boolean getPurchaseOrdInactive() {
        return PurchaseOrdInactive;
    }

    public void setPurchaseOrdInactive(Boolean PurchaseOrdInactive) {
        this.PurchaseOrdInactive = PurchaseOrdInactive;
    }

    public Boolean getMasterData() {
        return masterData;
    }

    public void setMasterData(Boolean masterData) {
        this.masterData = masterData;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String setUSERid(String dept_name) {
        DatabaseConnection db = new DatabaseConnection();
        String id = null;
        try {
            int num = 0;
            String query = "select count(dept_id) from `citradream_purchasing`.`hcdy_userdata` where dept_id='" + dept_name + "'";
            Statement statement = db.getConnection().createStatement();
            java.sql.ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                num = Integer.parseInt(rs.getString(1));
            }
            num++;
            switch (dept_name) {
                case "POMEC":
                    id = "HCDY_PM_" + num;
                    break;
                case "FO":
                    id = "HCDY_FO_" + num;
                    break;
                case "HK":
                    id = "HCDY_HK_" + num;
                    break;
                case "ACCT":
                    id = "HCDY_AC_" + num;
                    break;
                case "S&M":
                    id = "HCDY_SM_" + num;
                    break;
                case "A&G":
                    id = "HCDY_AG_" + num;
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void dataLoginFile(String userData, String deptData) {
        try{
            FileWriter outputStream = new FileWriter(System.getProperty("user.dir")+"\'filelogin.txt");
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
        }
    }
    
    public Boolean updateUser(String userID, String user, String pass, 
            String deptID, Boolean prf, Boolean prv, Boolean pof,
            Boolean pov, Boolean poi, Boolean rrf, Boolean rrv, Boolean master) {
        boolean simpanStatus = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "UPDATE hcdy_userdata SET user_name=?, user_pass=?, dept_id=?, bool_pfForm=?,"
                    + "bool_pfView=?, bool_poForm=?, bool_poView=?, bool_poInactive=?,"
                    + "bool_rrView=?, bool_rrForm=?, bool_masterData=? WHERE user_id=?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            
            statement.setString(1, user);
            statement.setString(2, pass);
            statement.setString(3, deptID);
            statement.setBoolean(4, prf);
            statement.setBoolean(5, prv);
            statement.setBoolean(6, pof);
            statement.setBoolean(7, pov);
            statement.setBoolean(8, poi);
            statement.setBoolean(9, rrv);
            statement.setBoolean(10, rrf);
            statement.setBoolean(11, master);
            statement.setString(12, userID);
            
            statement.executeUpdate();
            simpanStatus = true;

        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return simpanStatus;
    }
    
}
