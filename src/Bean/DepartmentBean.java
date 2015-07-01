/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

//import Controller.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Elifelia
 */
public class DepartmentBean {

    private String deptID;
    private String deptName;

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Boolean simpanDept(String DeptID, String DeptName) {
        Boolean department = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement = db.getConnection().createStatement();
            String query = "INSERT INTO hcdy_department VALUES ('" + DeptID + "','" + DeptName + "') ";
            statement.executeUpdate(query);
            department = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return department;
    }

    public String[] showDepartment() {
        DatabaseConnection db = new DatabaseConnection();
        String[] list = null;
        try {
            int i = 1;
            Statement statement = db.getConnection().createStatement();
            String query = "select department_id from hcdy_department";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString(1);
                list[i] = name;
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        DepartmentBean wow = new DepartmentBean();
        for (int i = 1; i <= 6; i++) {
            System.out.println(wow.showDepartment()[i]);
        }
    }
}
