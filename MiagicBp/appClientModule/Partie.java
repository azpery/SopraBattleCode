import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import ia.IA;


public class Partie {

	private int lvlmin = 1; 
	private int lvlmax = 6;
	
	private String idPartie; 
	private String idEquipe; 
   
	private String statut;
	
	private Robot botMiagic;  
	private Robot botAdverse;

	private IA ia; 
	private ArrayList<String> coupsAdv; 
	
	
	public Partie(String idEquipe){
		
		this.idEquipe = idEquipe; 
	    
		botMiagic = new Robot(idEquipe, 0, 0, 0, false); 
		botAdverse = new Robot(idEquipe, 0, 0, 0, false); 
		
		coupsAdv = new ArrayList<String>(); 
		
		ia = new IA();
	
	}
	
	
	/*
	 * @author
	 * @description : Initialise une partie : soit un versus ou un practice 
	 * @pre :
	 * @post :
	 */
    public void initPartie() throws IOException{
    	
    	int i = -1 ;
    	while (i==-1)
    	{
    		System.out.println("Practice (0) ou Versus (1)");
    		Scanner sc = new Scanner(System.in);
    		i =  sc.nextInt();
    	}
    	
    	if(i == 0){
    		int lvl = -1; 
    		System.out.println(idEquipe);
    		
    		while(lvl<this.lvlmin||lvl>this.lvlmax){
    		System.out.println("Choix lvl compris entre " + lvlmin + ":" + lvlmax);
    		Scanner sc = new Scanner(System.in); 
    		lvl= sc.nextInt();
    		}
    		idPartie =  get("http://www.battlearena.io/battle-ws/duel/practice/new/"+lvl+"/"+idEquipe);   	
    		System.out.println("id partie : " + idPartie);
    	}
    	
    	if(i == 1){
    		// On rejoind un versus // 
    	}
    
    }	
	
public static String get(String url) throws IOException{
    	
    	String res=""; 
    	   
    	URL oracle = null;
		try {
			oracle = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
         BufferedReader in = new BufferedReader(
           new InputStreamReader(oracle.openStream()));

           String inputLine;
           while ((inputLine = in.readLine()) != null)
              res = inputLine; 
           in.close();
    	
    	return res; 
    }
    
    

   
    
    public String Jouer() throws InterruptedException, IOException{
    	
        statut="CANTPLAY";
    	String resPartie=""; 
    	
    	while(statut.equals("CANTPLAY"))
    	{
    	
    	System.out.println("WHILE CANTPLAY Statut partie = " + statut); 	
    	
    	Thread.sleep(100); 
  
    	majStatut(); 
	
        System.out.println("WHILE CANTPLAY : Statut partie = " + statut);
  
	
    	}
    	
    	
    	if(statut.equals("CANPLAY")){
    	 
    		ia.devinerFuturCoup(coupsAdv,getDernierCoup() );
    		
    		System.out.println("IFFFF Statut partie = " + statut);
    		
    		try {
				System.out.println(getBoard());
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
			
    		
    		
    		
			// Prise de d���cision // 
			
			System.out.println(coup());
    	}
        
        	
    	return statut; 
    } // Fin jouer // 
    
 // RECUPERATION DONNES DE JEUX  + TRAITEMENTS  INFORMATIONS 
    
   // Parseur //  
	public String[] Parser() throws IOException{
		
		String datas = getBoard();
		String delims = "[;]";
		String[] tokens = datas.split(delims);
		
		return tokens; 
	}
	
	// Traitement Partie practice // 
    /*
     * @warning ATTENTION CETTE METHODE EST A MODIFIER SI LE FORMAT CHANGE DANS LE PRACTICE // 
     * @description : On suppose que le premier robot à jouer est le notre // 
     */
    public void majRobotsPractice() throws IOException{
    	
    	String[] tokens; 
    	tokens= Parser();
    	
    	// MAJ VALEURS DE NOTRE BOT  // 
    	botMiagic.setNumJoeur("Joueur 1");
    	
    	botMiagic.setNbVies(tokens[1]);
    	
    	botMiagic.setNbBullet(tokens[2]);
    	
    	botMiagic.setNbBouclier(tokens[3]);
    	
    	// MAJ VALEURS DE l'ADVERSAIRE PRACTICE // 
    	botAdverse.setNumJoeur("Joueur 2");
    	
    	botAdverse.setNbVies(tokens[5]);
    	
    	botAdverse.setNbBullet(tokens[6]);
    	
    	botAdverse.setNbBouclier(tokens[7]);
    }
    
	
	
	
	
    
    
    public  void majStatut() throws IOException{
    	statut =  get("http://www.battlearena.io/battle-ws/duel/game/status/"+idPartie+"/"+idEquipe);
    }
    
    public  String getBoard() throws IOException{
    	System.out.println("Dans get board"); 
    	return get("http://www.battlearena.io/battle-ws/duel/game/board/"+idPartie+"?format=JSON)");
    }
    
    
    public String getDernierCoup() throws IOException{
    
    	String s=  get("http://www.battlearena.io/battle-ws/duel/game/getlastmove/"+idPartie+"/"+idEquipe);
    	System.out.println("\n\n\n\n\n DERNIER COUP JOUE " + s);
    	return s; 
    }
    
    
    


    
    public String coup() throws IOException{
    	
    	System.out.println("Quel coup ? "); 
    	
    	Scanner sc = new Scanner(System.in); 
    	String coup = sc.next();
    	
	    return get("http://www.battlearena.io/battle-ws/duel/game/play/"+idPartie+"/"+idEquipe+"/"+coup);

    }
    
	
    
    /* Requests */ 
    
    
    
    
    
    
    
    
	
	/* Getters and Setters */ 
	
	public void setId(String id) {
		this.idPartie = id;
	}
	public String getId() {
		return idPartie;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getStatut() {
		return statut;
	} 
	public void setBotMiagic(Robot robot) {
		this.botMiagic = robot;
	}
	public Robot getBotMiagic() {
		return this.botMiagic;
	} 
	public void setBotAdverse(Robot robot) {
		this.botAdverse = robot;
	}
	public Robot getBotAdverse() {
		return this.botAdverse;
	} 
	/* Affichage */ 
	

	public String toString(){
		
		
		
		String s = "\n---------------------------------------------\n";
		s+= "\n" + this.botMiagic; 
		s+= "\n" +  this.botAdverse; 
		s += "\n---------------------------------------------\n";
		
		return s; 
	}
	
}
