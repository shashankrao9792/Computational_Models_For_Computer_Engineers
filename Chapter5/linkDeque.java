import java.util.Scanner;

public class linkDeque {
	
	class Link{
		public long data;
		public Link next;
		public Link previous;
		public Link(long d){
			data = d;
		}
		public void displayLink(){
			System.out.print(data+" ");
		}
	}
	
	class doublyLinked{
		private Link first;
		private Link last;
		public doublyLinked(){
			first = null;
			last = null;
		}
		public boolean isEmpty(){
			return (first == null);
		}
		public void insertLeft(long dd){
			Link newLink = new Link(dd);
			if(isEmpty()){
				last = newLink;
			}
			else{
				first.previous = newLink;
			}
			newLink.next = first;
			first = newLink;
		}
		public void insertRight(long dd){
			Link newLink = new Link(dd);
			if(isEmpty()){
				first = newLink;
			}
			else{
				last.next = newLink;
				newLink.previous = last;
			}
			last = newLink;
		}
		public long removeLeft(){
			Link t = first;
			long temp = 0;
			if(t == null){
				temp = -1;
				return temp;
			}
			else{
				temp = t.data;
			}
			if(first.next == null){
				last = null;
			}
			else{
				first.next.previous = null;
			}
			first = first.next;
			return temp;
		}
		public long removeRight(){
			Link t = last;
			long temp = 0;
			if(t == null){
				temp = -1;
				return temp;
			}
			else{
				temp = t.data;
			}
			if(first.next == null){
				first = null;
			}
			else{
				last.previous.next = null;
			}
			last = last.previous;
			return temp;
		}
		public void displayFromLeft(){
			if(isEmpty()){
				System.out.println("Deque is EMPTY!!");
			}
			else{
				System.out.print("Deque (left-->right): ");
				Link current = first;
				while(current != null){
					current.displayLink();
					current = current.next;
				}
				System.out.println("");
			}
		}
		public void displayFromRight(){
			if(isEmpty()){
				System.out.println("Deque is EMPTY!!");
			}
			else{
				System.out.print("Deque (right-->left): ");
				Link current = last;
				while(current != null){
					current.displayLink();
					current = current.previous;
				}
				System.out.println("");
			}
		}
	}
	
	/*
	 * This code implements a dequeue based on doubly linked list as per Homework Assignment 5.2 requirements
	 * insertLeft and insertRight methods are used to add elements to the left and right sides respectively in the dequeue
	 * removeLeft and removeRight methods are used to remove elements from the left and right sides respectively of the dequeue
	 * displayFromLeft method is used to display the dequeue elements from the left to the right end and 
	 * displayFromRight method is used to display the dequeue elements from the right to the left end
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		linkDeque thelist = new linkDeque();
		linkDeque.doublyLinked dq = thelist.new doublyLinked();
		
		Scanner reader = new Scanner(System.in);
		long temp;
		int selection = 0;
		do
		{
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("Press 1 for inserting element into the deque from the left");
			System.out.println("Press 2 for inserting element into the deque from the right");
			System.out.println("Press 3 for removing element of the deque from the left");
			System.out.println("Press 4 for removing element of the deque from the right");
			System.out.println("Press 5 for displaying elements in deque from the left");
			System.out.println("Press 6 for displaying elements in deque from the right");
			System.out.println("Press 0 for exiting");
			System.out.println("Enter your selection: ");
			selection = reader.nextInt();
			
			switch(selection){
				case 0:
					System.out.println("Exiting!!");
					break;
				case 1:					
					System.out.print("Enter element to insert into deque (from the left): ");
					dq.insertLeft(reader.nextLong());
					break;
				case 2:
					System.out.println("Enter element to insert into deque (from the right): ");
					dq.insertRight(reader.nextLong());
					break;
				case 3:
					temp = dq.removeLeft();
					if(temp != -1){
						System.out.println("Element removed from left of deque is: "+temp);
					}
					else{
						System.out.println("Sorry...Cannot remove element from empty deque!!");
					}
					break;
				case 4:
					temp = dq.removeRight();
					if(temp != -1){
						System.out.println("Element removed from right of deque is: "+temp);
					}
					else{
						System.out.println("Sorry...Cannot remove element from empty deque!!");
					}
					break;
				case 5:
					dq.displayFromLeft();
					break;
				case 6:
					dq.displayFromRight();
					break;
				default:
					System.out.println("Please enter 0 or 1 or 2 or 3 or 4 or 5 or 6");
					break;
			}
		}while(selection != 0);
		
		reader.close();
	}
}
