package sundemo.DomarProgram;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

public class AddEditRefWindow extends Stage {

	public GridPane grid;
	public TextField nameField;
	public CheckComboBox<Team> availableCoachTeams;
	public CheckComboBox<Team> availablePlayTeams;
	public Button addRefButton;
	
	public AddEditRefWindow(MainApp main) {
		grid = new GridPane();
		grid.setPadding(new Insets(20,20,20,20));
		grid.setVgap(10);
		grid.setHgap(10);
		
		
		Label name = new Label("Namn:");
		nameField = new TextField();
		nameField.setPromptText("Namn");
		
		GridPane.setConstraints(name, 0, 0);
		GridPane.setConstraints(nameField, 1, 0);
		
		Label coaches = new Label("Coachar:");
		
		ObservableList<Team> teams = FXCollections.observableArrayList();
		for(Team a : main.allTeams) {
			teams.add(a);
		}
		availableCoachTeams = new CheckComboBox<Team>(teams);
		
		GridPane.setConstraints(coaches, 0, 1);
		GridPane.setConstraints(availableCoachTeams, 1, 1);
		
		Label plays = new Label("Spelar:");
		availablePlayTeams = new CheckComboBox<Team>(teams);
		
		GridPane.setConstraints(plays, 0, 2);
		GridPane.setConstraints(availablePlayTeams, 1, 2);
		
		Button cancelButton = new Button("Avbryt");
		cancelButton.setOnAction(e -> this.close());
		
		addRefButton = new Button("Ok");
		GridPane.setConstraints(addRefButton, 0, 3);
		GridPane.setConstraints(cancelButton, 1, 3);
		
		
		grid.getChildren().addAll(name,nameField,coaches,availableCoachTeams, plays, availablePlayTeams,addRefButton, cancelButton);
		this.setScene(new Scene(grid));
		this.show();
	}
	
}
