package Code;

import organizingCode.IEssentialPrimeImplicants;

/**
 * This class works on getting essential PIs from given PIs.
 *
 * @author H
 *
 */
public class EssentialPrimeImplicants implements IEssentialPrimeImplicants {

	@Override
	public void findCombinations(final DLNode node, int sum, final DoublyLinkedList coveredMTs) {
		if (node != null) {
			if (node.getNext() != null) {
				findCombinations(node.getNext(), sum, coveredMTs); // Skipped element
			}
			sum += (int) node.getElement();
			coveredMTs.add(sum);
			findCombinations(node.getNext(), sum, coveredMTs); // Counted element
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
			if (headPrime.getNext() != null ) {
				findCombinations(headPrime.getNext(), (int) headPrime.getElement(), coveredMTs[index]);
			}
			index++;
		}
		return coveredMTs;
	}

	@Override
	public DoublyLinkedList[] coveringPIs(final DoublyLinkedList[] coveredMT, final int[] minterms) {

		final DoublyLinkedList[] coveringPIs = new DoublyLinkedList[minterms.length];
		int index = 0; //index of the minterm. We will see which implicants cover it.
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
	public String getEssentials(final DoublyLinkedList[] coveringImplicants) {
		DoublyLinkedList essentials = new DoublyLinkedList();
		for (DoublyLinkedList coveringPI4m : coveringImplicants) {
			if (coveringPI4m.getSize() == 1) {
				essentials.add(coveringPI4m.get(0)); //Adding index in primes
			}
		}
		
		if (essentials.getSize() != 0) {
			final StringBuilder formula = new StringBuilder();
			DLNode iterator;
			iterator = essentials.getHead();
			while (iterator != null) {
				formula.append('P');
				formula.append((int) iterator.getElement());
				iterator = iterator.getNext();
			}
			return formula.toString();
		}
		return null;
	}
	
	@Override
	public SinglyLinkedList findSolutions(SinglyLinkedList solutions, final int toCover, DoublyLinkedList[] coveringPIs, SinglyLinkedList thisSolution) {
		if (toCover != coveringPIs.length) { //We still need to cover MTs
			if (coveringPIs[toCover].getSize() != 1) { //There is more than one PI that could cover the MT
				DLNode iteratorNode;
				iteratorNode = coveringPIs[toCover].getHead();
				while (iteratorNode != null) {
					thisSolution.add(iteratorNode.getElement());
					solutions = findSolutions(solutions, toCover + 1, coveringPIs, thisSolution);
					iteratorNode = iteratorNode.getNext();
					thisSolution.remove(thisSolution.size() - 1);
				}
			} else {
				thisSolution.add(coveringPIs[toCover].getHead().getElement());
				solutions = findSolutions(solutions, toCover + 1, coveringPIs, thisSolution);
			}
		} else {
			solutions.add(thisSolution);
		}
		return solutions;
	}

	@Override
	public SinglyLinkedList getSolutions(DoublyLinkedList[] primes, int[] minterms) {
		DoublyLinkedList[] coveredMTs = coveredMinterms(primes);
		DoublyLinkedList[] coveringPIs = coveringPIs(coveredMTs, minterms);
		
		SinglyLinkedList solutions = new SinglyLinkedList();
		SinglyLinkedList thisSolution = new SinglyLinkedList();
		solutions = findSolutions(solutions, 0, coveringPIs, thisSolution);
		return solutions;
	}

	@Override
	public String[] possibleOptimization(DoublyLinkedList[] primes, SinglyLinkedList solutions) {
		// TODO Auto-generated method stub
		String[] possibleOptimization = new String[solutions.size];
		//iterate over the SLL solutions
		for (int i = 0; i < solutions.size; i++) {
			//add a string to the returned array representing a solution
			possibleOptimization[i]=new String();
			//iterate over the SLL of the solution
			for (int j = 0; j < ((SinglyLinkedList)solutions.get(i)).size; j++) {
				//create a reference to the implicants of the solution
				DoublyLinkedList currentImplicant =primes[(int)((SinglyLinkedList)solutions.get(i)).get(j)];
				//convert the implicant to binary
				String binary = Integer.toString((int)currentImplicant.get(0), 2);
				int A = 'A';
				if(j!=0){
					possibleOptimization[i]=possibleOptimization[i]+'+';
				}
				//convert the binary to literals
				for (int k = 0; k < binary.length(); k++) {
					boolean found = false;
					for (int k2 = 1; k2 < currentImplicant.size(); k2++) {
						if((int)currentImplicant.get(k2)==Math.pow(2, k)){
							found= true;
						}
					}
					if(!found){
					if(binary.charAt(k)=='0'){
					possibleOptimization[i]=possibleOptimization[i]+A;	
					}else{
						possibleOptimization[i]=possibleOptimization[i]+(A)+'\'';
					}
				}
				A++;
				}}
			}
		
		return possibleOptimization;
	}

}
