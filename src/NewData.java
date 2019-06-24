import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewData extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField numb;
	private JTextField task;
	private JTextField datum;
	private JTextField prior;
	private JTextField name;	
	private Table tableclass;
	private Ot data;
	private int exit=0;

	public NewData(JFrame f, Table tablein) {

		super(f, true);
		tableclass = tablein;
		
		setTitle("\u00DAj feladat felvitele");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 450, 245);
		getContentPane().setLayout(null);
		
		JLabel labelsorsz = new JLabel("Sorsz\u00E1m:");
		labelsorsz.setBounds(10, 21, 57, 14);
		getContentPane().add(labelsorsz);
		
		numb = new JTextField();
		numb.setEditable(false);
		numb.setBounds(66, 18, 46, 20);
		getContentPane().add(numb);
		numb.setColumns(10);
		
		JLabel labeltask = new JLabel("Feladat:");
		labeltask.setBounds(10, 53, 46, 14);
		getContentPane().add(labeltask);
		
		task = new JTextField();
		task.setBounds(66, 49, 285, 20);
		getContentPane().add(task);
		task.setColumns(10);
		
		JLabel labelido = new JLabel("Id\u0151pont:");
		labelido.setBounds(10, 87, 46, 14);
		getContentPane().add(labelido);
		
		datum = new JTextField();
		datum.setBounds(66, 84, 121, 20);
		getContentPane().add(datum);
		datum.setColumns(10);
		
		JLabel lblPriorits = new JLabel("priorit\u00E1s:");
		lblPriorits.setBounds(10, 118, 57, 14);
		getContentPane().add(lblPriorits);
		
		prior = new JTextField();
		prior.setBounds(66, 115, 46, 20);
		getContentPane().add(prior);
		prior.setColumns(10);
		
		JLabel lblNv = new JLabel("N\u00E9v:");
		lblNv.setBounds(10, 148, 46, 14);
		getContentPane().add(lblNv);
		
		name = new JTextField();
		name.setBounds(66, 145, 170, 20);
		getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblhhnn = new JLabel("\u00E9\u00E9\u00E9\u00E9.hh.nn");
		lblhhnn.setBounds(204, 87, 121, 14);
		getContentPane().add(lblhhnn);
		
		JButton btnBezr = new JButton("Bez\u00E1r");
		btnBezr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBezr.setBounds(335, 11, 89, 23);
		getContentPane().add(btnBezr);
		
		JButton bbeszur = new JButton("Besz\u00FAr");
		bbeszur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int line = tableclass.getRowCount()+1;
				if (!BasicClass.filled(numb)) numb.setText(""+line);
				if (!BasicClass.filled(name)) BasicClass.showMD("A Név mezõ üres!", 0);
				else if (!BasicClass.filled(datum)) BasicClass.showMD("Az idõ mezõ üres!", 0);
				else if (!BasicClass.goodDate(datum)) BasicClass.showMD("Az idõ mezõben hibásadat van!", 0);
				else if (!BasicClass.filled(task)) BasicClass.showMD("A feladat mezõ üres!", 0);
				else if (!BasicClass.filled(prior)) BasicClass.showMD("A prioritás mezõ üres!", 0);
				else if (!BasicClass.goodInt(prior)) BasicClass.showMD("A prioritás mezõben hibás adat van!", 0);
				else {
					data = new Ot( BasicClass.StoI(BasicClass.RF(numb)), BasicClass.RF(task), BasicClass.StoD(BasicClass.RF(datum)), BasicClass.StoI(BasicClass.RF(prior)), BasicClass.RF(name));
					BasicClass.showMD("Adat beszúrva!", 1);
					exit=1;
					dispose();
					setVisible(false);
					}
			}
		});
		bbeszur.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bbeszur.setBounds(309, 161, 104, 32);
		getContentPane().add(bbeszur);
		
		JButton btnLekr = new JButton("Lek\u00E9r");
		btnLekr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numb.setText(""+(tableclass.getRowCount()+1));
			}
		});
		btnLekr.setBounds(127, 17, 89, 23);
		getContentPane().add(btnLekr);
	}
	
	public Ot getOt(){
		return data;
	}
	
	public int Exit() {
		return exit;
	}
}
