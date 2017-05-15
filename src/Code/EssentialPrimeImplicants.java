package Code;

import organizingCode.IEssentialPrimeImplicants;

/**
 * This class works on getting essential PIs from given PIs.
 *
 * @author H
 *
 */
public class EssentialPrimeImplicants implements IEssentialPrimeImplicants {

	private static int MAX_TERMS_ASSUMED = 999999999;

	@Override
	public void findCombinations(final DLNode node, int sum, final DoublyLinkedList coveredMTs) {
		if (node != null) {
			if (node.getNext() != null) {
				findCombinations(node.getNext(), sum, coveredMTs); // Skipped
																	// element
			}
			sum += (int) node.getElement();
			coveredMTs.add(sum);
			findCombinations(node.getNext(), sum, coveredMTs); // Counted
																// element
		}
	}

	@Override
	public DoublyLinkedList[] coveredMinterms(final DoublyLinkedList[] primes) {
		final DoublyLinkedList[] coveredMTs = new DoublyLinkedList[primes.length];
		int index = 0;
		for (final DoublyLinkedList prime : primes) {
			final DLNode headPrime = prime.getHead();
			coveredMTs[index] = new DoublyLinkedList();
			coveredMTs[index].add(headPrime.getElement());
			if (headPrime.getNext() != null) {
				findCombinations(headPrime.getNext(), (int) headPrime.getElement(), coveredMTs[index]);
			}
			index++;
		}
		return coveredMTs;
	}

	@Override
	public DoublyLinkedList[] coveringPIs(final DoublyLinkedList[] coveredMT, final int[] minterms) {

		final DoublyLinkedList[] coveringPIs = new DoublyLinkedList[minterms.length];
		int index = 0; // index of the minterm. We will see which implicants
						// cover it.
		for (final int m : minterms) {

			final DoublyLinkedList coveringPI4m = new DoublyLinkedList();

			for (int i = 0; i < coveredMT.length; i++) {
				DLNode iteratorNode;
				iteratorNode = coveredMT[i].getHead();
				while (iteratorNode != null) {
					if ((int) iteratorNode.getElement() == m) {
						coveringPI4m.add(i);
					}
					iteratorNode = iteratorNode.getNext();
				}
			}

			coveringPIs[index] = coveringPI4m;
			index++;
		}
		return coveringPIs;
	}

	@Override
	public String getFormula(final DoublyLinkedList[] coveringImplicants) {
		final StringBuilder formula = new StringBuilder();
		boolean firstIteration;
		for (final DoublyLinkedList coveringImplicant : coveringImplicants) {
			DLNode iterator = coveringImplicant.getHead();
			formula.append('(');
			firstIteration = true;
			while (iterator != null) {
				if (!firstIteration) {
					formula.append('+');
				}
				firstIteration = false;
				formula.append('P');
				formula.append(iterator.getElement());
				iterator = iterator.getNext();
			}
			formula.append(')');
		}
		return formula.toString();
	}

	@Override
	public DoublyLinkedList getEssentials(final DoublyLinkedList[] coveringImplicants) {
		DoublyLinkedList essentials = new DoublyLinkedList();
		for (DoublyLinkedList coveringPI4m : coveringImplicants) {
			if (coveringPI4m.getSize() == 1) {
				essentials.add(coveringPI4m.get(0)); // Adding index in primes
			}
		}
		return essentials;
		// if (essentials.getSize() != 0) {
		// final StringBuilder formula = new StringBuilder();
		// DLNode iterator;
		// iterator = essentials.getHead();
		// while (iterator != null) {
		// formula.append('P');
		// formula.append((int) iterator.getElement());
		// iterator = iterator.getNext();
		// }
		// return formula.toString();
		// }
	}

	/********/
	@Override
	public DoublyLinkedList findSolutions(DoublyLinkedList[] coveredMT, DoublyLinkedList[] powerSet, int[] minterms) {
		DoublyLinkedList solutions = new DoublyLinkedList();
		for (DoublyLinkedList p : powerSet) {
			boolean[] covered = new boolean[minterms.length];
			boolean allCovered = true;
			for (int i = 0; i < p.getSize(); i++) {
				DoublyLinkedList checker = coveredMT[(int) p.get(i)];
				DLNode iteratorNode = checker.getHead();
				while (iteratorNode != null) {
					int mintermIndex = -1;
					for (int minterm : minterms) {
						mintermIndex++;
						if (minterm == (int) iteratorNode.getElement()) {
							break;
						}
					}
					covered[mintermIndex] = true;
					iteratorNode = iteratorNode.getNext();
				}
			}
			for (boolean cMinterm : covered) {
				if (cMinterm != true) {
					allCovered = false;
					break;
				}
			}
			if (allCovered) {
				solutions.add(p);
			}
		}
		return solutions;
	}

	@Override
	public DoublyLinkedList getSolutions(DoublyLinkedList[] primes, int[] minterms) {
		DoublyLinkedList[] coveredMTs = coveredMinterms(primes);
		// DoublyLinkedList[] coveringPIs = coveringPIs(coveredMTs, minterms);
		DoublyLinkedList solutions = new DoublyLinkedList();
		// DoublyLinkedList thisSolution = new DoublyLinkedList();
		// DoublyLinkedList taken = new DoublyLinkedList();
		DoublyLinkedList[] powerSet = powerSet(primes.length);
		solutions = findSolutions(coveredMTs, powerSet, minterms);
		return solutions;
	}

