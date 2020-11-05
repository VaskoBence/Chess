
public class Futo extends Babuk {

	public Futo(String szin) {
		super(szin,"futo");
		if(szin == "fehér") {
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
		// irány változó
		String irany; 
		
		// megnézzük, milyen irányba megy a futó
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
		
		//for ciklussal végig megyünk a lépéseken és az iránynak megfelelõen eltárolt teszt mezõt ellenõrizzük, hogy üres-e vagy nincs rajta semmi
		for(int i = 1; i <= tavolsag; i++){
			// elõször leellenõrizzük, hogy ugyanannyit változzon az x és az y, ha nem, olyan is valid lenne pl., hogy c1 a4
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
