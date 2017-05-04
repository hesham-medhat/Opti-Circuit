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
	public void getCombinations(final DLNode node, int sum, final DoublyLinkedList coveredMTs) {
		if (node.getNext() != null) {
			getCombinations(node.getNext(), sum, coveredMTs); // Skipped element
		}
		sum += (int) node.getElement();
		DLNode mintermNode = new DLNode(sum, null, null);
		coveredMTs.add(mintermNode);
		getCombinations(node.getNext(), sum, coveredMTs); // Counted element
	}

	@Override
	public DoublyLinkedList[] coveredMinterms(DoublyLinkedList[] primes) {
		DoublyLinkedList[] coveredMTs = new DoublyLinkedList[primes.length];
		int index = 0;
		for (DoublyLinkedList prime : primes) {
			DLNode headPrime = prime.getHead();
			coveredMTs[index].add(headPrime.getElement());
			getCombinations(headPrime, 0, coveredMTs[index]);
			index++;
		}
		return coveredMTs;
	}

	@Override
	public DoublyLinkedList[] coveringPIs(DoublyLinkedList[] coveredMT, int[] minterms) {

		DoublyLinkedList[] coveringPIs = new DoublyLinkedList[minterms.length];

		for (int m : minterms) {

			DoublyLinkedList coveringPI4m = new DoublyLinkedList();

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

			coveringPIs[m] = coveringPI4m;

		}
		return coveringPIs;
	}

	@Override
	public String getFormula(final DoublyLinkedList[] coveringImplicants) {
		StringBuilder formula = new StringBuilder();
		boolean firstIteration;
		for (int i = 0 ; i < coveringImplicants.length ; i++) {
			DLNode iterator = coveringImplicants[i].getHead();
			formula.append('(');
			firstIteration = true;
			while (iterator != null) {
				if (!firstIteration) {
					formula.append('+');
					firstIteration = false;
				}
				formula.append('P');
				formula.append(iterator.getElement());
			}
			formula.append(')');
		}
		return formula.toString();
	}


}
