
public class Ures extends Mezo{
	
	public Ures() {
		super("ures");
		jeloles = "   ";
		super.szin=null;
	}
	
	// �res mez� nem mozgathat�
	public boolean mozoghatE(int[] honnan, int[] hova, String szin) {
		return false;
	};
}
