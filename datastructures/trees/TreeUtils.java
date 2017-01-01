package tree;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class TreeUtils 
{
	public static class Traversals
	{
		public static void preOrderIterative(Node root)
		{
			Stack<Node> stack = new Stack<>();
			if(root!=null)
				stack.push(root);
			while(!stack.isEmpty())
			{
				root = stack.pop();
				System.out.println(root.data);
				if(root.right!=null)
					stack.push(root.right);
				if(root.left!=null)
					stack.push(root.left);
			}
		}
		public static void preOrderIterative2(Node root)
		{
			Stack<Node> stack = new Stack<>();
			while(true)
			{
				if(root!=null)
				{
					System.out.println(root.data);
					stack.push(root);
					root = root.left;
				}
				else
				{
					if(stack.isEmpty())
						break;
					root = stack.pop();				
					root = root.right;
				}
			}
		}
		
		public static void inOrderIterative(Node root)
		{
			Stack<Node> stack = new Stack<>();
			while(true)
			{
				if(root!=null)
				{
					stack.push(root);
					root = root.left;
				}
				else
				{
					if(stack.isEmpty())
						break;
					root = stack.pop();
					System.out.println(root.data);
					root = root.right;
				}
			}
		}
		
		public static void postOrderIterative(Node root)
		{	
			Stack<Node> tempStack = new Stack<>();
			Stack<Node> finalStack = new Stack<>();
			tempStack.push(root);
			
			while(!tempStack.isEmpty())
			{
				root = tempStack.pop();			
				finalStack.push(root);
				if(root.left!=null)
					tempStack.push(root.left);
				if(root.right!=null)
					tempStack.push(root.right);
			}
			while(!finalStack.isEmpty())
				System.out.println(finalStack.pop());
		}
		
		public static void inOrderTraversal(Node node)
		{
			if(node==null)
				return;
			inOrderTraversal(node.left);
			System.out.println(node.data);
			inOrderTraversal(node.right);
		}
		
		public static void preOrderTraversal(Node node)
		{
			if(node==null)
				return;
			System.out.println(node.data);
			preOrderTraversal(node.left);		
			preOrderTraversal(node.right);
		}
		
		public static void postOrderTraversal(Node node)
		{
			if(node==null)
				return;
			postOrderTraversal(node.left);		
			postOrderTraversal(node.right);
			System.out.println(node.data);		
		}
		
		public static void levelOrderTraversal(Node root)
		{
			System.out.println("===LEVEL ORDER TRAVERSAL===");
			if(root==null)
				return;
			Queue<Node> queue = new ArrayDeque<>();
			queue.offer(root);
			while(!queue.isEmpty())
			{
				Node curr = queue.poll();
				System.out.println(curr.data);
				if(curr.left!=null)
					queue.offer(curr.left);
				if(curr.right!=null)
					queue.offer(curr.right);
			}
		}
	}
	
	public static int findMax(Node root)
	{
		int max = Integer.MIN_VALUE;
		if(root!=null)
		{
			int curr = root.data;
			int left = findMax(root.left);
			int right = findMax(root.right);
			
			if(left>right)
				max=left;
			else
				max=right;
			if(curr>max)
				max=curr;
		}
		return max;
	}
	
	public static boolean exists(int data, Node root)
	{
		if(root==null)
			return false;
		if(root.data==data)
			return true;
		boolean exists = exists(data, root.left);
		if(exists)
			return true;
		else
			return exists(data,root.right);
	}
	
	public static int size(Node root)
	{
		if(root==null)
			return 0;
		return 1+size(root.left) + size(root.right);
	}
	
	public static void deleteTree(Node root)
	{
		if(root==null)
			return;
		deleteTree(root.left);
		deleteTree(root.right);
		
		//eliminate the references
		root.left = null;
		root.right = null;
		root = null;
	}
	
	public static int heightRecursive(Node root)
	{
		if(root==null)
			return 0;
		int leftHeight = heightRecursive(root.left);
		int rightHeight = heightRecursive(root.right);
		return 1+Math.max(leftHeight, rightHeight);
	}
	
	public static int heightInterative(Node root)
	{
		Node marker = new Node(null, null, Integer.MIN_VALUE);
		if(root==null)
			return 0;
		int height = 0;
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		queue.offer(marker);
		while(!queue.isEmpty())
		{
			Node curr = queue.poll();
			if(curr==marker)
			{
				//we hit the endof current level , increment the level and put a marker for next level end.
				++height;
				if(!queue.isEmpty()) //if queue is empty then entire tree is traversed,then dont insert marker.
					queue.offer(marker);
			}			
			else
			{
				if(curr.left!=null)
					queue.offer(curr.left);
				if(curr.right!=null)
					queue.offer(curr.right);
			}
			
		}
		return height;
	}
	
	public static int countLeafNodes(Node root)
	{
		if(root==null)
			return 0;
		int count = 0;
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		while(!queue.isEmpty())
		{
			Node curr = queue.poll();
			if(curr.left==null && curr.right==null)
				++count;
			if(curr.left!=null)
				queue.offer(curr.left);
			if(curr.right!=null)
				queue.offer(curr.right);
		}
		
		return count;
	}
	
	public static boolean areTreesIdentical(Node root1, Node root2)
	{
		if(root1 == null && root2 == null)
			return true;
		if(root1 == null || root2 == null)
			return false;
		return ((root1.data==root2.data) 
				&& areTreesIdentical(root1.left, root2.left)
				&& areTreesIdentical(root1.right, root2.right)
				);
	}
	
	public static boolean areTreesMirrors(Node root1, Node root2)
	{
		if(root1 == null && root2 == null)
			return true;
		if(root1 == null || root2 == null)
			return false;
		return ((root1.data==root2.data) 
				&& areTreesMirrors(root1.left, root2.right)
				&& areTreesMirrors(root1.right, root2.left)
				);
	}
	
	public static void depthOfEachNode(Node root)
	{
		if(root==null)
			return;
		int leftHeight = heightRecursive(root.left);
		int rightHeight = heightRecursive(root.right);
		int nodesInLongestPath = leftHeight+rightHeight+1;
		System.out.println(root+" , leftHeight="+leftHeight+" : rightHeight="+rightHeight+" : nodesInLongestPath="+nodesInLongestPath);
		depthOfEachNode(root.left);
		depthOfEachNode(root.right);
	}
	
	public static int diameter(Node root)
	{
		if(root==null)
			return 0;
		int leftHeight = heightRecursive(root.left);
		int rightHeight = heightRecursive(root.right);
		int nodesInLongestPath = leftHeight+rightHeight+1; //total nodes in the longest path in which current node lies
		
		int leftDiameter = diameter(root.left);
		int rightDiameter = diameter(root.right);
		int maxDiameter = Math.max(leftDiameter, rightDiameter); //find which tree is giving max diameter
		
		return Math.max(nodesInLongestPath, maxDiameter);	
	}
	
	public static int findLevelWithMaxSum(Node root)
	{		
		if(root==null)
			return 0;
		Node marker = new Node(null, null, Integer.MIN_VALUE);
		int maxSum = 0, maxLevel = 0;
		int level = 0;
		int currSum = 0;
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		queue.offer(marker);
		while(!queue.isEmpty())
		{
			Node curr = queue.poll();
			if(curr==marker)
			{				
				if(currSum>maxSum)
				{
					maxSum = currSum;
					maxLevel = level;
				}				
				if(!queue.isEmpty())
					queue.offer(marker); //adding marker for next level					
				++level;				
				currSum = 0;//reset the currSum for the next level sum
			}
			else
			{
				currSum+=curr.data;
				if(curr.left!=null)
					queue.offer(curr.left);
				if(curr.right!=null)
					queue.offer(curr.right);
			}
		}
		return maxLevel;
	}
	
	public static void printAllRootToLeafPaths(Node root, Stack<Integer> path)
	{
		if(root==null)
			return;
		path.add(root.data);
		if(root.left==null && root.right==null)
		{
			System.out.println("Found a path = "+path);			
		}
		
		printAllRootToLeafPaths(root.left, path);
		printAllRootToLeafPaths(root.right, path);
		path.pop();
	}
	
	public static void printPathMatchingSum(Node root, Stack<Integer> path,int sum)
	{
		if(root==null)
			return;
		path.add(root.data);
		if(root.left==null && root.right==null)
		{
			int pathSum = 0;
			for(int i: path)
				pathSum+=i;
			if(pathSum==sum)
			{
				System.out.println("Found a path matching the sum= "+path+", sum = "+sum);
			}
						
		}
		
		printPathMatchingSum(root.left, path, sum);
		printPathMatchingSum(root.right, path, sum);
		path.pop();
	}
	
	public static int sum(Node root)
	{
		if(root==null)
			return 0;
		return (root.data + sum(root.left) + sum(root.right));
	}

	public static void image(Node root)
	{
		if(root==null)
			return;
		
		image(root.left);
		image(root.right);
		
		Node temp = root.right;
		root.right = root.left;
		root.left = temp;
		
	}
	
	public static Node lca(Node root, int left, int right)
	{
		if(root==null) return null; // Base condition
		
		//if either of the two is the parent of the other, or we have traversed down to one of the two nodes
		if(root.data==left || root.data==right) 
			return root;
		
		//find left/right subtree lca
		Node leftLca = lca(root.left, left, right);
		Node rightLca = lca(root.right, left, right);
		
		// If both of the above calls return Non-NULL, then one key
	    // is present in once subtree and other is present in other,
	    // So this node is the LCA
		if(leftLca!=null && rightLca!=null)
			return root;
		
		// Otherwise check if left subtree or right subtree is LCA
		return (leftLca!=null?leftLca:rightLca);
	}
}
