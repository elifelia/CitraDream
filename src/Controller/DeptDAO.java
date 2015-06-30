/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Bean.PurchaseRequestBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Elifelia
 */
public class DeptDAO {
    public static ArrayList<PurchaseRequestBean> getAllData() throws SQLException {
        ArrayList<PurchaseRequestBean> result = new ArrayList<>();

        try {
            Class.forName(com.mysql.jdbc.Driver.class.getCanonicalName());
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HCDY_Purchasing", "root", "citradream");
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM purchaseRequest";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            PurchaseRequestBean pr = new PurchaseRequestBean();
            pr.setQtyRequest(resultSet.getString(query));
            pr.setQtyOnHand(resultSet.getString(query));
            pr.setUnit(resultSet.getString(query));
            pr.setDescription(resultSet.getString(query));
            pr.setProductRemarks(resultSet.getString(query));
            pr.setDate(resultSet.getDate(query));
            pr.setDeliveryDate(resultSet.getDate(query));
            pr.setDeptRequest(resultSet.getString(query));
            pr.setPRRemarks(resultSet.getString(query));
            pr.setPrNo(resultSet.getString(query));

            //TODO add all attributes
            result.add(pr);
        }
        return result;
    }
}
