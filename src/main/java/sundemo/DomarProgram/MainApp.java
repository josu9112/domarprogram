package sundemo.DomarProgram;

import java.util.ArrayList;


import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainApp extends Application{
	
	
	public ArrayList<Referee> referees;
	public ArrayList<Team> allTeams;
	public TableView<Referee> table;

	public static void main(String[] args) {
		launch(args);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button b1 = new Button("Add");
		b1.setOnAction(e -> addButtonAction());
		Button b2 = new Button("Edit");
		b2.setOnAction(e -> editButtonAction());
		Button b3 = new Button ("Remove");
		b3.setOnAction(e -> deleteButtonAction());
		
	
		TableColumn<Referee, String> nameColumn = new TableColumn<>("Namn");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Referee, ArrayList<Team>> playingTeamColumn = new TableColumn<>("Spelar");
		playingTeamColumn.setMinWidth(200);
		playingTeamColumn.setCellValueFactory(new PropertyValueFactory<>("playsTeams"));
		TableColumn<Referee, ArrayList<Team>> coachingTeamColumn = new TableColumn<>("Coachar");
		coachingTeamColumn.setMinWidth(200);
		coachingTeamColumn.setCellValueFactory(new PropertyValueFactory<>("coachTeams"));
		
		referees = new ArrayList<Referee>();
		allTeams = new ArrayList<Team>();
		allTeams.add(new Team("DU13"));
		allTeams.add(new Team("DU15"));
		allTeams.add(new Team("HD3"));
		allTeams.add(new Team("DD3"));
		allTeams.add(new Team("DD2"));
		
		table = new TableView<>();
		table.getColumns().addAll(nameColumn,coachingTeamColumn,playingTeamColumn);
		
		HBox hbox1 = new HBox();
		VBox vbox1 = new VBox();
		hbox1.getChildren().addAll(b1,b2,b3);
		hbox1.setPadding(new Insets(0,0,10,0));
		hbox1.setSpacing(10);
		vbox1.setPadding(new Insets(20,20,20,20));
		vbox1.getChildren().addAll(hbox1,table);
		
		
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		pane.setLeft(vbox1);
		primaryStage.setScene(scene);
		primaryStage.setTitle("window");
		primaryStage.show();
		
	}
	
	
	public void addButtonAction() {
		AddEditRefWindow refWin= new AddEditRefWindow(this);
		refWin.addRefButton.setOnAction(e -> {
			if(!refWin.nameField.getText().equals("")) {
				Referee ref = new Referee(refWin.nameField.getText());
				if(!refWin.availableCoachTeams.getCheckModel().getCheckedItems().isEmpty()) {
					for(Team a : refWin.availableCoachTeams.getCheckModel().getCheckedItems())
						ref.setCoachTeams(a);
				}
				if(!refWin.availablePlayTeams.getCheckModel().getCheckedItems().isEmpty()) {
					for(Team a : refWin.availablePlayTeams.getCheckModel().getCheckedItems())
						ref.setPlaysTeams(a);
				}
				referees.add(ref);
				table.getItems().add(ref);
				refWin.close();
			}
		});
	}
	
	public void editButtonAction() {
		if(table.getSelectionModel().getSelectedItem() != null) {
			Referee selected = table.getSelectionModel().getSelectedItem();
			AddEditRefWindow refWin = new AddEditRefWindow(this);
			refWin.nameField.setText(selected.getName());
			
			for(Team a : selected.getCoachTeams()) {
				refWin.availableCoachTeams.getCheckModel().check(a);
			}
			
			for(Team a : selected.getPlaysTeams()) {
				refWin.availablePlayTeams.getCheckModel().check(a);
			}
			
			refWin.addRefButton.setOnAction(e -> {
				selected.setName(refWin.nameField.getText());
				if(!refWin.availableCoachTeams.getCheckModel().getCheckedItems().isEmpty()) {
					selected.resetListOfCoachTeams();
					for(Team a : refWin.availableCoachTeams.getCheckModel().getCheckedItems())
						selected.setCoachTeams(a);
					
				}
				if(!refWin.availablePlayTeams.getCheckModel().getCheckedItems().isEmpty()) {
					selected.resetListOfPlaysTeams();
					for(Team a : refWin.availablePlayTeams.getCheckModel().getCheckedItems())
						selected.setPlaysTeams(a);
					
					table.refresh();
					refWin.close();
				}
				
			});
		}
	}
	
	public void deleteButtonAction() {
		if(table.getSelectionModel().getSelectedItem() != null) {
			Boolean answer = ConfirmBox.display("Ta bort domare", "�r du s�ker p� att du vill ta bort domare?");
			if(answer) {
				referees.remove(table.getSelectionModel().getSelectedItem());
				table.getItems().remove(table.getSelectionModel().getSelectedItem());
			}
		}
	}
	

}
