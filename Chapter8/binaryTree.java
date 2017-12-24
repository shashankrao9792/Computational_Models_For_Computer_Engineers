import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class binaryTree {

	class Node {
		public int key;
		public double data;
		public char cdata;
		public Node leftChild;
		public Node rightChild;

		public void displayNode() {
			System.out.print("{ ");
			System.out.print(key);
			System.out.print(", ");
			System.out.print(data);
			System.out.print(" }");
		}
	}

	class tree {
		private Node root;

		public tree() {
			root = null;
		}

		public Node find(int key) {
			Node current = root;
			while (current.key != key) {
				if (key < current.key) {
					current = current.leftChild;
				} else {
					current = current.rightChild;
				}
				if (current == null) {
					return null;
				}
			}
			return current;
		}

		public void insert(int key, double data) {
			Node newNode = new Node();
			newNode.key = key;
			newNode.data = data;
			if (root == null) {
				root = newNode;
			} else {
				Node current = root;
				Node parent;
				while (true) {
					parent = current;
					if (key < current.key) {
						current = current.leftChild;
						if (current == null) {
							parent.leftChild = newNode;
							return;
						}
					} else {
						current = current.rightChild;
						if (current == null) {
							parent.rightChild = newNode;
							return;
						}
					}
				}
			}
		}

		public boolean delete(int key) {
			Node current = root;
			Node parent = root;
			boolean isLeftChild = true;

			while (current.key != key) {
				parent = current;
				if (key < current.key) {
					isLeftChild = true;
					current = current.leftChild;
				} else {
					isLeftChild = false;
					current = current.rightChild;
				}
				if (current == null) {
					return false;
				}
			}

			// deletion case: leaf node to be deleted
			if (current.leftChild == null && current.rightChild == null) {
				if (current == root) {
					root = null;
				} else if (isLeftChild) {
					parent.leftChild = null;
				} else {
					parent.rightChild = null;
				}
			}

			// deletion case: parent node with no right child to be deleted
			else if (current.rightChild == null) {
				if (current == root) {
					root = current.leftChild;
				} else if (isLeftChild) {
					parent.leftChild = current.leftChild;
				} else {
					parent.rightChild = current.leftChild;
				}
			}

			// deletion case: parent node with no left child to be deleted
			else if (current.leftChild == null) {
				if (current == root) {
					root = current.rightChild;
				} else if (isLeftChild) {
					parent.leftChild = current.rightChild;
				} else {
					parent.rightChild = current.rightChild;
				}
			}

			// deletion case: parent node with both left and right child nodes to be deleted
			else {
				Node successor = getSuccessor(current);

				if (current == root) {
					root = successor;
				} else if (isLeftChild) {
					parent.leftChild = successor;
				} else {
					parent.rightChild = successor;
				}

				successor.leftChild = current.leftChild;
			}
			return true;
		}

		private Node getSuccessor(Node delNode) {
			Node successorParent = delNode;
			Node successor = delNode;
			Node current = delNode.rightChild;
			while (current != null) {
				successorParent = successor;
				successor = current;
				current = current.leftChild;
			}
			if (successor != delNode.rightChild) {
				successorParent.leftChild = successor.rightChild;
				successor.rightChild = delNode.rightChild;
			}
			return successor;
		}

		public void traverse(int type) {
			switch (type) {
			case 1:
				System.out.print("\nPreOrder Traversal: ");
				preOrder(root);
				break;
			case 2:
				System.out.print("\nInOrder Traversal: ");
				inOrder(root);
				break;
			case 3:
				System.out.print("\nPostOrder Traversal: ");
				postOrder(root);
				break;
			}
			System.out.println();
		}

		private void preOrder(Node localRoot) {
			if (localRoot != null) {
				System.out.print(localRoot.key + " ");
				preOrder(localRoot.leftChild);
				preOrder(localRoot.rightChild);
			}
		}

		private void inOrder(Node localRoot) {
			if (localRoot != null) {
				inOrder(localRoot.leftChild);
				System.out.print(localRoot.key + " ");
				inOrder(localRoot.rightChild);
			}
		}

		private void postOrder(Node localRoot) {
			if (localRoot != null) {
				postOrder(localRoot.leftChild);
				postOrder(localRoot.rightChild);
				System.out.print(localRoot.key + " ");
			}
		}

		public void displayTree(double d) {
			Stack gStack = new Stack();
			gStack.push(root);
			double nBlanks = d;
			boolean isRowEmpty = false;
			for (int i = 0; i < d * 2; i++) {
				System.out.print('*');
			}
			System.out.println("");

			while (isRowEmpty == false) {
				Stack lStack = new Stack();
				isRowEmpty = true;
				for (int j = 0; j < nBlanks; j++) {
					System.out.print(' ');
				}
				while (gStack.isEmpty() == false) {
					Node temp = (Node) gStack.pop();
					if (temp != null) {
						System.out.print(temp.key);
						lStack.push(temp.leftChild);
						lStack.push(temp.rightChild);
						if (temp.leftChild != null || temp.rightChild != null) {
							isRowEmpty = false;
						}
					} else {
						System.out.print("--");
						lStack.push(null);
						lStack.push(null);
					}
					for (int j = 0; j < nBlanks * 2 - 2; j++) {
						System.out.print(' ');
					}
				}
				System.out.println();
				nBlanks /= 2;
				while (lStack.isEmpty() == false) {
					gStack.push(lStack.pop());
				}
			}

			for (int i = 0; i < d * 2; i++) {
				System.out.print('*');
			}
			System.out.println("");
		}

		/*
		 * This method is used to find the depth of the tree from its root; It is used
		 * in this class to during display of graph
		 */
		public int findLevelsInTree() {
			return levelsInTree(root, 1);
		}

		private int levelsInTree(Node localRoot, int level) {
			int leftLevel = level;
			int rightLevel = level;

			if (localRoot.leftChild != null) {
				leftLevel = levelsInTree(localRoot.leftChild, level + 1);
			}
			if (localRoot.rightChild != null) {
				rightLevel = levelsInTree(localRoot.rightChild, level + 1);
			}

			return ((leftLevel > rightLevel) ? leftLevel : rightLevel);
		}

		/*
		 * This method creates the unbalanced tree as per requirements of Programming
		 * assignment 8.1
		 */
		public void stringTree(String s) {
			char[] ch = s.toCharArray();
			Node[] forest = new Node[ch.length];
			int i = 0;
			while (i < ch.length) {
				Node newNode = new Node();
				newNode.key = i;
				newNode.cdata = ch[i];
				forest[i] = newNode;
				i++;
			}

			i = 0;
			Node parent = new Node();
			while (i < ch.length) {
				if (i == 0) {
					parent.key = -999;
					parent.cdata = '+';
					parent.leftChild = forest[i++];
					parent.rightChild = forest[i++];
				}
				Node newNode = new Node();
				newNode.key = -999;
				newNode.cdata = '+';
				newNode.leftChild = parent;
				newNode.rightChild = forest[i++];
				parent = newNode;
			}
			root = parent;
		}

		/*
		 * This method creates the balanced tree as per requirements of Programming
		 * assignment 8.2
		 */
		public void balancedStringTree(String s) {
			char[] ch = s.toCharArray();
			Node[] forest = new Node[ch.length];
			int i = 0;
			while (i < ch.length) {
				Node newNode = new Node();
				newNode.key = i;
				newNode.cdata = ch[i];
				forest[i] = newNode;
				i++;
			}

			int size = 2;
			while (size > 1) {
				int evenOdd = (forest.length % 2);
				if (evenOdd == 0) {
					size = (forest.length / 2);
				} else {
					size = (forest.length / 2) + 1;
				}
				Node[] forest2 = new Node[size];
				int j = 0, k = 0;
				while (k < size) {
					Node newNode = new Node();
					newNode.key = -999;
					newNode.cdata = '+';

					if (j < forest.length) {
						newNode.leftChild = forest[j++];
					} else {
						newNode.leftChild = null;
					}

					if (j < forest.length) {
						newNode.rightChild = forest[j++];
					} else {
						newNode.rightChild = null;
					}
					forest2[k++] = newNode;
				}
				forest = forest2;
			}
			root = forest[0];
		}

		/*
		 * The methods sDisplayTree, sTraverse, spreOrder, sinOrder & spostOrder are
		 * used for display purposes of Programming assignment 8.1 & 8.2
		 */
		public void sDisplayTree(double d) {
			Stack gStack = new Stack();
			gStack.push(root);
			double nBlanks = d;
			boolean isRowEmpty = false;
			for (int i = 0; i < d * 2; i++) {
				System.out.print('*');
			}
			System.out.println("");

			while (isRowEmpty == false) {
				Stack lStack = new Stack();
				isRowEmpty = true;
				for (int j = 0; j < nBlanks; j++) {
					System.out.print(' ');
				}
				while (gStack.isEmpty() == false) {
					Node temp = (Node) gStack.pop();
					if (temp != null) {
						System.out.print(temp.cdata);
						lStack.push(temp.leftChild);
						lStack.push(temp.rightChild);
						if (temp.leftChild != null || temp.rightChild != null) {
							isRowEmpty = false;
						}
					} else {
						System.out.print("-");
						lStack.push(null);
						lStack.push(null);
					}
					for (int j = 0; j < nBlanks * 2 - 1; j++) {
						System.out.print(' ');
					}
				}
				System.out.println();
				nBlanks /= 2;
				while (lStack.isEmpty() == false) {
					gStack.push(lStack.pop());
				}
			}

			for (int i = 0; i < d * 2; i++) {
				System.out.print('*');
			}
			System.out.println("");
		}

		public void sTraverse(int type) {
			switch (type) {
			case 1:
				System.out.print("\nPreOrder Traversal: ");
				spreOrder(root);
				break;
			case 2:
				System.out.print("\nInOrder Traversal: ");
				sinOrder(root);
				break;
			case 3:
				System.out.print("\nPostOrder Traversal: ");
				spostOrder(root);
				break;
			}
			System.out.println();
		}

		private void spreOrder(Node localRoot) {
			if (localRoot != null) {
				System.out.print(localRoot.cdata + " ");
				spreOrder(localRoot.leftChild);
				spreOrder(localRoot.rightChild);
			}
		}

		private void sinOrder(Node localRoot) {
			if (localRoot != null) {
				sinOrder(localRoot.leftChild);
				System.out.print(localRoot.cdata + " ");
				sinOrder(localRoot.rightChild);
			}
		}

		private void spostOrder(Node localRoot) {
			if (localRoot != null) {
				spostOrder(localRoot.leftChild);
				spostOrder(localRoot.rightChild);
				System.out.print(localRoot.cdata + " ");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int val;
		binaryTree bt = new binaryTree();
		// binaryTree.tree dtree = bt.new tree();
		//
		// dtree.insert(56, 1.5);
		// dtree.insert(23, 1.2);
		// dtree.insert(72, 1.7);
		// dtree.insert(16, 1.5);
		// dtree.insert(32, 1.2);
		// dtree.insert(47, 1.7);
		// dtree.insert(39, 1.5);
		// dtree.insert(33, 1.2);
		// dtree.insert(80, 1.7);
		// dtree.insert(99, 1.5);
		// dtree.insert(97, 1.5);

		binaryTree.tree stree = bt.new tree();
		binaryTree.tree stree1 = bt.new tree();

		while (true) {
			// System.out.println("Enter first character of show, insert, find, delete, or
			// traverse");
			System.out.println("Enter 'a' for Programming Assignment 8.1 ");
			System.out.println("Enter 'b' for Programming Assignment 8.2 ");
			System.out.println("Enter 'e' for exit ");
			int choice = getChar();
			switch (choice) {
			// case 's':
			// val = dtree.findLevelsInTree();
			// dtree.displayTree(Math.pow(2, val));
			// break;
			// case 'i':
			// System.out.print("Enter value to insert: ");
			// val = getInt();
			// dtree.insert(val, val+1.3);
			// break;
			// case 'f':
			// System.out.print("Enter the value to find: ");
			// val = getInt();
			// Node found = dtree.find(val);
			// if(found != null) {
			// System.out.print("Found: ");
			// found.displayNode();
			// System.out.println();
			// }
			// else {
			// System.out.println("Could not find "+val);
			// }
			// break;
			// case 'd':
			// System.out.print("Enter the value to delete: ");
			// val = getInt();
			// boolean del = dtree.delete(val);
			// if(del) {
			// System.out.println("Deleted "+val);
			// }
			// else {
			// System.out.println("Couldn't delete "+val);
			// }
			// break;
			// case 't':
			// System.out.print("Enter 1, 2 or 3 for PreOrder, InOrder or PostOrder
			// traversal: ");
			// val = getInt();
			// dtree.traverse(val);
			// break;
			case 'a':
				System.out.print("Enter the string to store in binary tree: ");
				String s = getString();
				stree.stringTree(s);
				System.out.println("The entered string is stored in the binary tree as: ");
				val = stree.findLevelsInTree();
				stree.sDisplayTree(Math.pow(2, val));
				stree.sTraverse(1);
				stree.sTraverse(2);
				stree.sTraverse(3);
				System.out.println("");
				break;
			case 'b':
				System.out.print("Enter the string to store in binary tree: ");
				String s1 = getString();
				stree1.balancedStringTree(s1);
				System.out.println("The entered string is stored in the binary tree as: ");
				val = stree1.findLevelsInTree();
				stree1.sDisplayTree(Math.pow(2, val));
				stree1.sTraverse(1);
				stree1.sTraverse(2);
				stree1.sTraverse(3);
				System.out.println("");
				break;
			case 'e':
				System.out.println("Exiting!!");
				return;
			default:
				System.out.println("Invalid Entry");
			}
		}
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
