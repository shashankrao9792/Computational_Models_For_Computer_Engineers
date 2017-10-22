
public class graphicTree {

	public static String[] graphicTree;

	public static void makeBranches(long rowNum, long leftSide, long rightSide) {
		if (rowNum == graphicTree.length) {
			return;
		}
		long mid = (leftSide + rightSide) / 2;
		for (long i = leftSide; i <= rightSide; i++) {
			if (i == mid) {
				graphicTree[(int) rowNum] = graphicTree[(int) rowNum] + 'X';
			} else {
				graphicTree[(int) rowNum] = graphicTree[(int) rowNum] + '-';
			}
		}

		makeBranches(rowNum + 1, leftSide, mid);
		makeBranches(rowNum + 1, mid + 1, rightSide);
	}

	public static void display(String[] graphicTree) {
		for (int i = 0; i < graphicTree.length; i++) {
			System.out.println(graphicTree[i]);
		}
	}

	/*
	 * Main method for requirements of Homework Assignment 6.2 Width(or size) of
	 * each row is given Number of rows are calculated based on this width and
	 * makeBranches method is recursively called to create the branches the tree
	 * and then display method is called to display the tree
	 */
	public static void main(String[] args) {
		long sizeOfRow = 16;
		long numOfRows = 0;
		long temp = sizeOfRow;
		while (temp > 1) {
			numOfRows++;
			temp = temp / 2;
		}
		numOfRows++;
		graphicTree = new String[(int) numOfRows];
		for (long i = 0; i < graphicTree.length; i++) {
			graphicTree[(int) i] = "";
		}

		makeBranches(0, 0, sizeOfRow - 1);
		display(graphicTree);
	}
}
