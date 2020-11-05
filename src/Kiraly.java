
public class Kiraly extends Babuk {

	public Kiraly(String szin) {
		super(szin,"kir�ly");
		if(szin == "feh�r") {
			jeloles = "wKi";
		}
		else {
			jeloles = "bKi";
		}
	}
	
	
	public boolean mozoghatE(int[]honnan,int[]hova,String szin) {
		//sz�tszedj�k a bek�rt adatokat
		int honnanX = honnan[0];
		int honnanY = honnan[1];
		int hovaX = hova[0];
		int hovaY = hova[1];
		// elt�roljuk, hogy hova szeretn�nk l�pni, ford�tva
		Mezo Hova = Board.board[hovaY][hovaX];
		//ellen�rizz�k, hogy ha a l�p�s egyezik a lehets�ges l�p�sekkel,
		//akkor az a mez� legyen vagy �res, vagy az ellenf�l sz�ne
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