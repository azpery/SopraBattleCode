package ia;

import java.util.ArrayList;


import battleCode.Bot;
 



public class IA {


    
	
	public IA(){
		
		
		
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
	
	
	/*
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
	*/
	
	public String devinerFuturCoup(Bot miage,Bot adve,ArrayList<String> coupsMiagic,ArrayList<String> coupsAdv,String dernierCoup,int nbCoupRestant)
	{
		String c = "NA"; 
		
		if(!dernierCoup.equals("NA"))
		{
			System.out.println("AJOUTER");
			coupsAdv.add(dernierCoup); 
		}
		// ON UTILISE LE MODELE // 
		/*
		// On regarde si le dernier coup joué par l'adversaire à déjà été fait //
		if(coupsAdv.contains(dernierCoup)){
	
			if(coupsAdv.size()>2)
			{
				System.out.println("TAILLE TAB" + coupsAdv.size());
				for(int i =0;i<coupsAdv.size()-1;i++)
				{
					String s = coupsAdv.get(i);
					if(s.equals(dernierCoup))
					{
						int pos = coupsAdv.indexOf(dernierCoup); 
						System.out.println("POSITION : " +  pos);
						System.out.println("TailleTaa" + coupsAdv.size());
						if(pos < coupsAdv.size()-2)
						{
							System.out.println("Ce coup a été joué par l'adversaire " + dernierCoup + " Au coup : " + pos*2);
							c = coupsAdv.get(pos+1); 
						}
					}
				}
			}
		}	
		*/
		// SI DEFAUT // 
		if(c == "NA"){
			
			if(miage.getNbBullet()>0)
			{	
				if(coupsMiagic.size()>0){
					System.out.println("**** DGETBULLET \n\n\n\n\n");

					if(coupsMiagic.get(coupsMiagic.size()-1).equals("AIM")){
						c = "COVER";
					}
				}
			}
			if(adve.getNbBullet()>0 && coupsAdv.get(coupsAdv.size()-1).equals("AIM")){
						c = "SHOOT"; 
			}
			
			if(adve.getNbBullet()==0 ){
				if(coupsMiagic.size()>0){
					if(!coupsMiagic.get(coupsMiagic.size()-1).equals("AIM")){
						c= "RELOAD";
					}
				}
				else{
						c= "RELOAD";
					}	
			}
			/*
			else if(adve.getNbBouclier()==0){
				if(coupsMiagic.size()>0){
					if(!coupsMiagic.get(coupsMiagic.size()-1).equals("AIM")){
						c = "NA";
					}
				}
			}
			*/
		}
		System.out.println("\n\n\n FUTUR COUP JOUER PAR ADV :" + c);
		return  c; 
	}
	
	
	
	
	private enum Coups {
		SHOOT, RELOAD, COVER, AIM;
	}
   }

	
