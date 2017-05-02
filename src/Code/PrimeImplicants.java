package Code;

import organizingCode.IPrimeImplicants;

public class PrimeImplicants implements IPrimeImplicants {

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
				groups[0].add(0);
			} else {
				final String binary = Integer.toString(minterm, 2);
				int ones = 0;
				// count ones
				for (int j = 0; j < binary.length(); j++) {
					if (binary.charAt(j) == '1') {
						ones++;
					}
				}
				groups[ones].add(minterm);
			}
		}

		return groups;
	}

	@Override
	public SinglyLinkedList[] compining(final SinglyLinkedList group1, final SinglyLinkedList group2) {
		// TODO Auto-generated method stub
		final SinglyLinkedList[] result = new SinglyLinkedList[2];
		result[0] = new SinglyLinkedList();
		for (int i = 0; i < group1.size; i++) {
			for (int j = 0; j < group2.size; j++) {
				final int x = (int) group1.get(i);
				final int y = (int) group2.get(j);
				if (x < y && Math.log(y - x) / Math.log(2) - (int) (Math.log(y - x) / Math.log(2)) == 0) {

				}
			}
		}
		return null;
	}

}
