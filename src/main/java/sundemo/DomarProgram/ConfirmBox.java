package sundemo.DomarProgram;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    //Create variable
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox layout = new HBox(10);
        VBox lay2 = new VBox(10);
        lay2.setPadding(new Insets(10,10,10,10));

        //Add buttons
        layout.getChildren().addAll(yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        lay2.getChildren().addAll(label, layout);
        lay2.setAlignment(Pos.CENTER);
        Scene scene = new Scene(lay2);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }
	
}
