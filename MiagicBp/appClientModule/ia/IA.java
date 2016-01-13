package ia;

import java.util.ArrayList;


import battleCode.Bot;
 



public class IA {


    int pdvCritique = 3;  // Niveau critique à éviter // 
	
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
				}
			}
			if(adve.getNbBullet()>0 && dernierCoup.equals("AIM")){
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
		}
		System.out.println("\n\n\n FUTUR COUP JOUER PAR ADV :" + c);
		
		String dernierCoupMiage = "";
		if(coupsMiagic.size()>0){
			dernierCoupMiage =coupsMiagic.get(coupsMiagic.size()-1);
		}
		else{
			dernierCoupMiage = "NA";
		}
		
		c = deciderAction(dernierCoup,c,dernierCoupMiage,miage,adve);
		
		System.out.println("ON VA JOUER : " + c);
		return  c; 
	}
	
	
	public String deciderAction(String dernierAdv,String predAdv,String dernierMia,Bot miage,Bot adve){
		String s =""; 
		boolean decisionPrise = false; 

		boolean shootMia 	  = false; 
		boolean coverMia	  = false; 
	   
	
	    if(miage.getNbBullet()>0){
    		shootMia = true; 
	    }
	    if(miage.getNbBouclier()>0){
    		coverMia = true; 
	    }
		
		// SI NOTRE ADVERSAIRE VA TIRER // 
		if(predAdv.equals("SHOOT"))
		{
		  
			// SOUS CONTRAITES -> TIRER + CACHER // 
			
			// ****** COVER ***** // 
			// Pour se cacher -> Contrainte de bouclier // 
			
			// Pour se cacher il faut absolument un bouclier // 
			
				if(coverMia){
					// On peut se cacher //	
					// On se pose la question si on doit se cacher // 
					// Manque de vie | Si l'autre IA à visé // 
					if(miage.getNbVies()<=this.pdvCritique){
						s = "COVER";
						decisionPrise=true; 
					}
					if(dernierAdv.equals("AIM")){
						s="COVER"; 
						decisionPrise=true; 
					}
				}	
				// ****** SHOOT ***** // 
				if(!decisionPrise){				// Si pas de décision prise // 
					if(shootMia){  // Si on a des balles // 
						s = "SHOOT"; 
						decisionPrise = true; 
					}
					else{ // Si pas de décision 
						s = "RELOAD"; 
						decisionPrise = true; 
					}		
			}	
		} // FIN DECISION SHOOT // 
		
		// SI NOTRE ADVERSAIRE VA VISER // 
		else if(predAdv.equals("AIM"))
		{
			if(shootMia) // Si on peut tirer // 
			{
				if(adve.getNbVies()<=pdvCritique){ // Si il a peu de point de vie // 
					s = "SHOOT"; 
					decisionPrise = true; 
				}
				else if(coverMia||adve.getNbVies()>pdvCritique){
					s = "AIM"; 
					decisionPrise = true; 
				}
				else{
					s = "SHOOT"; 
					decisionPrise = true; 
				}
			}
			else
			{
				 s = "RELOAD"; 
				 decisionPrise = true; 
			}
		} // FIN ADVERSAIRE VISER // 
		
		else if(predAdv.equals("COVER")){
			
				// Si on peut tirer // 
				if(shootMia){
					if(dernierMia.equals("AIM")){
						s = "AIM"; 
						decisionPrise = true; 
					}
					else if(adve.getNbVies()<=this.pdvCritique){
						s = "SHOOT";
						decisionPrise = true; 
					}
				}
				else{   // Sinon // 
					 s = "RELOAD"; 
					 decisionPrise = true; 
				}
		}
		else if(predAdv.equals("RELOAD")){
				if(shootMia){
					if(dernierMia.equals("AIM")){
						s = "SHOOT"; 
						decisionPrise = true; 
					}
					else if(adve.getNbVies()<=this.pdvCritique){
						s = "SHOOT"; 
						decisionPrise = true; 
					}
				}
				else{   // Sinon // 
				 s = "RELOAD";
				 decisionPrise = true; 
				}	
		}
		else if(predAdv.equals("NA")){
			
			System.out.println("\n\n ------------------------DANS NA");
			if(shootMia){
				if(dernierMia.equals("RELOAD")){
					s = "AIM"; 
					decisionPrise = true; 
				}
				else {
					s = "SHOOT"; 
					decisionPrise = true; 
				}
			}
			else{   // Sinon // 
			 s = "RELOAD";
			 decisionPrise = true; 
			}	
		}
		return s; 
	}
	

	





	private enum Coups {
		SHOOT, RELOAD, COVER, AIM;
	}
   }

	
