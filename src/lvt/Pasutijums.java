package lvt;

public class Pasutijums {

	private int klientaIndekss;
	private String izmers;
	private String tips;
	private String piedevas;
	private String piegade;
	private int daudzums;
	private double cena;
	private String statuss;
	
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
	}
	public int getSatuss() {
		int intStatuss = Integer.valueOf(statuss);
		return intStatuss;
	}
	public int getKlientIndekss() {
		return klientaIndekss;
	}
	public String getDetails(Klients klients) {
		return klients.getDati()+","+izmers+""+tips+
				", piedevas: "+ piedevas+ ","+piegade+", skaits:"+daudzums+", kopā:" +cena+ "EUR";
	}
}
