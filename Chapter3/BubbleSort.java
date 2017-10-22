import java.util.Scanner;

public class BubbleSort {
	private long[] a;
	private int numElems;
	public BubbleSort(int max){
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
	public void bubbleSort(){
		int out, in;
		for(out=numElems-1; out>1; out--){
			for(in=0; in<out; in++){
				if(a[in]>a[in+1]){
					swap(in, in+1);
				}
			}
		}
	}
	public void swap(int one, int two){
		long temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}
	
	/*
	 * The function oddEvenSort has been added as per project 3.4 requirements
	 * This function implements odd-even sort for the array elements entered
	 */
	public void oddEvenSort(){
		int even, odd;
		int numEvenOddLoops = numElems/2;
		while(numEvenOddLoops>0){
			if(numElems%2 == 0){
				for(even=0; even<numElems-1; even+=2){
					if(a[even]>a[even+1]){
						swap(even, even+1);
					}
				}
				for(odd=1; odd<numElems-2; odd+=2){
					if(a[odd]>a[odd+1]){
						swap(odd, odd+1);
					}
				}
			}
			else{
				for(even=0; even<numElems-2; even+=2){
					if(a[even]>a[even+1]){
						swap(even, even+1);
					}
				}
				for(odd=1; odd<numElems-1; odd+=2){
					if(a[odd]>a[odd+1]){
						swap(odd, odd+1);
					}
				}
			}
			numEvenOddLoops--;
		}
	}
	
	public static void main(String[] args){
		int maxSize = 100;
		BubbleSort arr;
		arr = new BubbleSort(maxSize);
		
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter no. of elements in array (max possible size = 100): ");
		int num = reader.nextInt();
		
		for(int i=0; i<num; i++){
			System.out.print("Enter "+(i+1)+" Element: ");
			arr.insert(reader.nextLong());
		}
		reader.close();

		System.out.print("Following array was entered: ");
		arr.display();
		System.out.print("Homework Assignment 3.4: Array sorted through oddEvenSort() function: ");
		arr.oddEvenSort();
		arr.display();
	}
}
