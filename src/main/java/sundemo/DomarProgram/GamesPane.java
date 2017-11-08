package sundemo.DomarProgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class GamesPane extends TitledPane {

	public TableView<Referee> table;
	public MainApp main;

	@SuppressWarnings("unchecked")
	public GamesPane(MainApp mainWindow) {
		main = mainWindow;
		Button b1 = new Button("Add");
		b1.setOnAction(e -> addButtonAction());
		Button b2 = new Button("Edit");
		b2.setOnAction(e -> editButtonAction());
		Button b3 = new Button("Remove");
		b3.setOnAction(e -> deleteButtonAction());
		Button b4 = new Button("Save");
		b4.setOnAction(e -> saveButtonAction());
		Button b5 = new Button("Load");
		b5.setOnAction(e -> loadButtonAction());

		HBox addEditRemoveBox = new HBox();
		addEditRemoveBox.getChildren().addAll(b1, b2, b3);
		addEditRemoveBox.setSpacing(10);
		HBox loadSaveBox = new HBox();
		loadSaveBox.getChildren().addAll(b4, b5);
		loadSaveBox.setSpacing(10);
		loadSaveBox.setPadding(new Insets(0, 0, 0, 140));

		TableColumn<Referee, String> nameColumn = new TableColumn<>("Namn");
		nameColumn.setMinWidth(150);
		nameColumn.setStyle("-fx-background: #7F98B7; -fx-font-family: Sansation Bold; -fx-font-size: 14");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Referee, ArrayList<Team>> playingTeamColumn = new TableColumn<>("Spelar");
		playingTeamColumn.setMinWidth(150);
		playingTeamColumn.setStyle("-fx-background: #7F98B7; -fx-font-family: Sansation Bold; -fx-font-size: 14");
		playingTeamColumn.setCellValueFactory(new PropertyValueFactory<>("playsTeams"));
		TableColumn<Referee, ArrayList<Team>> coachingTeamColumn = new TableColumn<>("Coachar");
		coachingTeamColumn.setMinWidth(150);
		coachingTeamColumn.setStyle("-fx-background: #7F98B7; -fx-font-family: Sansation Bold; -fx-font-size: 14");
		coachingTeamColumn.setCellValueFactory(new PropertyValueFactory<>("coachTeams"));

		table = new TableView<>();

		table.getColumns().addAll(nameColumn, coachingTeamColumn, playingTeamColumn);

		HBox hbox1 = new HBox();
		VBox vbox1 = new VBox();
		hbox1.getChildren().addAll(addEditRemoveBox, loadSaveBox);
		hbox1.setSpacing(10);
		vbox1.getChildren().addAll(hbox1, table);
		vbox1.setSpacing(10);

		this.setFont(new Font("Sansation Bold", 20));
		this.setStyle("-fx-text-fill: #000000; -fx-text-alignment: CENTER");
		this.setText("Matcher");
		this.setExpanded(false);
		this.setContent(vbox1);
		this.setPadding(new Insets(20, 20, 20, 20));

	}

	private void saveButtonAction() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Spara Fil");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".ref", "*.ref"));
		File selectedFile = fileChooser.showSaveDialog(null);
		if (selectedFile != null) {
			try {
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(selectedFile));
				output.writeObject(main.referees);
				output.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@SuppressWarnings("unchecked")
	private void loadButtonAction() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Öppna Fil");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".ref", "*.ref"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(selectedFile));
				main.referees = (ArrayList<Referee>) input.readObject();
				input.close();
				
				table.getItems().clear();
				for(Referee a : main.referees)
					table.getItems().add(a);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void addButtonAction() {
		AddEditRefWindow refWin = new AddEditRefWindow(main);
		refWin.addRefButton.setOnAction(e -> {
			if (!refWin.nameField.getText().equals("")) {
				Referee ref = new Referee(refWin.nameField.getText());
				if (!refWin.availableCoachTeams.getCheckModel().getCheckedItems().isEmpty()) {
					for (Team a : refWin.availableCoachTeams.getCheckModel().getCheckedItems())
						ref.setCoachTeams(a);
				}
				if (!refWin.availablePlayTeams.getCheckModel().getCheckedItems().isEmpty()) {
					for (Team a : refWin.availablePlayTeams.getCheckModel().getCheckedItems())
						ref.setPlaysTeams(a);
				}
				main.referees.add(ref);
				table.getItems().add(ref);
				refWin.close();
			}
		});
	}

	public void editButtonAction() {
		if (table.getSelectionModel().getSelectedItem() != null) {
			Referee selected = table.getSelectionModel().getSelectedItem();
			AddEditRefWindow refWin = new AddEditRefWindow(main);
			refWin.nameField.setText(selected.getName());

			for (Team a : selected.getCoachTeams()) {
				refWin.availableCoachTeams.getCheckModel().check(a);
			}

			for (Team a : selected.getPlaysTeams()) {
				refWin.availablePlayTeams.getCheckModel().check(a);
			}

			refWin.addRefButton.setOnAction(e -> {
				selected.setName(refWin.nameField.getText());
				if (!refWin.availableCoachTeams.getCheckModel().getCheckedItems().isEmpty()) {
					selected.resetListOfCoachTeams();
					for (Team a : refWin.availableCoachTeams.getCheckModel().getCheckedItems())
						selected.setCoachTeams(a);
				}
				if (!refWin.availablePlayTeams.getCheckModel().getCheckedItems().isEmpty()) {
					selected.resetListOfPlaysTeams();
					for (Team a : refWin.availablePlayTeams.getCheckModel().getCheckedItems())
						selected.setPlaysTeams(a);
				}
				table.refresh();
				refWin.close();
			});
		}
	}

	public void deleteButtonAction() {
		if (table.getSelectionModel().getSelectedItem() != null) {
			Boolean answer = ConfirmBox.display("Ta bort ", "Ta bort " + table.getSelectionModel().getSelectedItem().getName() + "?");
			if (answer) {
				main.referees.remove(table.getSelectionModel().getSelectedItem());
				table.getItems().remove(table.getSelectionModel().getSelectedItem());
			}
		}
	}

}

