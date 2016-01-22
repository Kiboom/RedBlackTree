package tree;

public class Node {

	int color;
	int value;
	boolean isLeftChild;	// 부모의 왼쪽 자식인지 여부. 단, 부모가 없는 root는 오른쪽 자식이라고 설정함.

	Node parent = nil;
	Node left = nil;
	Node right = nil;

	public static final int RED = -1, BLACK = 1;
	public static final Node nil = new Node(Node.BLACK, Integer.MIN_VALUE, null, null, null); // nil 노드

	
	
	/* 생성자 */
	public Node(int value) {
		this.value = value;
	}
	public Node(int color, int value) {
		this.color = color;
		this.value = value;
	}
	public Node(int color, int value, Node parent, boolean isLeftChild) {
		this.color = color;
		this.value = value;
		this.parent = parent;
		this.isLeftChild = isLeftChild;
	}
	public Node(int color, int value, Node parent, Node left, Node right) {
		this.color = color;
		this.value = value;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	
	
	/* Getter */
	public boolean isLeftChild() {
		return isLeftChild;
	}
	public int getColor() {
		return color;
	}
	public int getValue() {
		return value;
	}
	public Node getParent() {
		return parent;
	}
	public Node getLeft() {
		return left;
	}
	public Node getRight() {
		return right;
	}

	
	
	/* Setter */
	public void setIsLeftChild(boolean isLeftChild){
		this.isLeftChild = isLeftChild;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public void setParentWithPos(Node parent, boolean isLeftChild) {
		this.parent = parent;
		this.isLeftChild = isLeftChild;
	}
	
	public void appendChildWithPos(Node child, boolean isLeftChild) {
		if (isLeftChild) {
			this.left = child;
			child.isLeftChild = true;
		}
		else {
			this.right = child;
			child.isLeftChild = false;
		}
	}
	
	
	
	/* equals & hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + color;
		result = prime * result + (isLeftChild ? 1231 : 1237);
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + value;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (color != other.color)
			return false;
		if (isLeftChild != other.isLeftChild)
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

}
