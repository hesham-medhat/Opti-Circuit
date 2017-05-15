package application;

import Code.DLNode;
import Code.DoublyLinkedList;
import Code.EssentialPrimeImplicants;
import Code.IO;
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
	@FXML
	private TextField mintermsFile;
	@FXML
	private TextField dcFile;
	@FXML
	private Label fileFeedback;
	
	private int[] minterms;
	private int[] mintermsDC;

	public void optimise(ActionEvent event) {
		try {
			messageLabel.setText(" ");
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

			workWithInput();
			
		} catch (RuntimeException fail) {
			messageLabel.setText("Wrong input!");
		}
	}

	public void optimiseFileInput(ActionEvent fileEvent) {
		IO io = new IO();
		String mintermsPath = mintermsFile.getText();
		this.minterms = io.read(mintermsPath);
		this.mintermsDC = this.minterms;
		try {
			fileFeedback.setText(" ");
			String dcPath = dcFile.getText();
			int[] dc = io.read(dcPath);
			this.mintermsDC = new int[dc.length + minterms.length];
			int i;
			for (i = 0 ; i < minterms.length ; i++) {
				mintermsDC[i] = minterms[i];
			}
			for (int j = 0 ; j < dc.length ; j++) {
				mintermsDC[i + j] = dc[j];
			}
			fileFeedback.setText("Success! Solution is on solutions.txt");
		} catch (RuntimeException eFile) {
			fileFeedback.setText("Failed to read from file.");
		}
		workWithInput();
		String[] bestSolutions = workWithInput();
		io.write(bestSolutions);
	}

	public String[] workWithInput() {
		int i;
		PrimeImplicants primeImplicants = new PrimeImplicants();
		DoublyLinkedList[] primes = primeImplicants.generatePrimeImplicants(mintermsDC);
		EssentialPrimeImplicants essential = new EssentialPrimeImplicants();
		DoublyLinkedList[] coveredMT = essential.coveredMinterms(primes);
		DoublyLinkedList[] coveringImplicants = essential.coveringPIs(coveredMT, minterms);

		DoublyLinkedList essentialsList = essential.getEssentials(coveringImplicants);
		int maxChar = primeImplicants.getMaxChar();
		String essentialString = essential.printImplicants(essentialsList, primes, maxChar);
		if (minterms.length == Math.pow(2, maxChar)) {
			solutionOut.setText("1");
			optimalOut.setText("1");
			stepsOut.setText("All minterms are active.\nTherefore, the solution is always one for this input function.");
			String[] s1 = new String[1];
			s1[0] = "1";
			return s1;
		}
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

		String[] bestSolutions = essential.bestOptimization(possibleSolutions);
		int bestNum = bestSolutions.length;
		String bestNumString = Integer.toString(bestNum);
		String headlineOpt = "There is " + bestNumString + " optimal solution(s).\n";
		optimalOut.setText(headlineOpt);
		i = 1;
		for (String bestSolution : bestSolutions) {
			optimalOut.appendText(i + ". ");
			optimalOut.appendText(bestSolution);
			optimalOut.appendText("\n");
			i++;
		}

		stepsOut.setText("Each prime implicant covers a set of minterms as follows:\n");
		stepsOut.setWrapText(true);
		DLNode iteratorNode;
		for (i = 0 ; i < primes.length ; i++) {
			iteratorNode = coveredMT[i].getHead();
			stepsOut.appendText("Prime implicant no. " + (i + 1) + " covers minterms: ");
			StringBuilder sb = new StringBuilder();
			while (iteratorNode != null) {
				sb.append((int) iteratorNode.getElement());
				iteratorNode = iteratorNode.getNext();
				sb.append(' ');
			}
			stepsOut.appendText(sb.toString() + "\n");
		}
		stepsOut.appendText("\n\n");
		for (i = 0 ; i < coveringImplicants.length ; i++) {
			iteratorNode = coveringImplicants[i].getHead();
			stepsOut.appendText("The covering implicants for minterm " + minterms[i] + " are no.: ");
			StringBuilder sb = new StringBuilder();
			while (iteratorNode != null) {
				sb.append(((int) iteratorNode.getElement() + 1));
				iteratorNode = iteratorNode.getNext();
				sb.append(' ');
			}
			stepsOut.appendText(sb.toString() + "\n");
		}
		stepsOut.appendText("\nObviously, the minterms covered by only one implicant are essential.\n\nHence, Petrick's formula would be like this: \n");
		stepsOut.appendText(essential.getFormula(coveringImplicants));
		stepsOut.appendText("\nAfter simplification of this formula we should find all the possible solutions and then find the least costly ones to cover all minterms.");

		return bestSolutions;
	}
}
