import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class List extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private Table tableclass;

	public List(JFrame f, Table tablein) {
		super (f, "Céges feladatok listája", true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		tableclass = tablein;
		
		setBounds(100, 100, 650, 300);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Bez\u00E1r");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnNewButton.setBounds(535, 11, 89, 23);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 614, 211);
		getContentPane().add(scrollPane);
		
		table = new JTable(tableclass);
		scrollPane.setViewportView(table);
	
		
		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i==0 || i==1 || i==4) tc.setPreferredWidth(30);
				else {tc.setPreferredWidth(130);}
		}
		
		table.setAutoCreateRowSorter(true);
		@SuppressWarnings("unchecked")
		TableRowSorter<Table> trs =(TableRowSorter<Table>)table.getRowSorter();
		trs.setSortable(0, false);
	}
}
