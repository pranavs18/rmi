package Server;

import generics.Message;
import generics.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectClient {


	Integer port = 9999;
	public void createConnection() throws IOException{
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			while ( true ) {

				try {
					Socket clientSocket = ss.accept();
					ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
					Message msg = null;
					Object obj = null;
					try {


						obj =ois.readObject();

						Class<?> thisClass = null;

						thisClass = Class.forName(obj.getClass().getCanonicalName());

						Class<?>[] params = {};
						Method method = null;
						Message returnMessage = null;

						method = thisClass.getDeclaredMethod("getMessage", params);

						msg = (Message)method.invoke(obj, (Object[])null);

						String key = msg.getLookupName();
						Object o = ObjectMap.getObjectFromKey(key);

						System.out.println("key value "+ key +" "+ o.getClass().getCanonicalName());

						thisClass = null;

						thisClass = Class.forName(msg.getRor().getClass_Name());

						method = null;
						returnMessage = null;
						Object returnValue = null;
						Object newObj[] = new Object[msg.getArguments().length];
						newObj = msg.getArguments();		

//						Class<?>[] argTypes = new Class[newObj.length];
//						for (int i = 0; i < newObj.length; i++) {
//
//							argTypes[i] = newObj[i].getClass();
//
//							if(!argTypes[i].isPrimitive()){
//
//								if(argTypes[i].equals(Integer.class)){
//									argTypes[i]= int.class;
//								}
//								else if(argTypes[i].equals(Float.class)){
//									argTypes[i] = float.class;
//								}
//								else if(argTypes[i].equals(Double.class)){
//									argTypes[i] = double.class;
//
//								}
//								else if(argTypes[i].equals(Long.class)){
//									argTypes[i] = long.class;
//								}
//							}
//
//						} 

						if(msg.getArgTypes().length>0){
						method = thisClass.getMethod(msg.getMethodName(), msg.getArgTypes());
						}
						else{
							method = thisClass.getMethod(msg.getMethodName());
						}
						try {
							returnValue = method.invoke(o, newObj);

							returnMessage = new Message(MessageType.RETURN, msg.getMethodName(), msg.getArguments(), msg.getArgTypes(), msg.getReturnType(), returnValue, null, null,null);

							Object msgObj[] = new Object[1];
							msgObj[0] = returnMessage;
							Class<?> setParams[] = {returnMessage.getClass()};
							method = null;
							thisClass = Class.forName(obj.getClass().getCanonicalName());

							method = thisClass.getDeclaredMethod("setMessage", setParams);
							method.invoke(obj, msgObj);

							ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
							oos.writeObject(obj);
							oos.close();

						} catch (InvocationTargetException e) {

							Throwable cause = e.getCause();
							returnMessage = new Message(MessageType.EXCEPTION, msg.getMethodName(), msg.getArguments(), msg.getArgTypes(), msg.getReturnType(), null, cause.getMessage(), msg.getRor(),null);

							Object msgObj[] = new Object[1];
							msgObj[0] = returnMessage;

							Class<?> setParams[] = {returnMessage.getClass()};
							method = null;
							thisClass = Class.forName(obj.getClass().getCanonicalName());

							method = thisClass.getDeclaredMethod("setMessage", setParams);
							method.invoke(obj, msgObj);

							ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
							oos.writeObject(obj);
							oos.close();
						}

					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException  | IllegalAccessException | IllegalArgumentException | InvocationTargetException e
							) {

						e.printStackTrace();
					}

					ois.close();
				}   

				catch (IOException e) {
					System.out.println(e);
				}



			}

		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		finally{

			ss.close();

		}
	}

}
