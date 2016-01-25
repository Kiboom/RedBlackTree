package test;

import tree.Node;
import tree.Tree;

public class deletionTest {
	static Node leafNode;

	public static void main(String[] args) {
		Tree tree = new Tree();
//		buildTree(tree, 0, "ASC");			// tree의 node가 0개일 때 삭제할 경우
//		buildTree(tree, 1, "ASC");			// tree의 node가 1개일 때 삭제할 경우
//		buildTree(tree, 5, "ASC");			// tree의 node가 5개일 때 삭제할 경우 
		buildTree(tree, 8, "ASC");			// tree의 node가 8개일 때 삭제할 경우
		
//		tree.delete(null);					// null을 삭제할 경우    (*트리에 존재하지 않는 노드를 삭제하는 경우는 예외처리하지 않았습니다)
//		tree.delete(Node.nil);				// nil을 삭제할 경우
//		tree.delete(tree.root);				// root 노드 삭제할 경우
//		tree.delete(tree.root.getLeft());	// root 노드의 왼쪽 자식을 삭제할 경우
//		tree.delete(leafNode);				// leaf 노드 삭제할 경우
		tree.delete(leafNode.getParent());	// leaf 노드의 parent를 삭제할 경우
		tree.print();
	}

	
	
	private static void buildTree(Tree tree, int nodeNum, String insertOrder) {
		Node newNode;
		System.out.println("[" + nodeNum + "개의 노드를 입력하는 경우]\n");
		
		for(int i=0 ; i<nodeNum ; i++){
			switch (insertOrder) {
			case "ASC" : newNode = new Node(i); 
						 break;
			case "DSC" : newNode = new Node(nodeNum-i); 
						 break;
			case "RANDOM" : newNode = new Node((int)(Math.random()*nodeNum)); 
						 	break;
			case "EQUAL" : newNode = new Node(1); 
							break;
			default : System.out.println("ASC, DSC, RANDOM, EQUAL 중 하나를 입력하세요.");
					  return;
			}
			tree.insert(newNode);
			leafNode = newNode;
		}
		tree.print();
		System.out.println("\n\n");
	}
	
}
