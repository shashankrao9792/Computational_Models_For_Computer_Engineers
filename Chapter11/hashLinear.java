import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class hashLinear {

	class dataItem {
		private int iData;
		public dataItem(int i) {
			iData = i;
		}
		public int getKey() {
			return iData;
		}
	}
	
	class hashTable {
		private dataItem[] hashArray;
		private int arraySize;
		private dataItem nonItem;
		
		public int getHashTableSize() {
			int count = 0;
			for(int i=0; i<arraySize; i++) {
				if(hashArray[i] != null && hashArray[i].getKey() != -1) {
					count++;
				}
			}
			return count;
		}
		
		public hashTable(int size) {
			arraySize = size;
			hashArray = new dataItem[arraySize];
			nonItem = new dataItem(-1);
		}
		
		public void displayTable() {
			System.out.print("Hash Table: ");
			for(int j=0; j<arraySize; j++) {
				if(hashArray[j] != null) {
					System.out.print(hashArray[j].getKey() + " ");
				}
				else {
					System.out.print("** ");
				}
			}
			System.out.println("");
		}
		
		public int hashFunc (int key) {
			return (key%arraySize);
		}
		
		public void insert(dataItem item) {
			int key = item.getKey();
			int hashval = hashFunc(key);
			while((hashArray[hashval]!=null) && (hashArray[hashval].getKey() != -1)) {
				++hashval;
				hashval %= arraySize;
			}
			hashArray[hashval] = item;
		}
		
		/**
		 * @param key
		 * @return
		 */
		public dataItem delete(int key) {
			int hashval = hashFunc(key);
			int count = 0;
			while(hashArray[hashval] != null && count<=getHashTableSize()) {
				if(hashArray[hashval].getKey() == key) {
					dataItem temp = hashArray[hashval];
					hashArray[hashval] = nonItem;
					return temp;
				}
				hashval++;
				hashval %= arraySize;
				count++;
			}
			System.out.println("Couldn't find "+key+" in hash table to delete!");
			return null;
		}
		
		public int find(int key) {
			int hashval = hashFunc(key);
			int count = 0;
			while(hashArray[hashval] != null && count<=getHashTableSize()) {
				if(hashArray[hashval].getKey() == key) {
					return hashval;
				}
				hashval++;
				hashval %= arraySize;
				count++;
			}
			return -99;
		}
	}
	
	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
	
	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}
	
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		hashLinear H = new hashLinear();
		hashLinear.dataItem adataItem;
		int aKey, size, n, keysPerCell;
		System.out.print("Enter size of hash table: ");
		size = getInt();
		System.out.print("Enter initial number of items: ");
		n = getInt();
		keysPerCell = 10;
		
		hashLinear.hashTable theHT = H.new hashTable(size);
		
		for(int j=0; j<n; j++) {
			aKey = (int)(java.lang.Math.random() * keysPerCell * size);
			adataItem = H.new dataItem(aKey);
			theHT.insert(adataItem);
		}
		
		while(true) {
			System.out.print("Enter first character of show, insert, delete, or find: ");
			char choice = getChar();
			switch(choice) {
				case 's':
					theHT.displayTable();
					break;
				case 'i':
					System.out.println("Current Hash Table size: "+ theHT.getHashTableSize());
					if(theHT.getHashTableSize() >= size) {
						System.out.println("Hash Table full!!");
						break;
					}
					System.out.print("Enter key value to insert: ");
					aKey = getInt();
					adataItem = H.new dataItem(aKey);
					theHT.insert(adataItem);
					break;
				case 'd':
					System.out.print("Enter key value to delete: ");
					aKey = getInt();
					theHT.delete(aKey);
					break;
				case 'f':
					System.out.print("Enter key value to find: ");
					aKey = getInt();
					int foundIndex = theHT.find(aKey);
					if(foundIndex != -99) {
						System.out.println("Found "+aKey+" at index "+foundIndex+" of hash table");
					}
					else {
						System.out.println("Couldn't find key "+aKey+" in hash table!");
					}
					break;
				default:
					System.out.println("Invalid Entry");
			}
		}
	}
}
