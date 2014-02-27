package org;

import javax.swing.table.AbstractTableModel;

public class MyTable extends AbstractTableModel {
	final String[] columnNames = { "num.0", "num.1", "num.2", "num.3", "num.4",
			"num.5", "num.6", "num.7", };
	Object[][] data = new Object[8][8];

	public MyTable(GUI gui) {
		// TODO �Զ����ɵĹ��캯�����
		data = gui.getData();
	}

	@Override
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return this.data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		return this.columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO �Զ����ɵķ������
		return this.data[rowIndex][columnIndex];
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		this.data[rowIndex][columnIndex] = value;
		this.fireTableDataChanged();
	}

	public void datachaged(GUI gui) {
		this.data = gui.getData();
		this.fireTableDataChanged();
	}

	public String getColumnName(int colIndex) {
		return this.columnNames[colIndex];
	}

}
