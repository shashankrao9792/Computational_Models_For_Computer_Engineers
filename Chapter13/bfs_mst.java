
public class bfs_mst {

	class Queue {
		private final int size = 20;
		private int[] qArray;
		private int front;
		private int rear;
		
		public Queue() {
			qArray = new int[size];
			front = 0;
			rear = -1;
		}
		public void insert(int j) {
			if(rear == size-1) {
				rear = -1;
			}
			qArray[++rear] = j;
		}
		public int remove() {
			int temp = qArray[front++];
			if(front == size) {
				front = 0;
			}
			return temp;
		}
		public boolean isEmpty() {
			return ((rear+1 == front) || (front+size-1 == rear));
		}
	}
	
	class Vertex {
		public char label;
		public boolean wasVisited;
		
		public Vertex(char l) {
			label = l;
			wasVisited = false;
		}
	}
	
	class Graph {
		private final int max_vertx = 20;
		private Vertex vertexList[];
		private int adjMat[][];
		private int nverts;
		private Queue theQ;
		
		public Graph() {
			vertexList = new Vertex[max_vertx];
			adjMat = new int[max_vertx][max_vertx];
			nverts = 0;
			for(int j=0; j<max_vertx; j++) {
				for(int k=0; k<max_vertx; k++) {
					adjMat[j][k] = 0;
				}
			}
			theQ = new Queue();
		}
		public void addVertex(char l) {
			vertexList[nverts++] = new Vertex(l);
		}
		public void addEdge(int start, int end) {
			adjMat[start][end] = 1;
			adjMat[end][start] = 1;
		}
		public void displayVertex(int v) {
			System.out.print(vertexList[v].label);
		}
		public void bfs() {
			vertexList[0].wasVisited = true;
			displayVertex(0);
			theQ.insert(0);
			int v2;
			while(!theQ.isEmpty()) {
				int v1 = theQ.remove();
				while((v2=getAdjUnvisitedVertex(v1)) != -1) {
					vertexList[v2].wasVisited = true;
					displayVertex(v2);
					theQ.insert(v2);
				}
			}
			for(int j=0; j<nverts; j++) {
				vertexList[j].wasVisited = false;
			}
		}
		public int getAdjUnvisitedVertex(int v) {
			for(int j=0; j<nverts; j++) {
				if(adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
					return j;
				}
			}
			return -1;
		}
		public void mst() {
			vertexList[0].wasVisited = true;
			theQ.insert(0);
			int v2;
			while(!theQ.isEmpty()) {
				int currentVertex = theQ.remove();
				while((v2=getAdjUnvisitedVertex(currentVertex)) != -1) {
					vertexList[v2].wasVisited = true;
					displayVertex(currentVertex);
					displayVertex(v2);
					System.out.print(" ");
					theQ.insert(v2);
				}
			}
			for(int j=0; j<nverts; j++) {
				vertexList[j].wasVisited = false;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bfs_mst t = new bfs_mst();
		bfs_mst.Graph theG = t.new Graph();
		
		//added 9 vertices
		theG.addVertex('A');
		theG.addVertex('B');
		theG.addVertex('C');
		theG.addVertex('D');
		theG.addVertex('E');
		theG.addVertex('F');
		theG.addVertex('G');
		theG.addVertex('H');
		theG.addVertex('I');
		
		//added 12 edges
		theG.addEdge(0, 1);
		theG.addEdge(1, 2);
		theG.addEdge(0, 3);
		theG.addEdge(3, 4);
		theG.addEdge(2, 5);
		theG.addEdge(4, 7);
		theG.addEdge(5, 6);
		theG.addEdge(6, 8);
		theG.addEdge(7, 8);
		theG.addEdge(1, 8);
		theG.addEdge(2, 8);
		theG.addEdge(5, 8);
		
		System.out.print("BFS: ");
		theG.bfs();
		System.out.println();
		
		System.out.print("MST: ");
		theG.mst();
		System.out.println();
	}
}
