package Server; 
import generics.Communication; 
import generics.FinalCompilerTestInterface; 
import generics.Message;
import generics.MessageType;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import registry.RemoteException;
import registry.RemoteObjectRef;
public class FinalCompilerTest_stub implements generics.FinalCompilerTestInterface, Serializable{
private static final long serialVersionUID = 1L;
String host ;
Integer port;
Message message;
String lookupName;
RemoteObjectRef ror;
public FinalCompilerTest_stub(){
}
public FinalCompilerTest_stub(String lookupName, RemoteObjectRef ror){
this.lookupName = lookupName;
this.ror = ror;
}
public String getHost() {
return host;
}
public void setHost(String host) {
this.host = host;
}
public Integer getPort() {
return port;
}
public void setPort(Integer port) {
this.port = port;
}
public Message getMessage() {
return message;
}
public void setMessage(Message message) {
this.message = message;
}
	public String getLookupName() {
return lookupName;
}
public void setLookupName(String lookupName) {
this.lookupName = lookupName;
}
public RemoteObjectRef getRor() {
return ror;
}
public void setRor(RemoteObjectRef ror) {
this.ror = ror;
}
@Override
public java.util.ArrayList arrayWorld(java.lang.Integer a1){
try {this.setHost(InetAddress.getLocalHost().getHostAddress());
} catch (UnknownHostException e2) {
e2.printStackTrace();
}
this.setPort(9999);Object retValue = null;
Class<?> thisClass = null;
try {
thisClass = Class.forName(this.getClass().getCanonicalName());
} catch (ClassNotFoundException e1) {
e1.printStackTrace();
}
Method method = null;
Object newObj[] = new Object[1];
newObj[0] = a1;
String[] parameterString ={"java.lang.Integer"};
Class<?>[] argTypes = new Class[1];
for (int i = 0; i < newObj.length; i++) {
if(parameterString[i].equals("int")){
argTypes[i] = Integer.TYPE;
}
else if(parameterString[i].equals("float")){
argTypes[i] = Float.TYPE;
}
else if(parameterString[i].equals("short")){
	argTypes[i] = Short.TYPE;
}
else if(parameterString[i].equals("long")){
	argTypes[i] = Long.TYPE;
}
else if(parameterString[i].equals("double")){
	argTypes[i] = Double.TYPE;
}
else if(parameterString[i].equals("boolean")){
	argTypes[i] = Boolean.TYPE;
}
else if(parameterString[i].equals("char")){
	argTypes[i] = Character.TYPE;
}
else if(parameterString[i].equals("byte")){
	argTypes[i] = Byte.TYPE;
}
else{
	argTypes[i] = newObj[i].getClass();
}
}
try {
method = thisClass.getMethod("arrayWorld", argTypes);
} catch (NoSuchMethodException | SecurityException e) {
e.printStackTrace();
}
Class<?> returnType =method.getReturnType();
Message localMessage = new Message(MessageType.METHOD, "arrayWorld", newObj, argTypes, returnType, null, null,this.getRor(),this.getLookupName());
this.setMessage(localMessage);
Communication comm = new Communication(this.getHost(), this.getPort(), this);
Message returnMessage = comm.connect();
if(returnMessage.getMessageType() == MessageType.EXCEPTION){
try {throw new Exception(returnMessage.getException());
} catch (Exception e) {
e.printStackTrace();
}}else if(returnMessage.getMessageType() == MessageType.RETURN) {
retValue = returnMessage.getReturnValue();
return (java.util.ArrayList)retValue;
}
return null;
}
@Override
public java.lang.String hello(){
try {this.setHost(InetAddress.getLocalHost().getHostAddress());
} catch (UnknownHostException e2) {
e2.printStackTrace();
}
this.setPort(9999);Object retValue = null;
Class<?> thisClass = null;
try {
thisClass = Class.forName(this.getClass().getCanonicalName());
} catch (ClassNotFoundException e1) {
e1.printStackTrace();
}
Method method = null;
Object newObj[] = new Object[0];
String[] parameterString ={};
Class<?>[] argTypes = new Class[0];
for (int i = 0; i < newObj.length; i++) {
if(parameterString[i].equals("int")){
argTypes[i] = Integer.TYPE;
}
else if(parameterString[i].equals("float")){
argTypes[i] = Float.TYPE;
}
else if(parameterString[i].equals("short")){
	argTypes[i] = Short.TYPE;
}
else if(parameterString[i].equals("long")){
	argTypes[i] = Long.TYPE;
}
else if(parameterString[i].equals("double")){
	argTypes[i] = Double.TYPE;
}
else if(parameterString[i].equals("boolean")){
	argTypes[i] = Boolean.TYPE;
}
else if(parameterString[i].equals("char")){
	argTypes[i] = Character.TYPE;
}
else if(parameterString[i].equals("byte")){
	argTypes[i] = Byte.TYPE;
}
else{
	argTypes[i] = newObj[i].getClass();
}
}
try {
method = thisClass.getMethod("hello", argTypes);
} catch (NoSuchMethodException | SecurityException e) {
e.printStackTrace();
}
Class<?> returnType =method.getReturnType();
Message localMessage = new Message(MessageType.METHOD, "hello", newObj, argTypes, returnType, null, null,this.getRor(),this.getLookupName());
this.setMessage(localMessage);
Communication comm = new Communication(this.getHost(), this.getPort(), this);
Message returnMessage = comm.connect();
if(returnMessage.getMessageType() == MessageType.EXCEPTION){
try {throw new Exception(returnMessage.getException());
} catch (Exception e) {
e.printStackTrace();
}}else if(returnMessage.getMessageType() == MessageType.RETURN) {
retValue = returnMessage.getReturnValue();
return (java.lang.String)retValue;
}
return null;
}

}
