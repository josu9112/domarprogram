package sundemo.DomarProgram;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.beans.property.SimpleStringProperty;

public class Referee {

	private String name;
	private ArrayList<Team> playsTeams;
	private ArrayList<Team> coachTeams;
	private ArrayList<Game> gamesToRef;
	
	public Referee(String name) {
		this.name = name;
		playsTeams = new ArrayList<Team>();
		coachTeams = new ArrayList<Team>();
		gamesToRef = new ArrayList<Game>();
	}
	
	public Referee(String name, ArrayList<Team> refPlaysOnTeams,ArrayList<Team> refCoachesTeams) {
		this.name = name;
		playsTeams = refPlaysOnTeams;
		coachTeams = refCoachesTeams;
		gamesToRef = new ArrayList<Game>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addGameToRef(Game game) {
		gamesToRef.add(game);
	}
	
	public ArrayList<Game> getRefsGames(){
		return gamesToRef;
	}
	
	public void setPlaysTeams(Team team) {
		playsTeams.add(team);
	}
	
	public ArrayList<Team> getPlaysTeams(){
		return playsTeams;
	}
	
	public void resetListOfPlaysTeams() {
		playsTeams = new ArrayList<Team>();
	}
	
	public void setCoachTeams(Team team) {
		coachTeams.add(team);
	}
	
	public ArrayList<Team> getCoachTeams(){
		return coachTeams;
	}
	
	public void resetListOfCoachTeams() {
		coachTeams = new ArrayList<Team>();
	}
	
	
	public boolean isRefBusy(Team team) {
		for(Team refTeam : coachTeams) {
			if(refTeam.getLeagueName().equals(team.getLeagueName()))
				return true;
		}
		for(Team refTeam : playsTeams) {
			if(refTeam.getLeagueName().equals(team.getLeagueName()))
				return true;
		}
		return false;
	}
	
	public boolean canRefGetOnTime(Game game) {
		for(Team refsTeam : coachTeams) {
			for(Game teamsGame : refsTeam.getGames()) {
				if(game.getDate().equals(teamsGame.getDate())) {
					String currentGameStartTime = game.getStartTime();
					String refsOtherGameStartTime = game.getStartTime();
					
					if(timeSubtractTime(currentGameStartTime, refsOtherGameStartTime) < 3 && timeSubtractTime(currentGameStartTime, refsOtherGameStartTime) > -3.5 
					&& !teamsGame.getLocation().equals("Kollahallen") ) {
						return false;
					}
				}
			}
		}
		for(Team refsTeam : playsTeams) {
			for(Game teamsGame : refsTeam.getGames()) {
				if(game.getDate().equals(teamsGame.getDate())) {
					String currentGameStartTime = game.getStartTime();
					String refsOtherGameStartTime = game.getStartTime();
					
					if(timeSubtractTime(currentGameStartTime, refsOtherGameStartTime) < 3 && timeSubtractTime(currentGameStartTime, refsOtherGameStartTime) > -3.5 
					&& !teamsGame.getLocation().equals("Kollahallen") ) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	public static double timeSubtractTime(String time1, String time2) {
		double time1Hour = Double.parseDouble(time1.substring(0,2));
		double time1Minute = Double.parseDouble(time1.substring(3,5));
		double time2Hour = Double.parseDouble(time2.substring(0,2));
		double time2Minute = Double.parseDouble(time2.substring(3,5));
		
		double minutes = time1Minute - time2Minute;
		double hours = time1Hour - time2Hour;
		
		if(time1Hour > time2Hour) {
			 if(minutes < 0) {
				 hours -= 1 - (60+minutes)/60;
			 }
			 else {
				 hours += minutes/60;
			 }
		}
		else {
			if(minutes < 0) {
				hours += 1 - (60-minutes)/60;
			}
			else {
				hours -= 1 - (60+minutes)/60;
			}
		}
		return hours;
	}
	
	public String toString() {
		return name;
	}
	
}
