/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

//import Controller.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.servlet.mvc.Controller;

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
//        ub = null;
        try {
            DatabaseConnection db = new DatabaseConnection();
            
            Statement statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_userdata WHERE user_id ='" + userID + "' AND user_pass ='" + pass + "' ";
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {

                if (null != resultSet) {
                    ub.setUserID(resultSet.getString("user_id"));
                    ub.setUser_name(resultSet.getString("user_name"));
                    ub.setPass(resultSet.getString("user_pass"));
                    ub.setDept_id(resultSet.getString("dept_id"));
                    ub.setPurchaseReq(resultSet.getBoolean("bool_pfForm"));
                    ub.setPurchaseReqView(resultSet.getBoolean("bool_pfView"));
                    ub.setPurchaseOrd(resultSet.getBoolean("bool_poForm"));
                    ub.setPurchaseOrdView(resultSet.getBoolean("bool_poView"));
                    ub.setReceiving(resultSet.getBoolean(""));
                    ub.setReceivingView(resultSet.getBoolean(""));
                    ub.setMasterData(masterData);
                    
                }

            }

        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return ub;
    }
    
    public Boolean login(UserBean ub){
        if (ub.getUserID()!=null) {
            return true;
        }else{
            return false;
        }
        
    }
    
    public Boolean simpanUser(String userID, String user, String pass, String deptID, Boolean prf, Boolean prv, Boolean pof, 
    Boolean pov, Boolean poi, Boolean rrf, Boolean rrv, Boolean master ){
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
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    return simpanStatus;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
//        this.userID = userID;
        
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
}
