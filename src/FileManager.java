import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileManager {
	private static String mes = "Program üzenet";
	
	public static void CsvReader(File fname, Table tableclass) {
		String s="";
		try {
			BufferedReader in = new BufferedReader(new FileReader(fname));
			s=in.readLine(); // skip
			s=in.readLine(); // data
			while(s!=null) {
				String[] st = s.split(";");
				tableclass.addRow(new Object[]{new Boolean(false), StoI(st[0]), st[1], st[2], StoI(st[3]), st[4]});
				s=in.readLine();
			}
			in.close();
			JOptionPane.showMessageDialog(null, "Adatok beolvasva!", mes, 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvReader: "+ioe.getMessage(), mes, 0);
		  }
		}
	
	public static void CsvWriter(String fname, Table tableclass) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fname));
			out.println("Sorszám;Feladat;Idõpont;Prioritás;Név");
			int rdb = tableclass.getRowCount();
			int cdb = tableclass.getColumnCount();
			for (int i=0; i < rdb; i++) {
				for (int j=1; j < cdb-1; j++) {
					out.print(""+tableclass.getValueAt(i,j)+";");
				}
				out.println(""+tableclass.getValueAt(i, cdb-1));
			}
			out.close();
			JOptionPane.showMessageDialog(null, "Adatok kiírva!", mes, 1);
		 } catch (IOException ioe) {
			 JOptionPane.showMessageDialog(null, "CsvWriter: "+ioe.getMessage(), mes, 0);
		  }
		}
	
	public static void XmlReader(File fname, Table tableclass) {
		try {
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(fname);         
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("row");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               
					tableclass.addRow(new Object[]{new Boolean(false), 
								StoI(eElement.getElementsByTagName("Sorszam").item(0).getTextContent()), 
								eElement.getElementsByTagName("Feladat").item(0).getTextContent(),
								eElement.getElementsByTagName("Idopont").item(0).getTextContent(),
								StoI(eElement.getElementsByTagName("Prioritas").item(0).getTextContent()), 
								eElement.getElementsByTagName("Nev").item(0).getTextContent()});
	            }
	         }
	         JOptionPane.showMessageDialog(null, "Adatok beolvasva!", mes, 1);
	      } catch (Exception e) {
	    	  JOptionPane.showMessageDialog(null, "XmlReader: "+e.getMessage(), mes, 0);
	      }	
	}
	
public static void XmlWriter(String fname, Table tableclass) {
		
		int rows = tableclass.getRowCount();
		int columns = tableclass.getColumnCount();
		ArrayList<String> tasks = new ArrayList<String>();
			
		for(int i = 0; i < rows; i++) {
			String s = "";
			for(int j = 1; j < columns-1; j++) {
				 s += "" + tableclass.getValueAt(i, j) + ",";
			}
			s += "" + tableclass.getValueAt(i, columns - 1);
			tasks.add(s);
		}
		
		DocumentBuilderFactory dbfact = DocumentBuilderFactory.newInstance();
		Document dom;
		try {
			DocumentBuilder builder = dbfact.newDocumentBuilder();
			dom = builder.newDocument();
			Element root = dom.createElement("root");
			
			for(int i = 0; i < tasks.size(); i++) {
				Element object = dom.createElement("row");
				Element e = null;
				String[] parts = tasks.get(i).split(",");;
				
				e = dom.createElement("Sorszam");
				e.appendChild(dom.createTextNode(parts[0]));
				object.appendChild(e);
				
				e = dom.createElement("Feladat");
				e.appendChild(dom.createTextNode(parts[1]));
				object.appendChild(e);
				
				e = dom.createElement("Idopont");
				e.appendChild(dom.createTextNode(parts[2]));
				object.appendChild(e);
				
				e = dom.createElement("Prioritas");
				e.appendChild(dom.createTextNode(parts[3]));
				object.appendChild(e);
				
				e = dom.createElement("Nev");
				e.appendChild(dom.createTextNode(parts[4]));
				object.appendChild(e);
				
				root.appendChild(object);
			}
			dom.appendChild(root);
			try {
				Transformer tr= TransformerFactory.newInstance().newTransformer();
				tr.setOutputProperty(OutputKeys.INDENT, "yes");
				tr.setOutputProperty(OutputKeys.METHOD, "xml");
				tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				tr.setOutputProperty("{http://xml.apache.org/xslt} indent-amount", "4");
				tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(fname)));
				JOptionPane.showMessageDialog(null, "Adatok kiírva!", mes, 1);
			 }catch(TransformerException e) {
				System.out.println(e.getMessage());
			 }catch(IOException e) {
				System.out.println(e.getMessage());
			 }
			 }catch(ParserConfigurationException e) {
			  System.out.println(e.getMessage());
			 }		
	} 
	
	public static int StoI(String s){
		int x=-55;
		x = Integer.parseInt(s);
		return x;
	}
}
