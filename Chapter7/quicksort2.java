
public class quicksort2 {
	private long[] theArray;
	private int numElems;

	private int compareCount = 0;
	private int copyCount = 0;

	public quicksort2(int max) {
		theArray = new long[max];
		numElems = 0;
	}

	public void insert(long value) {
		theArray[numElems] = value;
		numElems++;
	}

	public void display() {
		System.out.print("A = ");
		for (int j = 0; j < numElems; j++) {
			System.out.print(theArray[j] + " ");
		}
		System.out.println("");
	}

	public void quickSort() {
		recQuickSort(0, numElems - 1);
	}

	public void recQuickSort(int left, int right) {
		int size = right - left + 1;
		if (size <= 3) {
			manualSort(left, right);
			System.out.print("Manual Sort; Intermediate array: ");
			display();
			System.out.println("Copy Count = " + copyCount + " ; Compare Count = " + compareCount);
		} else {
			long median = medianOf3(left, right);
			System.out.print("Calculating median; Intermediate array: ");
			display();
			System.out.println("Copy Count = " + copyCount + " ; Compare Count = " + compareCount);

			int partition = partitionIt(left, right, median);
			System.out.print("Partitioning; Intermediate array: ");
			display();
			System.out.println("Copy Count = " + copyCount + " ; Compare Count = " + compareCount);

			recQuickSort(left, partition - 1);
			recQuickSort(partition + 1, right);
		}
	}

	public long medianOf3(int left, int right) {
		int center = (left + right) / 2;
		if (theArray[left] > theArray[center]) {
			swap(left, center);
		}
		if (theArray[left] > theArray[right]) {
			swap(left, right);
		}
		if (theArray[center] > theArray[right]) {
			swap(center, right);
		}
		compareCount += 3;
		swap(center, right - 1);
		return theArray[right - 1];
	}

	public void swap(int dex1, int dex2) {
		long temp;
		temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
		copyCount += 3;
	}

	public int partitionIt(int left, int right, long pivot) {
		int leftPtr = left;
		int rightPtr = right - 1;

		while (true) {
			while (theArray[++leftPtr] < pivot) {
				compareCount++;
			}
			compareCount++;
			while (theArray[--rightPtr] > pivot) {
				compareCount++;
			}
			compareCount++;
			if (leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}

		swap(leftPtr, right - 1);
		return leftPtr;
	}

	public void manualSort(int left, int right) {
		int size = right - left + 1;
		if (size <= 1)
			return;
		if (size == 2) {
			if (theArray[left] > theArray[right]) {
				swap(left, right);
			}
			compareCount++;
			return;
		} else {
			if (theArray[left] > theArray[right - 1]) {
				swap(left, right - 1);
			}
			if (theArray[left] > theArray[right]) {
				swap(left, right);
			}
			if (theArray[right - 1] > theArray[right]) {
				swap(right - 1, right);
			}
			compareCount += 3;
		}
	}

	/*
	 * Updated quicksort2.java class as per requirements of Homework Assignment 7.2
	 * The program counts the number of copies and comparisons made during a sort
	 * and then displays the totals
	 */
	public static void main(String[] args) {
		int maxSize = 16;
		quicksort2 arr;
		arr = new quicksort2(maxSize);
		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 99);
			arr.insert(n);
		}
		System.out.print("Initial array: ");
		arr.display();
		System.out.println("Copy Count = " + arr.copyCount + " ; Compare Count = " + arr.compareCount);

		arr.quickSort();

		System.out.print("Final array: ");
		arr.display();
		System.out.println("Copy Count = " + arr.copyCount + " ; Compare Count = " + arr.compareCount);
	}
}
