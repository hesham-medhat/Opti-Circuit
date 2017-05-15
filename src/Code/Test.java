package Code;

public class Test {

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
//		EssentialPrimeImplicants e = new EssentialPrimeImplicants();
//		DoublyLinkedList[] d = new DoublyLinkedList[2];
//		d[0]=new DoublyLinkedList();
//		d[0].add(0);
//		d[0].add(2);
//		d[1]=new DoublyLinkedList();
//		d[1].add(5);d[1].add(2);
//		int[] minterms = {2,4,8,5,6,10,12,14,15};
		int[] mintermsWDC = {0,2,4,5,7,11,12};
//		PrimeImplicants p = new PrimeImplicants();
//		DoublyLinkedList[] primes = p.generatePrimeImplicants(minterms);
//		DoublyLinkedList[] coveringPIs = e.coveringPIs(e.coveredMinterms(d), m);
//		
		
//		EssentialPrimeImplicants epi = new EssentialPrimeImplicants();
//		DoublyLinkedList solutionsArray = epi.getSolutions(primes, minterms);
//		int maxChar = p.getMaxChar();
//		String[] solutions = epi.possibleOptimization(primes, solutionsArray, maxChar);
//		for (String solution: solutions) {
//			System.out.println(solution);
//		}

		PrimeImplicants primeImplicants = new PrimeImplicants();
		DoublyLinkedList[] primes = primeImplicants.generatePrimeImplicants(mintermsWDC);
		EssentialPrimeImplicants essential = new EssentialPrimeImplicants();
		DoublyLinkedList[] coveredMT = essential.coveredMinterms(primes);
		DoublyLinkedList[] coveringImplicants = essential.coveringPIs(coveredMT, mintermsWDC);
		DoublyLinkedList essentialsList = essential.getEssentials(coveringImplicants);
		String essentialString = essential.printImplicants(essentialsList, primes, primeImplicants.getMaxChar());
		System.out.println(essentialString);
		
	}

}
