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
import javax.swing.JOptionPane;

/**
 *
 * @author Elifelia
 */
public class SupplierBean {

    private String supID;
    private String compName;
    private String compTitle;
    private String contactName;
    private String contactTitle;
    private String city;
    private String address;
    private String postCode;
    private String country;
    private String phone;
    private String fax;
    private String bankName;
    private String bankAccount;
    private String bankHolder;
    private Date lastDelivery;
    private String comment;
    private String accountNo;

    public String getSupID() {
        return supID;
    }

    public void setSupID(String supID) {
        this.supID = supID;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompTitle() {
        return compTitle;
    }

    public void setCompTitle(String compTitle) {
        this.compTitle = compTitle;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankHolder() {
        return bankHolder;
    }

    public void setBankHolder(String bankHolder) {
        this.bankHolder = bankHolder;
    }

    public Date getLastDelivery() {
        return lastDelivery;
    }

    public void setLastDelivery(Date lastDelivery) {
        this.lastDelivery = lastDelivery;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    //id, company name, company title, contact name, 
    //contact title, city, address, postcode, country, telephone, 
    //faximile, bank name, bank account no, 
    //bank account holder, last delivery, comment

    public Boolean simpanSupplier(String supID, String compName, String compTitle, 
            String contactName, String contactTitle, String city, String address, 
            String postCode, String country, String phone, String fax, String bankName, String accountNo,
            String bankHolder, Date lastDelivery, String comment) {
        Boolean sup = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "INSERT INTO `citradream_purchasing`.`hcdy_suppliers`"
                    + " (`suppliers_id`, `company_name`, `company_title`, "
                    + "`contact_name`, `contact_title`, `city`, `address`,"
                    + " `postcode`, `country`, `telephone`, `faximile`,"
                    + " `bank_name`, `bank_acctNo`, `bank_acctHolder`,"
                    + " `last_delivery`, `comment`) VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, supID);
            statement.setString(2, compName);
            statement.setString(3, compTitle);
            statement.setString(4, contactName);
            statement.setString(5, contactTitle);
            statement.setString(6, city);
            statement.setString(7, address);
            statement.setString(8, postCode);
            statement.setString(9, country);
            statement.setString(10, phone);
            statement.setString(11, fax);
            statement.setString(12, bankName);
            statement.setString(13, accountNo);
            statement.setString(14, bankHolder);
            statement.setDate(15, (java.sql.Date) lastDelivery);
            statement.setString(16, comment);
            statement.executeUpdate();
            sup = true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierBean.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return sup;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public SupplierBean cariSup(String sup) {
        SupplierBean sb = new SupplierBean();
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_suppliers WHERE company_name like '%" + sup + "%'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                sb.setSupID(resultSet.getString("suppliers_id"));
                sb.setCompName(resultSet.getString("company_name"));
                sb.setCompTitle(resultSet.getString("company_title"));
                sb.setContactName(resultSet.getString("contact_name"));
                sb.setContactTitle(resultSet.getString("contact_title"));
                sb.setCity(resultSet.getString("city"));
                sb.setAddress(resultSet.getString("address"));
                sb.setPostCode(resultSet.getString("postcode"));
                sb.setCountry(resultSet.getString("country"));
                sb.setPhone(resultSet.getString("telephone"));
                sb.setFax(resultSet.getString("faximile"));
                sb.setBankName(resultSet.getString("bank_name"));
                sb.setBankAccount(resultSet.getString("bank_acctNo"));
                sb.setBankHolder(resultSet.getString("bank_acctHolder"));
                sb.setComment(resultSet.getString("comment"));
                sb.setLastDelivery(resultSet.getDate("last_delivery"));

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return sb;
    }
}
