import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class Partie {

	private int lvlmin = 1; 
	private int lvlmax = 6;
	private String idPartie; 
	private String idEquipe; 
	
	private String statut;
	
	private Robot botMiagic;  
	private Robot botAdverse;

	
	
	public Partie(String idEquipe){
		
		this.idEquipe = idEquipe; 
	    
		botMiagic = new Robot(idEquipe, 0, 0, 0, false); 
		botAdverse = new Robot(idEquipe, 0, 0, 0, false); 
		
	
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
    	
    		System.out.println("IFFFF Statut partie = " + statut);
    		
    		try {
				System.out.println(getBoard());
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
			// TRAITEMENT = PARSE + MAJ DES VALEURS DES BOTS EN LOCAL //
    		
			// Prise de d���cision // 
			
			System.out.println(coup());
    	}
        
        	
    	return statut; 
    }
    
    
    public  void majStatut() throws IOException{
    	statut =  get("http://www.battlearena.io/battle-ws/duel/game/status/"+idPartie+"/"+idEquipe);
    }
    
    public  String getBoard() throws IOException{
    	System.out.println("Dans get board"); 
    	return get("http://www.battlearena.io/battle-ws/duel/game/board/"+idPartie+"?format=JSON)");
    }
    
    public String coup() throws IOException{
    	
    	System.out.println("Quel coup ? "); 
    	
    	Scanner sc = new Scanner(System.in); 
    	String coup = sc.next();
    	
	    return get("http://www.battlearena.io/battle-ws/duel/game/play/"+idPartie+"/"+idEquipe+"/"+coup);

    }
    
	
	
	
	
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
	
	
}
