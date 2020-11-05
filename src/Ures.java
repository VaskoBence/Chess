
public class Ures extends Mezo{
	
	public Ures() {
		super("ures");
		jeloles = "   ";
		super.szin=null;
	}
	
	// üres mezõ nem mozgatható
	public boolean mozoghatE(int[] honnan, int[] hova, String szin) {
		return false;
	};
}
