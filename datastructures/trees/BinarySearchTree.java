package tree;

public class BinarySearchTree {
	Node root;

	
	public void reverse(Node node)
	{
		if(node==null)
			return;
		
		if(node.left==null && node.right==null)
			return;
		
		Node temp = node.left;
		node.left = node.right;
		node.right = temp;
		
		reverse(node.left);
		reverse(node.right);
	}
	
	public void insert(Node node,int data)
	{
		
		Node newNode = new Node(null, null, data);
 		if(root==null)
		{
 			root = newNode;
 			System.out.println("root node created");
 			return;
		}	
		
		if(node.data==data)
		{
			System.out.println(data+" already present");
			return;
		}
			
		if(data<node.data)
		{
			if(node.left==null)
				node.left=newNode;
			else
				insert(node.left,data);
		}
			
		
		if(data>node.data){
			if(node.right==null)
				node.right=newNode;
			else
				insert(node.right,data);
		}
			
	}
	
	public Node min(Node rootNode)
	{
		Node node = rootNode;
		while(node!=null && node.left!=null)
			node=node.left;
		return node;
			
	}
	
	public Node max(Node rootNode)
	{
		Node node = rootNode;
		while(node!=null && node.right!=null)
			node=node.right;
		return node;
			
	}
	
	
	
	public Node search(Node node, int data)
	{
		if(node==null)
			return null;
		
		if(node.data==data)
			return node;
		else if(data<node.data)
			return search(node.left, data);
		else
			return search(node.right, data);
			
	}
	
	public void delete(Node node, int data)
	{
		if(node==null)
			return;
		delete(search(node, data), data);
		if(node.left==null && node.right==null)
			node=null;
		
		if(node.left!=null && node.right!=null)
		{			
			Node minRightNode = this.min(node.right);
			node.data = minRightNode.data;
			minRightNode=null;
		}
		else if(node.right==null)
		{
			Node temp = node.left;
			node.data = temp.data;
			node.left = temp.left;
			node.right = temp.right;
		}
		else{
			Node temp = node.right;
			node.data = temp.data;
			node.left = temp.left;
			node.right = temp.right;
		}
	}
	
}
