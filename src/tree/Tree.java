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

		appendChildNode(newNode, newNode.parent);
		insertFixup(newNode);
		nodeCount++;
		return true;
	}

	
	
	private void insertFixup(Node nodeToFixup) {
		while (nodeToFixup.parent.color == RED) {
			Node parent = nodeToFixup.parent;
			Node uncle = (parent.isLeftChild) ? parent.parent.right : parent.parent.left;
			
			if (uncle.color == RED) {
				parent.color = BLACK;
				uncle.color = BLACK;
				parent.parent.color = RED;
				nodeToFixup = parent.parent;
			}
			else {
				if (nodeToFixup.isLeftChild != parent.isLeftChild) {
					nodeToFixup = parent;
					if (parent.isLeftChild) 
						leftRotate(nodeToFixup);
					else 
						rightRotate(nodeToFixup);
				}
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
	
	

	private void appendChildNode(Node child, Node parent) {
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
	
	
	
	public void print(){
		List<Node> nodeList = new ArrayList<Node>();
		List<Node> queue = new ArrayList<Node>();
		nodeList.add(root);
		queue.add(root);
		while(queue.isEmpty() == false){
			Node curNode = queue.remove(0);
			nodeList.add(curNode.left);
			if(curNode.left!=nil){
				queue.add(curNode.left);
			}
			nodeList.add(curNode.right);
			if(curNode.right!=nil){
				queue.add(curNode.right);
			}
		}
		nodeList.add(0, nil);
		for(Node node : nodeList){
			System.out.println(node.value + " " + node.color);
		}
		int height = (int)Math.ceil((Math.log10(nodeList.size())/Math.log10(2)));
		int indent = height;
		
		for(int levelStart=1; levelStart < nodeList.size() ; levelStart*=2, indent--){

			for(int pos=levelStart ; pos<2*levelStart ; pos++){
				if(pos>=nodeList.size()){
					System.out.println("");
					return;
				}
				Node node = nodeList.get(pos);
				String value = (node == nil) ? "n" : Integer.toString(node.value);
				if(pos%2==0){
					System.out.print(value + "   ");
				}else{
					System.out.print(value + "   ");
				}
			}
			System.out.println("");
		}
		
	}
}
