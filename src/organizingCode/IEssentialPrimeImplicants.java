package organizingCode;

import Code.DoublyLinkedList;

/**
 * This is the interface of the class that finds the essential
 * prime implicants.
 * @author H
 *
 */
public interface IEssentialPrimeImplicants {

	/**
	 * This method gets the array of MTs covered by given PIs.
	 * @param primes array of PIs lists.
	 * @return an array of lists of MTs covered by the PIs.
	 * Indices of this array correspond to the indices of
	 * the given PIs array.
	 */
	public DoublyLinkedList[] coveredMinterms(DoublyLinkedList[] primes);

	/**
	 * This method produces a list of given prime implicants.
	 * @param primes this is the list of prime implicants.
	 * @param coveredMT this is the array of lists of covered MTs by each PI.
	 * @return
	 */
	public DoublyLinkedList[] coveringImplicants(DoublyLinkedList[] coveredMT);
}
