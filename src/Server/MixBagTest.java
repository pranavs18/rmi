package Server;

import java.util.HashMap;

import generics.MixbagTestInterface;

public class MixBagTest implements MixbagTestInterface {

	@Override
	public Float divide(float a, float b, Float c) {
		
		Float result = (a * b)/c;
		
		return (Float)result;
	}

	@Override
	public void hello() {
		System.out.println("Server Hit can computation done" );

	}

	@Override
	public HashMap<String, Integer> has(String a, Integer b) {
		
		HashMap<String, Integer> h = new HashMap<String, Integer>();
		h.put(a, b);
		return h;
		
	}

}
