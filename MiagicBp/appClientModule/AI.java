import java.util.ArrayList;

public class AI {
	private Partie p;
	public AI(Partie p){
		this.p = p;
	}
	
	public String analyseHabits(){
		String move="";
		ArrayList coups = this.p.getBotAdverse().getCoups();
		int nbCoups = coups.size();
		String dernierCoup = (String) coups.get(nbCoups-1);
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
			if (p.getBotMiagic().getNbBullet()== 0){
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
			if (p.getBotMiagic().getNbBullet()> 0){
				move = "SHOOT";
			} else {
				move = "RELOAD";
			}
		}
		return move;
		
	}
	
	
	private enum Coups {
		SHOOT, RELOAD, COVER, AIM;
	}
}

