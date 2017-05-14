package Code;


import organizingCode.IQueue;;

public class LinkedBasedQ implements IQueue {
	private SinglyLinkedList q = new SinglyLinkedList();
	@Override
	public void enqueue(Object item) {
		q.add(item);
	}

	@Override
	public Object dequeue() {
		if(q.size==0)throw null;
		Object temp = q.get(0);
		q.remove(0);
		return temp;
	}

	@Override
	public boolean isEmpty() {
		if(q.size==0)return true;
		return false;
	}

	@Override
	public int size() {
		return  q.size;
	}

}
