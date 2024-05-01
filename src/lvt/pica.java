package lvt;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
public class pica {

	public static void main(String[] args) {
		String izvele;
		int izveleIndekss;
		String[] darbibas = {"Reģistrēt klientu", "Jauns pasūtījums", "Aktīvie pasūtījumi", "Pabeigtie pasūtiījumi",
				"Pabeigt pasūtījumu", "Beigt darbu"};
		ArrayList<Klients>klienti = new ArrayList<Klients>();
		
		
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
				Object[] message={
					"klients: ", klientuSaraksts
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
