import java.util.Scanner;

public class queue {
	
	private int maxSize;
	private long[] qArray;
	private int front;
	private int rear;
	private int nItems;
	
	public queue(int s){
		maxSize = s;
		qArray = new long[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}
	
	public long remove(){
		long temp = qArray[front++];
		if(front == maxSize){
			front = 0;
		}
		nItems--;
		return temp;
	}
	
	public void insert(long j){
		if(rear == maxSize-1){
			rear = -1;
		}
		qArray[++rear] = j;
		nItems++;
	}
	
	public long peekFront(){
		return qArray[front];
	}
	
	public boolean isEmpty(){
		return (nItems == 0);
	}
	
	public boolean isFull(){
		return (nItems == maxSize);
	}
	
	public int size(){
		return nItems;
	}
	
	/*
	 * The function display() has been implemented to meet the requirements of Homework assignment 4.1
	 * The function displays the elements present in the queue at any given time
	 * Handling has also been done in case queue is empty 
	 */
	public void display(){
		if(isEmpty()){
			System.out.println("Queue is EMPTY!!");
		}
		else{
			System.out.print("The following elements are present in the queue: ");
			int t = front;
			int tItems = 0;
			do{
				tItems++;
				long temp = qArray[t++];
				if(t == maxSize){
					t = 0;
				}
				System.out.print(temp+" ");
			}while(tItems != size());
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner reader = new Scanner(System.in);
		int len;
		System.out.print("Enter the maximum size of queue: ");
		len = reader.nextInt();
		
		queue q = new queue(len);
		int selection = 0;
		do
		{
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("Press 1 for inserting element into the queue");
			System.out.println("Press 2 for removing element from the queue");
			System.out.println("Press 3 for displaying elements in queue (Homework Assignment 4.1)");
			System.out.println("Press 0 for exiting");
			System.out.println("Enter your selection: ");
			selection = reader.nextInt();
			
			switch(selection){
				case 0:
					System.out.println("Exiting!!");
					break;
				case 1:					
						if(q.isFull()){
							System.out.println("Sorry! Queue is full...Please remove elements to make space for new elements to insert");
						}
						else{
							System.out.print("Enter element ("+(len-q.nItems)+" element(s) can still be inserted in the queue): ");
							q.insert(reader.nextLong());
						}
					break;
				case 2:
					System.out.println("The following element has been removed from queue: "+q.remove());
					break;
				case 3:
					q.display();
					break;
				default:
					System.out.println("Please enter 0 or 1 or 2 or 3");
					break;
			}
		}while(selection != 0);
		
		reader.close();
	}

}
