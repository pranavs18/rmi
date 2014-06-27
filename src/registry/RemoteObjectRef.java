/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */


package registry;

import java.io.Serializable;

import generics.myRemoteInterface;

public class RemoteObjectRef implements myRemoteInterface,Serializable
{
    
	private static final long serialVersionUID = 1L;
	String IP_adr;
    int Port;
    String name;
    String class_name;
    String callee;

    public RemoteObjectRef(String ip, int port, String name_key, String cname, String callee) 
    {
    	this.IP_adr=ip;
    	this.Port=port;
    	this.name =name_key;
    	this.class_name =cname;
    	this.callee = callee;
    }

    // this method is important, since it is a stub creator.
    // 
    Object localise()
    {
    	try{
    		Class<?> c = Class.forName(class_name);
    	    Object o = c.newInstance();
    	    return o;
    	}
    	catch (Exception e){}
    	return null;
    }
    
    // getter for the above defined variables
    
    
    public String getClass_Name(){
    	return this.class_name;
    }
    
    public int getPort(){
    	return this.Port;
    }
    
    public String getIP_adr(){
    	return this.IP_adr;
    }
    
    public String getkeyName(){
    	return this.name;
    }
    
    public String getCallee(){
    	return this.callee;
    }
    
}