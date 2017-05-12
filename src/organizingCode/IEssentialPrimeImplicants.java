package organizingCode;

import Code.DLNode;
import Code.DoublyLinkedList;
import Code.SinglyLinkedList;

/**
 * This is the interface of the class that finds the essential
 * prime implicants.
 * @author H
 *
 */
public interface IEssentialPrimeImplicants {

	/**
	 * Gets possible minterm combinations from a PI.
	 * This method works recursively adding combinations to a given list.
	 * @param node of the list representation of PI.
	 * @param sum of the combinations picked so far.
	 * @param coveredMTs list where the possible combinations will be added.
	 */
	public void findCombinations (final DLNode node, int sum, final DoublyLinkedList coveredMTs);

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
	public String getFormula(final DoublyLinkedList[] coveringImplicants);
	
	/**
	 * This method finds the essential PIs using dominance.
	 * @param coveringImplicants array obtained from coveringImplicants.
	 * @return The essential prime implicants in the form of a
	 * string as Px Py Pz where (x,y,z..) represent the indices
	 * of these implicants in the "primes" input list.
	 */
	public String getEssentials(final DoublyLinkedList[] coveringImplicants);
	
	/**
	 * This function uses recursion to find all possible solutions
	 * of how to use the covering PIs to cover the MTs we need covered.
	 * @param solutions the returner list of solutions
	 * @param toCover index of coveringPIs MT that we need covered
	 * @param coveringPIs obtained from coveringPIs method
	 * @param thisSolution the current solution that the method
	 * is trying to get in the recursive calls
	 * @return the list of SLLs of solutions.
	 */
	public SinglyLinkedList findSolutions(SinglyLinkedList solutions, int toCover, DoublyLinkedList[] coveringPIs, SinglyLinkedList thisSolution);
	
	/**
	 * This method uses all methods in this class to obtain all the
	 * possible solutions for the given primes to cover given minterms.
	 * @param primes array of prime implicants (DLLs).
	 * @param minterms list of minterms to cover.
	 * @return a String list of solutions.
	 * Each element of this list is a string solution in the form
	 * of PxPyPz where (x,y,z,..) are the indices of the PIs in the
	 * given primes array.
	 */
	public SinglyLinkedList getSolutions(final DoublyLinkedList[] primes, final int[] minterms);
	
	/**
	 * this method turn all the possible silotions into functions with letrals 
	 * @author Marina
	 * @param primes array of prime implicants (DLLs).
	 * @parameter SLL of SLL each list is a solution 
	 * @return array of strings each string is a possible solution
	 */
	public String[]possibleOptimization (DoublyLinkedList[] primes, SinglyLinkedList solutions );
}
