package organizingCode;

/**
 * This class takes the string generated from the
 * EssentialPrimeImplicants and performs Boolean
 * Algebra operations on it using Petrick's
 * method to find the optimal solution.
 * @author H
 *
 */
public interface IBooleanAlgebra {

	/**
	 * Performs the distributive laws on the formula.
	 * @param formula obtained from EssentialPrimeImplicants
	 * @return formula after performing distributive laws
	 */
	String distribution(final String formula);

	/**
	 * Performs the absorption theorem to simplify the formula
	 * after distribution to get the possible optimal answers.
	 * @param formula obtained from EssentialPrimeImplicants
	 * after distribution.
	 * @return final simplified formula
	 */
	String absorption(final String formula);
}
