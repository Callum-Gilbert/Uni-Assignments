
//Name:Callum Gilbert
//ID:1268971
public class BSTlex {
	Node root;

	public void addNode(String key) {
		Node newNode = new Node(key);
		// if the root is empty
		if (root == null) {
			System.out.println(key + " ADDED");
			root = newNode;
		} else {
			// setting nodes
			Node currentNode = root;
			Node parent;
			// loop
			while (true) {
				parent = currentNode;
				// display path
				System.out.print(parent.key + " ");
				// check if its less than 0
				if (key.compareTo(currentNode.key) < 0) {
					currentNode = currentNode.leftChild;
					if (currentNode == null) {
						// set node and output last key and then ADDED Label
						parent.leftChild = newNode;
						System.out.println(newNode.key + " ADDED");
						return;
					}
					// check if its greater than 0
				} else if (key.compareTo(currentNode.key) > 0) {
					currentNode = currentNode.rightChild;
					if (currentNode == null) {
						// set node and output last key and then ADDED Label
						parent.rightChild = newNode;
						System.out.println(newNode.key + " ADDED");
						return;

					}
				} else {
					// delete and display removed label at the end of the path
					delete(key);
					System.out.println("REMOVED");
					return;
				}
			}
		}
	}

	public void delete(String key) {
		// initilise bool to test condition isLeft
		boolean isLeft = false;

		// setting the nodes
		Node parentNode = root;
		Node currentNode = root;

		// while loop
		while (!currentNode.key.equals(key)) {
			// set node
			parentNode = currentNode;
			// if statment then assign to false and right if true
			if (currentNode.key.compareTo(key) < 0) {
				isLeft = false;
				currentNode = currentNode.rightChild;
			} else {// will default if the if statement fails
				isLeft = true;
				currentNode = currentNode.leftChild;
			}
			// if the node is empty then return false
			if (!(currentNode != null)) {
				return;
			}
		}

		// if current nodes left and right children are null
		if (currentNode.leftChild == null && currentNode.rightChild == null) {
			// if statements and setting the root or either child to null based
			// off the if statements
			if (currentNode == root) {
				root = null;
			}
			if (isLeft == true) {
				parentNode.leftChild = null;
			} else {
				parentNode.rightChild = null;
			}
		}
		// test to see if node has only 1 child
		else if (currentNode.rightChild == null) {
			if (currentNode.equals(root)) {
				root = currentNode.leftChild;
				// tests to check which and then assign value from current nodes
				// leftchild
			} else if (isLeft) {
				parentNode.leftChild = currentNode.leftChild;
			} else {
				parentNode.rightChild = currentNode.leftChild;
			}
			// check which node
		} else if (currentNode.leftChild == null) {
			if (currentNode.equals(root)) {
				root = currentNode.rightChild;
				// tests to check which and then assign value from current nodes
				// rightchild
			} else if (isLeft) {
				parentNode.leftChild = currentNode.rightChild;
			} else {
				parentNode.rightChild = currentNode.rightChild;
			}
		} else if (currentNode.leftChild != null && currentNode.rightChild != null) {

			//assign nodes 
			Node replacement = getReplacement(currentNode);
			//determine which node gets replacement
			if (currentNode == root) {
				root = replacement;
			} else if (isLeft) {
				parentNode.leftChild = replacement;
			} else {
				parentNode.rightChild = replacement;
			}
			replacement.leftChild = currentNode.leftChild;
		}

		return;
	}

	public Node getReplacement(Node oldNode) {
		//set nodes 
		Node replacement = null;
		Node replacementParent = null;
		Node currentNode = oldNode.rightChild;
		//loop while current node isnt null
		while (currentNode != null) {
			//assigning replacementParent to replacement, replacement to currentNode and current node to itsleftchild
			replacementParent = replacement;
			replacement = currentNode;
			currentNode = currentNode.leftChild;
		}
		//test replacement doesnt equal oldNodes right child
		if (!replacement.equals(oldNode.rightChild)) {
			//shift nodes 
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = oldNode.rightChild;
		}
		
		return replacement;
	}

	public void display(Node root) {
		//if the root isnt null
		if (root != null) {
			//display the BST
			display(root.leftChild);
			System.out.println(root.key);
			display(root.rightChild);
		}

	}

	class Node {
		//initilise 
		String key;
		Node leftChild;
		Node rightChild;
		//constructor
		Node(String key) {
			this.key = key;
		}

	}
}