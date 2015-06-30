/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.purchaseRequest;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elifelia
 */
public class citraDreamTableModel extends AbstractTableModel {

    private static final String[] HEADER = {"Qty Request", "Qty On Hand", "Unit", "Description", "Remarks"};
    private ArrayList<purchaseRequest> data;

    @Override
    public int getRowCount() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return data.size();
    }

    @Override
    public int getColumnCount() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getQtyRequest();
            case 1:
                return data.get(rowIndex).getQtyOnHand();
            case 2:
                return data.get(rowIndex).getUnit();
            case 3:
                return data.get(rowIndex).getDescription();
            case 4:
                return data.get(rowIndex).getRemarks();
            default:
                throw new IllegalArgumentException("arg");
        }

    }
    
    public String getColumnName(int column){
        return HEADER[column];
    }

    public void addRow(purchaseRequest pr) {
        data.add(pr);
        fireTableDataChanged();
    }

    public void removeRow(String qtyRequest, String qtyOnHand, String unit, String description) {
        for (int i = 0; i < data.size(); i++) {
            String qtyRequestData = data.get(i).getQtyRequest();
            String qtyOnHandData = data.get(i).getQtyOnHand();
            String unitData = data.get(i).getUnit();
            String descriptionData = data.get(i).getDescription();
            if (qtyRequest == qtyRequestData) {
                data.remove(i);
                break;
            }
        }
    }

    public citraDreamTableModel() {
        data = new ArrayList<>();
    }

    public void setData(ArrayList<purchaseRequest> allData) {
        data = allData;
        fireTableDataChanged();
    }

    public Vector<purchaseRequest> saveToDB() throws Exception {
        Vector<Vector<String>> prVector = new Vector<Vector<String>>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HCDY_Purchasing", "root", "citradream");
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            if (connection != null) {
                String query = "INSERT INTO purchaseRequest VALUES =?";
                
                
                try {
                    statement = (PreparedStatement) connection.prepareStatement("INSERT INTO purchaseRequest values(?)");
                    resultSet = statement.executeQuery();
                    
                    while (resultSet.next()) {                        
                        Vector<String> pr = new Vector<String>();
                        pr.add(resultSet.getString(1));
                        pr.add(resultSet.getString(2));
                        pr.add(resultSet.getString(3));
                        pr.add(resultSet.getString(4));
                        pr.add(resultSet.getString(5));
                        prVector.add(pr);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(resultSet!=null){
                    resultSet.close();
                }
            } catch (Exception e) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
            }
        }

        return null;
    }
}
