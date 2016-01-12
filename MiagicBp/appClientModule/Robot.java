
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
	public void setNbVies(int nbVies) {
		this.nbVies = nbVies;
	}
	public int getNbVies() {
		return nbVies;
	}
	public void setNbBullet(int nbBullet) {
		this.nbBullet = nbBullet;
	}
	public int getNbBullet() {
		return nbBullet;
	}
	public void setNbBouclier(int nbBouclier) {
		this.nbBouclier = nbBouclier;
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
    
}
