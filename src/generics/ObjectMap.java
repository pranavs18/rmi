package generics;

import java.util.concurrent.ConcurrentHashMap;

public class ObjectMap {
	
	private static ConcurrentHashMap<String, Object> objectMap = new ConcurrentHashMap<String, Object>();

public static void insertIntoServerMap(String key, Object obj){
	
	objectMap.put(key, obj);
	System.out.println("Key "+key+" Value"+obj.toString());
	
}
	
public static void removeFromServerMap(String key){
	
	objectMap.remove(key);
	
}

public static Object getObjectFromKey(String key){
	return objectMap.get(key);
}
}
