package swingmvclab;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class StudentTableCellRenderer implements TableCellRenderer {

	private final TableCellRenderer renderer;

	public StudentTableCellRenderer(TableCellRenderer defRenderer) {
		this.renderer = defRenderer;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		row = table.getRowSorter().convertRowIndexToModel(row);
		Component component = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if ((Boolean) table.getModel().getValueAt(row, 2) && (Integer) table.getModel().getValueAt(row, 3) > 2) {
			component.setBackground(Color.GREEN);
		}
		else component.setBackground(Color.RED);

		return component;
	}
}
