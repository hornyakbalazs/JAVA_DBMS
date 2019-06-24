import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.util.Date;
import java.awt.Font;

public class Program extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fname;
	private JTextField fnum;
	
	private String source="Válasszon!";
	private File fin;
	private String mes = "Program üzenet";	
	private Table tableclass;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	private JTextField writefname;
	private String des="Válasszon!";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 332);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bload = new JButton("Bet\u00F6lt\u00E9s");
		bload.setFont(new Font("Tahoma", Font.PLAIN, 13));
		bload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (source.equals("Válasszon!"))
					JOptionPane.showMessageDialog(null, "Elõször válassza ki a Forrás-t!", mes, 0);
				
				if (source.equals("Helyi .csv fájl")) {
					FileDialog fd = new FileDialog(new Frame(), " ", FileDialog.LOAD);
					fd.setFile("*.csv");
					fd.setVisible(true);
					if (fd.getFile() != null) {
						fin = new File(fd.getDirectory(), fd.getFile());
						String infname = fd.getFile();
						fname.setText(infname);
						FileManager.CsvReader(fin, tableclass);
					}
				} 
				
				if (source.equals("Helyi .xml fájl")) {
					FileDialog fd = new FileDialog(new Frame(), " ", FileDialog.LOAD);
					fd.setFile("*.xml");
					fd.setVisible(true);
					if (fd.getFile() != null) {
						fin = new File(fd.getDirectory(), fd.getFile());
						String infname = fd.getFile();
						fname.setText(infname);
						FileManager.XmlReader(fin, tableclass);
					}
				}
				
				fnum.setText(""+tableclass.getRowCount());
			}
		});
		bload.setBounds(313, 23, 89, 23);
		contentPane.add(bload);
		
		JButton blist = new JButton("Lista");
		blist.setFont(new Font("Tahoma", Font.PLAIN, 20));
		blist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List l = new List(Program.this, tableclass);
				l.setVisible(true);
			}
		});
		blist.setBounds(10, 95, 150, 33);
		contentPane.add(blist);
		
		JButton bexit = new JButton("Bez\u00E1r");
		bexit.setBackground(new Color(204, 51, 51));
		bexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bexit.setBounds(451, 11, 80, 23);
		contentPane.add(bexit);
		
		JLabel lblNewLabel = new JLabel("Forr\u00E1s");
		lblNewLabel.setBounds(10, 27, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAdatokSzma = new JLabel("Adatok sz\u00E1ma");
		lblAdatokSzma.setBounds(10, 54, 89, 14);
		contentPane.add(lblAdatokSzma);
		
		String element[] = {"Válasszon!","Helyi .xml fájl","Helyi .csv fájl"};
		
		JComboBox<String> jcbf = new JComboBox<String>();
		for (String s: element) jcbf.addItem(s);
		jcbf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				source = (String)jcbf.getSelectedItem();
				fname.setText(source);
				fnum.setText("0");

			}
		});
		jcbf.setBounds(54, 24, 106, 20);
		for (String s: element) jcbf.addItem(s);
		contentPane.add(jcbf);
		
		fname = new JTextField();
		fname.setBounds(176, 24, 121, 20);
		contentPane.add(fname);
		fname.setColumns(10);
		
		fnum = new JTextField();
		fnum.setText("0");
		fnum.setHorizontalAlignment(SwingConstants.RIGHT);
		fnum.setColumns(10);
		fnum.setBounds(93, 51, 46, 20);
		contentPane.add(fnum);
		
		JButton newtask = new JButton("\u00DAj feladat kioszt\u00E1sa");
		newtask.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newtask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewData en = new NewData(Program.this, tableclass);
				en.setVisible(true);
				int exit = en.Exit();
				if (exit==1) {
					Ot newOt = en.getOt();
					Date d = newOt.getDatum();
					String ddd = sdf.format(d).toString();
					tableclass.addRow(new Object[]{new Boolean(false),newOt.getNumb(), newOt.getTask(), ddd, newOt.getPrior(), newOt.getName()});
					fnum.setText(""+tableclass.getRowCount());
				}
			}
		});
		newtask.setBounds(170, 95, 162, 33);
		contentPane.add(newtask);
		
		JButton modify = new JButton("Feladat m\u00F3dost\u00E1sa");
		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tableclass.getRowCount()==0) BasicClass.showMD("Nincs módosítható adat!", 0);
					else {
						Modify em = new Modify(Program.this, tableclass);
						em.setVisible(true);
				}
			}
		});
		modify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		modify.setBounds(170, 139, 162, 33);
		contentPane.add(modify);
		
		JButton delete = new JButton("Feladat t\u00F6rl\u00E9se");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableclass.getRowCount()==0) BasicClass.showMD("Nincs törölhetõ adat!", 0);
				else {
				Delete ed = new Delete(Program.this, tableclass);
				ed.setVisible(true);
				fnum.setText(""+tableclass.getRowCount());
				}
			}
		});
		delete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		delete.setBounds(170, 183, 162, 33);
		contentPane.add(delete);
		
		JButton write = new JButton("Ki\u00EDr\u00E1s");
		write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (des.equals("Válasszon!")) SMD("Elõször válassza ki a Cél-t!");
					else if (tableclass.getRowCount()==0) SMD("Nincs kiírható adat");
						else if (des.equals("Helyi .csv fájl")) {	
				if (writefname.getText().length()==0) SMD("Nincs megadva a cél fájl neve!");
					else {
						FileManager.CsvWriter(writefname.getText().toString(), tableclass);
					}
				}	
					else if (des.equals("Helyi .xml fájl")) {	
						if (writefname.getText().length()==0) SMD("Nincs megadva a cél fájl neve!");
							else {
								FileManager.XmlWriter(writefname.getText().toString(), tableclass);
							}
					}
						else if (des.equals(">>> Forrás") && source.equals("Helyi .csv fájl")) {
							String write_file_name = fname.getText();
							writefname.setText(write_file_name);
							FileManager.CsvWriter(write_file_name, tableclass);
						}
						else if (des.equals(">>> Forrás") && source.equals("Helyi .xml fájl")) {
							String write_file_name = fname.getText();
							writefname.setText(write_file_name);
							FileManager.XmlWriter(write_file_name, tableclass);
						}
			}
		});
		
		write.setFont(new Font("Tahoma", Font.PLAIN, 20));
		write.setBounds(10, 223, 150, 33);
		contentPane.add(write);
		
		String element2[] = {"Válasszon!",">>> Forrás","Helyi .xml fájl","Helyi .csv fájl","Helyi .pdf fájl"};
	
				JComboBox<String> jcbc = new JComboBox<String>();
				for (String s: element2) jcbc.addItem(s);
				jcbc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						des = (String)jcbc.getSelectedItem();
						if (des.equals(">>> Forrás") && fname.getText().equals(""))SMD("Nincs megadva a Forrás!");
						if (des.equals(">>> Forrás") && !fname.getText().equals("")) writefname.setText(fname.getText());
					}
				});
			
		jcbc.setBounds(176, 227, 121, 23);
		contentPane.add(jcbc);
		
		writefname = new JTextField();
		writefname.setColumns(10);
		writefname.setBounds(309, 227, 121, 23);
		contentPane.add(writefname);
		
		Object tableclass_names[] = {"Jel","Sorszám","Feladat","Idõpont","Prioritás","Név"};
		tableclass = new Table(tableclass_names, 0);	
	}
	
	public void SMD (String s){
		JOptionPane.showMessageDialog(null, s, mes, 0);
		}
}
