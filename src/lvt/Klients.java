package lvt;

import java.io.PrintWriter;
import java.io.*;

public class Klients {

	private String vards;
	private String adrese;
	private String talrunis;
	
	public Klients(String vards, String adrese, String talrunis) {
		this.vards = vards;
		this.adrese = adrese;
		this.talrunis = talrunis;
	}
	public String getVards() {
		return vards;
	}
	public String getAdrese() {
		return adrese;	
	}
	public String getTalrunis() {
		return talrunis;
	}
	public String getDati() {
		return vards+", "+adrese+", "+talrunis;
	}
	public void saglabatKlientu() {
		String s = vards+"///Adrese: "+ adrese+ "///Telefons: "+talrunis;
		try {
			PrintWriter out = new PrintWriter(new FileWriter("klienti.txt", true));
			out.println(s);
			out.flush();
			out.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
