

public class Huszar extends Babuk {

	public Huszar(String szin) {
		super(szin,"huszar");
		if(szin == "fehér") {
			jeloles = "wHu";
		}
		else {
			jeloles = "bHu";
		}
	}
	
	
	public boolean mozoghatE(int[]honnan,int[]hova,String szin) {
		
		int honnanX = honnan[0];
		int honnanY = honnan[1];
		int hovaX = hova[0];
		int hovaY = hova[1];
		
		
		// ebben a változóban tároljuk, hogy a hely valid-e
		boolean hely = false;
		
		//ellenõrizzük, hogy az egyik irányba 2-t, a másik irányba 1-et lépünk, akkor jó a lépés
		for(int i = -2; i <= 2; i++){
		
			if(i != 0){
				if(hovaX == honnanX + i){
					
					if(Math.abs(i) == 1){ 
						for(int j = -2; j <= 2; j += 4){
							if(hovaY == honnanY + j){
								hely = true;
							}
						}
					}
					else{ 
						for(int j = -1; j <= 1; j += 2){
							if(hovaY == honnanY + j){
								hely = true;
							}
						}
					}
				}
			}
		}
		// ha a hely valid volt, megnézzük, hogy a mezõ üres-e vagy nem a saját szinünk
		if(hely){ 
			Mezo celmezo = Board.board[hovaY][hovaX];
			if((celmezo.getFajta() == "ures") || (celmezo.getSzin() != szin)){
				return true;
			}
		}
		
		return false;
	}

}
