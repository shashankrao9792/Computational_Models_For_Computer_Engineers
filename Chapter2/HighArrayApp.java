
public class HighArrayApp {
	public static void main(String[] args) {
		int maxSize = 100;
		HighArray arr;
		arr = new HighArray(maxSize);
		
		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);
		
		arr.display();
		
		int searchKey = 35;
		if( arr.find(searchKey) )
			System.out.println("Found " + searchKey);
		else
			System.out.println("Can’t find " + searchKey);
		
		//arr.delete(00);
		//arr.delete(66);
		//arr.delete(33);

		arr.display();
		
		/*
		 * Code to test homework assignment 2.1
		 */
		long maxElem = arr.getMax();
		System.out.println("Max Element : " + maxElem);
		
		/*
		 * Code to test homework assignment 2.2
		 */
		long maxRemElem = arr.removeMax();
		System.out.println("Max Element is " + maxRemElem + " and it has been removed from array");
		System.out.println("Current array is: ");
		arr.display();
		
		/*
		 * Code to implement programming project 2.3
		 */
		long arr2[];
		arr2 = new long[maxSize];
		int count = 0;
		while(arr.getMax() != -1){
			arr2[count] = arr.removeMax();
			count++;
		}
		for(int i=count-1; i>=0; i--){
			arr.insert(arr2[i]);
		}
		System.out.print("Sorted array is : ");
		arr.display();
		
	}
}

