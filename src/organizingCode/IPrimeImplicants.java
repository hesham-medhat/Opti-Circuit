package organizingCode;

import Code.DoublyLinkedList;
import Code.SinglyLinkedList;

public interface IPrimeImplicants {
	/**
	 * listing minterms in groups
	 * 
	 * @parameters int array of minterms return array of linked lists
	 */
	SinglyLinkedList[] listing(int[] minterms);
	
	/**
	 * Sorts the combinations of the implicant.
	 * @param implicant input
	 * @return sorted implicant
	 */
	DoublyLinkedList sortImplicantCombinations(DoublyLinkedList implicant);

	/**
	 * combine one haming distance minterms
	 * 
	 * @parameters 2 lists return a list
	 */
	SinglyLinkedList combiningTwoGroups(SinglyLinkedList group1, SinglyLinkedList group2S);
}
