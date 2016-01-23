package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	Node root = nil;
	int nodeCount;
	
	static final int RED = -1, BLACK = 1;
	static final Node nil = Node.nil;
	static final boolean LEFT = true, RIGHT = false;

	
	
	/* INSERT 관련 메서드 */
	
	public boolean insert(Node newNode) {
		if (newNode == null) {
			return false;
		}
		newNode.parent = searchInsertPosition(newNode);
		newNode.left = nil;
		newNode.right = nil;
		newNode.color = RED;

		appendChild(newNode, newNode.parent);
		insertFixup(newNode);
		nodeCount++;
		return true;
	}

	
	
	private void insertFixup(Node fixupNode) {
		while (fixupNode.parent.color == RED) {
			Node parent = fixupNode.parent;
			Node uncle = (parent.isLeftChild) ? parent.parent.right : parent.parent.left;
			
			if (uncle.color == RED) {
				parent.color = BLACK;
				uncle.color = BLACK;
				parent.parent.color = RED;
				fixupNode = parent.parent;
			}
			else {
				if (fixupNode.isLeftChild != parent.isLeftChild) {
					fixupNode = parent;
					if (parent.isLeftChild) 
						leftRotate(fixupNode);
					else 
						rightRotate(fixupNode);
				}
				parent = fixupNode.parent;
				parent.color = BLACK;
				parent.parent.color = RED;
				if (parent.isLeftChild)
					rightRotate(parent.parent);
				else 
					leftRotate(parent.parent);
			}
		}
		root.color = BLACK;
	}

	
	
	private void leftRotate (Node target) {
		Node targetsRight = target.right;
		target.right = targetsRight.left;
		
		if (targetsRight.left != nil) {
			targetsRight.left.setParentWithPos(target, RIGHT);
		}
		
		targetsRight.parent = target.parent;
		if (target.parent == nil) {
			root = targetsRight;
		} else if (target.isLeftChild){
			target.parent.appendChildWithPos(targetsRight, LEFT);
		} else {
			target.parent.appendChildWithPos(targetsRight, RIGHT);
		}
		
		target.parent = targetsRight;
		targetsRight.appendChildWithPos(target, LEFT);
	}

	
	
	private void rightRotate (Node target) {
		Node targetsLeft = target.left;
		target.left = targetsLeft.right;
		
		if (targetsLeft.right != nil) {
			targetsLeft.right.setParentWithPos(target, LEFT);
		}
		
		targetsLeft.parent = target.parent;
		if (target.parent == nil) {
			root = targetsLeft;
		} else if (target.isLeftChild){
			target.parent.appendChildWithPos(targetsLeft, LEFT);
		} else {
			target.parent.appendChildWithPos(targetsLeft, RIGHT);
		}
		
		target.parent = targetsLeft;
		targetsLeft.appendChildWithPos(target, RIGHT);
	}
	
	

	private void appendChild(Node child, Node parent) {
		if (parent == nil) {
			root = child;
			return;
		} 
		
		if (child.value < parent.value) {
			parent.appendChildWithPos(child, LEFT);
		} 
		else {
			parent.appendChildWithPos(child, RIGHT);
		}
	}

	
	
	private Node searchInsertPosition(Node newNode){
		Node insertPos = nil;
		Node curNode = root;
		
		while (curNode != nil) {
			insertPos = curNode;
			if (newNode.value < insertPos.value) {
				curNode = insertPos.left;
			} else {
				curNode = insertPos.right;
			}
		}
		return insertPos;
	}
	
	
	
	
	/* DELETE 관련 메서드 */
	public boolean delete (Node node) {
		if (node == null){
			return false;
		}
		
		int erasedColor = node.color;
		Node fixupNode = null;
		Node fixupParent = node.parent;				// nodeToFixup의 parent. nodeToFixup이 nil이어서 그 부모를 알 수 없을 때를 대비 
		
		// delete node의 자식이 왼쪽에만 있거나 둘다 없을 때	--> 자식이 둘다 없어서 nodeToFixup이 nil일 때, deleteFixup할 때 문제 안생기나?
		if (node.left == nil) {
			fixupNode = node.right;
			transplant(node, node.right);		
		}
		// delete node의 자식이 오른쪽에만 있는 경우
		else if (node.right == nil) {
			fixupNode = node.left;
			transplant(node, node.left);
		}
		// delete node의 자식이 둘다 있는 경우
		else {
			Node successor = getMinimumNode(node.right);
			erasedColor = successor.color;	
			fixupNode = successor.right;	// 첫번째 if문과 동일한 패턴. 왜냐면 이 경우는 왼쪽 자식이 nil이기 때문에.
			fixupParent = (successor.parent==node) ? successor : successor.parent;
			
			// delete node의 아랫 노드들을 successor에 인수인계  --> 어차피 nodeToFixup의 parent는 successor니까 이 if문은 없애고 else문만 살려도 되지 않을까?
			/*if (successor.parent == node) {			// --> nodeToFixup이 nil인 경우에는 안되겠다..
				nodeToFixup.parent = successor;
			}*/
			if (successor.parent != node) {
				transplant(successor, successor.right);
				successor.right = node.right;
				successor.right.parent = successor;
			}
			
			// delete node의 윗 노드들에게 successor를 인수인계
			transplant(node, successor);
			successor.left = node.left;
			successor.left.parent = successor;
			successor.color = node.color;
		}
		
		if (erasedColor == BLACK) {
			deleteFixup(fixupNode, fixupParent);
		}
		
		return true;
	}
	
	
	
	
	private void deleteFixup(Node fixupNode, Node fixupParent) {
		
		Node sibling;
		
		while ((fixupNode!=root) && (fixupNode.color==BLACK)){
			if (fixupNode == fixupParent.left) {
				sibling = fixupParent.right;
				if (sibling.color == RED) {
					sibling.color = BLACK;
					fixupParent.color = RED;
					leftRotate(fixupParent);
					fixupParent = fixupParent.parent;
					sibling = fixupParent.right;
				}
				if ((sibling.left.color==BLACK) && (sibling.right.color==BLACK)) {
					sibling.color = RED;
					fixupNode = fixupParent;
				}
				else {
					if (sibling.right.color == BLACK) {
						sibling.left.color = BLACK;
						sibling.color = RED;
						rightRotate(sibling);
						sibling = fixupParent.right;
					}
					sibling.color = fixupParent.color;
					fixupParent.color = BLACK;
					sibling.right.color = BLACK;
					leftRotate(fixupParent);
					fixupNode = root;
				}
			}
			else {
				sibling = fixupParent.left;
				if (sibling.color == RED) {
					sibling.color = BLACK;
					fixupParent.color = RED;
					rightRotate(fixupParent);
					fixupParent = fixupParent.parent;
					sibling = fixupParent.left;
				}
				if ((sibling.left.color==BLACK) && (sibling.right.color==BLACK)) {
					sibling.color = RED;
					fixupNode = fixupParent;
				}
				else {
					if (sibling.left.color == BLACK) {
						sibling.right.color = BLACK;
						sibling.color = RED;
						leftRotate(sibling);
						sibling = fixupParent.left;
					}
					sibling.color = fixupParent.color;
					fixupParent.color = BLACK;
					sibling.left.color = BLACK;
					rightRotate(fixupParent);
					fixupNode = root;
				}
			}
			fixupNode.color = BLACK;
		}
		
	}



	private Node getMinimumNode(Node root) {
		Node minimumNode = root;
		
		while (minimumNode.left != nil) {
			minimumNode = minimumNode.left;
		}
		
		return minimumNode;
	}



	private void transplant (Node oldNode, Node newNode){
		if(oldNode.parent == nil){
			root = newNode;
		}
		else if(oldNode.isLeftChild){
			oldNode.parent.left = newNode;
		}
		else{
			oldNode.parent.right = newNode;
		}
		
		if(newNode != nil){
			newNode.parent = oldNode.parent;
		}
	}
	
	
	

	/* PRINT 관련 메서드 */
	
	public void print(){
		List<Node> tree = treeToArray();
		
		for(int levelStart=1; levelStart < tree.size() ; levelStart*=2){
			for(int pos=levelStart ; pos<2*levelStart ; pos++){
				if(pos>=tree.size())
					break;
				Node node = tree.get(pos);
				String color = (node.color == RED) ? "R" : "B";
				String value = (node == nil) ? "n" : Integer.toString(node.value);
				if(pos%2==0)
					System.out.print(value + "(" + color + ")   ");
				else
					System.out.print(value + "(" + color + ")      ");
			}
			System.out.println("");
		}
		
	}
	
	
	
	public List<Node> treeToArray(){
		List<Node> queue = new ArrayList<Node>();
		List<Node> nodeList = new ArrayList<Node>();
		queue.add(root);
		nodeList.add(root);
		
		// --> 10, ASC insert하면 node 9의 위치가 잘못됨. 왜냐면 nil을 더이상 큐에 안넣는 순간 그다음 레벨에서 꼬여버림.  
		while((queue.isEmpty() == false) && (root!=nil)){
			Node curNode = queue.remove(0);
			nodeList.add(curNode.left);
			if(curNode.left!=nil)
				queue.add(curNode.left);
			nodeList.add(curNode.right);
			if(curNode.right!=nil)
				queue.add(curNode.right);
		}
		nodeList.add(0, nil);
		
		return nodeList;
	}
}
