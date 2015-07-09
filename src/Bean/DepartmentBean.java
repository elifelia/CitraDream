/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

//import Controller.Controller;
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
            Logger.getLogger(DepartmentBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Data isn't complete/ Wrong format");
        }
        return department;
    }

    public DepartmentBean cariDepartment(String dept) {
        DepartmentBean deptb = new DepartmentBean();
//        ub = null;
        try {
            DatabaseConnection db = new DatabaseConnection();

            Statement statement = db.getConnection().createStatement();
            String query = "SELECT * FROM hcdy_department WHERE department_id like '%" + dept + "%'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                    deptb.setDeptID(resultSet.getString("department_id"));
                    deptb.setDeptName(resultSet.getString("department_name"));

            }

        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return deptb;
    }

    public Boolean updateDept(String DeptID, String DeptName) {
        Boolean department = false;
        try {
            DatabaseConnection db = new DatabaseConnection();
            Statement statement = db.getConnection().createStatement();
            String query = "UPDATE hcdy_department SET department_name = ('" + DeptName + "')"
                    + "WHERE department_id ='" + DeptID + "'";
            statement.executeUpdate(query);
            department = true;
        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return department;
    }

}
