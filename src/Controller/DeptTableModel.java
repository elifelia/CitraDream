/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.DepartmentBean;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elifelia
 */
public class DeptTableModel extends AbstractTableModel {

    public static final String[] HEADER = {"Dept ID", "Dept Name"};
    private ArrayList<DepartmentBean> data;

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
                return data.get(rowIndex).getDeptID();
            case 1:
                return data.get(rowIndex).getDeptName();

            default:
                throw new IllegalArgumentException("arg");
        }
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public DeptTableModel() {
        data = new ArrayList<>();
    }
    
    public void setData(ArrayList<DepartmentBean> allData) {
        data = allData;
        fireTableDataChanged();
    }

}
