package tree;
public class Node {
	Node left;
	Node right;
	int data;
	public Node(Node left, Node right, int data) {
		super();
		this.left = left;
		this.right = right;
		this.data = data;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
}
