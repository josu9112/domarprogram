package sundemo.DomarProgram;

import java.io.FileInputStream;
import java.util.ArrayList;


import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application{
	
	
	public ArrayList<Referee> referees;
	public ArrayList<Team> allTeams;
	public TableView<Referee> table;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		referees = new ArrayList<Referee>();
		allTeams = new ArrayList<Team>();
		allTeams.add(new Team("DU13"));
		allTeams.add(new Team("DU15"));
		allTeams.add(new Team("HD3"));
		allTeams.add(new Team("DD3"));
		allTeams.add(new Team("DD2"));
		
		ImageView logo = new ImageView(new Image(new FileInputStream("C:\\Users\\John\\Desktop\\Basket\\Loggor\\kba-logo.png")));
		
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		pane.setStyle("-fx-background: #013370;");
		pane.setLeft(new RefPane(this));
		pane.setCenter(logo);
		BorderPane.setMargin(logo, new Insets(20,20,20,20));
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Kungsbacka Domarprogram v1.0");
		primaryStage.show();
		
	}
	

}
