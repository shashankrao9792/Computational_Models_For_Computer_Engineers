
public class Warshall {
	
	class StackX {
		private final int size = 20;
		private int[] stack;
		private int top;
		
		public StackX() {
			stack = new int[size];
			top = -1;
		}
		public void push(int t) {
			stack[++top] = t;
		}
		public int pop() {
			return stack[top--];
		}
		public int peek() {
			return stack[top];
		}
		public boolean isEmpty() {
			return (top == -1);
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
	
	class WGraph {
		private final int max_vertx = 20;
		private Vertex vertexList[];
		private int adjMat[][];
		private int nverts;
		private StackX theS;
		
		public WGraph() {
			vertexList = new Vertex[max_vertx];
			adjMat = new int[max_vertx][max_vertx];
			nverts = 0;
			for(int j=0; j<max_vertx; j++) {
				for(int k=0; k<max_vertx; k++) {
					adjMat[j][k] = 0;
				}
			}
			theS = new StackX();
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
		public int getAdjUnvisitedVertex(int v) {
			for(int j=0; j<nverts; j++) {
				if(adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
					return j;
				}
			}
			return -1;
		}
		public void dfs(int startVert) {
			vertexList[startVert].wasVisited = true;
			displayVertex(startVert);
			System.out.print(" -- ");
			theS.push(startVert);
			
			while(!(theS.isEmpty())) {
				int v = getAdjUnvisitedVertex(theS.peek());
				if(v == -1) {
					theS.pop();
				}
				else {
					vertexList[v].wasVisited = true;
					theS.push(v);
					displayVertex(v);
					System.out.print(' ');
				}
			}
			for(int j=0; j<nverts; j++) {
				vertexList[j].wasVisited = false;
			}
		}
		public void displayAdjMatConnectivityTab() {
			for(int j = 0; j<nverts; j++) {
				dfs(j);
				System.out.println("");
			}
			System.out.println("");
		}
		public int[][] warshall() {
			int[][] newWarshallMat = new int[max_vertx][max_vertx];
			for(int i = 0; i < nverts; i++) {
				for(int j = 0; j < nverts; j++) {
					newWarshallMat[i][j] = adjMat[i][j];
				}
			}
			System.out.println("The initial adjacency matrix of graph:");
			displayMatrix(newWarshallMat);
			System.out.println("");
			
			int z = 1;
			for(int j = 0; j < nverts; j++) {
				for(int i = 0; i < nverts; i++) {
					if(adjMat[j][i] == 1) {
						for(int k = 0; k < nverts; k++) {
							if(adjMat[k][j] == 1) {
								newWarshallMat[k][i] = 1;
								System.out.println("Transitive Closure Adj Matrix Step "+(z++)+":");
								displayMatrix(newWarshallMat);
								System.out.println("");
							}
						}
					}
				}
			}
			return newWarshallMat;
		}
		public void displayMatrix(int[][] M) {
			for(int i = 0; i < nverts; i++) {
				for(int j = 0; j < nverts; j++) {
					System.out.print(M[i][j] + " ");
				}
				System.out.println("");
			}
		}
	}
	
	public static void main(String[] args) {
		Warshall t = new Warshall();
		Warshall.WGraph theG = t.new WGraph();
		
		//added 5 vertices
		theG.addVertex('A');
		theG.addVertex('B');
		theG.addVertex('C');
		theG.addVertex('D');
		theG.addVertex('E');
		
		//added 5 edges
		theG.addEdge(0, 3);
		theG.addEdge(1, 0);
		theG.addEdge(3, 2);
		theG.addEdge(2, 4);
		theG.addEdge(3, 1);
//		theG.addEdge(4, 3);
//		theG.addEdge(2, 1);

		System.out.println("Connectivity Table of graph:");
		theG.displayAdjMatConnectivityTab();
		int[][] newAdjMat = theG.warshall();
		System.out.println("");
		System.out.println("The final transitive closure adjacency matrix of graph:");
		theG.displayMatrix(newAdjMat);
	}
}
