
public class Robot {

    private String numJoeur; 
	private int nbVies; 
    private int nbBullet; 
    private int nbBouclier; 
    private boolean focused;
    
    
    public Robot(String numJoueur,int nbVies,int nbBullet,int nbBouclier,boolean focused){
    	
    	this.focused= focused; 
    	this.nbVies = nbVies; 
    	this.nbBullet = nbBullet; 
    	this.nbBouclier = nbBouclier; 
    	this.numJoeur = numJoueur; 
    	
    }
    
    
 
    
	public void setNumJoeur(String numJoeur) {
		this.numJoeur = numJoeur;
	}
	public String getNumJoeur() {
		return numJoeur;
	}
	public void setNbVies(String tokens) {
		
		this.nbVies =  Integer.parseInt(tokens);
	}
	public int getNbVies() {
		return nbVies;
	}
	public void setNbBullet(String tokens) {
		this.nbBullet =  Integer.parseInt(tokens);
	}
	public int getNbBullet() {
		return nbBullet;
	}
	public void setNbBouclier(String tokens) {
		this.nbBouclier =  Integer.parseInt(tokens);
	}
	public int getNbBouclier() {
		return nbBouclier;
	}
	public void setFocused(boolean focused) {
		this.focused = focused;
	}
	public boolean isFocused() {
		return focused;
	} 
    
	

	public String toString(){
		
		String s ="------------- \n"; 
		s += "\nNum joueur " + this.numJoeur; 
		s += "\nNb vies " + this.nbVies; 
		s += "\nNb bouclier " + this.nbBouclier; 
		s += "\nNb balles " + this.nbBullet; 
		
		return s; 
		
	}
	
}
