package Code;



public class Node {
	Object data ;
	Node next;
	public Node (Object data , Node next){
		this.data=data;
		this.next=next;
	}
	public Object getElement(){
		return data ;
	}
	public Node getNext(){
		return next;
	}
	public void setElement(Object data){
		this.data=data;
	}
	public void setNext(Node next){
		this.next=next;
	}
	public String toString(){
		return data+""; 
	}


}
