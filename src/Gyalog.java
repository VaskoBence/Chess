
public class Gyalog extends Babuk{
	
	public Gyalog(String szin) {
		super(szin,"gyalog");
		if(szin == "fehér") {
			jeloles = "wGy";
		}
		else {
			jeloles = "bGy";
		}
	}
	
	
	public boolean mozoghatE(int[]honnan,int[]hova,String szin) {
		// bekért adatok szétszedése
		int honnanX = honnan[0];
		int honnanY = honnan[1];
		int hovaX = hova[0];
		int hovaY = hova[1];
		
		int ketto; // fehér és fekete esetében más irányba lép 1-et vagy 2-t, így negatív vagy pozitív lesz
		int egy;
		int pawnloc; // ahonnan kezdenek az egyes gyalogok
		
		Mezo celmezo = Board.board[hovaY][hovaX];
		
		// adatok megadása szín alapján
		if(szin == "fehér"){ 
			ketto = -2;
			egy = -1;
			pawnloc = 6;
		}
		else{ 
			ketto = 2;
			egy = 1;
			pawnloc = 1;
		}
			
		// megvizsgáljuk hogy a cél Y mezõ eggyel változik-e csak, vagy kettõvel
		if(hovaY == honnanY + egy){
			
			// ha eggyel változik, megvizsgáljuk hogy az X-en is változik-e
			// ha igen, akkor az nem lehet üres, és nem lehet saját szín
			if((hovaX == honnanX - 1) || (hovaX == honnanX + 1)){
				if((celmezo.getFajta() != "ures") && (celmezo.getSzin() != szin)){
					return true; 
				}
			}	
			
			else if((hovaX == honnanX) && (celmezo.getFajta() == "ures")){ 
				return true;
			}
		}
		// ha kettõt akarunk lépni, meg kell vizsgálni, hogy hogy ugyanabban az oszlopban legyünk, valamint a cél mezõ és a köztes mezõ üres legyen
		else if((hovaY == honnanY + ketto) && (hovaX == honnanX) && (celmezo.getFajta() == "ures") && (Board.board[(honnanY+egy)][hovaX].getFajta() == "ures")){ 
			
			System.out.println();
			if(honnanY == pawnloc){ 
				return true;
			}
		}
		// ha semmi sem jött össsze, akkor kilép false-al
		return false; 
	}	
}

