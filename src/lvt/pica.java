package lvt;

import java.util.Arrays;

import javax.swing.*;
public class pica {

	public static void main(String[] args) {
		String izvele;
		int izveleIndekss;
		String[] darbibas = {"Reģistrēt klientu", "Jauns pasūtījums", "Aktīvie pasūtījumi", "Pabeigtie pasūtiījumi",
				"Pabeigt pasūtījumu", "Beigt darbu"};
		
		
		do {
			izvele = (String)JOptionPane.showInputDialog(null, "Izvēlies darbību: ", "Izvele",
					JOptionPane.QUESTION_MESSAGE,null, darbibas, darbibas[0]);
			izveleIndekss = Arrays.asList(darbibas).indexOf(izvele);
			switch(izveleIndekss) {
			case 0:
				break;
			case 1:
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
