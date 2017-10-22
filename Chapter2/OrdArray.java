
public class OrdArray {
	private long[] a;
	private int nElems;
	public OrdArray(int max){
		a = new long[max];
		nElems = 0;
	}
	public int size(){
		return nElems;
	}
	public int find(long searchKey){
		int lowerBound = 0;
		int upperBound = nElems - 1;
		int curIn;
		while(true){
			curIn = (lowerBound + upperBound ) / 2;
			if(a[curIn]==searchKey)
				return curIn;
			else if(lowerBound > upperBound)
				return nElems;
			else
			{
				if(a[curIn] < searchKey)
					lowerBound = curIn + 1;
				else
					upperBound = curIn - 1;
			}
		}
	}
	public void insert(long value){
		int j;
		for(j=0; j<nElems; j++)
			if(a[j] > value)
				break;
		for(int k=nElems; k>j; k--)
			a[k] = a[k-1];
		a[j] = value;
		nElems++;
	}
	public boolean delete(long value){
		int j = find(value);
		if(j==nElems)
			return false;
		else
		{
			for(int k=j; k<nElems; k++)
				a[k] = a[k+1];
			nElems--;
			return true;
		}
	}
	public void display(){
		for(int j=0; j<nElems; j++)
			System.out.print(a[j] + " ");
		System.out.println("");
	}
	
	/*
	 * Merge functions are implemented in order to implement homework project assignment
	 * 2.5. It is used to merge two sorted arrays into a single array
	 */
	
	/*
	 * Implementation No. 1
	 */
	public void merge(long b[], long c[], int size_b, int size_c){
		int i=0, j=0;
		while(i!=size_b && j!=size_c){
			if(b[i] <= c[j]){
				this.insert(b[i]);
				i++;
			}
			else{
				this.insert(c[j]);
				j++;
			}
		}
		if(i==size_b && j!=size_c){
			while(j!=size_c){
				this.insert(c[j]);
				j++;
			}
		}
		else if(i!=size_b && j==size_c){
			while(i!=size_b){
				this.insert(b[i]);
				i++;
			}
		}
	}
	
	/*
	 * Implementation No. 2
	 */
	public long getElement(int key){
		return a[key];
	}
	public void merge2(OrdArray b, OrdArray c){
		int i=0, j=0;
		while(i != b.size() && j != c.size()){
			if(b.getElement(i) <= c.getElement(j)){
				this.insert(b.getElement(i));
				i++;
			}
			else{
				this.insert(c.getElement(j));
				j++;
			}
		}
		while(i != b.size()){
			this.insert(b.getElement(i));
			i++;
		}
		while(j != c.size()){
			this.insert(c.getElement(j));
			j++;
		}
	}
}
