package organizingCode;

import Code.DoublyLinkedList;
import Code.SinglyLinkedList;

public interface IPrimeImplicants {
	/**
	 * listing minterms in groups
	 * @author Marina
	 * @parameters int array of minterms
	 * @return  array of linked lists
	 */
	SinglyLinkedList[] listing(int[] minterms);
	
	/**
	 * Sorts the combinations of the implicant.
	 * @author Marina
	 * @param implicant input
	 * @return sorted implicant
	 */
	DoublyLinkedList sortImplicantCombinations(DoublyLinkedList implicant);

	/**
	 * combine one haming distance minterms
	 * 
	 * @parameters 2 lists return a list
	 */
	SinglyLinkedList combiningTwoGroups(SinglyLinkedList group1, SinglyLinkedList group2);

	/**
	 * performs the method combine 2 groups depending on the length of the array of lists
	 * call itself recursively till the length of the list is dropped down to 1 list 
	 * @author Marina
	 * @parameter array of singly linked lists
	 * @return array of singly linked lists
	 */
	SinglyLinkedList []combineOneLevel (SinglyLinkedList[]list);

	/**
	 * performs the method combining one level till it generates 1 group only
	 * containing the essential prime implicants
	 * 
	 * @author Marina
	 * @parameter array of integers containing minterms
	 * @return one singly linked list of prime implicants ready to get
	 *         combinations method
	 */
	DoublyLinkedList[] generatePrimeImplicants(int[] minterms);
}
