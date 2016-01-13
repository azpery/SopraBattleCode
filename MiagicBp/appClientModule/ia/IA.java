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
	
	
	
	public String analyseHabits(Bot miage,Bot adve,ArrayList<String> coupsMiagic,ArrayList<String> coups,String dernierCoup){
		String move="";
		int nbCoups = coups.size();
		int reload = 0;
		int shoot = 0;
		int cover = 0;
		int aim = 0; 
		Coups valCoup;
		String coup;
		
		
		for(int i = 0; i < nbCoups; i++){
			coup = (String) coups.get(i);
			if(coup == dernierCoup){
				valCoup = Coups.valueOf(coup);
				switch(valCoup){
				case SHOOT:
					shoot++;
					break;
				case AIM:
					aim++;
					break;
				case COVER:
					cover++;
					break;
				case RELOAD: 
					reload++;
					break;
				}
			}
		}
		int max =  Math.max(Math.max(Math.max(shoot,reload),cover),aim);
		
		
		if(max == reload) {
			if (miage.getNbBullet()== 0&&dernierCoup!="AIM"){
				move = "RELOAD";
			} else {
				move = "SHOOT";
			}
		}
		if(max == shoot) {
			move = "COVER";
		}
		if(max == cover) {
			move = "RELOAD";
		}
		if(max == aim) {
			if (miage.getNbBullet()> 0){
				move = "SHOOT";
			} else {
				move = "RELOAD";
			}
		}
		
		System.out.println("\n\n\n Coup A JOUER :" + move);
		
		return move;
		
	}
	
	
	public String devinerFuturCoup(Bot miage,Bot adve,ArrayList<String> coupsMiagic,ArrayList<String> coupsAdv,String dernierCoup)
	{
		
		
		String c = "NA"; 
		
		coupsAdv.add(dernierCoup); 
		
		// On regarde si le dernier coup joué par l'adversaire à déjà été fait //
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
	
	
	
	
	private enum Coups {
		SHOOT, RELOAD, COVER, AIM;
	}
   }

	
