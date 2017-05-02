package Code;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]minterms=new int[16];
		for (int i = 0; i < minterms.length; i++) {
			minterms[i]=i;
		}
		PrimeImplicants p = new PrimeImplicants();
		p.listing(minterms);

	}

}
