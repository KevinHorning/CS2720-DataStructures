// Given a tree, this class can find all paths from root to leaves and sum the 
// numbers formed by concatenating the path elements

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreePaths {
	// List of all paths
	static ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
	// Stack of paths at split nodes 
	Stack<int[]> splits = new Stack<int[]>();
	
	// Tests methods on sample tree
	public static void main (String[] args){
		BinaryTreePaths b = new BinaryTreePaths();
		
		TreeNode root = new TreeNode(6);
		root.leftChild = new TreeNode(3);
		root.rightChild = new TreeNode(5);
		root.leftChild.leftChild = new TreeNode(2);
		root.leftChild.rightChild = new TreeNode(5);
		root.rightChild.rightChild = new TreeNode(4);
		root.leftChild.rightChild.leftChild = new TreeNode(7);
		root.leftChild.rightChild.rightChild = new TreeNode(4);
		ArrayList<Integer> p = new ArrayList<Integer>();
		
		b.findAllPaths(root, p);					
		b.printPaths();
		System.out.println();
		System.out.print("Total of combined numbers made up by path routes: " + b.findTotal());
	}
	
	// Makes and adds all paths to the paths list from the initial current node to leaf nodes 
	// Recursively finds all paths to leaves from current node by calling the method
	// from the child nodes and adding current node to the current node's path
	public void findAllPaths(TreeNode current, ArrayList<Integer> path){
		path.add(current.number); 
		if (current.leftChild != null && current.rightChild != null){
			int[] arr = new int[path.size()];
			for (int i = 0; i < path.size(); i++){
				arr[i] = path.get(i);
			}
			splits.push(arr);
			findAllPaths(current.leftChild, path);
			ArrayList<Integer> p = new ArrayList<Integer>();
			for (int i = 0; i < arr.length; i++){
				p.add(arr[i]);
			}
			findAllPaths(current.rightChild, p);
		}
		else if (current.leftChild != null){
			findAllPaths(current.leftChild, path);
		}
		else if (current.rightChild != null){
			findAllPaths(current.rightChild, path);
		}
		else{
			paths.add(path);
		}
	}
	
	// Displays all paths
	public void printPaths(){
		System.out.print("Possible Paths: ");
		for (int i = 0; i < paths.size(); i++){
			System.out.println();
			System.out.print(paths.get(i).get(0));
			for (int j = 1; j < paths.get(i).size(); j++){
				System.out.print(" --> " + paths.get(i).get(j));
			}
		}
	}
	
	// For each path, takes all node numbers, concatenates them into 1 number
	// then adds all concatenated path numbers together
	public int findTotal(){
		int total = 0;
		for (int i = 0; i < paths.size(); i ++){    
			int subTotal = 0;
			int degree = paths.get(i).size() - 1;
			for (int j = 0; j < paths.get(i).size(); j++){ 
				subTotal += (int)(paths.get(i).get(j) * Math.pow(10, degree));
				degree--;
			}
			total += subTotal;
		}
		return total;
	}
	
	public static class TreeNode{
		TreeNode leftChild;
		TreeNode rightChild;
		int number;
		
		public TreeNode(int number){
			this.number = number;
		}
	}
}