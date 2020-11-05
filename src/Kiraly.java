
public class Kiraly extends Babuk {

	public Kiraly(String szin) {
		super(szin,"király");
		if(szin == "fehér") {
			jeloles = "wKi";
		}
		else {
			jeloles = "bKi";
		}
	}
	
	
	public boolean mozoghatE(int[]honnan,int[]hova,String szin) {
		//szétszedjük a bekért adatokat
		int honnanX = honnan[0];
		int honnanY = honnan[1];
		int hovaX = hova[0];
		int hovaY = hova[1];
		// eltároljuk, hogy hova szeretnénk lépni, fordítva
		Mezo Hova = Board.board[hovaY][hovaX];
		//ellenõrizzük, hogy ha a lépés egyezik a lehetséges lépésekkel,
		//akkor az a mezõ legyen vagy üres, vagy az ellenfél színe
		for(int x=-1;x<2;x++) {
			for(int y=-1;y<2;y++) {
				if(hovaX == honnanX + x && hovaY == honnanY + y) {
					if(Hova.getFajta() != "ures" && Hova.getSzin() != szin) {
						return true;
					}
					else if(Hova.getFajta() == "ures") {
						return true;
					}
				}
				
			}
		}
		
		return false;
	}

}