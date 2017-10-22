import java.util.Scanner;

public class InsertionSort {
	private long[] a;
	private int numElems;
	public InsertionSort(int max){
		a = new long[max];
		numElems = 0;
	}
	public void insert(long value){
		a[numElems] = value;
		numElems++;
	}
	public void display(){
		for(int j=0; j<numElems; j++){
			System.out.print(a[j]+" ");
		}
		System.out.println("");
	}
	public void delete(){
		for(int j=0; j<numElems; j++){
			a[j] = 0;
		}
		numElems = 0;
	}
	public void insertionSort(){
		int in, out;
		for(out=1; out<numElems; out++){
			long temp = a[out];
			in = out;
			while(in>0 && a[in-1]>=temp){
				a[in] = a[in-1];
				--in;
			}
			a[in] = temp;
		}
	}
	
	/*
	 * The function noDups has been added as per project 3.3 requirements
	 * This function removes the duplicates among the elements of the array with O(N) complexity 
	 */
	public void noDups(){
		int numOfDup = 0;
		int OrgArrSize = numElems;
		long curElem = -1; 
		for(int i=0; i<OrgArrSize; i++){
			if(a[i] != curElem){
				curElem = a[i];
				a[i-numOfDup] = a[i];
			}
			else{
				numOfDup++;
				numElems--;
			}
		}
	}
	
	/*
	 * The function insertionSortRemoveDups has been added as per project 3.6 requirements
	 * This function removes the duplicates among the elements of the array during Insertion Sort
	 */
	public void insertionSortRemoveDups(){
		int in, out;
		int numOfDups = 0;
		for(out=1; out<numElems; out++){
			long temp = a[out];
			for(in=out; in>0 && a[in-1]>=temp; in--){
				if(a[in-1] == temp){
					if(temp>-1){
						temp = -1;
						numOfDups++;
					}
				}
				a[in] = a[in-1];
			}
			a[in] = temp;
		}
		int newTotalElemNum = numElems - numOfDups;
		for(int i=0; i<newTotalElemNum; i++){
			a[i] = a[i+numOfDups];
		}
		numElems = numElems - numOfDups;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int maxSize = 100;
		InsertionSort arr;
		arr = new InsertionSort(maxSize);
		int num;
		int selection = 0;
		Scanner reader = new Scanner(System.in);
		
		do
		{
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.println("Press 1 for Homework Assignment 3.3");
			System.out.println("Press 2 for Homework Assignment 3.6");
			System.out.println("Press 0 for exiting");
			System.out.println("Enter your selection: ");
			selection = reader.nextInt();
			
			switch(selection){
				case 0:
					System.out.println("Exiting!!");
					break;
				case 1: 
					System.out.print("Enter no. of elements in array (max possible size = 100): ");
					num = reader.nextInt();
					
					for(int i=0; i<num; i++){
						System.out.print("Enter "+(i+1)+" Element: ");
						arr.insert(reader.nextLong());
					}
					
					System.out.println("Following array was entered: ");
					arr.display();
					
					arr.insertionSort();
					System.out.println("Array sorted through Insertion sort: ");
					arr.display();
					
					arr.noDups();
					System.out.println("Homework Assignment 3.3: Sorted array after running noDups() function: ");
					arr.display();
					break;
				case 2:
					System.out.print("Enter no. of elements in array (max possible size = 100): ");
					num = reader.nextInt();
					
					for(int i=0; i<num; i++){
						System.out.print("Enter "+(i+1)+" Element: ");
						arr.insert(reader.nextLong());
					}
					
					System.out.println("Following array was entered: ");
					arr.display();
					
					arr.insertionSortRemoveDups();
					System.out.println("Homework Assignment 3.6: Array sorted through insertionSortRemoveDups() function: ");
					arr.display();
					break;
				default:
					System.out.println("Please enter 0 or 1 or 2");
					break;
			}
			arr.delete();
		}while(selection != 0);
		
		reader.close();
	}
}
