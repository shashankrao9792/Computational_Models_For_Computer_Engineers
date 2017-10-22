
public class proj_2_1 {
	private long[] a;
	private int nElems;
	public proj_2_1(int max){
			a = new long[max];
			nElems = 0;
	}
	public boolean find(long searchKey){
		int j;
		boolean found = false;
		for(j=0; j<nElems; j++){
			if(a[j] == searchKey){
				found = true;
				break;
			}
		}
		return found;
	}
	public void insert(long value){
		a[nElems] = value;
		nElems++;
	}
	public boolean delete(long value){
		int j;
		for(j=0; j<nElems; j++){
			if(value == a[j])
				break;
		}
		if(j == nElems){
			return false;
		}
		else{
			for(int k=j; k<nElems; k++){
				a[k] = a[k+1];
			}
			nElems--;
			return true;
		}
	}
	public void display(){
		for(int j=0; j<nElems; j++)
			System.out.print(a[j]+" ");
		System.out.println("");
	}
	public long getMax(){
		long max = 0;
		if(nElems == 0){
			return -1;
		}
		else{
			max = a[0];
			for(int j=1; j<nElems; j++){
				if(a[j]>max){
					max = a[j];
				}
			}
			return max;
		}
	}
	public long removeMax(){
		long remMax = 0;
		remMax = getMax();
		delete(remMax);
		return remMax;
	}
}