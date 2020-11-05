import java.util.Scanner;

public class Board {
	public static  Mezo board[][] = new Mezo[8][8]; //p�lya p�ld�nyos�t�sa

	
	// p�lya felt�lt�se a j�t�k kezdetekor
	public static void boardGenerate() {
		// fekete b�buk
		board[0][0] = new Bastya("fekete");
		board[0][1] = new Huszar("fekete");
		board[0][2] = new Futo("fekete");
		board[0][3] = new Vezer("fekete");
		board[0][4] = new Kiraly("fekete");
		board[0][5] = new Futo("fekete");
		board[0][6] = new Huszar("fekete");
		board[0][7] = new Bastya("fekete");
		
		// parasztok
		for(int i = 0; i < 8; i++){
			board[1][i] = new Gyalog("fekete");
			board[6][i] = new Gyalog("feh�r");
		}
		
		// �res mez�k
		for(int i = 2; i < 6; i++){ 
			for(int j = 0; j < 8; j++){
				board[i][j] = new Ures();
			}
		}
		
		// feh�r b�buk
		board[7][0] = new Bastya("feh�r");
		board[7][1] = new Huszar("feh�r");
		board[7][2] = new Futo("feh�r");
		board[7][3] = new Vezer("feh�r");
		board[7][4] = new Kiraly("feh�r");
		board[7][5] = new Futo("feh�r");
		board[7][6] = new Huszar("feh�r");
		board[7][7] = new Bastya("feh�r");
		
	}
	
	// az �j helyet a b�bu t�pus�ra cser�li, a r�git pedig �res mez�re
	public static void boardUpdate(int[] startLoc, int[] newLoc) {
		board[newLoc[1]][newLoc[0]] = board[startLoc[1]][startLoc[0]];
		board[startLoc[1]][startLoc[0]] = new Ures();
	}
	
	// a sakkt�bla lerajzol�sa
	public static void boardDraw() {
		System.out.print("\n   ");
		
		for(Betuk betu : Betuk.values()) {
			System.out.print("  " + betu + "  "); //a bet�k lerajzol�sa a t�bla tetej�n
		}
		
		System.out.print("\n   ");
		
		for(int i=0;i<8;i++) {
			System.out.print(" --- ");
		}
		
		System.out.print("\n");
		for(int i=0;i<8;i++) {
			System.out.print(" " + (8-i) + " ");	// a sz�mok lerajzol�sa a t�bla oldal�n
		
			for(int j=0;j<8;j++) {
				System.out.print("|" + board[i][j].getJeloles() + "|"); // kell majd szimb�lum vagy jel�l�s a sakk b�buknak
			}
			
			System.out.print("\n   ");
			
			for(int j=0;j<8;j++) {
				System.out.print(" --- ");
			}
			System.out.print("\n");
		}
	}
	
	//ha egy gyalog be�rkezik a v�gpontba, el�l�ptethetj�k vez�rr�, husz�rr�, fut�v�, vagy b�sty�v�
	public static void promotion() {
		Scanner scanner = new Scanner(System.in);
		for(int i=0;i<8;i++)
		if(board[0][i].getSzin()=="feh�r"  && board[0][i].getFajta() == "gyalog") {
			System.out.println("El�l�ptetheted a gyalogodat! A k�vetkez�k k�z�l v�laszthatsz: Vez�r, Husz�r, Fut�, B�stya");
			boolean ellenoriz = true;
			while(ellenoriz) {
			String input = scanner.nextLine().trim();
			switch (input){
				case "Vez�r": board[0][i] = new Vezer("feh�r"); ellenoriz=false; break;
				case "Husz�r": board[0][i] = new Huszar("feh�r"); ellenoriz=false; break;
				case "Fut�": board[0][i] = new Futo("feh�r"); ellenoriz=false; break;
				case "B�stya": board[0][i] = new Bastya("feh�r"); ellenoriz=false; break;
				default:System.out.println("A lehet�s�gek k�z�l v�lassz...");
			}
			}
		}
		else if(board[7][i].getSzin()=="fekete"  && board[7][i].getFajta() == "gyalog") {
			System.out.println("El�l�ptetheted a gyalogodat! A k�vetkez�k k�z�l v�laszthatsz: Vez�r, Husz�r, Fut�, B�stya");
			boolean ellenoriz = true;
			while(ellenoriz) {
			String input = scanner.nextLine().trim();
			switch (input){
				case "Vez�r": board[7][i] = new Vezer("fekete"); ellenoriz=false; break;
				case "Husz�r": board[7][i] = new Huszar("fekete"); ellenoriz=false; break;
				case "Fut�": board[7][i] = new Futo("fekete"); ellenoriz=false; break;
				case "B�stya": board[7][i] = new Bastya("fekete"); ellenoriz=false; break;
				default:System.out.println("A lehet�s�gek k�z�l v�lassz...");
			}
			}
		}
	}
	

