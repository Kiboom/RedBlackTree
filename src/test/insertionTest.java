package test;

import tree.Tree;

public class insertionTest {

	public static void main(String[] args) {
		Tree tree = new Tree();
		
		// parameter 1 : 삽입할 노드의 개수
		// parameter 2 : 삽입할 노드의 키값 (ASC:키값을 증가시키며 삽입, DSC:키값을 감소시키며 삽입, RANDOM:랜덤한 키값으로 삽입, EQUAL:키값이 전부 같게 삽입) 
		tree.buildTree(0, "ASC");		
		tree.buildTree(1, "ASC");		 
		tree.buildTree(3, "ASC");		 
		tree.buildTree(5, "ASC");			
		tree.buildTree(16, "ASC"); 
		tree.buildTree(16, "DSC");	
		tree.buildTree(16, "RANDOM");	 
		tree.buildTree(16, "EQUAL");	 
		tree.buildTree(150, "ASC");	 
		tree.buildTree(150, "DSC");	 
		tree.buildTree(150, "RANDOM"); 
		tree.buildTree(150, "EQUAL");	 
	}
	
}
