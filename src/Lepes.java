import java.util.Scanner;


public class Lepes {
		private String szin;
		private Scanner scanner = new Scanner(System.in);
		
		public Lepes(String szin) {
			this.szin=szin;
		}
		
		
		// a bekért karakter számmá konvertálása 
	    private int ChartoNum(char charIn) {
	    	int szam = -1;
	    	int szamlalo = 0;
	    		for(Betuk betu : Betuk.values()) {
	    			if(betu.toString().charAt(0) == charIn) {
	    				szam = szamlalo;
	    			}
	    			szamlalo++;
	    		}
	    	return szam;
	    }
	    
	    
	    // a bekért karakter típusú szám számmá konvertálása
	    private int NumtoNum(char charIn) {
	    	int szam = -1;
	    	int konvertaltszam = Integer.parseInt(String.valueOf(charIn));
	    	
	    	for( int i=1;i<=8;i++) {
	    		if(i==konvertaltszam) {
	    			szam=konvertaltszam;
	    		}
	    	}
	    	return szam;
	    }
	    
	    
	    // lépés bekérése, ami visszaad egy tömböt a bekért adatokkal(honnan lép, hova lép)
	    public int[][] lepes(){
	    	System.out.println("A " + szin + " színû játékos jön: ");
	    	int[][] lepes = new int[2][2];
			for(int i = 1; i <= 2; i++){
				while(true){
					// elsõ lefutásnál megkérdezi, hogy honnan, második lefutásnál pedig hogy hova lépjen
					if(i == 1){ 
						System.out.print("Mivel szeretnél lépni? (Pl.: B2)");
					}
					else{ 
						System.out.print("Hova szeretnél lépni? (Pl.: B4)");
					}
					// bekérés
					String Input = scanner.nextLine().trim();
					//ellenõrizzük, hogy az elsõ karakter
					if(!Input.isEmpty() && Input.length() == 2 && !(Input.contains(" ") || Input.contains("\t"))){
						// ellenõrizzük, hogy az elsõ karakter egy karakter, a második parakter pedig egy szám
						if(!Character.isDigit(Input.charAt(0)) && Character.isDigit(Input.charAt(1))){
				
							int x, y;
							//karakterek helyességének ellenõrzése
							if((x = ChartoNum(Character.toUpperCase(Input.charAt(0)))) != -1){
								if((y = NumtoNum(Input.charAt(1))) != -1){
									y = 8 - y; //az y értéket megcseréljük, mivel fordítva van a sakktáblán
									int tempArray[] = {x, y};
									if(i == 1){
										if(Board.board[y][x].getFajta() == "ures" || Board.board[y][x].getSzin() != szin){ 
											// ha elsõ körben egy üres helyrõl akarunk lépni, akkor egy -1-ekkel teli tömböt küld vissza
											// ami majd hibaüzenethez vezet a main-ben
											tempArray[0] = -1;																
											tempArray[1] = -1;
											int[][] errorArray = {tempArray, tempArray};
											return errorArray;
										}
									}
									
									lepes[i - 1] = tempArray;
									break;
								}
							}
						}
					}
					System.out.println("Nem jól írtad be! Próbáld újra!"); 
				}		
			}
			return lepes;
	    }
}