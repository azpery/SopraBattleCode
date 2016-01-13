package ia;

import java.util.ArrayList;

import battleCode.Bot;
 



public class IA {

	private ArrayList<String> coups; 
    
	
	public IA(){
		
		coups = new ArrayList<String>(); 
		
	}
	
	
	
	
	
	public boolean devinerCover(Bot miage,Bot adve,ArrayList<String> coupsMiagic,ArrayList<String> coupsAdv,String dernierCoup){
		
		boolean res = false;  
		
		
		return res; 
	}
	
	public boolean devinerShoot(Bot miage,Bot adve,ArrayList<String> coupsMiagic,ArrayList<String> coupsAdv,String dernierCoup){
		
		boolean res = false;  
		
		
		return res; 
	}
	
	public boolean devinerAIM(Bot miage,Bot adve,ArrayList<String> coupsMiagic,ArrayList<String> coupsAdv,String dernierCoup){
		
		boolean res = false;  
		
		
		return res; 
	}
	
	public boolean devinerReload(Bot miage,Bot adve,ArrayList<String> coupsMiagic,ArrayList<String> coupsAdv,String dernierCoup){
		
		boolean res = false;  
		
		
		return res; 
	}
	
	
	
	
	
	public String devinerFuturCoup(Bot miage,Bot adve,ArrayList<String> coupsMiagic,ArrayList<String> coupsAdv,String dernierCoup)
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
		// Si pas de prédiction alors // 
		if(c == "NA"){
			
			
			if(coupsMiagic.size()>0){	
				if(coupsMiagic.get(coupsMiagic.size()-1)=="AIM"){
					c = "COVER";
				}
			}
			
			
			if(adve.getNbBullet()==0&&c!="COVER"){
				
				    c= "RELOAD";
			}
			if(adve.getNbBouclier()==0&&c=="COVER"){
				    
				c = "NA";
			}
		
			
		}
		System.out.println("\n\n\n FUTUR COUP JOUER PAR ADV :" + c);
		return  c; 
	}
	
   }

	
