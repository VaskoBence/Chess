
public abstract class Mezo {
	protected String szin; // fekete,feh�r, vagy semmi, ha �res a mez�
	protected String fajta; // b�stya, paraszt, �res, stb...
	protected String jeloles; // amivel jel�lj�k a b�bukat
	
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
