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
	public DoublyLinkedList[] coveredMinterms(DoublyLinkedList[] primes) {
		// TODO Auto-generated method stub
		return null;
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
	public String getFormula(DoublyLinkedList[] coveringImplicants) {
		// TODO Auto-generated method stub
		return null;
	}

}
