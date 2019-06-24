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
import java.awt.Font;
import javax.swing.JLabel;

public class Delete extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Table tableclass;

	public Delete(JFrame f, Table tablein) {
		super (f, "Céges feladatok listája", true);
		setTitle("C\u00E9ges feladatok t\u00F6rl\u00E9se");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		tableclass = tablein;
		
		setBounds(100, 100, 650, 345);
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
		
		JButton delete = new JButton("T\u00F6rl\u00E9s");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, x=0;
				for(x = 0; x < tableclass.getRowCount(); x++)
				if ((Boolean)tableclass.getValueAt(x,0)) db++;
				if (db==0) BasicClass.showMD("Nincs kijelölve a törlendõ rekord!",0);
				for (int i=0; i<tableclass.getRowCount(); i++)
				if ((Boolean)tableclass.getValueAt(i,0)) {tableclass.removeRow(i); i--;}
				BasicClass.showMD("Rekord(ok) törölve!",1);	
			}
		});
		delete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		delete.setBounds(484, 261, 122, 38);
		getContentPane().add(delete);
		
		JLabel lbltobbbRekordIs = new JLabel("//Tobbb rekord is torolheto egyszerre");
		lbltobbbRekordIs.setBounds(20, 261, 236, 14);
		getContentPane().add(lbltobbbRekordIs);
		@SuppressWarnings("unchecked")
		TableRowSorter<Table> trs =(TableRowSorter<Table>)table.getRowSorter();
		trs.setSortable(0, false);
	}
}
