
public class Futo extends Babuk {

	public Futo(String szin) {
		super(szin,"futo");
		if(szin == "feh�r") {
			jeloles = "wFu";
		}
		else {
			jeloles = "bFu";
		}
	}
	
	
	public boolean mozoghatE(int[]honnan,int[]hova,String szin) {
		
		int honnanX = honnan[0];
		int honnanY = honnan[1];
		int hovaX = hova[0];
		int hovaY = hova[1];
		
		int tavolsag = Math.abs(hovaX - honnanX);		
		// ir�ny v�ltoz�
		String irany; 
		
		// megn�zz�k, milyen ir�nyba megy a fut�
		if(hovaX > honnanX){
			if(hovaY < honnanY){
				irany = "jobbraFel";
			}
			else{
				irany = "jobbraLe";
			}
		}
		else{
			if(hovaY < honnanY){
				irany = "balraFel";
			}
			else{
				irany = "balraLe";
			}
		}
		
		
		Mezo testMezo; 
		
		//for ciklussal v�gig megy�nk a l�p�seken �s az ir�nynak megfelel�en elt�rolt teszt mez�t ellen�rizz�k, hogy �res-e vagy nincs rajta semmi
		for(int i = 1; i <= tavolsag; i++){
			// el�sz�r leellen�rizz�k, hogy ugyanannyit v�ltozzon az x �s az y, ha nem, olyan is valid lenne pl., hogy c1 a4
			if(Math.abs(hovaY - honnanY) != Math.abs(hovaX - honnanX)){
				return false;
			}
			else if(irany == "jobbraFel"){
				testMezo = Board.board[honnanY - i][honnanX + i];
			}
			else if(irany == "jobbraLe"){
				testMezo = Board.board[honnanY + i][honnanX + i];
			}
			else if(irany == "balraFel"){
				testMezo = Board.board[honnanY - i][honnanX - i];
			}
			else{ 
				testMezo = Board.board[honnanY + i][honnanX - i];
			}
			
			if((testMezo.getFajta() != "ures") && (i != tavolsag)){
				return false;
			}
			else if((i == tavolsag) && ((testMezo.getSzin() != szin) || (testMezo.getFajta() == "ures"))){
				return true;
			}
		}
		return false; 
	}
}
