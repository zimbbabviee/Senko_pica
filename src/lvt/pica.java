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
		ArrayList<Pasutijums>pasutijumi = new ArrayList<Pasutijums>();
		String[] izmers = {"Maza", "Vidēja", "Liela"};
		String[] picasTipi = {"Margarita", "Siera plate", "Gaļas pica"};
		String[] piedevas = {"Salami", "Pipari", "Siers", "Paprika"};
		String[] picuSanems = {"Ar piegādi", "Uz vietas"};
		double kopejaCena = 0;
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
					JTextField picuSkaits = new JTextField();
				Object[] message={
					"klients: ", klientuSaraksts,
					"picas izmers: ", izmeri,
					"picas tips: ", tips,
					"picas piedevas: ",picaspiedevs,
					"picu saņems: ", piegade,
					"picu skaitu: ", picuSkaits
				};
				int option = JOptionPane.showConfirmDialog(null, message, "Izveidot pasūtījumu", JOptionPane.OK_CANCEL_OPTION);
				if(option == JOptionPane.OK_OPTION) {
					int klientaIndekss = klientuSaraksts.getSelectedIndex();
					kopejaCena = 0.00;
					String piegadesTeksts = piegade.getSelectedItem().toString();
					switch(piegadesTeksts) {
					case "Uz vietas":
						kopejaCena = kopejaCena+0;
						break;
					case "Ar piegādi":
						kopejaCena = kopejaCena +2;
						break;
					}
					String izmeriTeksts = izmeri.getSelectedItem().toString();
					switch(izmeriTeksts) {
					case "Maza":
						kopejaCena = kopejaCena +3;
						break;
					case "Vidēja":
						kopejaCena = kopejaCena +5;
						break;
					case "Liela":
						kopejaCena = kopejaCena + 7;
						break;
					}
					String tipsTeksts = tips.getSelectedItem().toString();
					switch(tipsTeksts) {
					case "Margarita":
						kopejaCena = kopejaCena +3;
					break;
					case "Siera plate":
						kopejaCena = kopejaCena +2;
						break;
					case "Gaļas pica":
						kopejaCena = kopejaCena +5;
						break;
					}
					String piedevasTeksts = "";
					for(int i=0; i<piedevas.length; i++) {
						if (picaspiedevs[i].isSelected()) {
							kopejaCena = kopejaCena +2;
							piedevasTeksts = piedevasTeksts +""+picaspiedevs[i].getText();
						}
					}
					try {
						int picasKopa = Integer.valueOf(picuSkaits.getText());
						kopejaCena = kopejaCena * Double.valueOf(picuSkaits.getText());
						String pasutijums = izmeriTeksts+""+tipsTeksts+",\n"+"piedevās:"+piedevasTeksts+",\n"
								+piegadesTeksts+"\n"+"Kopējā cena:"+kopejaCena;
						option = JOptionPane.showConfirmDialog(null, pasutijums, "Vai apstiprināt pasūtījmu?",
								JOptionPane.OK_CANCEL_OPTION);
						if(option == JOptionPane.OK_OPTION) {
							Pasutijums jaunsPasutijums = new Pasutijums(klientaIndekss, izmeriTeksts, tipsTeksts,
									piedevasTeksts, piegadesTeksts, picasKopa, kopejaCena, "0");
							pasutijumi.add(jaunsPasutijums);
						}
						
					}catch(Exception e) {
					}
				}
				}
				break;
			case 2:
				String aktiviePasutijumi = "";
				for(int i=0; i<pasutijumi.size(); i++) {
					if(pasutijumi.get(i).getSatuss()==0) {
						Pasutijums pasutijums = pasutijumi.get(i);
						aktiviePasutijumi = aktiviePasutijumi + pasutijums.getDetails(klienti.get(pasutijums.getKlientIndekss()))+"\n";
					}
				}
				JOptionPane.showMessageDialog(null, aktiviePasutijumi, "Aktīvie pasūtījumi", JOptionPane.PLAIN_MESSAGE);
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
