package test;

import tree.Node;
import tree.Tree;

public class insertionTest {

	public static void main(String[] args) {
		Tree tree = new Tree();
		
		for(int i=0 ; i<15 ; i++){
			Node newNode = new Node(i);
			tree.insert(newNode);
		}
		
		tree.print();
	}

}
