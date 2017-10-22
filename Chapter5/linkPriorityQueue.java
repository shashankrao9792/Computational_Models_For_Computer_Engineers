import java.util.Scanner;

public class linkPriorityQueue {
	
	class Link{
		public long data;
		public Link next;
		public Link(long d){
			data = d;
		}
		public void displayLink(){
			System.out.print(data+" ");
		}
	}
	
	class FirstLastList{
		private Link first;
		private Link last;
		public FirstLastList(){
			first = null;
			last = null;
		}
		public boolean isEmpty(){
			return (first == null);
		}
		public void insertPQ(long dd){
			Link newLink = new Link(dd);
			Link previous = null;
			Link temp = first;
			while(temp!=null && dd>temp.data){
				previous = temp;
				temp = temp.next;
			}
			if(previous == null){
				first = newLink;
			}
			else{
				previous.next = newLink;
			}
			newLink.next = temp;
			last = first;
			while(last.next != null){
				last = last.next;
			}
		}
		public long deletePQ(){
			Link t = first;
			long temp = 0;
			if(t == null){
				temp = -1;
				return temp;
			}
			else{
				temp = first.data;
			}
			if(first.next == null){
				last = null;
			}
			first = first.next;
			return temp;
		}
		public void displayList(){
			Link current = first;
			while(current != null){
				current.displayLink();
				current = current.next;
			}
			System.out.println("");
		}
	}
	
	class LinkPQueue{
		private FirstLastList l;
		public LinkPQueue()
		{
			l = new FirstLastList();
		}
		public boolean isEmpty(){
			return (l.isEmpty());
		}
		public void insert(long j){
			l.insertPQ(j);
		}
		public long remove(){
			return (l.deletePQ());
		}
		public void displayQueue(){
			if(isEmpty()){
				System.out.println("Priority Queue is Empty!!");
			}
			else{
				System.out.print("Priority Queue (front-->rear): ");
				l.displayList();
			}
		}
	}
	
	/*
	 * This code implements priority queue based on linked list as per Homework Assignment 5.1 requirements
	 * The priority queue uses sort during insertion, hence elements stored in the priority queue are in sorted order(ascending in this case)
	 * On removing element from priority queue, the smallest key, which is present in the front of the priority queue, is removed
	 */
	public static void main(String[] args){
		linkPriorityQueue thepq = new linkPriorityQueue();
		linkPriorityQueue.LinkPQueue pq = thepq.new LinkPQueue();
		
		Scanner reader = new Scanner(System.in);
		long temp;
		int selection = 0;
		do
		{
			System.out.println("-------------------------------------------------------------------------------------------------------------");
			System.out.println("Press 1 for inserting element into the priority queue");
			System.out.println("Press 2 for removing element from the priority queue");
			System.out.println("Press 3 for displaying elements in priority queue");
			System.out.println("Press 0 for exiting");
			System.out.println("Enter your selection: ");
			selection = reader.nextInt();
			
			switch(selection){
				case 0:
					System.out.println("Exiting!!");
					break;
				case 1:					
					System.out.print("Enter element to insert into priority queue: ");
					pq.insert(reader.nextLong());
					break;
				case 2:
					temp = pq.remove();
					if(temp != -1){
						System.out.println("The item with the smallest key which is removed from priority queue is: "+temp);
					}
					else{
						System.out.println("Sorry...Cannot remove element from empty priority queue!!");
					}
					break;
				case 3:
					pq.displayQueue();
					break;
				default:
					System.out.println("Please enter 0 or 1 or 2 or 3");
					break;
			}
		}while(selection != 0);
		
		reader.close();
	}
}
