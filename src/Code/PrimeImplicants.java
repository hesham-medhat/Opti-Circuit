package Code;

import organizingCode.IPrimeImplicants;

public class PrimeImplicants implements IPrimeImplicants {

	/**
	 * The prime implicants obtained.
	 */
	private SinglyLinkedList primes = new SinglyLinkedList();
	
	@Override
	public SinglyLinkedList[] listing(final int[] minterms) {
		// TODO Auto-generated method stub
		// get max of minterms
		int max = minterms[0];
		for (int i = 1; i < minterms.length; i++) {
			if (max < minterms[i]) {
				max = minterms[i];
			}
		}
		// determine number of groups based on max minterm
		final int digits = (int) (Math.log(max) / Math.log(2) + 1);
		// create array of arrays each row represent a group
		final SinglyLinkedList[] groups = new SinglyLinkedList[digits + 1];
		for (int i = 0; i < groups.length; i++) {
			groups[i] = new SinglyLinkedList();
		}
		// distribute minterms on rows of the array
		for (final int minterm : minterms) {
			if (minterm == 0) {
				groups[0].add(new DoublyLinkedList());
				DoublyLinkedList x = (DoublyLinkedList) groups[0].get(0);
				x.add(0);
			} else {
				final String binary = Integer.toString(minterm, 2);
				int ones = 0;
				// count ones
				for (int j = 0; j < binary.length(); j++) {
					if (binary.charAt(j) == '1') {
						ones++;
					}
				}
				groups[ones].add(new DoublyLinkedList());
				DoublyLinkedList x = (DoublyLinkedList) groups[ones]
						.get(groups[ones].isEmpty() ? 0 : groups[ones].size - 1);
				x.add(minterm);

			}
		}

		return groups;
	}

	@Override
	public SinglyLinkedList combiningTwoGroups(SinglyLinkedList group1, final SinglyLinkedList group2) {
		SinglyLinkedList result = new SinglyLinkedList();
		for (int i = 0; i < group1.size; i++) {
			for (int j = 0; j < group2.size; j++) {
				final int x = (int) ((DoublyLinkedList) group1.get(i)).get(0);
				final int y = (int) ((DoublyLinkedList) group2.get(j)).get(0);
				// the difference between 2 implicants is a power of 2
				if (x < y && Math.log(y - x) / Math.log(2) - (int) (Math.log(y - x) / Math.log(2)) < 1e-10) {
					DLNode iteratorNode1;
					DLNode iteratorNode2;
					iteratorNode1 = ((DoublyLinkedList) (group1.get(i))).getNode(1);
					iteratorNode2 = ((DoublyLinkedList) group2.get(j)).getNode(1);
					boolean mismatch = false;
					while (iteratorNode1 != null) {
						if (iteratorNode1.getElement() != iteratorNode2.getElement()) {
							mismatch = true;
							break;
						}
						iteratorNode1 = iteratorNode1.getNext();
						iteratorNode2 = iteratorNode2.getNext();
					}
					if (!mismatch) {
						((DoublyLinkedList) group1.get(i)).setTaken(true);
						((DoublyLinkedList) group2.get(j)).setTaken(true);
						((DoublyLinkedList) group1.get(i)).add(y - x);
						result.add(this.sortImplicantCombinations((DoublyLinkedList) group1.get(i)));
					}
				}
			}
		}
		Node iterator;
		iterator = group1.head.getNext();
		while (iterator != null) {
			if (!((DoublyLinkedList) (iterator.getElement())).isTaken()) {
				primes.add(iterator.getElement());
			}
			iterator = iterator.getNext();
		}
		group1 = result;
		return group1;
	}

	@Override
	public DoublyLinkedList sortImplicantCombinations(DoublyLinkedList implicant) {
		if (implicant.getSize() > 2) {
			int addedTail = (int) implicant.getTail().getElement();
			DLNode iteratorNode = implicant.getHead().getNext();
			int nodeIndex = 1;
			while (iteratorNode != null && (int) iteratorNode.getElement() <= addedTail) {
				iteratorNode = iteratorNode.getNext();
				nodeIndex++;
			}
			if (iteratorNode != null) {
				implicant.add(nodeIndex, addedTail);
				implicant.remove(implicant.getSize() - 1);
			}
		}
		return implicant;
	}

}
