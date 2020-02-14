import java.util.ArrayList;
import java.util.Arrays;
public class Hashtable {
	
	//declaring private variables
	private String crStrat;
	private int size, load,methodCount,hashCount;
	private Node[] table;
	//contructor for hashtable class
	public Hashtable(int size,String s){  
		crStrat = s;
		this.size = size;
		load = 0;
		methodCount = 0;
		hashCount = 0;
		table = new Node[size];
	}
	//node class
	private class Node{
		//initilizing variables
		int Key;
		String value;
		//constructor 
		public Node(int Key,String value){
			this.Key = Key;
			this.value = value;

		}
		//accessor
		public int getKey()
		{
			return Key;
		}
		//accessor 
		public String getValue()
		{
			return value;
		}

	}

	
	//get method that will return the value given a key or null 
	public String get(int key) {
		//incrementing counters and creating initial hash
		methodCount++;
		hashCount ++;
		int hash = (key % size);
		//checks that index position hash in the table is not null
		while (table[hash] != null)
		{	
			//if the key passed matches that at the index of the hash
			if(key == (table[hash].getKey()))
			{
				return table[hash].getValue();
			}
			//checking the collision resolution strategy provided called crStrat and calling the appropriate rehash method 
			if(crStrat.equals("L"))
			{
				hash = rehashLinear(key,hash);
			}
			else if(crStrat.equals("K"))
			hash = rehashKey(key,hash);	
		}
		return null;
	}


	//method to put something into the hashtable
	public void put(int key, String s) {
		//incrementing counters and creating initial hash
		methodCount++;
		hashCount ++;
		int hash = (key % size);
		//checks that index position hash in the table is not null
		while (table[hash] != null)
		{
			//checking the collision resolution strategy provided called crStrat and calling the appropriate rehash method 
			if(crStrat.equals("L"))
			{
				hash = rehashLinear(key,hash);
			}
			else if(crStrat.equals("K"))
			hash = rehashKey(key,hash);	
		}
		//put the data into the table at the newly hashed position and increment the load counter
		table[hash] = new Node(key,s);
		load++;
	}

	//when user inputs L call this method to do a linear probing rehash
	private int rehashLinear(int k, int hc){
		//increment hashcount
		hashCount ++;
		//implement the linear rehash and then return the new hash
		hc++;
		hc = hc % size;
		return hc;
	}
	//when user inputs K call this method to do a key-offset probing rehash
	private int rehashKey(int k, int hc){
		//increment hashcount
		hashCount ++;
		//implement the key-offset probing rehash and return the new hash
		hc += (k % 7) +1;
		hc = hc % size;
		return hc;
	}
	//method to calculate the performance
	public double performance()
	{	//returns the performance by dividing the hashcount by methodcount and casting to double 
		return (double) hashCount/methodCount;
	}

	//method to calculate the load
	public double load()
	{	//returns the load by dividing load by size and casting to double
		return (double) load/size;
	}

}