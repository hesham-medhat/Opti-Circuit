package Code;

public class Test {

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		final int[] minterms = new int[16];
		for (int i = 0; i < minterms.length; i++) {
			minterms[i] = i;
		}
		final PrimeImplicants p = new PrimeImplicants();
		p.listing(minterms);

	}

}
