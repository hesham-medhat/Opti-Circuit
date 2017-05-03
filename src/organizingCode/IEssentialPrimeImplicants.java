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
	 * @return An array of lists of MTs covered by the PIs.
	 * Indices of this array correspond to the indices of
	 * the given PIs array.
	 */
	public DoublyLinkedList[] coveredMinterms(DoublyLinkedList[] primes);

	/**
	 * This method produces a list of given prime implicants.
	 * @param coveredMT this is the array of lists of covered MTs by each PI.
	 * Obtained from coveredMinterms method.
	 * @param minterms this is the sorted array of MTs.
	 * @return An array of covering PIs for each MT.
	 * Indices of this array correspond to the MTs.
	 */
	public DoublyLinkedList[] coveringPIs(DoublyLinkedList[] coveredMT, int[] minterms);

	/**
	 * This method produces the solution formula.
	 * @param coveringImplicants array obtained from coveringImplicants.
	 * @return String of the solution formula.
	 * This needs simplification to get the best possible solutions
	 * for optimization.
	 * At this point, we need boolean algebra solving techniques to
	 * get the final optimal answers.
	 */
	public String getFormula(DoublyLinkedList[] coveringImplicants);
}
