
public class Vezer extends Babuk {

	public Vezer(String szin) {
		super(szin,"vezer");
		if(szin == "fehér") {
			jeloles = "wVe";
		}
		else {
			jeloles = "bVe";
		}
	}
	
	
	public boolean mozoghatE(int[]honnan,int[]hova,String szin) {
		
		int honnanX = honnan[0];
		int honnanY = honnan[1];
		int hovaX = hova[0];
		int hovaY = hova[1];
				
		// a bástya és a futó kombinácója
		String irany;
		String egyenesatlos; // átlós vagy egyenes
		// u.a., mint a bástya
		if(hovaY == honnanY){ 
			if(hovaX > honnanX){
				irany = "jobb";
				egyenesatlos = "egyenes";
			}
			else{
				irany = "bal";
				egyenesatlos = "egyenes";
			}
		}
		
		else if(hovaX == honnanX){
			if(hovaY > honnanY){
				irany = "le";
				egyenesatlos = "egyenes";
			}
			else{
				irany = "fel";
				egyenesatlos = "egyenes";
			}
		}
		// u.a., mint a futó
		else if(hovaX > honnanX){
			if(hovaY < honnanY){
				irany = "jobbraFel";
				egyenesatlos = "atlos";
			}
			else{
				irany = "jobbraLe";
				egyenesatlos = "atlos";
			}
		}
		else if(hovaX < honnanX){
			if(hovaY < honnanY){
				irany = "balraFel";
				egyenesatlos = "atlos";
			}
			else{
				irany = "balraLe";
				egyenesatlos = "atlos";
			}
		}
		else{
			return false;
		}
		
		Mezo testMezo;
		//átlós (ugyanaz, mint a futó)
		if(egyenesatlos == "atlos"){
			int tavolsag = Math.abs(hovaX - honnanX);
		
			for(int i = 1; i <= tavolsag; i++){
				if(Math.abs(hovaY - honnanY) != Math.abs(hovaX - honnanX)){
					return false;
				}
				if(irany == "jobbraFel"){
					testMezo = Board.board[honnanY - i][honnanX + i];
				}
				else if(irany == "jobbraLe"){
					testMezo = Board.board[honnanY + i][honnanX + i];
				}
				else if(irany == "balraFel"){
					testMezo = Board.board[honnanY - i][honnanX - i];
				}
				else{
					testMezo = Board.board[honnanY + i][honnanX - i];
				}
			
				if((testMezo.getFajta() != "ures") && (i != tavolsag)){
					return false;
				}
				else if((i == tavolsag) && ((testMezo.getSzin() != szin) || (testMezo.getFajta() == "ures"))){
					return true;
				}
			}
		}
		
		else{ //egyenes ( ugyanaz, mint a bástya)
			if((irany == "jobb") || (irany == "bal")){
				int max = Math.abs(hovaX - honnanX); 
		
				for(int i = 1; i <= max; i++){ 
					if(irany == "jobb"){
						testMezo = Board.board[honnanY][honnanX + i];
					
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
		}
		return false;
	}

}