package biodata;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;



public class MyTableModel extends AbstractTableModel{
	private String[] columnNames = {" Nama ", " Jenis Kelamin ", " Nomor Hp ", " Alamat "};
	private ArrayList<Member> data = new ArrayList<Member>();
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public int getRowCount() {
		return data.size();
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Object getValueAt(int row, int col) {
		Member rowItem = data.get(row);
		String value = "";
		switch(col)
		{
		case 0:
			value = rowItem.getNama();
			break;
		case 1:
			value = rowItem.getJenisKelamin();
			break;
		case 2:
			value = rowItem.getNomor();
			break;
		case 3:
			value = rowItem.getAlamat();
			break;
		}
		return value;
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void add(Member value) {
		data.add(value);
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}

	public void setValueAt(String value, int row, int col) {
		Member rowItem = data.get(row);
		switch(col)
		{
		case 0:
			rowItem.setNama(value);
			break;
		case 1:
			rowItem.setJenisKelamin(value);
			break;
		case 2:
			rowItem.setNomor(value);
			break;
		case 3:
			rowItem.setAlamat(value);
			break;
		}
		
		fireTableRowsUpdated(data.size()-1, data.size()-1);
	}

	public void removeRow(int row) {
		data.remove(row);
		fireTableRowsInserted(row, row);
		
	}

	
}
