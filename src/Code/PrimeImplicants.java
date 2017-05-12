package Code;

import organizingCode.IPrimeImplicants;

public class PrimeImplicants implements IPrimeImplicants {

	/**
	 * The prime implicants obtained.
	 */
	private SinglyLinkedList primes = new SinglyLinkedList();
	
	@Override
	public SinglyLinkedList[] listing(final int[] minterms) {
		// get max of minterms
		int max = minterms[0];
		for (int i = 1; i < minterms.length; i++) {
			if (max < minterms[i]) {
				max = minterms[i];
			}
		}
		// determine number of groups based on max minterm
		final int digits = (int) (Math.log(max) / Math.log(2) + 1);
		// create array of SLL each list represent a group
		final SinglyLinkedList[] groups = new SinglyLinkedList[digits + 1];
		for (int i = 0; i < groups.length; i++) {
			groups[i] = new SinglyLinkedList();
		}
		// distribute minterms on rows of the array based on number
		//of ones in the binary representation of the minterm
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
		//end of distribution 
		return groups;
	}

	@Override
	public SinglyLinkedList combiningTwoGroups(SinglyLinkedList group1, final SinglyLinkedList group2) {
		//create a new SLL to put the new group in it
		SinglyLinkedList result = new SinglyLinkedList();
		//iterate over the first group sent to the method
		for (int i = 0; i < group1.size; i++) {
			//iterate over the second group sent to the method
			for (int j = 0; j < group2.size; j++) {
				//get the elements from the groups one by one
				final int x = (int) ((DoublyLinkedList) group1.get(i)).get(0);
				final int y = (int) ((DoublyLinkedList) group2.get(j)).get(0);
				// check if the difference between 2 implicants is a power of 2
				if (x < y && Math.log(y - x) / Math.log(2) - (int) (Math.log(y - x) / Math.log(2)) < 1e-10) {
					//iterate over the 2 DLL of the element to check if the haming distances match or not
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
					//if we get matching mark the elements as taken 
					if (!mismatch) {
						((DoublyLinkedList) group1.get(i)).setTaken(true);
						((DoublyLinkedList) group2.get(j)).setTaken(true);
						//put the new element in the SLL of result
						DoublyLinkedList temp = new DoublyLinkedList();
						for (int c = 0 ; c < ((DoublyLinkedList) group1.get(i)).getSize() ; c++) {
							temp.add(((DoublyLinkedList) group1.get(i)).get(c));
						}
						//add the haming distance between the 2 combined elements to the new element
						temp.add(y - x);
						//arrange the haming distances to make it easy to check matching elements
						result.add(this.sortImplicantCombinations(temp));
					}
				}
			}
		}
		//check if there is any non-taken elements in the first group and add it to the primes
		Node iterator;
		iterator = group1.head.getNext();
		while (iterator != null) {
			if (!((DoublyLinkedList) (iterator.getElement())).isTaken()) {
				primes.add(iterator.getElement());
			}
			iterator = iterator.getNext();
		}
		//return the resulting group as the first group to the next level
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

	
	

	@Override
	public SinglyLinkedList[] combineOneLevel(SinglyLinkedList[] list) {
		//if the list contains only one group end recursion
		if(list.length==1)return list;
		//iterate over the groups of the list and mark all the elements as not taken
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].size; j++) {
				((DoublyLinkedList)(list[i].get(j))).setTaken(false);
			}
		}
		//iterate over the groups of the list and combine them 2 by 2 and store the result for the next level
		for (int i = 0; i < list.length-1; i++) {
			list[i]=this.combiningTwoGroups(list[i], list[i+1]);
			//remove repetition here
			
		        

		}
		//check the none taken elements in the last group because it hasn't been
		//checked in the combiningTowGroups method
		Node iterator;
		iterator = list[list.length-1].head.getNext();
		while (iterator != null) {
			if (!((DoublyLinkedList) (iterator.getElement())).isTaken()) {
				primes.add(iterator.getElement());
			}
			iterator = iterator.getNext();
		}
		//prepare a new list for the new level
		SinglyLinkedList []newList=new SinglyLinkedList[list.length-1];
		for (int i = 0; i < newList.length; i++) {
			newList[i]=list[i];
		}
		//recursively call the function again for the new list to combine the new level
		return this.combineOneLevel(newList);
	}

	@Override
	public SinglyLinkedList generatePrimeImplicants(int[] minterms) {
		this.combineOneLevel(this.listing(minterms));
		return this.primes;
	}

}
