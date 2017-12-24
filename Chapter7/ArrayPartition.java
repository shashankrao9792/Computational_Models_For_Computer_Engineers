public class ArrayPartition {
	private long theArray[];
	private int numElems;

	public ArrayPartition(int max) {
		theArray = new long[max];
		numElems = 0;
	}

	public void insert(long value) {
		theArray[numElems] = value;
		numElems++;
	}

	public int size() {
		return numElems;
	}

	public void display() {
		System.out.print("A = ");
		for (int j = 0; j < numElems; j++) {
			System.out.print(theArray[j] + " ");
		}
		System.out.println("");
	}

	/*
	 * partitionIt function has been modified as per Homework assignment 7.1
	 * requirements; Pivot will always be the last rightmost element of the array
	 * Arrays of three or fewer elements also work with this function
	 */
	public int partitionIt(int left, int right) {
		int leftPtr = left - 1;
		long pivot = theArray[right + 1];
		int rightPtr = right + 1;
		System.out.print("Pivot is " + pivot);
		if (numElems <= 1) {
			return 0;
		}
		while (true) {
			while (leftPtr < (right) && theArray[++leftPtr] < pivot)
				;
			while (rightPtr > left && theArray[--rightPtr] > pivot)
				;
			if (leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		int x = 0;
		if (numElems > 1) {
			if (theArray[leftPtr] > pivot) {
				swap(leftPtr, right + 1);
				x = leftPtr;
			} else if (theArray[leftPtr] <= pivot) {
				swap(leftPtr + 1, right + 1);
				x = leftPtr + 1;
			}
		}
		return x;
	}

	public void swap(int dex1, int dex2) {
		long temp;
		temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}

	public static void main(String[] args) {
		System.out.println("Partitioning for 16 element array");
		int maxSize = 16;
		ArrayPartition arr;
		arr = new ArrayPartition(maxSize);
		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 199);
			arr.insert(n);
		}
		arr.display();
		int size = arr.size();
		int partDex = arr.partitionIt(0, size - 2);
		System.out.println(", Partition is at index " + partDex);
		arr.display();

		System.out.println("");
		System.out.println("Partitioning for 3 element array");
		maxSize = 3;
		ArrayPartition arr1;
		arr1 = new ArrayPartition(maxSize);
		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 199);
			arr1.insert(n);
		}
		arr1.display();
		size = arr1.size();
		partDex = arr1.partitionIt(0, size - 2);
		System.out.println(", Partition is at index " + partDex);
		arr1.display();

		System.out.println("");
		System.out.println("Partitioning for 2 element array");
		maxSize = 2;
		ArrayPartition arr2;
		arr2 = new ArrayPartition(maxSize);
		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 199);
			arr2.insert(n);
		}
		arr2.display();
		size = arr2.size();
		partDex = arr2.partitionIt(0, size - 2);
		System.out.println(", Partition is at index " + partDex);
		arr2.display();

		System.out.println("");
		System.out.println("Partitioning for 1 element array");
		maxSize = 1;
		ArrayPartition arr3;
		arr3 = new ArrayPartition(maxSize);
		for (int j = 0; j < maxSize; j++) {
			long n = (int) (java.lang.Math.random() * 199);
			arr3.insert(n);
		}
		arr3.display();
		size = arr3.size();
		partDex = arr3.partitionIt(0, size - 2);
		System.out.println(", Partition is at index " + partDex);
		arr3.display();
	}
}
