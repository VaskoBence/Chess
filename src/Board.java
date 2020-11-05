import java.util.Scanner;

public class Board {
	public static  Mezo board[][] = new Mezo[8][8]; //pálya példányosítása

	
	// pálya feltöltése a játék kezdetekor
	public static void boardGenerate() {
		// fekete bábuk
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
			board[6][i] = new Gyalog("fehér");
		}
		
		// üres mezõk
		for(int i = 2; i < 6; i++){ 
			for(int j = 0; j < 8; j++){
				board[i][j] = new Ures();
			}
		}
		
		// fehér bábuk
		board[7][0] = new Bastya("fehér");
		board[7][1] = new Huszar("fehér");
		board[7][2] = new Futo("fehér");
		board[7][3] = new Vezer("fehér");
		board[7][4] = new Kiraly("fehér");
		board[7][5] = new Futo("fehér");
		board[7][6] = new Huszar("fehér");
		board[7][7] = new Bastya("fehér");
		
	}
	
	// az új helyet a bábu típusára cseréli, a régit pedig üres mezõre
	public static void boardUpdate(int[] startLoc, int[] newLoc) {
		board[newLoc[1]][newLoc[0]] = board[startLoc[1]][startLoc[0]];
		board[startLoc[1]][startLoc[0]] = new Ures();
	}
	
	// a sakktábla lerajzolása
	public static void boardDraw() {
		System.out.print("\n   ");
		
		for(Betuk betu : Betuk.values()) {
			System.out.print("  " + betu + "  "); //a betûk lerajzolása a tábla tetején
		}
		
		System.out.print("\n   ");
		
		for(int i=0;i<8;i++) {
			System.out.print(" --- ");
		}
		
		System.out.print("\n");
		for(int i=0;i<8;i++) {
			System.out.print(" " + (8-i) + " ");	// a számok lerajzolása a tábla oldalán
		
			for(int j=0;j<8;j++) {
				System.out.print("|" + board[i][j].getJeloles() + "|"); // kell majd szimbólum vagy jelölés a sakk bábuknak
			}
			
			System.out.print("\n   ");
			
			for(int j=0;j<8;j++) {
				System.out.print(" --- ");
			}
			System.out.print("\n");
		}
	}
	
	//ha egy gyalog beérkezik a végpontba, elõléptethetjük vezérré, huszárrá, futóvá, vagy bástyává
	public static void promotion() {
		Scanner scanner = new Scanner(System.in);
		for(int i=0;i<8;i++)
		if(board[0][i].getSzin()=="fehér"  && board[0][i].getFajta() == "gyalog") {
			System.out.println("Elõléptetheted a gyalogodat! A következõk közül választhatsz: Vezér, Huszár, Futó, Bástya");
			boolean ellenoriz = true;
			while(ellenoriz) {
			String input = scanner.nextLine().trim();
			switch (input){
				case "Vezér": board[0][i] = new Vezer("fehér"); ellenoriz=false; break;
				case "Huszár": board[0][i] = new Huszar("fehér"); ellenoriz=false; break;
				case "Futó": board[0][i] = new Futo("fehér"); ellenoriz=false; break;
				case "Bástya": board[0][i] = new Bastya("fehér"); ellenoriz=false; break;
				default:System.out.println("A lehetõségek közül válassz...");
			}
			}
		}
		else if(board[7][i].getSzin()=="fekete"  && board[7][i].getFajta() == "gyalog") {
			System.out.println("Elõléptetheted a gyalogodat! A következõk közül választhatsz: Vezér, Huszár, Futó, Bástya");
			boolean ellenoriz = true;
			while(ellenoriz) {
			String input = scanner.nextLine().trim();
			switch (input){
				case "Vezér": board[7][i] = new Vezer("fekete"); ellenoriz=false; break;
				case "Huszár": board[7][i] = new Huszar("fekete"); ellenoriz=false; break;
				case "Futó": board[7][i] = new Futo("fekete"); ellenoriz=false; break;
				case "Bástya": board[7][i] = new Bastya("fekete"); ellenoriz=false; break;
				default:System.out.println("A lehetõségek közül válassz...");
			}
			}
		}
	}
	

	// ellenõrizzük, hogy az ellenfél sakkban áll-e ( már saját magunkra ellenõrizzük )
		public static boolean sakkEllenorzes(String szin) {
			// az ellenfél színére nézzük a sakkot
			String Kiraly;
			if(szin == "fehér") {
				Kiraly = "fekete";
			}else {
				Kiraly= "fehér";
			}
			
			// megkeressük a király helyét
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					// ha a fajtája király, és a színe az ellenfélé, akkor jó helyen járunk
					if((board[i][j].getFajta() == "király") && board[i][j].getSzin() == Kiraly) {
						// megnézzük az összes bábunkra, hogy üthetõ-e vele az ellenfél királya, ha igen, akkor igaz értéket adunk vissza
						for(int k=0;k<8;k++) {
							for(int l=0;l<8;l++) {
								// ha nem üres, és a mi színünk, akkor jó
								if((board[k][l].getFajta()!= "ures") && (board[k][l].getSzin()==szin)) {
									// változók létrehozása a mozoghatE metódus meghívásához
									Mezo tamado = board[k][l];
									int[] honnan = {l,k};
									int[] hova = {j,i};
									// megvizsgáljuk hogy mozoghat-e a bábu a királyra
									if(tamado.mozoghatE(honnan,hova, szin)) {
										//System.out.println("A " + Kiraly + " király sakkban áll!");
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
			szin2 = "fehér";
		}
		else {
			szin2 = "fekete";
		}
		// ha az ellenfél sakkban áll
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
	    System.out.println("Sakk Matt! Vége a játéknak!");
	    return true;
	}	

		
	//main
	public static void main(String[] args) {
		// fekete és fehér lépések 
		Lepes feher = new Lepes("fehér");
		Lepes fekete = new Lepes("fekete");
		// pálya generálása
		boardGenerate(); 
		boolean fut = true;
		// fõ ciklus
		while(fut){
			// lefut mindkét félnek
			for(int i = 1; i <= 2; i++){ 
				// pálya lerajzolása
				boardDraw(); 
				// sakk ellenõrzése
				boolean sakk = false;
				boolean matt = false;
				if(i==1) {
					sakk =sakkEllenorzes("fekete");
					if(sakk == true) {
						System.out.println("A fehér király sakkban áll!");
						matt = sakkMatt("fehér");
						if(matt == true) {
							System.out.println("A fekete játékos nyert! Gratulálok!");
							fut= false;
							break;
						}
						
					}
				}else {
					sakk =sakkEllenorzes("fehér");
					if(sakk == true) {
						System.out.println("A fekete király sakkban áll!");
						matt = sakkMatt("fekete");
						if(matt == true) {
							System.out.println("A fehér játékos nyert! Gratulálok!");
							fut=false;
							break;
						}
					}
				}		


				// tömb létrehozása a bekért adatoknak
				int lepes[][] = new int[2][2];
				
				while(true){
					// lépés lefuttatása attól függõen,hogy ki lép 
					if(i == 1){ 
						lepes = feher.lepes();
					}
					else{ 
						lepes = fekete.lepes();
					}
					// ha -1et kapsz vissza, akkor újrakezdi a ciklust a continue-val
					if(lepes[0][0] == -1){ 
						System.out.println("Ezzel nem léphetsz!");
						continue;
					}
					
					int[] honnan = lepes[0];
					int[] hova = lepes[1];
					
					Mezo honnanMezo = board[honnan[1]][honnan[0]];
				
					boolean checkValue;
					if(i == 1){
						checkValue = honnanMezo.mozoghatE(honnan, hova, "fehér"); 
					}
					else{
						checkValue = honnanMezo.mozoghatE(honnan, hova, "fekete");
					}					
					if(checkValue){
						// ha minden jó, akkor a boardUpdate áthelyezi a tömbelemet az új helyre
						Mezo helpermezo = board[hova[0]][hova[1]];
						boardUpdate(honnan, hova);
						if(i==1) {
							boolean x = sakkEllenorzes("fekete");
							if (x) {
								System.out.println("Oda nem léphetsz! Sakkba kerülnél!");
								boardUpdate(hova, honnan);
								board[hova[0]][hova[1]] = helpermezo;
								continue;
							}
						}
						else {
							boolean x = sakkEllenorzes("fehér");
							if (x) {
								System.out.println("Oda nem léphetsz! Sakkba kerülnél!");
								boardUpdate(hova, honnan);
								board[hova[0]][hova[1]] = helpermezo;
								continue;
							}
						}
						// paraszt elõléptetése, ha beeért az ellenfél vonalára
						promotion();	
						break;
					}
					System.out.println("Oda nem léphetsz!"); 
				}
			}
		}
	}
}
