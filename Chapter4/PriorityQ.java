import java.util.Scanner;

public class PriorityQ {

	private int maxSize;
	private long[] pqArray;
	private int nItems;
	
	public PriorityQ(int s){
		maxSize = s;
		pqArray = new long[maxSize];
		nItems = 0;
	}
	
	public void insert(long item){
		int j;
		if(nItems == 0){
			pqArray[nItems++] = item;
		}
		else{
			for(j=nItems-1; j>=0; j--){
				if(item>pqArray[j]){
					pqArray[j+1] = pqArray[j];
				}
				else{
					break;
				}
			}
			pqArray[j+1] = item;
			nItems++;
		}
	}
	
	public long remove(){
		return pqArray[--nItems];
	}
	
	public long peekMin(){
		return pqArray[nItems-1];
	}
	
	public boolean isEmpty(){
		return (nItems == 0);
	}
	
	public boolean isFull(){
		return (nItems == maxSize);
	}
	
	/*
	 * This function fastInsert() inserts the elements in O(1) time 
	 * as per requirements of Homework Assignment 4.4
	 */
	public void fastInsert(long item){
		pqArray[nItems++] = item;
	}
	/*
	 * This function slowRemove() removes the elements from Priority queue
	 * as per the requirements of Homework Assignment 4.4
	 * The lowest key is removed first, followed by the second lowest, and so on
	 * regardless of the order in which the insert has been made 
	 */
	public long slowRemove(){
		long temp = 0;
		int t = 0;
		for(int i=nItems-1; i>=0; i--){
			if(pqArray[i]<pqArray[t]){
				t = i;
			}
		}
		temp = pqArray[t];
		for(int i=t; i<nItems-1; i++){
			pqArray[i] = pqArray[i+1];
		}
		nItems--;
		return temp;
	}
	
	/*
	 * This function display() displays the elements in the queue 
	 * as per requirements of Homework assignment 4.4
	 * The elements in the queue are displayed on the order in which they are inserted
	 * and empty queue is also handled 			
	 */
	public void display(){
		if(nItems == 0){
			System.out.println("Queue is EMPTY!!");
		}
		else{
			System.out.print("Queue is: ");
			for(int i=0; i<nItems; i++){
				System.out.print(pqArray[i]+" ");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Scanner reader = new Scanner(System.in);
		int len;
		System.out.print("Enter the maximum size of queue: ");
		len = reader.nextInt();
		
		PriorityQ pq = new PriorityQ(len);
		int selection = 0;
		do
		{
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("Press 1 for inserting element into the queue (as per Homework Assignment 4.4)");
			System.out.println("Press 2 for removing element from the queue (as per Homework Assignment 4.4)");
			System.out.println("Press 3 for displaying elements in queue, in the order with which insert was done");
			System.out.println("Press 0 for exiting");
			System.out.println("Enter your selection: ");
			selection = reader.nextInt();
			
			switch(selection){
				case 0:
					System.out.println("Exiting!!");
					break;
				case 1:					
						if(pq.isFull()){
							System.out.println("Sorry! Queue is full...Please remove elements to make space for new elements to insert");
						}
						else{
							System.out.print("Enter element ("+(len-pq.nItems)+" element(s) can still be inserted in the queue): ");
							pq.fastInsert(reader.nextLong());
						}
					break;
				case 2:
					System.out.println("The following element has been removed from queue: "+pq.slowRemove());
					break;
				case 3:
					pq.display();
					break;
				default:
					System.out.println("Please enter 0 or 1 or 2 or 3");
					break;
			}
		}while(selection != 0);
		
		reader.close();
	}
}
