import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;





	public class Main {
	
		  String idPartie = ""; 
		  String idEquipe = ""; 

		 

	public static void main(String[] args) throws IOException, InterruptedException {
		
	
		String idEquipe = get("http://www.battlearena.io/battle-ws/duel/player/getIdEquipe/Miagic%20Bot/Universite2Nantes2016$");
		System.out.println(idEquipe); 
		
		
		Partie p = new Partie(idEquipe); 
		p.initPartie(); 
		
		
		p.majStatut();
		System.out.println("Dans moin statut : " + p.getStatut());
	
	    while(p.getStatut().equals("CANTPLAY")||p.getStatut().equals("CANPLAY")){
		
	    p.majRobotsPractice(); 	
	    System.out.println(p);	
	    
	    p.Jouer();
		p.majStatut();
		
	    }
	    p.majStatut();
	    System.out.println("FIN :"+ p.getStatut());
	    
			
		
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
    
    

    

  
    



}