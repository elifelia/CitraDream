/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.PurchaseRequestBean;
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
public class PurchaseRequestTableModel extends AbstractTableModel {

    private static final String[] HEADER = {"Description", "Units","Qty Request", "Qty On Hand","Remarks"};
    private ArrayList<PurchaseRequestBean> data;

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
                return data.get(rowIndex).getProductRemarks();
            default:
                throw new IllegalArgumentException("arg");
        }

    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public void addRow(PurchaseRequestBean pr) {
        data.add(pr);
        fireTableDataChanged();
    }

    public void removeRow(String description) {
        int index = purchaseRequestDAO.searchByDescription(data, description);
        if (index != -1) {
            data.remove(index);
        }
        fireTableDataChanged();
    }

    public PurchaseRequestTableModel() {
        data = new ArrayList<>();
    }

    public void setData(ArrayList<PurchaseRequestBean> allData) {
        data = allData;
        fireTableDataChanged();
    }

   }
