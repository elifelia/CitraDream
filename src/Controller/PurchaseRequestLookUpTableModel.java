/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.ItemBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elifelia
 */
public class PurchaseRequestLookUpTableModel extends AbstractTableModel {

    private static final String[] HEADER = {"Item ID", "Item Name"};
    private ArrayList<ItemBean> data;
    Connection connection;
    ResultSet resultSet;
    Statement statement;

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
                return data.get(rowIndex).getItemID();
            case 1:
                return data.get(rowIndex).getItemName();
            
            default:
                throw new IllegalArgumentException("arg");
        }

    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public void addRow(ItemBean itemID) {
        data.add(itemID);
        fireTableDataChanged();
    }

//    public void removeRow(String description) {
//        int index = purchaseRequestDAO.searchByDescription(data, description);
//        if (index != -1) {
//            data.remove(index);
//        }
//        fireTableDataChanged();
//    }

    public PurchaseRequestLookUpTableModel() {
        data = new ArrayList<>();
    }

    public void setData(ArrayList<ItemBean> allData) {
        data = allData;
        fireTableDataChanged();
    }
    
    }
