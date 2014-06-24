package Server; 
import generics.Communication; 
import generics.serverArithmeticInterface; 
import generics.Message;
import generics.MessageType;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import registry.RemoteException;
import registry.RemoteObjectRef;
public class serverArithmetic_stub implements generics.serverArithmeticInterface, Serializable{
private static final long serialVersionUID = 1L;
String host ;
Integer port;
Message message;
String lookupName;
RemoteObjectRef ror;
public serverArithmetic_stub(){
}
public serverArithmetic_stub(String lookupName, RemoteObjectRef ror){
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
public int divide(int a1,int a2){
this.setHost(this.getRor().getIP_adr());
this.setPort(9999);Object retValue = null;
Class<?> thisClass = null;
try {
thisClass = Class.forName(this.getClass().getCanonicalName());
} catch (ClassNotFoundException e1) {
e1.printStackTrace();
}
Method method = null;
Object newObj[] = new Object[2];
newObj[0] = a1;
newObj[1] = a2;
String[] parameterString ={"int","int"};
Class<?>[] argTypes = new Class[2];
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
method = thisClass.getMethod("divide", argTypes);
} catch (NoSuchMethodException | SecurityException e) {
e.printStackTrace();
}
Class<?> returnType =method.getReturnType();
Message localMessage = new Message(MessageType.METHOD, "divide", newObj, argTypes, returnType, null, null,this.getRor(),this.getLookupName());
this.setMessage(localMessage);
Communication comm = new Communication(this.getHost(), this.getPort(), this);
Message returnMessage = comm.connect();
if(returnMessage.getMessageType() == MessageType.EXCEPTION){
try {throw new Exception(returnMessage.getException());
} catch (Exception e) {
e.printStackTrace();
}}else if(returnMessage.getMessageType() == MessageType.RETURN) {
retValue = returnMessage.getReturnValue();
return (int)retValue;
}
return 0;
}
@Override
public int subtract(int a1,int a2){
this.setHost(this.getRor().getIP_adr());
this.setPort(9999);Object retValue = null;
Class<?> thisClass = null;
try {
thisClass = Class.forName(this.getClass().getCanonicalName());
} catch (ClassNotFoundException e1) {
e1.printStackTrace();
}
Method method = null;
Object newObj[] = new Object[2];
newObj[0] = a1;
newObj[1] = a2;
String[] parameterString ={"int","int"};
Class<?>[] argTypes = new Class[2];
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
method = thisClass.getMethod("subtract", argTypes);
} catch (NoSuchMethodException | SecurityException e) {
e.printStackTrace();
}
Class<?> returnType =method.getReturnType();
Message localMessage = new Message(MessageType.METHOD, "subtract", newObj, argTypes, returnType, null, null,this.getRor(),this.getLookupName());
this.setMessage(localMessage);
Communication comm = new Communication(this.getHost(), this.getPort(), this);
Message returnMessage = comm.connect();
if(returnMessage.getMessageType() == MessageType.EXCEPTION){
try {throw new Exception(returnMessage.getException());
} catch (Exception e) {
e.printStackTrace();
}}else if(returnMessage.getMessageType() == MessageType.RETURN) {
retValue = returnMessage.getReturnValue();
return (int)retValue;
}
return 0;
}
@Override
public int multiply(int a1,int a2){
this.setHost(this.getRor().getIP_adr());
this.setPort(9999);Object retValue = null;
Class<?> thisClass = null;
try {
thisClass = Class.forName(this.getClass().getCanonicalName());
} catch (ClassNotFoundException e1) {
e1.printStackTrace();
}
Method method = null;
Object newObj[] = new Object[2];
newObj[0] = a1;
newObj[1] = a2;
String[] parameterString ={"int","int"};
Class<?>[] argTypes = new Class[2];
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
method = thisClass.getMethod("multiply", argTypes);
} catch (NoSuchMethodException | SecurityException e) {
e.printStackTrace();
}
Class<?> returnType =method.getReturnType();
Message localMessage = new Message(MessageType.METHOD, "multiply", newObj, argTypes, returnType, null, null,this.getRor(),this.getLookupName());
this.setMessage(localMessage);
Communication comm = new Communication(this.getHost(), this.getPort(), this);
Message returnMessage = comm.connect();
if(returnMessage.getMessageType() == MessageType.EXCEPTION){
try {throw new Exception(returnMessage.getException());
} catch (Exception e) {
e.printStackTrace();
}}else if(returnMessage.getMessageType() == MessageType.RETURN) {
retValue = returnMessage.getReturnValue();
return (int)retValue;
}
return 0;
}
@Override
public int add(int a1,int a2){
this.setHost(this.getRor().getIP_adr());
this.setPort(9999);Object retValue = null;
Class<?> thisClass = null;
try {
thisClass = Class.forName(this.getClass().getCanonicalName());
} catch (ClassNotFoundException e1) {
e1.printStackTrace();
}
Method method = null;
Object newObj[] = new Object[2];
newObj[0] = a1;
newObj[1] = a2;
String[] parameterString ={"int","int"};
Class<?>[] argTypes = new Class[2];
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
method = thisClass.getMethod("add", argTypes);
} catch (NoSuchMethodException | SecurityException e) {
e.printStackTrace();
}
Class<?> returnType =method.getReturnType();
Message localMessage = new Message(MessageType.METHOD, "add", newObj, argTypes, returnType, null, null,this.getRor(),this.getLookupName());
this.setMessage(localMessage);
Communication comm = new Communication(this.getHost(), this.getPort(), this);
Message returnMessage = comm.connect();
if(returnMessage.getMessageType() == MessageType.EXCEPTION){
try {throw new Exception(returnMessage.getException());
} catch (Exception e) {
e.printStackTrace();
}}else if(returnMessage.getMessageType() == MessageType.RETURN) {
retValue = returnMessage.getReturnValue();
return (int)retValue;
}
return 0;
}

}
