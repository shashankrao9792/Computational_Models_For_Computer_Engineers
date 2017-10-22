
public class OrderedApp {
	public static void main(String[] args)
	{
		int maxSize = 100;
		OrdArray arr;
		arr = new OrdArray(maxSize);
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
		int searchKey = 55;
		if( arr.find(searchKey) != arr.size() )
			System.out.println("Found " + searchKey);
		else
			System.out.println("Can’t find " + searchKey);
		arr.display();
		arr.delete(00);
		arr.delete(55);
		arr.delete(99);
		arr.display();
		
		
		/*
		 * Code implementation for homework assignment 2.5
		 * Here two implementations have been performed for the merge
		 */
		
		/*
		 * Implementation No.1 : Merge using array objects
		 */
		long[] src_arr1 = {44,55,77,99,222};
		long[] src_arr2 = {00,11,22,33,66,88,111,333,444};
				
		int size_arr1 = src_arr1.length;
		int size_arr2 = src_arr2.length;
		
		OrdArray arrMerged = new OrdArray(size_arr1+size_arr2);
		arrMerged.merge(src_arr1, src_arr2, size_arr1, size_arr2);
		System.out.print("Merged array is: ");
		arrMerged.display();
		
		/*
		 * Implementation No.2 : Merge using OrdArray objects
		 */
		OrdArray arr1 = new OrdArray(maxSize);
		OrdArray arr2 = new OrdArray(maxSize);
		
		arr1.insert(44);
		arr1.insert(55);
		arr1.insert(77);
		arr1.insert(99);
		arr1.insert(222);
		
		arr2.insert(00);
		arr2.insert(11);
		arr2.insert(22);
		arr2.insert(33);
		arr2.insert(66);
		arr2.insert(88);
		arr2.insert(111);
		arr2.insert(333);
		arr2.insert(444);
		
		OrdArray arrMerged2 = new OrdArray(arr1.size()+arr2.size());
		arrMerged2.merge2(arr1, arr2);
		System.out.print("Merged2 array is: ");
		arrMerged2.display();
	}
}
