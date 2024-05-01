package lvt;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;
public class pica {

	public static void main(String[] args) {
		String izvele;
		int izveleIndekss;
		String[] darbibas = {"Reģistrēt klientu", "Jauns pasūtījums", "Aktīvie pasūtījumi", "Pabeigtie pasūtiījumi",
				"Pabeigt pasūtījumu", "Beigt darbu"};
		ArrayList<Klients>klienti = new ArrayList<Klients>();
		String[] izmers = {"Maza", "Vidēja", "Liela"};
		String[] picasTipi = {"Margarita", "Siera plate", "Gaļas pica"};
		String[] piedevas = {"Salami", "Pipari", "Siers", "Paprika"};
		String[] picuSanems = {"Ar piegādi", "Uz vietas"};
		//ielaide klientus no failus
		try(Scanner scanner = new Scanner(Paths.get("klienti.txt"))) {
			while(scanner.hasNextLine()) {
				String r = scanner.nextLine();
				String[] klientsNoFaila = r.split("///");
				klienti.add(new Klients(klientsNoFaila[0], klientsNoFaila[1], klientsNoFaila[2]));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		do {
			izvele = (String)JOptionPane.showInputDialog(null, "Izvēlies darbību: ", "Izvele",
					JOptionPane.QUESTION_MESSAGE,null, darbibas, darbibas[0]);
			izveleIndekss = Arrays.asList(darbibas).indexOf(izvele);
			switch(izveleIndekss) {
			case 0:
				String vards = JOptionPane.showInputDialog("Klientā vārds");
				String adrese = JOptionPane.showInputDialog("Klientā adrese");
				String talrunis = JOptionPane.showInputDialog("Klientā talrunis");
				if(vards != null && adrese != null && talrunis != null
						&& vards.length()>0 && adrese.length()>0&& talrunis.length()>0) {
					Klients jaunsKlients = new Klients(vards, adrese,talrunis);
					jaunsKlients.saglabatKlientu();
					klienti.add(jaunsKlients);
				}
				break;
			case 1:
				if(klienti.size()>0) {
					JComboBox<String> klientuSaraksts = new JComboBox<String>();
					for(int i = 0; i<klienti.size(); i++) {
						String klientsSarakstam = klienti.get(i).getDati();
						klientuSaraksts.addItem(klientsSarakstam);
					}
					JComboBox<String> izmeri = new JComboBox<String>();
					for(int i=0; i<izmers.length; i++) {
						izmeri.addItem(izmers[i]);
					}
					JComboBox<String> tips = new JComboBox<String>();
					for(int i=0; i<picasTipi.length; i++) {
						tips.addItem(picasTipi[i]);
					}
					JCheckBox[] picaspiedevs = new JCheckBox[piedevas.length];
					for(int i=0; i<piedevas.length; i++) {
						picaspiedevs[i] = new JCheckBox(piedevas[i]);
					}
					JComboBox<String> piegade = new JComboBox<String>();
					for(int i=0; i<picuSanems.length; i++) {
						piegade.addItem(picuSanems[i]);
					}
					
				Object[] message={
					"klients: ", klientuSaraksts,
					"picas izmers: ", izmeri,
					"picas tips: ", tips,
					"picas piedevas: ",picaspiedevs,
					"picu saņems: ", piegade
				};
				int option = JOptionPane.showConfirmDialog(null, message, "Izveidot pasūtījumu", JOptionPane.OK_CANCEL_OPTION);
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				JOptionPane.showMessageDialog(null,"Uz redzēšanos!", "Programmas apturēšana", 
						JOptionPane.PLAIN_MESSAGE);
				break;
			}
		}while(izveleIndekss !=5);

	}

}
