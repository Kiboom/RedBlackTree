package test;

import tree.Node;
import tree.Tree;

public class deletionTest {
	static Node leafNode;

	public static void main(String[] args) {
		Tree tree = new Tree();
		
		/* tree 생성 */
//		tree.autoInsert(0, "ASC");			// tree의 node가 0개일 때 삭제할 경우
//		tree.autoInsert(1, "ASC");			// tree의 node가 1개일 때 삭제할 경우
		tree.autoInsert(5, "ASC");			// tree의 node가 5개일 때 삭제할 경우 
//		tree.autoInsert(8, "ASC");			// tree의 node가 8개일 때 삭제할 경우
		
		
		/* 특정 Node 객체 삭제하기 */
//		tree.delete(null);					// null을 삭제할 경우    (*트리에 존재하지 않는 노드를 삭제하는 경우는 예외처리하지 않았습니다)
//		tree.delete(Node.nil);				// nil을 삭제할 경우
//		tree.delete(tree.root);				// root 노드 삭제할 경우
//		tree.delete(tree.root.getLeft());	// root 노드의 왼쪽 자식을 삭제할 경우
		tree.delete(tree.getLeafNode());				// leaf 노드 삭제할 경우
		tree.delete(tree.getLeafNode().getParent());	// leaf 노드의 parent를 삭제할 경우
		
		
		/* 특정 key값의 노드 삭제하기 */
//		tree.delete(0);						// 값이 0인 노드를 삭제할 때
//		tree.delete(100);					// 트리에 존재하지 않는 노드 삭제할 때
		
		
		/* 빈 tree에서 삭제 연산할 때 */
//		tree = new Tree();
//		tree.delete(0);
		
		
		/* 원소가 1개 있을 때 삭제하기 */
//		tree.autoInsert(1, "ASC");
//		tree.delete(0);
		
		
		/* 삽입과 삭제 반복 */
//		tree.autoInsert(10, "RANDOM");
//		for(int i=0 ; i<10 ; i++){
//			tree.delete((int)(Math.random()*10));
//			tree.insert((int)(Math.random()*10));
//		}
	}
}
