
public class Gyalog extends Babuk{
	
	public Gyalog(String szin) {
		super(szin,"gyalog");
		if(szin == "feh�r") {
			jeloles = "wGy";
		}
		else {
			jeloles = "bGy";
		}
	}
	
	
	public boolean mozoghatE(int[]honnan,int[]hova,String szin) {
		// bek�rt adatok sz�tszed�se
		int honnanX = honnan[0];
		int honnanY = honnan[1];
		int hovaX = hova[0];
		int hovaY = hova[1];
		
		int ketto; // feh�r �s fekete eset�ben m�s ir�nyba l�p 1-et vagy 2-t, �gy negat�v vagy pozit�v lesz
		int egy;
		int pawnloc; // ahonnan kezdenek az egyes gyalogok
		
		Mezo celmezo = Board.board[hovaY][hovaX];
		
		// adatok megad�sa sz�n alapj�n
		if(szin == "feh�r"){ 
			ketto = -2;
			egy = -1;
			pawnloc = 6;
		}
		else{ 
			ketto = 2;
			egy = 1;
			pawnloc = 1;
		}
			
		// megvizsg�ljuk hogy a c�l Y mez� eggyel v�ltozik-e csak, vagy kett�vel
		if(hovaY == honnanY + egy){
			
			// ha eggyel v�ltozik, megvizsg�ljuk hogy az X-en is v�ltozik-e
			// ha igen, akkor az nem lehet �res, �s nem lehet saj�t sz�n
			if((hovaX == honnanX - 1) || (hovaX == honnanX + 1)){
				if((celmezo.getFajta() != "ures") && (celmezo.getSzin() != szin)){
					return true; 
				}
			}	
			
			else if((hovaX == honnanX) && (celmezo.getFajta() == "ures")){ 
				return true;
			}
		}
		// ha kett�t akarunk l�pni, meg kell vizsg�lni, hogy hogy ugyanabban az oszlopban legy�nk, valamint a c�l mez� �s a k�ztes mez� �res legyen
		else if((hovaY == honnanY + ketto) && (hovaX == honnanX) && (celmezo.getFajta() == "ures") && (Board.board[(honnanY+egy)][hovaX].getFajta() == "ures")){ 
			
			System.out.println();
			if(honnanY == pawnloc){ 
				return true;
			}
		}
		// ha semmi sem j�tt �sssze, akkor kil�p false-al
		return false; 
	}	
}

