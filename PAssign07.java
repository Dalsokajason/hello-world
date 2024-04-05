//Link to GitHub: https://github.com/Dalsokajason/hello-world
package keypad;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PAssign07 extends Application{
	protected Timer timer = new Timer();
	protected TimerTask timerTask = new Count();
	protected boolean isDone = false;
	protected boolean isPaused = false;
	protected TextField tfHours = new TextField();
	protected TextField tfMinutes = new TextField();
	protected TextField tfSeconds = new TextField();
	private Label lbHoursEdit = new Label("<-Selected");
	private Label lbMinutesEdit = new Label("");
	private Label lbSecondsEdit = new Label("");
	private Button btStart = new Button("START");
	private int inputSelect = 0;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		
		//Create a pane for the user inputs
		GridPane inputPane = new GridPane();
		inputPane.add(new Label("Hours: "), 0, 0);
		inputPane.add(tfHours, 1, 0);
		inputPane.add(lbHoursEdit, 2, 0);
		inputPane.add(new Label("Minutes: "), 0, 1);
		inputPane.add(tfMinutes, 1, 1);
		inputPane.add(lbMinutesEdit, 2, 1);
		inputPane.add(new Label("Seconds: "), 0, 2);
		inputPane.add(tfSeconds, 1, 2);
		inputPane.add(lbSecondsEdit, 2, 2);
		
		//Create pane for the button cluster
		GridPane buttonPane = new GridPane();
		buttonPane.add(btStart, 0, 0);
		
		//Create keypad pane using implementation from KeyPadPane.java
		KeyPadPane keyPane = new KeyPadPaneCustom();
		
		//Assemble panes into the main pane. I love Grid Panes!
		GridPane mainPane = new GridPane();
		mainPane.add(inputPane, 0, 0);
		mainPane.add(keyPane, 1, 0);
		mainPane.add(new Label("Use arrows to navigate inputs."), 2, 0);
		mainPane.add(buttonPane, 0, 1);
		
		//set gap
		mainPane.setHgap(15); 
        mainPane.setVgap(10); 
		
		Scene scene = new Scene(mainPane, 1000, 500);
		primaryStage.setTitle("THE MOST COMPLETE & COMPREHENSIVE COUNTER"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
		btStart.setOnAction(e -> startTimer());
		
		keyPane.btn0.setOnAction(e -> typeWithButtons(keyPane.btn0.getText(), inputSelect));
		keyPane.btn1.setOnAction(e -> typeWithButtons(keyPane.btn1.getText(), inputSelect));
		keyPane.btn2.setOnAction(e -> typeWithButtons(keyPane.btn2.getText(), inputSelect));
		keyPane.btn3.setOnAction(e -> typeWithButtons(keyPane.btn3.getText(), inputSelect));
		keyPane.btn4.setOnAction(e -> typeWithButtons(keyPane.btn4.getText(), inputSelect));
		keyPane.btn5.setOnAction(e -> typeWithButtons(keyPane.btn5.getText(), inputSelect));
		keyPane.btn6.setOnAction(e -> typeWithButtons(keyPane.btn6.getText(), inputSelect));
		keyPane.btn7.setOnAction(e -> typeWithButtons(keyPane.btn7.getText(), inputSelect));
		keyPane.btn8.setOnAction(e -> typeWithButtons(keyPane.btn8.getText(), inputSelect));
		keyPane.btn9.setOnAction(e -> typeWithButtons(keyPane.btn9.getText(), inputSelect));
		keyPane.btnBlank1.setOnAction(e -> typeWithButtons(keyPane.btnBlank1.getText(), inputSelect));
		keyPane.btnBlank2.setOnAction(e -> typeWithButtons(keyPane.btnBlank2.getText(), inputSelect));
		
		//deny direct edit by keyboard
		tfHours.setEditable(false);
		tfMinutes.setEditable(false);
		tfSeconds.setEditable(false);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void startTimer() {
		//replace empty strings with zeros
		if(tfHours.getText().equals("")) {
			tfHours.setText("0");
		}
		if(tfMinutes.getText().equals("")) {
			tfMinutes.setText("0");
		}
		if(tfSeconds.getText().equals("")) {
			tfSeconds.setText("0");
		}
		
		timer.scheduleAtFixedRate(timerTask, 1000, 1000);
		
	}
	
	//allow keypad to edit text fields
	public void typeWithButtons(String value, int selection) {
		if(value.equals(">")) {
			if(inputSelect < 2) {
				inputSelect++;
			}
			else {
				inputSelect = 0;
			}
			updateSelection();
		}
		else if(value.equals("<")) {
			if(inputSelect > 0) {
				inputSelect--;
			}
			else {
				inputSelect = 2;
			}
			updateSelection();
		}
		else {
			switch(selection){
			case 0:
				tfHours.setText(tfHours.getText() + Integer.parseInt(value));
		    	break;
			case 1:
				tfMinutes.setText(tfMinutes.getText() + Integer.parseInt(value));
		    	break;
			case 2:
				tfSeconds.setText(tfSeconds.getText() + Integer.parseInt(value));
		    	break;
			}
		}
	}
	
	public void updateSelection(){
		switch(inputSelect){
		case 0:
			lbHoursEdit.setText("<-Selected");
			lbMinutesEdit.setText("");
			lbSecondsEdit.setText("");
	    	break;
		case 1:
			lbHoursEdit.setText("");
			lbMinutesEdit.setText("<-Selected");
			lbSecondsEdit.setText("");
	    	break;
		case 2:
			lbHoursEdit.setText("");
			lbMinutesEdit.setText("");
			lbSecondsEdit.setText("<-Selected");
	    	break;
		}
	}
	
	private class Count extends TimerTask {
		
		public void run()
	    {
			int hours = Integer.parseInt(tfHours.getText());
			int minutes = Integer.parseInt(tfMinutes.getText());
			int seconds = Integer.parseInt(tfSeconds.getText());
			if(seconds > 0) {
	        	seconds--;
	        }
	        else if(minutes > 0) {
	        	seconds = 59;
	        	minutes--;
	        }
	        else if(hours > 0) {
	        	minutes = 59;
	        	hours--;
	        }
	        else{
	        	System.out.println("TIMER DONE!");
				
	        }
			
			tfSeconds.setText(String.valueOf(seconds));
			tfMinutes.setText(String.valueOf(minutes));
			tfHours.setText(String.valueOf(hours));
	    }
	}
	
	class KeyPadPaneCustom extends KeyPadPane{
		public KeyPadPaneCustom() {
			btnBlank1.setText("<");
			btnBlank2.setText(">");
		}
	}
}

