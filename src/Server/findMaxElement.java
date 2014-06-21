package Server;

import java.util.ArrayList;

import generics.*;
public class findMaxElement implements findMaxElementInterface {
	 
	public int findMax(ArrayList<Integer> al){
		int max = al.get(0);
		for(int i=1;i< al.size();i++){
			if(al.get(i) > max){
				max = al.get(i);
			}
		}
		return max;
	}
	
}