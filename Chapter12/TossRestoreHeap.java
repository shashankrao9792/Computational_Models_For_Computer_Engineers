import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TossRestoreHeap {

	class Node {
		private int data;

		public Node(int key) {
			data = key;
		}

		public int getKey() {
			return data;
		}

		public void setKey(int id) {
			data = id;
		}
	}

	class Heap {
		private Node[] heapArray;
		private int maxSize;
		private int currentSize;
		private int tossIndex = 0;

		public Heap(int mx) {
			maxSize = mx;
			currentSize = 0;
			heapArray = new Node[maxSize];
		}

		public boolean isEmpty() {
			return (currentSize == 0);
		}

		public boolean insert(int key) {
			if (currentSize == maxSize) {
				return false;
			}
			Node newNode = new Node(key);
			heapArray[currentSize] = newNode;
			trickleUp(currentSize++);
			return true;
		}

		public void trickleUp(int index) {
			int parent = (index - 1) / 2;
			Node bottom = heapArray[index];
			while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
				heapArray[index] = heapArray[parent];
				index = parent;
				parent = (parent - 1) / 2;
			}
			heapArray[index] = bottom;
		}

		public void displayHeap() {
			System.out.print("HeapArray: ");
			for (int m = 0; m < currentSize; m++) {
				if (heapArray[m] != null) {
					System.out.print(heapArray[m].getKey() + " ");
				} else {
					System.out.print("--");
				}
				System.out.print("  ");
			}
			int nBlanks = 32;
			int itemsPerRow = 1;
			int column = 0;
			int j = 0;
			String dots = "...............................................";
			System.out.println("\n" + dots + dots);
			while (currentSize > 0) {
				if (column == 0) {
					for (int k = 0; k < nBlanks; k++) {
						System.out.print(' ');
					}
				}
				System.out.print(heapArray[j].getKey());
				if (++j == currentSize) {
					break;
				}
				if (++column == itemsPerRow) {
					nBlanks /= 2;
					itemsPerRow *= 2;
					column = 0;
					System.out.println();
				} else {
					for (int k = 0; k < nBlanks * 2 - 2; k++) {
						System.out.print(' ');
					}
				}
			}
			System.out.println("\n" + dots + dots);
		}

		/*
		 * These functions have been written according to Programming Assignment 12.2
		 * requirements; toss function will just add the new element at the end of the
		 * heapArray; restoreHeap function will restore the ordering of the elements
		 * according to their priority
		 */

		public boolean toss(int key) {
			if (currentSize == maxSize) {
				return false;
			}
			Node newNode = new Node(key);
			heapArray[currentSize++] = newNode;
			tossIndex++;
			return true;
		}

		public void restoreHeap() {
			int index = 0;
			while (tossIndex > 0) {
				index = currentSize - tossIndex;
				trickleUp(index);
				tossIndex--;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int value;
		TossRestoreHeap H = new TossRestoreHeap();
		TossRestoreHeap.Heap theHeap = H.new Heap(31);
		boolean success;
		theHeap.toss(70);
		theHeap.toss(40);
		theHeap.toss(50);
		theHeap.toss(20);
		theHeap.toss(60);
		theHeap.toss(100);
		theHeap.toss(80);
		theHeap.toss(30);
		theHeap.toss(10);
		theHeap.toss(90);
		System.out.println("Tossed in 10 elements!");
		while (true) {
			System.out.print("Enter first letter of show, toss, restore, quit: ");
			int choice = getChar();
			switch (choice) {
			case 's':
				theHeap.displayHeap();
				break;
			case 't':
				System.out.print("Enter value to toss: ");
				value = getInt();
				success = theHeap.toss(value);
				if (!success)
					System.out.println("Can’t toss; heap full");
				break;
			case 'r':
				theHeap.restoreHeap();
				System.out.println("Restored Heap!!");
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
