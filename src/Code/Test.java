package Code;

public class Test {

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		EssentialPrimeImplicants e = new EssentialPrimeImplicants();
		DoublyLinkedList[] d = new DoublyLinkedList[3];
		d[0]=new DoublyLinkedList();
		d[0].add(1);
		d[0].add(2);
		d[0].add(4);
		d[0].add(1);
		d[1]=new DoublyLinkedList();
		d[2]=new DoublyLinkedList();
		d[1].add(2);d[1].add(1);d[1].add(4);
		d[2].add(3);d[2].add(8);d[2].add(1);
		int[] m = {1,2,3,4,5,6,7,8,11,12};
		DoublyLinkedList[] coveringPIs = e.coveringPIs(e.coveredMinterms(d), m);
		System.out.println(e.getFormula(coveringPIs));
		System.out.println("xD");
		System.out.println("xD");
		System.out.println("xD");
		
	}

}
