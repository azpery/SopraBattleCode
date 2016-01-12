package ia;
import java.util.ArrayList;



public class IA {

	private ArrayList<String> coups; 
    
	
	public IA(){
		
		coups = new ArrayList<String>(); 
		
	}
	
	
	
	
	
	public String devinerFuturCoup(ArrayList<String> coupsAdv,String dernierCoup)
	{
		String c = "NA"; 
		
		coupsAdv.add(dernierCoup); 
		
		// On regarde le dernier coup joué par l'adversaire //
		if(coupsAdv.contains(dernierCoup)){
			
			int pos = coupsAdv.indexOf(dernierCoup); 
			
			if(pos < coupsAdv.size()-1){
			c = coupsAdv.get(pos+1); 
			
			}
		}
		System.out.println("\n\n\n FUTUR COUP JOUER PAR ADV :" + c);
		return  c; 
	}
	
	
	
}
