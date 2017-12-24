import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class TreeHeapApp {

	class Node {
		private int data;
		public Node leftChildNode;
		public Node rightChildNode;
		public Node parentNode;
		boolean isLeftChildPresent = false;

		public int getKey() {
			return data;
		}

		public void setKey(int id) {
			data = id;
		}

		public void display() {
			System.out.println("Node Key: " + data);
		}
	}

	class TreeHeap {
		private Node root;
		private int numberOfNodes;

		public TreeHeap() {
			numberOfNodes = 0;
			root = new Node();
		}

		public boolean isEmpty() {
			return (numberOfNodes == 0);
		}

		public boolean insert(int key) {
			Node newNode = new Node();
			newNode.setKey(key);
			if (numberOfNodes == 0) {
				root = newNode;
			} else {
				Node currentNode = root;
				int n = numberOfNodes + 1;
				int j = 0;
				int[] pathTaken = new int[n];
				while (n >= 1) {
					pathTaken[j] = n % 2;
					n = n / 2;
					j++;
				}
				int k = j - 2;
				while (k > 0) {
					if (pathTaken[k] == 1) {
						currentNode = currentNode.rightChildNode;
					} else {
						currentNode = currentNode.leftChildNode;
					}
					k--;
				}
				if (currentNode.leftChildNode != null) {
					currentNode.rightChildNode = newNode;
					newNode.isLeftChildPresent = false;
				} else {
					currentNode.leftChildNode = newNode;
					newNode.isLeftChildPresent = true;
				}
				newNode.parentNode = currentNode;
				trickleUp(newNode);
			}
			numberOfNodes++;
			return true;
		}

		public void trickleUp(Node newNode) {
			int bottom = newNode.getKey();
			Node currentNode = newNode;
			while (currentNode.parentNode != null && currentNode.parentNode.getKey() < bottom) {
				currentNode.setKey(currentNode.parentNode.getKey());
				currentNode = currentNode.parentNode;
			}
			currentNode.setKey(bottom);
		}

		public Node remove() {
			Node deletedNode = root;
			if (numberOfNodes == 0) {
				return null;
			}
			if (numberOfNodes == 1) {
				root = null;
				numberOfNodes--;
				return deletedNode;
			}
			Node currentNode = root;
			int n = numberOfNodes;
			int j = 0;
			int[] pathTaken = new int[n];
			while (n >= 1) {
				pathTaken[j] = n % 2;
				n = n / 2;
				j++;
			}
			int k = j - 2;
			while (k >= 0) {
				if (pathTaken[k] == 1) {
					currentNode = currentNode.rightChildNode;
				} else {
					currentNode = currentNode.leftChildNode;
				}
				k--;
			}
			root.setKey(currentNode.getKey());
			if (currentNode.isLeftChildPresent) {
				currentNode.parentNode.leftChildNode = null;
			} else {
				currentNode.parentNode.rightChildNode = null;
			}
			trickleDown(root);
			numberOfNodes--;
			return deletedNode;
		}

		public void trickleDown(Node newNode) {
			Node currentNode = newNode;
			int upper = newNode.getKey();
			Node largerChildNode;
			while (currentNode.leftChildNode != null || currentNode.rightChildNode != null) {
				if (currentNode.rightChildNode != null
						&& currentNode.leftChildNode.getKey() < currentNode.rightChildNode.getKey()) {
					largerChildNode = currentNode.rightChildNode;
				} else {
					largerChildNode = currentNode.leftChildNode;
				}
				if (upper >= largerChildNode.getKey()) {
					break;
				}
				currentNode.setKey(largerChildNode.getKey());
				currentNode = largerChildNode;
			}
			currentNode.setKey(upper);
		}

		public Node findNodeKey(int key, Node startNode) {
			Node currentNode = startNode;
			Node temp = null;
			if (currentNode.getKey() == key) {
				return currentNode;
			}
			if (currentNode.leftChildNode == null && currentNode.rightChildNode == null) {
				return null;
			}
			if (currentNode.leftChildNode != null) {
				temp = (findNodeKey(key, currentNode.leftChildNode));
				// return temp;
			}
			if (currentNode.rightChildNode != null && temp == null) {
				temp = (findNodeKey(key, currentNode.rightChildNode));
				// return temp;
			}
			return temp;
		}

		public boolean change(int oldKey, int newValue) {
			Node currentNode = findNodeKey(oldKey, root);
			if (currentNode == null) {
				return false;
			}
			int oldVal = currentNode.getKey();
			currentNode.setKey(newValue);
			if (oldVal < newValue) {
				trickleUp(currentNode);
			} else {
				trickleDown(currentNode);
			}
			return true;
		}

		public void displayHeap() {
			Stack<Node> gStack = new Stack<Node>();
			gStack.push(root);
			double nBlanks = 32;
			boolean isRowEmpty = false;

			// System.out.println("HeapTree: "+root.getKey());
			// for(int z = 0; z<numberOfNodes; z++) {
			// Node currentNode = root;
			// int n = z;
			// int j = 0;
			// int[] pathTaken = new int[n];
			// while(n >= 1) {
			// pathTaken[j] = n%2;
			// n = n/2;
			// j++;
			// }
			// int k = j-2;
			// while(k >= 0) {
			// if(pathTaken[k] == 1) {
			// currentNode = currentNode.rightChildNode;
			// System.out.println(currentNode.getKey());
			// }
			// else {
			// currentNode = currentNode.leftChildNode;
			// System.out.println(currentNode.getKey());
			// }
			// k--;
			// }
			// }

			String dots = "...............................................";
			System.out.println(dots + dots);

			while (isRowEmpty == false) {
				Stack<Node> lStack = new Stack<Node>();
				isRowEmpty = true;
				for (int j = 0; j < nBlanks; j++) {
					System.out.print(' ');
				}
				while (gStack.isEmpty() == false) {
					Node temp = (Node) gStack.pop();
					if (temp != null) {
						System.out.print(temp.getKey());
						lStack.push(temp.leftChildNode);
						lStack.push(temp.rightChildNode);
						if (temp.leftChildNode != null || temp.rightChildNode != null) {
							isRowEmpty = false;
						}
					} else {
						System.out.print("  ");
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

			System.out.println(dots + dots);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int value, value2;
		TreeHeapApp H = new TreeHeapApp();
		TreeHeapApp.TreeHeap theHeap = H.new TreeHeap();
		// TreeHeapApp.Node N = H.new Node();
		// N.setKey(73);
		// N.display();
		boolean success;
		theHeap.insert(70);
		theHeap.insert(40);
		theHeap.insert(50);
		theHeap.insert(20);
		theHeap.insert(60);
		theHeap.insert(100);
		theHeap.insert(80);
		theHeap.insert(30);
		theHeap.insert(10);
		theHeap.insert(90);
		// N = theHeap.findNodeKey(61, theHeap.root);
		// if(N==null) {
		// System.out.println("NF");
		// }
		// N.display();
		while (true) {
			System.out.print("Enter first letter of show, insert, remove, change, quit: ");
			int choice = getChar();
			switch (choice) {
			case 's':
				theHeap.displayHeap();
				break;
			case 'i':
				System.out.print("Enter value to insert: ");
				value = getInt();
				success = theHeap.insert(value);
				if (!success)
					System.out.println("Can’t insert; heap full");
				break;
			case 'r':
				if (!theHeap.isEmpty())
					theHeap.remove();
				else
					System.out.println("Can’t remove; heap empty");
				break;
			case 'c':
				System.out.print("Enter key value of item to be changed: ");
				value = getInt();
				System.out.print("Enter new key: ");
				value2 = getInt();
				success = theHeap.change(value, value2);
				if (!success)
					System.out.println("Invalid key value");
				break;
			case 'q':
				System.out.println("Exiting!");
				return;
			default:
				System.out.println("Invalid entry\n");
			}
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}
