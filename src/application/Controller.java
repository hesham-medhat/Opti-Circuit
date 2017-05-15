package application;

import Code.DoublyLinkedList;
import Code.EssentialPrimeImplicants;
import Code.PrimeImplicants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * This is the controller class for the application.
 * @author H
 *
 */
public class Controller {
	
	@FXML
	private TextField mintermsTF;
	@FXML
	private Label messageLabel;
	@FXML
	private TextField dc;
	@FXML
	private TextArea essentialOut;
	@FXML
	private TextArea primeOut;
	@FXML
	private TextArea solutionOut;
	@FXML
	private TextArea stepsOut;
	@FXML
	private TextArea optimalOut;
	
	private int[] minterms;
	private int[] mintermsDC;

	public void optimise(ActionEvent event) {
		try {
			String mintermsString = mintermsTF.getText();
			mintermsString = mintermsString.trim();
			
			String[] numbers = mintermsString.split(" ");
			String dCString = dc.getText();
			dCString = dCString.trim();
			
			this.minterms = new int[numbers.length];
			int i;
			for (i = 0; i < numbers.length; i++) {
				minterms[i] = Integer.parseInt(numbers[i]);
			}
			if (dCString != null && dCString.length() != 0) {
				String[] dCNumbers = dCString.split(" ");
				this.mintermsDC = new int[numbers.length + dCNumbers.length];
				for (int j = 0 ; j < dCNumbers.length ; j++) {
					mintermsDC[i + j] = Integer.parseInt(dCNumbers[j]);
				}
				for (i = 0 ; i < minterms.length ; i++) {
					mintermsDC[i] = minterms[i];
				}
			}
			if (mintermsDC == null) {
				mintermsDC = this.minterms;
			}
			
			messageLabel.setText(" ");

			PrimeImplicants primeImplicants = new PrimeImplicants();
			DoublyLinkedList[] primes = primeImplicants.generatePrimeImplicants(mintermsDC);
			EssentialPrimeImplicants essential = new EssentialPrimeImplicants();
			DoublyLinkedList[] coveredMT = essential.coveredMinterms(primes);
			DoublyLinkedList[] coveringImplicants = essential.coveringPIs(coveredMT, minterms);
	
			DoublyLinkedList essentialsList = essential.getEssentials(coveringImplicants);
			int maxChar = primeImplicants.getMaxChar();
			String essentialString = essential.printImplicants(essentialsList, primes, maxChar);
			essentialOut.setText(essentialString);
			
			DoublyLinkedList primesList = new DoublyLinkedList();
			for (i = 0 ; i < primes.length ; i++) {
				primesList.add(i);
			}
			String primesString = essential.printImplicants(primesList, primes, maxChar);
			primeOut.setText(primesString);

			DoublyLinkedList solutions = essential.getSolutions(primes, minterms);
			String[] possibleSolutions = essential.possibleOptimization(primes, solutions, maxChar);
			i = 1;
			solutionOut.setText("All possible solutions:\n");
			for (String possibleSolution : possibleSolutions) {
				solutionOut.appendText(i + ". ");
				solutionOut.appendText(possibleSolution);
				solutionOut.appendText("\n");
				i++;
			}

			System.out.println("ok");
			String[] bestSolutions = essential.bestOptimization(possibleSolutions);
			System.out.println("ok");
			int bestNum = bestSolutions.length;
			String bestNumString = Integer.toString(bestNum);
			String headlineOpt = "There is " + bestNumString + " optimal solution(s).\n";
			optimalOut.setText(headlineOpt);
			i = 1;
			for (String bestSolution : bestSolutions) {
				optimalOut.appendText(i + ". ");
				optimalOut.appendText(bestSolution);
				optimalOut.appendText("\n");
			}
			
		} catch (RuntimeException fail) {
			messageLabel.setText("Wrong input!");
		}
	}
}
