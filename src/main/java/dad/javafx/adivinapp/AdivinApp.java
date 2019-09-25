package dad.javafx.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application{
	
	private Label textLabel;
	private Button checkButton;
	private TextField numberText;
	private Alert infoAlert, warningAlert, notNumberAlert, outOfBoundsAlert;
	private int count=0, hiddenNumber= (int)(Math.random()*100+1);
	

	@Override
	public void start(Stage primaryStage) throws Exception {

	
		
		infoAlert=new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("AdivinApp");
		infoAlert.setHeaderText("¡Has ganado!");
		

		
		
		warningAlert = new Alert(AlertType.WARNING);
		warningAlert.setTitle("AdivinApp");
		warningAlert.setHeaderText("Has fallado");
		
		
		notNumberAlert = new Alert(AlertType.ERROR);
		notNumberAlert.setTitle("AdivinApp");
		notNumberAlert.setHeaderText("Error");
		notNumberAlert.setContentText("Compañero/a, eso no es un número");

		outOfBoundsAlert = new Alert(AlertType.ERROR);
		outOfBoundsAlert.setTitle("AdivinApp");
		outOfBoundsAlert.setHeaderText("Error");
		outOfBoundsAlert.setContentText("Ese número está fuera del rango, asi que +2 intentos por listo");
		
		
		textLabel=new Label("Introduce un número del 1 al 100");
		
		numberText=new TextField();
		numberText.setPromptText("introduce un número");
		numberText.setMaxWidth(150);
		numberText.setAlignment(Pos.CENTER);
		
		checkButton=new Button("Comprobar");
		checkButton.setDefaultButton(true);
		checkButton.setOnAction(e-> onCheckButtonAction(e));
		
		VBox root=new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(textLabel, numberText, checkButton);
		
		
		
		Scene scene=new Scene(root, 320, 200);
		

		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}


	private void onCheckButtonAction(ActionEvent e) {
		count++;
		try {
			int number=Integer.parseInt(numberText.getText());
			
			if(number==hiddenNumber) {
				infoAlert.setContentText("Has necesitado "+ count+" intentos.\n Vuelve a jugar y hazlo mejor!");
				
				infoAlert.showAndWait();
				reset();
			}else if(number<hiddenNumber&&number>1) {
				warningAlert.setContentText("El número a adivinar es mayor a "+number);
				warningAlert.showAndWait();
			}else if(number>hiddenNumber&&number<=100) {
				warningAlert.setContentText("El número a adivinar es menor a "+number);
				warningAlert.showAndWait();
			}else {
				count++;
				outOfBoundsAlert.showAndWait();
			}
			
			
				
			
		} catch (NumberFormatException e1) {
			
			notNumberAlert.showAndWait();
			
		}
		numberText.clear();
		
		
	}
	private void reset() {
		count=0;
		hiddenNumber = (int)(Math.random()*100+1);
		
	}

}
