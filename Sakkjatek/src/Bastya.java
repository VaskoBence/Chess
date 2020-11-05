
public class Bastya extends Babuk {

	public Bastya(String szin) {
		super(szin,"bastya");
		if(szin == "feh�r") {
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
		
		// ir�ny kiv�laszt�sa
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
		// az ir�ny alapj�n v�gigmegy�nk a c�l mez�h�z vezet� �ton for ciklussal, k�z�tte �resnek kell lenni, a v�g�n pedig lehet �res, vagy az ellenf�l sz�ne
		if((irany == "jobb") || (irany == "bal")){
			int max = Math.abs(hovaX - honnanX); // maximum ennyit l�p�nk, ennyiszer fut le a for ciklus is 
		
			for(int i = 1; i <= max; i++){ 
				if(irany == "jobb"){
					testMezo = Board.board[honnanY][honnanX + i];
					// ha nem �rt�nk m�g el a c�l mez�ig, de az egyik mez� nem "�res", akkor nem j� a l�p�s
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
