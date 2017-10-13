package sundemo.DomarProgram;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable{

	private static final long serialVersionUID = 6543434096939789695L;
	private String league;
	private ArrayList<Game> games;
	
	public Team(String leagueName) {
		league = leagueName;
		games = new ArrayList<Game>();
	}
	
	public void setLeagueName(String leagueName) {
		league = leagueName;
	}
	
	public String getLeagueName() {
		return league;
	}
	
	public void addGame(Game game) {
		games.add(game);
	}
	
	public ArrayList<Game> getGames(){
		return games;
	}
	
	public String toString() {
		return league;
	}
	
	
}
