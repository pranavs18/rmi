package Server;

import java.util.ArrayList;

import generics.FinalCompilerTestInterface;

public class FinalCompilerTest implements FinalCompilerTestInterface {

	@Override
	public String hello() {
		String str = "Hello World";
		return str;
	}

	@Override
	public ArrayList<Integer> arrayWorld(Integer a) {
		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(a);
		return ar;
	}

}
