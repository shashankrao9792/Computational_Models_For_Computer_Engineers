
public class recMult {
	public static long mult(long x, long y) {
		if (y == 1) {
			return x;
		}
		return (x + (mult(x, y - 1)));
	}

	/*
	 * Main method for requirement of Homework Assignment 6.1 mult method is
	 * recursively called which provides product of two numbers by adding them
	 * instead of multiplying directly
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long x = 3, y = 5;
		System.out.println(x + " * " + y + " = " + mult(x, y));
	}
}
