
public abstract class Mezo {
	protected String szin; // fekete,fehér, vagy semmi, ha üres a mezõ
	protected String fajta; // bástya, paraszt, üres, stb...
	protected String jeloles; // amivel jelöljük a bábukat
	
	public Mezo( String fajta) {
		this.fajta = fajta;
	}

	public String getSzin() {
		return szin;
	}

	public String getFajta() {
		return fajta;
	}

	public String getJeloles() {
		return jeloles;
	}
	
	public abstract boolean mozoghatE(int[] honnan, int[] hova, String szin);
}
