package swingmvclab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

/*
 * A megjelenítendő ablakunk osztálya.
 */
public class StudentFrame extends JFrame {
    
    /*
     * Ebben az objektumban vannak a hallgatói adatok.
     * A program indulás után betölti az adatokat fájlból, bezáráskor pedig kimenti oda.
     * 
     * NE MÓDOSÍTSD!
     */
    private StudentData data;
    private JTextField nameField, neptunField;
    
    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különböző komponenseket
     * (táblázat, beviteli mező, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JTable jTable = new JTable(data);
        jTable.setFillsViewportHeight(true);
        jTable.setRowSorter(new TableRowSorter<>(data));

        for (int i = 0; i < jTable.getColumnCount(); i++) {
            Class<?> columnClass = jTable.getColumnClass(i);
            TableCellRenderer tableCellRenderer = jTable.getDefaultRenderer(columnClass);

            jTable.setDefaultRenderer(columnClass, new StudentTableCellRenderer(tableCellRenderer));
        }

        JScrollPane scrollPane = new JScrollPane(jTable);

        JPanel southPanel = new JPanel();

        southPanel.add(new JLabel("Nev:"));
        nameField = new JTextField(20);
        southPanel.add(nameField);

        southPanel.add(new JLabel("Neptun:"));
        neptunField = new JTextField(6);
        southPanel.add(neptunField);

        JButton submit = new JButton("Felvesz");
        submit.addActionListener(actionEvent -> data.addStudent(nameField.getText(), neptunField.getText()));
        southPanel.add(submit);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /*
     * Az ablak konstruktora.
     * 
     * NE MÓDOSÍTSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgat�i nyilv�ntart�s");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Induláskor betöltjük az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bezáráskor mentjük az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Felépítjük az ablakot
        setMinimumSize(new Dimension(550, 200));
        initComponents();
    }

    /*
     * A program belépési pontja.
     * 
     * NE MÓDOSÍTSD!
     */
    public static void main(String[] args) {
        // Megjelenítjük az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }
}
