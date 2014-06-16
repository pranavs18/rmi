package generics;

import java.io.Serializable;

public class MessageType implements Serializable {
	

	private static final long serialVersionUID = 1L;

	public enum msgType{
		RETURN, EXCEPTION,METHOD
	}
	
	private final msgType mtype;
	
	MessageType(msgType mtype){
		this.mtype = mtype;
	}
	
	@Override
    public String toString() {
        return mtype.toString();
    }
}