	@Override
	public String[] possibleOptimization(DoublyLinkedList[] primes, DoublyLinkedList solutions, int maxChar) {
		String[] possibleOptimization = new String[solutions.getSize()];
		// iterate over the DLL solutions
		for (int i = 0; i < solutions.getSize(); i++) {
			// add a string to the returned array representing a solution
			possibleOptimization[i] = new String();
			// iterate over the SLL of the solution
			for (int j = 0; j < ((DoublyLinkedList) solutions.get(i)).getSize(); j++) {
				// create a reference to the implicants of the solution
				DoublyLinkedList currentImplicant = primes[(int) ((DoublyLinkedList) solutions.get(i)).get(j)];
				// convert the implicant to binary
				String mirroredBinary = Integer.toString((int) currentImplicant.get(0), 2);
				StringBuilder fullMB = new StringBuilder();
				for (int m = 0; m < maxChar - mirroredBinary.length(); m++) {
					fullMB.append('0');
				}
				fullMB.append(mirroredBinary);
				mirroredBinary = fullMB.toString();
				String binary = new String();
				for (int k = mirroredBinary.length() - 1; k >= 0; k--) {
					binary = binary + mirroredBinary.charAt(k);
				}
				char A = 'A';
				if (j != 0) {
					possibleOptimization[i] = possibleOptimization[i] + '+';
				}
				// convert the binary to literals
				for (int k = 0; k < binary.length(); k++) {
					boolean found = false;
					for (int k2 = 1; k2 < currentImplicant.size(); k2++) {
						if ((int) currentImplicant.get(k2) == Math.pow(2, binary.length() - 1 - k)) {
							found = true;
						}
					}
					if (!found) {
						if (binary.charAt(binary.length() - 1 - k) == '0') {
							possibleOptimization[i] = possibleOptimization[i] + A + '\'';
						} else {
							possibleOptimization[i] = possibleOptimization[i] + (A);
						}
					}
					A++;
				}
			}
		}

		return possibleOptimization;
	}

	@Override
	public DoublyLinkedList[] powerSet(int numberOfPrimeImplicants) {
		int size = (int) Math.pow(2, numberOfPrimeImplicants);
		DoublyLinkedList[] powerSet = new DoublyLinkedList[size];

		for (int i = 0; i < size; i++) {
			DoublyLinkedList set = new DoublyLinkedList();
			for (int j = 0; j < numberOfPrimeImplicants; j++) {
				if ((i & 1 << j) != 0) {
					set.add(j);
				}
			}
			powerSet[i] = set;
		}
		return powerSet;
	}

	@Override
	public String[] bestOptimization(String[] possibleOptimization) {
		int[] weights = new int[possibleOptimization.length];
		int minimum = MAX_TERMS_ASSUMED;
		DoublyLinkedList bestList = new DoublyLinkedList();
		for (int i = 0; i < weights.length; i++) {
			int terms = 1;
			for (int j = 0 ; j < possibleOptimization[i].length() ; j++) {
				 if (possibleOptimization[i].charAt(j) == '+') {
					 terms++;
				 }
			}
			weights[i] = terms;
			if (weights[i] < minimum) {
				minimum = weights[i];
			}
		}
		for (int minIn = 0; minIn < possibleOptimization.length; minIn++) {
			if (weights[minIn] == minimum) {
				bestList.add(possibleOptimization[minIn]);
			}
		}
		String[] bestArray = new String[bestList.getSize()];
		DLNode iteratorNode = bestList.getHead();
		for (int g = 0; g < bestArray.length; g++) {
			bestArray[g] = (String) iteratorNode.getElement();
			iteratorNode = iteratorNode.getNext();
		}
		return bestArray;
	}

	@Override
	public String printImplicants(DoublyLinkedList list, DoublyLinkedList[] primes, int maxChar) {
		String[] implicants = new String[list.getSize()];
		for (int i = 0; i < list.getSize(); i++) {

			// add a string to the returned array representing a solution
			implicants[i] = new String();
			// iterate over the SLL of the solution
			// create a reference to the implicants of the solution
			DoublyLinkedList currentImplicant = primes[(int) list.get(i)];
			// convert the implicant to binary
			String mirroredBinary = Integer.toString((int) currentImplicant.get(0), 2);
			StringBuilder fullMB = new StringBuilder();
			for (int m = 0; m < maxChar - mirroredBinary.length(); m++) {
				fullMB.append('0');
			}
			fullMB.append(mirroredBinary);
			mirroredBinary = fullMB.toString();
			String binary = new String();
			for (int k = mirroredBinary.length() - 1; k >= 0; k--) {
				binary = binary + mirroredBinary.charAt(k);
			}
			char A = 'A';

			// convert the binary to literals
			for (int k = 0; k < binary.length(); k++) {
				boolean found = false;
				for (int k2 = 1; k2 < currentImplicant.size(); k2++) {
					if ((int) currentImplicant.get(k2) == Math.pow(2, binary.length() - 1 - k)) {
						found = true;
					}
				}
				if (!found) {
					if (binary.charAt(binary.length() - 1 - k) == '0') {
						implicants[i] = implicants[i] + A + '\'';
					} else {
						implicants[i] = implicants[i] + (A);
					}
				}
				A++;
			}
		}
		String result = new String();
		for (int i = 0; i < implicants.length; i++) {
			result += implicants[i];
			result += " ";
		}

		return result;

	}

}
