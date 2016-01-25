package test;

import tree.Node;
import tree.Tree;

public class insertionTest {

	public static void main(String[] args) {
		Tree tree = new Tree();
		
		// parameter 1 : 삽입할 노드의 개수
		// parameter 2 : 삽입할 노드의 키값 (ASC:키값을 증가시키며 삽입, DSC:키값을 감소시키며 삽입, RANDOM:랜덤한 키값으로 삽입, EQUAL:키값이 전부 같게 삽입) 
//		tree.autoInsert(0, "ASC");		
//		tree.autoInsert(1, "ASC");		 
//		tree.autoInsert(3, "ASC");		 
		tree.autoInsert(5, "ASC");			
//		tree.autoInsert(16, "ASC"); 
//		tree.autoInsert(16, "DSC");	
//		tree.autoInsert(16, "RANDOM");	 
//		tree.autoInsert(16, "EQUAL");	 
//		tree.autoInsert(150, "ASC");	 
//		tree.autoInsert(150, "DSC");	 
//		tree.autoInsert(150, "RANDOM"); 
//		tree.autoInsert(150, "EQUAL");
		
		tree.insert(4);					// 개별 node 삽입. (key값을 인자로 받음)
		tree.insert(new Node(5));		// 개별 node 삽입. (node 객체를 인자로 받음)
	}
	
}
