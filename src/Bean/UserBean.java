/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

//import Controller.Controller;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    
    public String setUSERid(String dept_name) {       
        DatabaseConnection db = new DatabaseConnection();
        String id = null;
        try {     
            int num = 0;
            String query = "select count(dept_id) from `citradream_purchasing`.`hcdy_userdata` where dept_id='"+dept_name+"'";
            Statement statement = db.getConnection().createStatement();
            java.sql.ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                num = Integer.parseInt(rs.getString(1));
            }
            num++;
            switch (dept_name) {
                case "POMEC":
                    id = "HCDY_PM_"+num;
                    break;
                case "FO":
                    id = "HCDY_FO_"+num;
                    break;
                case "HK":
                    id = "HCDY_HK_"+num;
                    break;
                case "ACCT":
                    id = "HCDY_AC_"+num;
                    break;
                case "S&M":
                    id = "HCDY_SM_"+num;
                    break;
                case "A&G":
                    id = "HCDY_AG_"+num;
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    private static final String OUTPUT_STRING="C:\\filelogin.txt";
    public void dataLoginFile(String userData, String deptData){
 
        byte[] bytesname = userData.getBytes();
        byte[] bytedept = deptData.getBytes();
        Path filePath = Paths.get(OUTPUT_STRING);
        try{
            OutputStream out = new BufferedOutputStream(new FileOutputStream(OUTPUT_STRING),1024);
            try {
                out.write(bytesname);
                out.write(bytedept);
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(purchaseRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
