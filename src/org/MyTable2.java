package org;

import javax.swing.table.AbstractTableModel;

public class MyTable2 extends AbstractTableModel{

	final String[] columnNames = { "num.0", "num.1", "num.2", "num.3", "num.4",
			"num.5", "num.6", "num.7", };
	Object[][] data2 = new Object[8][8];
	public MyTable2(GUI gui) {
		// TODO 自动生成的构造函数存根
		data2 = gui.getData2();
	}

	 
	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.data2.length;
	}

	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		return this.data2[rowIndex][columnIndex];
	}
	public void setValueAt(Object value,int rowIndex,int columnIndex){
		this.data2[rowIndex][columnIndex]=value;
		this.fireTableDataChanged();
	}
	public void datachaged(GUI gui){
		this.data2=gui.getData2();
		this.fireTableDataChanged();
	}
	public String getColumnName(int colIndex){
		return this.columnNames[colIndex];
	}

}