	// ellen�rizz�k, hogy az ellenf�l sakkban �ll-e ( m�r saj�t magunkra ellen�rizz�k )
		public static boolean sakkEllenorzes(String szin) {
			// az ellenf�l sz�n�re n�zz�k a sakkot
			String Kiraly;
			if(szin == "feh�r") {
				Kiraly = "fekete";
			}else {
				Kiraly= "feh�r";
			}
			
			// megkeress�k a kir�ly hely�t
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					// ha a fajt�ja kir�ly, �s a sz�ne az ellenf�l�, akkor j� helyen j�runk
					if((board[i][j].getFajta() == "kir�ly") && board[i][j].getSzin() == Kiraly) {
						// megn�zz�k az �sszes b�bunkra, hogy �thet�-e vele az ellenf�l kir�lya, ha igen, akkor igaz �rt�ket adunk vissza
						for(int k=0;k<8;k++) {
							for(int l=0;l<8;l++) {
								// ha nem �res, �s a mi sz�n�nk, akkor j�
								if((board[k][l].getFajta()!= "ures") && (board[k][l].getSzin()==szin)) {
									// v�ltoz�k l�trehoz�sa a mozoghatE met�dus megh�v�s�hoz
									Mezo tamado = board[k][l];
									int[] honnan = {l,k};
									int[] hova = {j,i};
									// megvizsg�ljuk hogy mozoghat-e a b�bu a kir�lyra
									if(tamado.mozoghatE(honnan,hova, szin)) {
										//System.out.println("A " + Kiraly + " kir�ly sakkban �ll!");
										return true;
									}
								}
							}
						}
					}
				}
			}
			return false;
		}
	
		
		public static boolean sakkMatt(String szin) { // sajat kor elejen tesztel
		String szin2;
		if(szin == "fekete") {
			szin2 = "feh�r";
		}
		else {
			szin2 = "fekete";
		}
		// ha az ellenf�l sakkban �ll
		boolean x = sakkEllenorzes(szin);


	    //vegigmegy a tabla osszes mezojen
	    for(int i=0;i<8;i++) {
	    	for(int j=0;j<8;j++) {
	    		if(board[i][j].getSzin()== szin) {
	    			Mezo vedo= board[i][j];
	    			//ha talalt sajat babut akkor megnezi az osszes lehetseges lepeset
	    			for(int k=0;k<8;k++) {
	    				for(int l=0;l<8;l++) {
	    					int[] honnan = {j,i};
	    					int[] hova = {l,k};
	    					//ha van olyan lepes ahol nincs sakk akkor nincs sakk matt 
	    					boolean y = vedo.mozoghatE(honnan,hova, szin);
	    					if(y) {
	    						Mezo helpermezo = board[k][l];
	    						boardUpdate(honnan, hova);
	    						boolean z = sakkEllenorzes(szin2);
	    						if(!z) {
	    							boardUpdate(hova, honnan);
	    							board[k][l]= helpermezo;
	    							return false;
	    						}
	    						else {
	    							boardUpdate(hova, honnan);
	    							board[k][l]= helpermezo;
	    						}
	    					}
	    				}
	    			}
	    		}
	    	}
	    }
	    System.out.println("Sakk Matt! V�ge a j�t�knak!");
	    return true;
	}	

		
	//main
	public static void main(String[] args) {
		// fekete �s feh�r l�p�sek 
		Lepes feher = new Lepes("feh�r");
		Lepes fekete = new Lepes("fekete");
		// p�lya gener�l�sa
		boardGenerate(); 
		boolean fut = true;
		// f� ciklus
		while(fut){
			// lefut mindk�t f�lnek
			for(int i = 1; i <= 2; i++){ 
				// p�lya lerajzol�sa
				boardDraw(); 
				// sakk ellen�rz�se
				boolean sakk = false;
				boolean matt = false;
				if(i==1) {
					sakk =sakkEllenorzes("fekete");
					if(sakk == true) {
						System.out.println("A feh�r kir�ly sakkban �ll!");
						matt = sakkMatt("feh�r");
						if(matt == true) {
							System.out.println("A fekete j�t�kos nyert! Gratul�lok!");
							fut= false;
							break;
						}
						
					}
				}else {
					sakk =sakkEllenorzes("feh�r");
					if(sakk == true) {
						System.out.println("A fekete kir�ly sakkban �ll!");
						matt = sakkMatt("fekete");
						if(matt == true) {
							System.out.println("A feh�r j�t�kos nyert! Gratul�lok!");
							fut=false;
							break;
						}
					}
				}		


				// t�mb l�trehoz�sa a bek�rt adatoknak
				int lepes[][] = new int[2][2];
				
				while(true){
					// l�p�s lefuttat�sa att�l f�gg�en,hogy ki l�p 
					if(i == 1){ 
						lepes = feher.lepes();
					}
					else{ 
						lepes = fekete.lepes();
					}
					// ha -1et kapsz vissza, akkor �jrakezdi a ciklust a continue-val
					if(lepes[0][0] == -1){ 
						System.out.println("Ezzel nem l�phetsz!");
						continue;
					}
					
					int[] honnan = lepes[0];
					int[] hova = lepes[1];
					
					Mezo honnanMezo = board[honnan[1]][honnan[0]];
				
					boolean checkValue;
					if(i == 1){
						checkValue = honnanMezo.mozoghatE(honnan, hova, "feh�r"); 
					}
					else{
						checkValue = honnanMezo.mozoghatE(honnan, hova, "fekete");
					}					
					if(checkValue){
						// ha minden j�, akkor a boardUpdate �thelyezi a t�mbelemet az �j helyre
						Mezo helpermezo = board[hova[0]][hova[1]];
						boardUpdate(honnan, hova);
						if(i==1) {
							boolean x = sakkEllenorzes("fekete");
							if (x) {
								System.out.println("Oda nem l�phetsz! Sakkba ker�ln�l!");
								boardUpdate(hova, honnan);
								board[hova[0]][hova[1]] = helpermezo;
								continue;
							}
						}
						else {
							boolean x = sakkEllenorzes("feh�r");
							if (x) {
								System.out.println("Oda nem l�phetsz! Sakkba ker�ln�l!");
								boardUpdate(hova, honnan);
								board[hova[0]][hova[1]] = helpermezo;
								continue;
							}
						}
						// paraszt el�l�ptet�se, ha bee�rt az ellenf�l vonal�ra
						promotion();	
						break;
					}
					System.out.println("Oda nem l�phetsz!"); 
				}
			}
		}
	}
}
