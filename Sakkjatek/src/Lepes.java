import java.util.Scanner;


public class Lepes {
		private String szin;
		private Scanner scanner = new Scanner(System.in);
		
		public Lepes(String szin) {
			this.szin=szin;
		}
		
		
		// a bek�rt karakter sz�mm� konvert�l�sa 
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
	    
	    
	    // a bek�rt karakter t�pus� sz�m sz�mm� konvert�l�sa
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
	    
	    
	    // l�p�s bek�r�se, ami visszaad egy t�mb�t a bek�rt adatokkal(honnan l�p, hova l�p)
	    public int[][] lepes(){
	    	System.out.println("A " + szin + " sz�n� j�t�kos j�n: ");
	    	int[][] lepes = new int[2][2];
			for(int i = 1; i <= 2; i++){
				while(true){
					// els� lefut�sn�l megk�rdezi, hogy honnan, m�sodik lefut�sn�l pedig hogy hova l�pjen
					if(i == 1){ 
						System.out.print("Mivel szeretn�l l�pni? (Pl.: B2)");
					}
					else{ 
						System.out.print("Hova szeretn�l l�pni? (Pl.: B4)");
					}
					// bek�r�s
					String Input = scanner.nextLine().trim();
					//ellen�rizz�k, hogy az els� karakter
					if(!Input.isEmpty() && Input.length() == 2 && !(Input.contains(" ") || Input.contains("\t"))){
						// ellen�rizz�k, hogy az els� karakter egy karakter, a m�sodik parakter pedig egy sz�m
						if(!Character.isDigit(Input.charAt(0)) && Character.isDigit(Input.charAt(1))){
				
							int x, y;
							//karakterek helyess�g�nek ellen�rz�se
							if((x = ChartoNum(Character.toUpperCase(Input.charAt(0)))) != -1){
								if((y = NumtoNum(Input.charAt(1))) != -1){
									y = 8 - y; //az y �rt�ket megcser�lj�k, mivel ford�tva van a sakkt�bl�n
									int tempArray[] = {x, y};
									if(i == 1){
										if(Board.board[y][x].getFajta() == "ures" || Board.board[y][x].getSzin() != szin){ 
											// ha els� k�rben egy �res helyr�l akarunk l�pni, akkor egy -1-ekkel teli t�mb�t k�ld vissza
											// ami majd hiba�zenethez vezet a main-ben
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
					System.out.println("Nem j�l �rtad be! Pr�b�ld �jra!"); 
				}		
			}
			return lepes;
	    }
}