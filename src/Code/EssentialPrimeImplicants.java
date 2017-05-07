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
		if (node != null) {
			if (node.getNext() != null) {
				getCombinations(node.getNext(), sum, coveredMTs); // Skipped element
			}
			sum += (int) node.getElement();
			coveredMTs.add(sum);
			getCombinations(node.getNext(), sum, coveredMTs); // Counted element
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
				getCombinations(headPrime.getNext(), (int) headPrime.getElement(), coveredMTs[index]);
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

}
