/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */


package generics;

import java.util.concurrent.ConcurrentHashMap;

public class ObjectMap {
	
/* Map for storing actual object on client side to invoke a method */
private static ConcurrentHashMap<String, Object> objectMap = new ConcurrentHashMap<String, Object>();

public static void insertIntoServerMap(String key, Object obj){
	
	objectMap.put(key, obj);
	
}
	
public static void removeFromServerMap(String key){
	
	objectMap.remove(key);
	
}

public static Object getObjectFromKey(String key){
	return objectMap.get(key);
}
}
