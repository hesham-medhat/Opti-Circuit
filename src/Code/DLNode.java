package Code;

/**
 * Doubly node.
 * @author A2HP11
 */
public class DLNode {

	/**
	 * Element in the node.
	 */
	private Object element;
	/**
	 * Next node.
	 */
	private DLNode next;
	/**
	 * Previous node.
	 */
	private DLNode previous;

	/**
	 * Constructor.
	 * @param elementI	input element.
	 * @param nextI	input next node.
	 * @param previousI	input previous node.
	 */
	public DLNode(final Object elementI
			, final DLNode nextI
			, final DLNode previousI) {
		this.element = elementI;
		this.next = nextI;
		this.previous = previousI;
	}

	/**
	 * Returns the element of the node.
	 * @return element getter.
	 *
	 */
	public Object getElement() {
		return element;
	}

	/**
	 * Gets the next node.
	 * @return next node.
	 */
	public DLNode getNext() {
		return next;
	}

	/**
	 * Returns the previous node.
	 * @return the previous node.
	 */
	public DLNode getPrevious() {
		return previous;
	}

	/**
	 * Sets the element of the node with the given object.
	 * @param elementI parameter.
	 * This is the object which will be assigned
	 * as the element of the node.
	 */
	public void setElement(final Object elementI) {
		this.element = elementI;
	}

	/**
	 * Sets the reference to the next node.
	 * @param nextI DL node.
	 */
	public void setNext(final DLNode nextI) {
		this.next = nextI;
	}

	/**
	 * Sets the reference to the previous node.
	 * @param previousI node.
	 */
	public void setPrevious(final DLNode previousI) {
		this.previous = previousI;
	}

}
