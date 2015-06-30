package Bean;


//import Controller.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private String qtyRequest;
    private String qtyOnHand;
    private String unit;
    private String description;
    private String productRemarks;
    private String PRRemarks;
    private String prNo;
    private String deptRequest;
    private Date date;
    private Date deliveryDate;
    Connection connection;
    ResultSet resultSet;
    Statement statement;

    public String getQtyRequest() {
        return qtyRequest;
    }

    public void setQtyRequest(String qtyRequest) {
        this.qtyRequest = qtyRequest;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrNo() {
        return prNo;
    }

    public void setPrNo(String prNo) {
        this.prNo = prNo;
    }

    public String getDeptRequest() {
        return deptRequest;
    }

    public void setDeptRequest(String deptRequest) {
        this.deptRequest = deptRequest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getProductRemarks() {
        return productRemarks;
    }

    public void setProductRemarks(String productRemarks) {
        this.productRemarks = productRemarks;
    }

    public String getPRRemarks() {
        return PRRemarks;
    }

    public void setPRRemarks(String PRRemarks) {
        this.PRRemarks = PRRemarks;
    }
    
    public Boolean searchProduct(String itemName){
        Boolean item= false;
        try {
            DatabaseConnection db  = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_itemmaster WHERE item_name like %'"+itemName+"'% ";
            statement.executeUpdate(query);
            item = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return item;
    }
    
    public Boolean searchPR(String prNo){
        Boolean pr = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_purchaseRequest WHERE pr_no like %'"+prNo+"'% ";
            statement.executeUpdate(query);
            pr = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return pr;
    }

}
