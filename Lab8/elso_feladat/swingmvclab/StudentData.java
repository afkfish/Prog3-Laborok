package swingmvclab;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/*
 * A hallgat�k adatait t�rol� oszt�ly.
 */
public class StudentData extends AbstractTableModel {

    /*
     * Ez a tagv�ltoz� t�rolja a hallgat�i adatokat.
     * NE M�DOS�TSD!
     */
    List<Student> students = new ArrayList<Student>();

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student tmp = students.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> tmp.getName();
            case 1 -> tmp.getNeptun();
            case 2 -> tmp.hasSignature();
            default -> tmp.getGrade();
        };
    }

    @Override
    public String getColumnName(int column) {
        String result = String.valueOf('A' + column);
        return switch (column) {
            case 0 -> "Nev";
            case 1 -> "Neptun";
            case 2 -> "Alairas";
            case 3 -> "Jegy";
            default -> result;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 2 -> Boolean.class;
            case 3 -> Integer.class;
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 1;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 2 -> students.get(rowIndex).setSignature((Boolean) aValue);
            case 3 -> students.get(rowIndex).setGrade((Integer) aValue);
        }
    }

    public void addStudent(String name, String neptun) {
        students.add(new Student(name, neptun, false, 0));
        fireTableRowsInserted(0, students.size());
    }
}
