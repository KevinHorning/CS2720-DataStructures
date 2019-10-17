// Creates a Linked List structure with methods for finding or deleting certain values

import java.util.ArrayList;
import java.util.LinkedList;

public class MyLinkedList{
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

class Node {								//Creates a Node for a linked list, with getter and setter methods
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
		ArrayList<Integer> targetNodeIndices = new ArrayList<Integer>();	//Creates an arraylist with the indices of nodes with the target value
		while (current != null){											//While loop that adds nodes with target value to the arraylist
			if (current.getData() == value)
				targetNodeIndices.add(current.getIndex());
			current = current.getNext();
		}
		for (int i = 0; i < targetNodeIndices.size(); i++){					//For loop that deletes each node with target value
			current = head;
			if (targetNodeIndices.get(i) == 0)								//Specific for deleting first node
				head = current.getNext();
			else{
				while (current.getIndex() != targetNodeIndices.get(i) - 1){ //Makes current the node before the node next to be deleted
					current = current.getNext();
				}
				Node temp = current;
				current = current.getNext();								//Current is now node to be deleted
				if (current.getNext() == null){								//Specific for deleting last node
					temp.setNext(null);
				}
				else{														//Makes preceding node link to seceding node
					current = current.getNext();
					temp.setNext(current);
				}
			}
		}
	}
	
	public void deleteByIndex(int index){
		Node current = head;
		if (index == 0){													//Specific for deleting first node
			head = current.getNext();
		}
		else{
			while (current.getIndex() != index - 1) {						//Same deleting code except with specific index
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