package ia;

import java.util.ArrayList;


import battleCode.Bot;




public class IA {
	private ArrayList<Schema> schemas;
	private ArrayList<Schema> schemasTrois;
	private ArrayList<Schema> schemasDeux;
	private ArrayList<Schema> schemasUn;
	private ArrayList<Schema> schemasZero;

	public IA(){
		this.schemas = new ArrayList<Schema>();
		this.schemasTrois = new ArrayList<Schema>();
		this.schemasDeux = new ArrayList<Schema>();
		this.schemasUn = new ArrayList<Schema>();
		this.schemasZero = new ArrayList<Schema>();
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


	public String devinerFuturCoup(Bot miage,Bot adve,ArrayList<String> coupsMiagic,ArrayList<String> coupsAdv,String dernierCoup,int nbCoupRestant)
	{
		String c = "NA"; 
		this.schemas = new ArrayList<Schema>();
		this.schemasZero = new ArrayList<Schema>();
		this.schemasUn = new ArrayList<Schema>();
		this.schemasDeux = new ArrayList<Schema>();
		this.schemasTrois = new ArrayList<Schema>();
		if(!dernierCoup.equals("NA"))
		{
			System.out.println("AJOUTER");
			coupsAdv.add(dernierCoup); 
		}
		
		if(coupsAdv.size() > 2){
			Schema cible = new Schema(dernierCoup, coupsAdv.get(coupsAdv.size() - 2), coupsMiagic.get(coupsMiagic.size() - 1));
			//this.schemas.add(cible);
			int i ;
			for(i = 1; i <= coupsAdv.size() - 2; i++){
				if(coupsAdv.get(i).equals(dernierCoup)){
					Schema s = new Schema(coupsAdv.get(i),coupsAdv.get(i-1),coupsMiagic.get(i));
					s.setCoupSuivant(coupsAdv.get(i+1));
					this.schemas.add(s);
				}
			}
			for(i = 0 ; i <= this.schemas.size() - 1 ; i++){
				Schema leSchema = this.schemas.get(i);
				leSchema.comparer(cible);
				switch(this.schemas.get(i).getPuissance()){
				case 3:
					this.schemasTrois.add(leSchema);
					break;
				case 2:
					this.schemasDeux.add(leSchema);
					break;
				case 1: 
					this.schemasUn.add(leSchema);
					break;
				case 0:
					this.schemasZero.add(leSchema);
					break;
				}

			}
			if(this.schemasTrois.size()>0){
				c = Schema.comparerValeurCoupsSuivant(this.schemasTrois);
			} else 
				if(this.schemasDeux.size()>0){
					c = Schema.comparerValeurCoupsSuivant(this.schemasDeux);
				} else 
					if(this.schemasUn.size()>0){
						c = Schema.comparerValeurCoupsSuivant(this.schemasUn);
					} else 
						if(this.schemasZero.size()>0){
							c = Schema.comparerValeurCoupsSuivant(this.schemasZero);
						} 

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
		if(c.equals("NA")){

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

	private enum Coups {
		SHOOT, RELOAD, COVER, AIM;
	}
}


