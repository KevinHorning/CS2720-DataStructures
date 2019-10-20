// Creates a Linked List structure with methods for finding or deleting certain values

import java.util.ArrayList;
import java.util.LinkedList;

public class MyLinkedListClass{
	public static void main(String args[]){
		MyLInkedList list = new MyLInkedList();
		  
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4); 
		list.add(8);
		list.add(88);
		list.add(86185);
		//list.deleteByValue(3);
		
		list.print(); 
		System.out.println("Middle element: " + list.findMiddleElement(list));
	}
}

//Creates a Node for a linked list, with getter and setter methods
class Node {								
	int data;
	Node next;
	int index;
	
	Node(){
	}
	Node(int data){
		this.data = data;
		next = null;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public int getIndex(){
		return index;
	}
	public void setIndex(int index){
		this.index = index;
	}
	
}
class MyLInkedList{
	Node head;
	int index = 0;
	 
	public void add(int num) {
		if (head == null) {
			head = new Node(num);
			head.setIndex(index);
			index++;
		}
		else{
			Node temp = new Node(num);
			temp.setIndex(index);
			index++;
			Node current = head;
			if (current != null) {
				while (current.getNext() != null) {
					current = current.getNext();
				}
				current.setNext(temp);
			} 
		}
	}
	
	public Object findMiddleElement(MyLInkedList list){
		Node current = head;
		int sum = 0;
		int counter = 0;
		
		while (current.getNext() != null){
			sum ++;
			current = current.getNext();
		}
		current = head;
		while (current.getNext() != null && counter < sum/2){
			current = current.getNext();
			counter++;
		}
		return current.getData();
	}
	
	public void deleteByValue(int value){
		Node current = head;
		//Creates an arraylist with the indices of nodes with the target value
		ArrayList<Integer> targetNodeIndices = new ArrayList<Integer>();	
		//While loop that adds nodes with target value to the arraylist
		while (current != null){											
			if (current.getData() == value)
				targetNodeIndices.add(current.getIndex());
			current = current.getNext();
		}
		//For loop that deletes each node with target value
		for (int i = 0; i < targetNodeIndices.size(); i++){					
			current = head;
			//Specific for deleting first node
			if (targetNodeIndices.get(i) == 0)								
				head = current.getNext();
			else{
				//Makes current the node before the node next to be deleted
				while (current.getIndex() != targetNodeIndices.get(i) - 1){ 
					current = current.getNext();
				}
				Node temp = current;
				//Current is now node to be deleted
				current = current.getNext();
				//Specific for deleting last node
				if (current.getNext() == null){								
					temp.setNext(null);
				}
				//Makes preceding node link to seceding node
				else{														
					current = current.getNext();
					temp.setNext(current);
				}
			}
		}
	}
	
	public void deleteByIndex(int index){
		Node current = head;
		if (index == 0){	
			//Specific for deleting first node
			head = current.getNext();
		}
		else{
			//Same deleting code except with specific index
			while (current.getIndex() != index - 1) {						
				current = current.getNext();
			}
			Node temp = current;
			current = current.getNext();
			if (current.getNext() == null){
				temp.setNext(null);
			}
			else{
				current = current.getNext();
				temp.setNext(current);
			}
		}
	}
	
	public void print(){
		Node current = head; 
		if (current != null) {
			while (current != null) {
				System.out.println(current.getData());
				current = current.getNext();
			}
		}
	}
}