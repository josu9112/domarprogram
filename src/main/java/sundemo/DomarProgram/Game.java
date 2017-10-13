package sundemo.DomarProgram;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable{

	private static final long serialVersionUID = 7589885191784831982L;
	private Team team;
	private String location;
	private String date;
	private String startTime;
	private String endTime;
	private double gamenr;
	private String opponent;
	private ArrayList<Referee> refs;
	
	public Game(){
	}
	
	public Game(Team team, double gamenr, String date, String time, String opponent,String location) {
		this.team = team;
		this.gamenr = gamenr;
		this.date = date;
		this.opponent = opponent;
		this.location = location;
		setStartTime(time);
		refs = new ArrayList<Referee>();
	}
	
	public void setLeague(Team team){
		this.team = team;
	}
	
	public Team getLeague(){
		return team;
	}
	
	public void setGameNr(int nr){
		gamenr = nr;
	}
	
	public double getGameNr() {
		return gamenr;
	}
	
	public void setLocation(String loc){
		location = loc;
	}
	
	public String getLocation(){
		return location;
	}
	
	public void setDate(String d){
		date = d;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setStartTime(String time){
		endTime = calcEndTime(time);
		startTime = time;
	}
	
	public String getStartTime(){
		return startTime;
	}
	
	public String getEndTime(){
		return endTime;
	}
	
	public String getOpponent() {
		return opponent;
	}
	
	public void addRef(Referee ref) {
		refs.add(ref);
	}
	
	public ArrayList<Referee> getRefs(){
		return refs;
	}
	
	private String calcEndTime(String time){
		
		int hour = Integer.parseInt(time.substring(0,2));
		int minute = Integer.parseInt(time.substring(3,5));
		
		if(minute >= 30){
			hour += 2;
			minute -= 30;
		}
		else{
			hour++;
			minute += 30;
		}
		if(minute != 0)
			return hour + ":" + minute + ":00";
		else
			return hour + ":00:00";
	}
	
	public String toString() {
		return "Game: " + team.getLeagueName() + " vs " + opponent + ", " + date + ", kl " + startTime + "-" + endTime + " i " + location;
	}
	
}
