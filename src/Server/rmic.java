/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */


package Server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class rmic {
	
	
	/* This function is used to append a strings which form the contents of the .java class for the stub */
	void appendText(File file, Class<?> className){
	
		String oldClassName = className.getSimpleName();
		String oldClassPackageName =className.getPackage().getName();
		String interfaceInherited = className.getInterfaces()[0].toString();
		interfaceInherited = interfaceInherited.replaceFirst("^interface ","");
		
		/* These are import statements and initial strings. We require only the class name and interface implemented
		 * which we have found from the server programs name and appended with _stub*/
		String commonContent = "package " + oldClassPackageName+"; \n"
			+"import generics.Communication; \n"
			+ "import "+interfaceInherited+"; \n"
			+ "import generics.Message;\n"
			+ "import generics.MessageType;\n"
			+ "import java.io.Serializable;\n"
			+ "import java.lang.reflect.Method;\n"
			+ "import java.net.InetAddress;\n"
			+ "import java.net.UnknownHostException;\n"
			+ "import registry.RemoteException;\n"
			+ "import registry.RemoteObjectRef;\n"
			+ "public class "+oldClassName+"_stub implements "+interfaceInherited+", Serializable{\n"
			+ "private static final long serialVersionUID = 1L;\n"
			+ "String host ;\n"
			+ "Integer port;\n"
			+ "Message message;\n"
			+ "String lookupName;\n"
			+ "RemoteObjectRef ror;\n"
			+ "public "+oldClassName+"_stub(){\n"
		    + "}\n"
		    + "public "+oldClassName+"_stub(String lookupName, RemoteObjectRef ror){\n"
		    + "this.lookupName = lookupName;\n"
		    + "this.ror = ror;\n"
		    + "}\n"
		    + "public String getHost() {\n"
		    + "return host;\n"
		    + "}\n"
		    + "public void setHost(String host) {\n"
		    + "this.host = host;\n"
		    + "}\n"
		    + "public Integer getPort() {\n"
		    + "return port;\n"
		    + "}\n"
		    + "public void setPort(Integer port) {\n"
		    + "this.port = port;\n"
		    + "}\n"
		    + "public Message getMessage() {\n"
		    + "return message;\n"
		    + "}\n"
		    + "public void setMessage(Message message) {\n"
		    + "this.message = message;\n"
		    + "}\n"
		    + "	public String getLookupName() {\n"
		    + "return lookupName;\n"
		    + "}\n"
		    + "public void setLookupName(String lookupName) {\n"
		    + "this.lookupName = lookupName;\n"
		    + "}\n"
		    + "public RemoteObjectRef getRor() {\n"
		    + "return ror;\n"
		    + "}\n"
		    + "public void setRor(RemoteObjectRef ror) {\n"
		    + "this.ror = ror;\n"
		    + "}\n";
		
		String methodContent = "";
		
		
		
		/* here we find all the methods in the server program class and make the stub codes for it*/
		Method methods[] =className.getDeclaredMethods();
		String methodNameCorrection = oldClassPackageName+"."+oldClassName+"_stub.";
	
		for(int i = 0; i<methods.length;i++){
			Method currentMethod = methods[i];
			int numberOfarguments = currentMethod.getParameterTypes().length; 
			String methodName = currentMethod.getName();
			String returntype = currentMethod.getReturnType().getName();
			String temp1 = currentMethod.toString().replaceAll(oldClassName, oldClassName+"_stub");
			temp1 = temp1.replaceAll(methodNameCorrection, "");
			
			
			String stringToChange =temp1.substring(temp1.indexOf("(")+1, temp1.indexOf(")"));
			
			
			String parameterString ="";
			String changedString = "";
			if(!stringToChange.equals("")){
			String tokens[] = stringToChange.split(",");
			
			parameterString ="\""; 
			for(int m = 0; m<tokens.length-1;m++){
				parameterString+=tokens[m].toString()+"\",\"";
			}
			parameterString += tokens[tokens.length-1].toString()+"\"";
			
			int counter = 1;
			for(int j = 0; j<tokens.length-1; j++){

				
				changedString += tokens[j] +" "+"a"+(counter++) +",";
				
			
			}
			String apCounter =" "+"a"+counter++;
	
			changedString += tokens[tokens.length - 1]+ apCounter;
			
			}
			temp1 = "@Override\n"+temp1.replaceFirst(stringToChange, changedString) + "{\n";
			
			temp1 = temp1
			+ "this.setHost(this.getRor().getIP_adr());\n"
				
				
					+ "this.setPort(9999);Object retValue = null;\n"
					+ "Class<?> thisClass = null;\n"
					+ "try {\n"
					+ "thisClass = Class.forName(this.getClass().getCanonicalName());\n"
					+ "} catch (ClassNotFoundException e1) {\n"
					+ "e1.printStackTrace();\n"
					+ "}\n"
					+ "Method method = null;\n"
					+ "Object newObj[] = new Object["+numberOfarguments+"];\n";
			String mid = "";
			for(int k = 0 ;k< numberOfarguments;k++){
					mid +="newObj["+k+"] = a"+(k+1)+";\n";
			}
			temp1 =temp1 +mid;
				temp1 = temp1	+ "String[] parameterString ={"+parameterString+"};\n"
						+"Class<?>[] argTypes = new Class["+numberOfarguments+"];\n"
					

				+"for (int i = 0; i < newObj.length; i++) {\n"
					+"if(parameterString[i].equals(\"int\")){\n"
					+	"argTypes[i] = Integer.TYPE;\n"
					+"}\n"
					+"else if(parameterString[i].equals(\"float\")){\n"
						+"argTypes[i] = Float.TYPE;\n"
					+"}\n"
					+"else if(parameterString[i].equals(\"short\")){\n"
					+"	argTypes[i] = Short.TYPE;\n"
					+"}\n"
					+"else if(parameterString[i].equals(\"long\")){\n"
					+"	argTypes[i] = Long.TYPE;\n"
					+"}\n"
					+"else if(parameterString[i].equals(\"double\")){\n"
					+"	argTypes[i] = Double.TYPE;\n"
					+"}\n"
					+"else if(parameterString[i].equals(\"boolean\")){\n"
					+"	argTypes[i] = Boolean.TYPE;\n"
					+"}\n"
					+"else if(parameterString[i].equals(\"char\")){\n"
					+"	argTypes[i] = Character.TYPE;\n"
					+"}\n"
					+"else if(parameterString[i].equals(\"byte\")){\n"
					+"	argTypes[i] = Byte.TYPE;\n"
					+"}\n"
					+"else{\n"
					+"	argTypes[i] = newObj[i].getClass();\n"
					+"}\n"

					+"}\n"
						
						
						
						
					+ "try {\n"
					+ "method = thisClass.getMethod(\""+methodName+"\", argTypes);\n"
							+ "} catch (NoSuchMethodException | SecurityException e) {\n"
							+ "e.printStackTrace();\n"
							+ "}\n"
							+ "Class<?> returnType =method.getReturnType();\n"
							+ "Message localMessage = new Message(MessageType.METHOD, \""+methodName+"\", newObj, argTypes, returnType, null, null,this.getRor(),this.getLookupName());\n"
									+ "this.setMessage(localMessage);\n"
									+ "Communication comm = new Communication(this.getHost(), this.getPort(), this);\n"
									+ "Message returnMessage = comm.connect();\n"
									+ "if(returnMessage.getMessageType() == MessageType.EXCEPTION){\n"
									+ "try {"
									+ "throw new Exception(returnMessage.getException());\n"
									+ "} catch (Exception e) {\n"
									+ "e.printStackTrace();\n"
									+ "}"
									+ "}"
									+ "else if(returnMessage.getMessageType() == MessageType.RETURN) {\n"
									+ "retValue = returnMessage.getReturnValue();\n";
									if(returntype.equals("void")){
										temp1 = temp1+"";
									}
									else{
									temp1 =temp1+ "return ("+returntype+")retValue;\n";
									}
									
									
									temp1+= "}\n";
									if(returntype.equals("int")||returntype.equals("float")||returntype.equals("double")){
									temp1 = temp1+"return 0;\n}\n";
									}
									else if(returntype.equals("char")){
										temp1 = temp1+"return '0';\n}\n";
									}
									else if (returntype.equals("void")){
										temp1 = temp1+"}\n";
									}
									
									else{
										temp1 = temp1+"return null;\n}\n";
										
									}
									methodContent+=temp1;
		}
		methodContent+="\n}\n";
		
		commonContent = commonContent+methodContent;
		
		/* We write the contents of the string in the .java file specified 
		 * which has the same name as the server Class appended with _stub*/ 
		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter(file);
			 BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		     bufferWritter.write(commonContent);
		     bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
		
	}
	
public static void main(String[] args){
	
	System.out.println("Please ensure that you have compiled the file you have provided as argument");
	String classPathName = args[0];
	classPathName = classPathName.trim();
	classPathName.replaceAll("\\\\", "/");

	String className = classPathName.substring(classPathName.lastIndexOf("/")+1); 
	
	
	String Path = classPathName.substring(0,classPathName.lastIndexOf("/"));
	
	
	String ClassNameSlashes = className.replaceAll("\\.", "/");
	
	File file = null;
	try {
		Class<?> givenClass = Class.forName(className);
		
		
		file = new File(Path+"/"+ClassNameSlashes+"_stub.java");
		String compileLocation = Path+"/"+ClassNameSlashes+"_stub.java";
		
		compileLocation = compileLocation.substring(0, compileLocation.lastIndexOf("/"))+"/";
		
		
		rmic rmic = new rmic();
		rmic.appendText(file, givenClass);
		
		//Compile this file (Reference: http://www.rgagnon.com/javadetails/java-0039.html)
		//System.setProperty("java.home", "C:\\Program Files\\Java\\jdk1.7.0_51");// For Windows if JAVA_HOME is not set 
		JavaCompiler jCompiler = ToolProvider.getSystemJavaCompiler();
		
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		
                StandardJavaFileManager fileMngr = jCompiler.getStandardFileManager(null, null, null);
		Boolean b = jCompiler.getTask(null, fileMngr, diagnostics, null, null, fileMngr.getJavaFileObjectsFromFiles(Arrays.asList(file))).call();
		if(b==true){
			System.out.println("Stub created and compiled succesfully"); 
		}
		else{
			System.out.println("Stub Creation/Compilation error");
		}
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
		
}
}
