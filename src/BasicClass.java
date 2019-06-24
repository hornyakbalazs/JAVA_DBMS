import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BasicClass {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	private static String mes = "Program üzenet";
	
	public static String RF(JTextField a) {
		return a.getText().toString();
	}
	
	public static boolean filled(JTextField a) {
		String s = RF(a);
		if (s.length() > 0) return true; else return false;
	}
	
	public static boolean goodDate(JTextField a) {
		String s = RF(a);
		Date testDate = null;
		try {testDate = sdf.parse(s);
			} catch (ParseException e){return false;}
		if (sdf.format(testDate).equals(s)) return true;
			else return false;
	}
	
	public static boolean goodInt(JTextField a) {
		String s = RF(a);
		try {Integer.parseInt(s); return true;
			} catch (NumberFormatException e){return false;}
	}
	
	public static void showMD(String s, int i){
		JOptionPane.showMessageDialog(null, s, mes, i);
	}
	
	public static Date StoD(String s){
		Date testDate = null, vid = null;
		try {testDate = sdf.parse(s);
			} catch (ParseException e){return vid;}
		if (!sdf.format(testDate).equals(s)){return vid;}
			return testDate;
	}
	
	public static int StoI(String s){
		int x=-55;
		x = Integer.parseInt(s);
		return x;
	}
	
	public static void DF(JTextField a) {
		a.setText("");
	}
}