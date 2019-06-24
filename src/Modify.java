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
import javax.swing.JTextField;
import java.awt.Font;

public class Modify extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Table tableclass;
	private JTextField name;
	private JTextField task;
	private JTextField datum;
	private JTextField prior;
	
	


	public Modify(JFrame f, Table tablein) {
		
		super (f, "Céges feladatok listája", true);
		setTitle("C\u00E9ges feladatok m\u00F3dos\u00EDt\u00E1sa");
		tableclass = tablein;
	
		getContentPane().setBackground(Color.LIGHT_GRAY);	
		setBounds(100, 100, 650, 382);
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
		
		name = new JTextField();
		name.setBounds(479, 253, 145, 30);
		getContentPane().add(name);
		name.setColumns(10);
		
		task = new JTextField();
		task.setColumns(10);
		task.setBounds(127, 253, 144, 30);
		getContentPane().add(task);
		
		datum = new JTextField();
		datum.setColumns(10);
		datum.setBounds(271, 253, 159, 30);
		getContentPane().add(datum);
		
		prior = new JTextField();
		prior.setColumns(10);
		prior.setBounds(432, 253, 45, 30);
		getContentPane().add(prior);
		
		JButton mod = new JButton("M\u00F3dos\u00EDt");
		mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!BasicClass.filled(task) && !BasicClass.filled(datum) && !BasicClass.filled(prior)&& !BasicClass.filled(name)) BasicClass.showMD("Egyetlen módosító adatsincs beírva!",0);
						else if (BasicClass.filled(datum) && !BasicClass.goodDate(datum)) BasicClass.showMD("Az idõ mezõben hibás adat van!",0);
						else if (BasicClass.filled(prior) && !BasicClass.goodInt(prior)) BasicClass.showMD("A prioritás mezõben hibás adat van!",0);
						else {
							int piece=0, sign=0, x=0;
							for(x = 0; x < tableclass.getRowCount(); x++)
							if ((Boolean)tableclass.getValueAt(x,0)) {piece++; sign=x;}
							if (piece==0) BasicClass.showMD("Nincs kijelölve a módosítandó rekord!",0);
							if (piece>1) BasicClass.showMD("Több rekord van kijelölve!\nEgyszerre csak egy rekord módosítható!",0);
							if (piece==1) {
								if (BasicClass.filled(task)) tableclass.setValueAt(BasicClass.RF(task), sign, 2);
								if (BasicClass.filled(datum)) tableclass.setValueAt(BasicClass.RF(datum), sign, 3);
								if (BasicClass.filled(prior)) tableclass.setValueAt(BasicClass.RF(prior), sign, 4);
								if (BasicClass.filled(name)) tableclass.setValueAt(BasicClass.RF(name), sign, 5);
								BasicClass.showMD("A rekord módosítva!",1);
								BasicClass.DF(task); BasicClass.DF(datum); BasicClass.DF(prior); BasicClass.DF(name);
								tableclass.setValueAt(new Boolean(false), sign, 0);
							}
						}	
			}
		});
		mod.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mod.setBounds(10, 290, 117, 42);
		getContentPane().add(mod);
		@SuppressWarnings("unchecked")
		TableRowSorter<Table> trs =(TableRowSorter<Table>)table.getRowSorter();
		trs.setSortable(0, false);
	}
}
