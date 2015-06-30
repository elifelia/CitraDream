/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.PurchaseRequestBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elifelia
 */
public class purchaseRequestDAO {

    public static int saveToDb(List<PurchaseRequestBean> data) throws SQLException {
        try {
            Class.forName(com.mysql.jdbc.Driver.class.getCanonicalName());
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HCDY_Purchasing", "root", "citradream");
        int rows = data.size();
        StringBuilder queryBuilder = new StringBuilder("insert into hcdy_purchasing(description, unit, qtyRequest, qtyOnHand, remarks, pr, date, deliveryDate, deptRequest, LeaderRemarks) ");
        for (int i = 0; i < rows; i++) {
            queryBuilder.append("(?,?,?,?,?,?,?,?,?,?)");
            if (i < rows - 1) {
                queryBuilder.append(",");
            }
        }
        PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
        for (int i = 0; i < rows; i++) {
            PurchaseRequestBean p = data.get(i);
            int j = 1;
            statement.setString(i * j + j++, p.getDescription());
            statement.setString(i * j + j++, p.getUnit());
            statement.setString(i * j + j++, p.getQtyRequest());
            statement.setString(i * j + j++, p.getQtyOnHand());
            statement.setString(i * j + j++, p.getProductRemarks());
            statement.setString(i * j + j++, p.getPrNo());
            statement.setDate(i * j + j++, (Date) p.getDate());
            statement.setDate(i * j + j++, (Date) p.getDeliveryDate());
            statement.setString(i * j + j++, p.getDeptRequest());
            statement.setString(i * j + j++, p.getPRRemarks());
        }

        return rows;
    }

    public static int searchByDescription(List<PurchaseRequestBean> data, String desc) {
        for (int i = 0; i < data.size(); i++) {
            PurchaseRequestBean p = data.get(i);
            if (p.getDescription().equals(desc)) {
                return i;
            }
        }
        return -1;
    }

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
