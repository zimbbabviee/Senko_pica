package lvt;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Pasutijums {

	private int klientaIndekss;
	private String izmers;
	private String tips;
	private String piedevas;
	private String piegade;
	private int daudzums;
	private double cena;
	private String statuss;
	private int indeksInList;
	
	public Pasutijums(int klientaIndekss, String izmers, String tips, String piedevas, String piegade,
			int daudzums, double cena, String statuss) {
		this.klientaIndekss = klientaIndekss;
		this.izmers = izmers;
		this.tips = tips;
		this.piedevas = piedevas;
		this.piegade = piegade;
		this.daudzums = daudzums;
		this.cena = cena;
		this.statuss = statuss;
		this.indeksInList = -1;
	}
	public int getSatuss() {
		int intStatuss = Integer.valueOf(statuss);
		return intStatuss;
	}
	public void setStatussFinished() {
		statuss = "1";
	}
	public int getKlientIndekss() {
		return klientaIndekss;
	}
	public String getDetails(Klients klients) {
		return klients.getDati()+","+izmers+""+tips+
				", piedevas: "+ piedevas+ ","+piegade+", skaits:"+daudzums+", kopā:" +cena+ "EUR";
	}
	public void setIndekssInList(int indeks) {
		indeksInList = indeks;
	}
	public void pabeigtPasutijumu() {
		ArrayList<String> pasutijumuSaraksts = new ArrayList<>();
		int rindasIndeks = 0;
		try(Scanner scanner = new Scanner(Paths.get("pasutijumi.txt"))){
			while(scanner.hasNextLine()) {
				String r = scanner.nextLine();
				if(rindasIndeks == indeksInList) {
					r = "1"+r.substring(1);
				}
				pasutijumuSaraksts.add(r);
				rindasIndeks++;
			}
			PrintWriter out = new PrintWriter(new FileWriter("pasutijumi.txt",false));
			for(int i=0;i<pasutijumuSaraksts.size(); i++) {
				out.println(pasutijumuSaraksts.get(i));
			}
			out.flush();
			out.close();
			setStatussFinished();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public void saglabatPasutijumu() {
		String s = statuss+"///"+klientaIndekss+"///"+izmers+"///"+tips+"///"+piedevas+"///"+piegade+"///"+
	daudzums+"///"+cena;
		try {
			PrintWriter out = new PrintWriter(new FileWriter("pasutijumi.txt", true));
			out.println(s);
			out.flush();
			out.close();
		
	}catch(Exception e) {
		System.out.println(e);
	}
	}
}
