package organizingCode;

import Code.SinglyLinkedList;

public interface IPrimeImplicants {
	/**listing minterms in groups
	 * @parameters int array of minterms
	 return array of linked lists */
	SinglyLinkedList[] listing (int[] minterms);
	/**compine one haming distance minterms
	 * @parameters 2 lists
	 return a list*/
	SinglyLinkedList[]compining (SinglyLinkedList group1,SinglyLinkedList group2);
}
