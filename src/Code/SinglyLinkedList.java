package Code;

import organizingCode.ILinkedList;

public class SinglyLinkedList implements ILinkedList {
	int size;
	Node head ;
	public  SinglyLinkedList() {
		size=0;
		head=new Node(null, null);
	}

	@Override
	public void add(int index, Object element) {
		if(size>=index){
		Node added = new Node (element,null);
		Node current= head.getNext();
		Node pre = head;
		for (int i = 0; i < index; i++) {
			pre=current;
			current=current.getNext();
		}
		pre.setNext(added);
		added.setNext(current);
		size++;
		}else throw null;
	}

	@Override
	public void add (Object element) {
		//System.out.println("h");
		Node added = new  Node(element, null);
		if (head.getNext()==null){
			head.setNext(added);
			size++;
		}else{
		Node current= head.getNext();
		Node pre = head;
		for (int i = 0; i < size; i++) {
			pre=current;
			current=current.getNext();
		}
		pre.setNext(added);
		
		size++;
		}

	}

	@Override
	public Object get(int index) {
		if (size<=index){
		throw null;
		}else{
			Node current= head.getNext();
			
			for (int i = 0; i < index; i++) {
				
				current=current.getNext();
			}
			return current.getElement();
		}
	}

	@Override
	public void set(int index, Object element) {
		if(index<size){
		Node current= head.getNext();
		
		for (int i = 0; i < index; i++) {
			
			current=current.getNext();
		}
		
		current.setElement(element);
		
		}else throw null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head.setNext(null);
		size=0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(head.getNext()==null)return true;
		return false;
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		if(size>index){
		Node current= head.getNext();
		Node pre = head;
		for (int i = 0; i < index; i++) {
			pre=current;
			current=current.getNext();
		}
		pre.setNext(current.getNext());
		
		size--;}else throw null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
	if (head.getNext()==null)return 0;
	else{
		int i;
		Node current= head.getNext();
		
		for ( i = 0; current!=null; i++) {
			
			current=current.getNext();
			
		}
		return i;
	}
		
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		try{ILinkedList result=new SinglyLinkedList() ;
		if(this.size()<=fromIndex||this.size()<=toIndex)
		throw null;
		else{
			
		Node current= head.getNext();
		Node pre = head;
		for (int i = 0; i < fromIndex; i++) {
			pre.setNext(current);
			current=current.getNext();
		}
		
		for (int i = fromIndex; i <= toIndex; i++) {
			result.add(current.getElement());
			current=current.getNext();
		}
		return result;
		}}catch(Exception e){throw null;}
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		if(o==null)throw null;
		if(this.size()==0)throw null;
		try{Node current = head.getNext();
		while (current!=null) {
			if(current.getElement()==o){
				return true;
			}
			current=current.getNext();
		}return false;}catch(Exception e){throw null;}
	}

}
