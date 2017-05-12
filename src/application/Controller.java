package application;

import Code.PrimeImplicants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This is the controller class for the application.
 * @author H
 *
 */
public class Controller {
	
	@FXML
	private TextField minterms;
	@FXML
	private Label messageLabel;
	@FXML
	private TextField dc;
	
	public void optimise(ActionEvent event) {
		try {
			String mintermsString = minterms.getText();
			mintermsString = mintermsString.trim();
			String[] numbers = mintermsString.split(" ");
			int[] minterms = new int[numbers.length];
			int[] mintermsWDC = new int[numbers.length];
			int i;
			for (i = 0; i < numbers.length; i++) {
				minterms[i] = Integer.parseInt(numbers[i]);
				mintermsWDC[i] = minterms[i];
			}
			String dCString = dc.getText();
			dCString = dCString.trim();
			String[] dCNumbers = dCString.split(" ");
			for (int j = 0 ; j < dCNumbers.length ; j++) {
				mintermsWDC[i + j] = Integer.parseInt(dCNumbers[j]);
			}
			messageLabel.setText("");
		} catch (RuntimeException fail) {
			messageLabel.setText("Wrong input!");
		}
		PrimeImplicants pI = new PrimeImplicants();
		
	}
}
