import javax.swing.table.DefaultTableModel;

public class Table extends DefaultTableModel {
	
	private static final long serialVersionUID = 1L;

	public Table (Object fildNames[], int rows){
			super(fildNames, rows);
		}
	
		public boolean isCellEditable(int row, int col) {
			if (col == 0) {return true;}
			return false;
		}
		
		public Class<?> getColumnClass(int index){
			if (index == 0) return(Boolean.class);
				else if (index==1 || index==4) return(Integer.class);
			return(String.class);
			}
	}
