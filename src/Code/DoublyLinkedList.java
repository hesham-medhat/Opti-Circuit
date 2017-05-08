package Code;

import java.util.ArrayList;

import organizingCode.ILinkedList;;

/**
 * Doubly Linked List Implementation.
 * @author A2HP11
 *
 */
public class DoublyLinkedList implements ILinkedList {

	/**
	 * Head of the list.
	 */
	private DLNode head;
	/**
	 * Tail of the list.
	 */
	private DLNode tail;
	/**
	 * Size of the list.
	 */
	private int size;
	private boolean taken ;
	/*special for this app implementation
	 * 
	 */

	/**
	 * @return the taken
	 */
	public boolean isTaken() {
		return taken;
	}

	/**
	 * @param taken the taken to set
	 */
	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	/**Constructor.
	 *
	 */
	public DoublyLinkedList() {
		super();
		head = null;
		tail = null;
		size = 0;
		taken=false;
	}

	@Override
	public void add(final int index, final Object element) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (isEmpty()) {
			final DLNode firstNode =
					new DLNode(element, null, null);
			head = firstNode;
			tail = firstNode;
			size++;
			return;
		}
		if (index == 0) {
			final DLNode newHead =
					new DLNode(element, head, null);
			head = newHead;
			size++;
			return;
		}

		if (index == size) {
			add(element);
			return;
		}
		final DLNode newNode =
				new DLNode(element, null, null);
		final DLNode previous = getNode(index - 1);
		previous.getNext().setPrevious(newNode);
		newNode.setNext(previous.getNext());
		previous.setNext(newNode);
		newNode.setPrevious(previous);
		size++;
	}

	@Override
	public void add(final Object element) {
		if (isEmpty()) {
			final DLNode firstNode =
					new DLNode(element, null, null);
			head = firstNode;
			tail = firstNode;
			size++;
			return;
		}
		final DLNode lastNode =
				new DLNode(element, null, null);
		lastNode.setPrevious(tail);
		tail.setNext(lastNode);
		size++;
		tail = lastNode;
	}

	/**
	 * @return builds the array and returns it.
	 */
	public Object[] buildArray() {
		final ArrayList<Object> arrayList =
				new ArrayList<Object>();
		DLNode node = head;
		for (int i = 0; i < size(); i++) {
			arrayList.add(node.getElement());
			node = node.getNext();
		}
		return arrayList.toArray();
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean contains(final Object o) {
		if (isEmpty()) {
			return false;
		}
		DLNode searcher = head;
		while (searcher != null) {
			if (searcher.getElement().equals(o)) {
				return true;
			} else {
				searcher = searcher.getNext();
			}
		}
		return false;
	}

	@Override
	public Object get(final int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return getNode(index).getElement();
	}

	/**
	 * Gets a node at a specified index.
	 * @return head of the list.
	 */
	public DLNode getHead() {
		return head;
	}

	/**
	 * Gets the node at specified index.
	 * @param index	given index.
	 * @return	that node.
	 */
	public DLNode getNode(final int index) {

		DLNode iterator = head;

		if (index > size) {
			return null;
		}

		for (int i = 0; i < index; i++) {
			iterator = iterator.getNext();
		}
		return iterator;

	}

	/**
	 * Gets the size.
	 * @return size of the list.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Getter.
	 * @return tail of the list.
	 */
	public DLNode getTail() {
		return tail;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void remove(final int index) {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			if (head == tail) { // If there is only one element.
				clear();
				return;
			}
			head = head.getNext();
			size--;
			return;
		}
		if (index == size - 1) {
			tail = getNode(size - 2);
			tail.setNext(null);
			size--;
			return;
		}
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		final DLNode previous = getNode(index - 1);
		previous.setNext(previous.getNext().getNext());
		previous.getNext().setPrevious(previous);
		size--;
		return;
	}

	@Override
	public void set(final int index, final Object element) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		getNode(index).setElement(element);
	}

	/**
	 * Setter for head.
	 * @param headI	input.
	 */
	public void setHead(final DLNode headI) {
		this.head = headI;
	}

	/**
	 * Setter for size.
	 * @param sizeI	input.
	 */
	public void setSize(final int sizeI) {
		this.size = sizeI;
	}

	/**
	 * Setter for tail.
	 * @param tailI	input.
	 */
	public void setTail(final DLNode tailI) {
		this.tail = tailI;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ILinkedList sublist(final int fromIndex
			, final int toIndex) {

		if (fromIndex > size || toIndex > size
				|| fromIndex < 0 || toIndex < 0
				|| fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		DLNode nodeToCopy = getNode(fromIndex);
		final DoublyLinkedList sublist = new DoublyLinkedList();

		for (int i = 0; i < toIndex - fromIndex + 1; i++) {
			sublist.add(nodeToCopy.getElement());
			nodeToCopy = nodeToCopy.getNext();
		}
		return sublist;
	}

}
