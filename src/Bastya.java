
public class Bastya extends Babuk {

	public Bastya(String szin) {
		super(szin,"bastya");
		if(szin == "fehér") {
			jeloles = "wBa";
		}
		else {
			jeloles = "bBa";
		}
	}
	
	
	public boolean mozoghatE(int[]honnan,int[]hova,String szin) {
		
		int honnanX = honnan[0];
		int honnanY = honnan[1];
		int hovaX = hova[0];
		int hovaY = hova[1];
		
		String irany;
		
		// irány kiválasztása
		if(hovaY == honnanY){
			if(hovaX > honnanX){
				irany = "jobb";
			}
			else{
				irany = "bal";
			}
		}
		
		else if(hovaX == honnanX){
			if(hovaY > honnanY){
				irany = "le";
			}
			else{
				irany = "fel";
			}
		}
		else{
			return false;
		}
		
		Mezo testMezo;
		// az irány alapján végigmegyünk a cél mezõhöz vezetõ úton for ciklussal, közötte üresnek kell lenni, a végén pedig lehet üres, vagy az ellenfél színe
		if((irany == "jobb") || (irany == "bal")){
			int max = Math.abs(hovaX - honnanX); // maximum ennyit lépünk, ennyiszer fut le a for ciklus is 
		
			for(int i = 1; i <= max; i++){ 
				if(irany == "jobb"){
					testMezo = Board.board[honnanY][honnanX + i];
					// ha nem értünk még el a cél mezõig, de az egyik mezõ nem "üres", akkor nem jó a lépés
					if((testMezo.getFajta() != "ures") && (i != max)){
						return false;
					}
					else if((i == max) && ((testMezo.getFajta() == "ures") || (testMezo.getSzin() != szin))){
						return true;
					}
				}
				else{
					testMezo = Board.board[honnanY][honnanX - i];
					
					if((testMezo.getFajta() != "ures") && (i != max)){
						return false;
					}
					else if((i == max) && ((testMezo.getFajta() == "ures") || (testMezo.getSzin() != szin))){
						return true;
					}
				}
			}
		}
		else{
			int max = Math.abs(hovaY - honnanY); 
				
			for(int i = 1; i <= max; i++){ 
				
				if(irany == "fel"){
					testMezo = Board.board[honnanY - i][honnanX];
					
					if((testMezo.getFajta() != "ures") && (i != max)){
						return false;
					}
					else if((i == max) && ((testMezo.getFajta() == "ures") || (testMezo.getSzin() != szin))){
						return true;
					}
				}
				else{
					testMezo = Board.board[honnanY + i][honnanX];
					
					if((testMezo.getFajta() != "ures") && (i != max)){
						return false;
					}
					else if((i == max) && ((testMezo.getFajta() == "ures") || (testMezo.getSzin() != szin))){
						return true;
					}
				}
			}
		}
		return false;
	
	}

}
