package tutorials;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Test {
	public static void main(String[] args) {
		
		
		SerializationObject obj = new SerializationObject(25);
		obj.setAge(25);
		obj.setName("sumit");
		obj.setDob(new Date());
		obj.setSalary(10f);
		
		/**
		 * Protostuff
		 */
		/*byte data[] = protostuffSerialize(obj, SerializationObject.class);
		SerializationObject readObj = (SerializationObject)protostuffDeSerialize(data, SerializationObject.class);*/
		
		/**
		 * Gson
		 */
		String str = gsonSerialize(obj);
		SerializationObject readObj = (SerializationObject)gsonDeSerialize(str);
		
		/**
		 * jackson
		 */
		/*String str = jacksonSerialize(obj);
		SerializationObject readObj = (SerializationObject)jacksonDeSerialize(str);*/
		
		System.out.println("Name : "+readObj.getName());
		System.out.println("Age : "+readObj.getAge());
		System.out.println("Dob : "+readObj.getDob());
		System.out.println("Salary :"+readObj.getSalary());
		
	}
	
	private static String gsonSerialize(Object obj){
		GsonBuilder gb = new GsonBuilder();
		Gson gson = gb.create();
		return gson.toJson(obj);
	}
	
	private static Object gsonDeSerialize(String str){
		Gson gson = new Gson();
		Object obj = gson.fromJson(str, SerializationObject.class);
		return obj;
	}

	private static String jacksonSerialize(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		String str = null;
		try {
			str = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	private static Object jacksonDeSerialize(String str){
		Object obj = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			obj = mapper.readValue(str, SerializationObject.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	private static Object  protostuffDeSerialize(byte[] data,Class clazz){
		Schema schema = RuntimeSchema.getSchema(clazz);
		Object obj= null;
		try {
			obj = clazz.newInstance();
			ProtostuffIOUtil.mergeFrom(data, obj, schema);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	private static byte[] protostuffSerialize(Object obj,Class clazz){
		byte[] data = null;
		Schema schema = RuntimeSchema.getSchema(clazz);
		final LinkedBuffer linkedBuffer = LinkedBuffer.allocate(4096);
		try{
			data = ProtostuffIOUtil.toByteArray(obj, schema, linkedBuffer);
			try {
				FileOutputStream out = new FileOutputStream("/home/sumit/poc_eclipse/resources/protostuffSerialize.txt");
				ObjectOutputStream write = new ObjectOutputStream(out);
				write.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}finally{
			linkedBuffer.clear();
		}
		return data;
	}
	
	
	
	private static void deserialize() {
		try {
			FileInputStream in = new FileInputStream("/home/sumit/poc_eclipse/resources/serialization.txt");
			ObjectInputStream read = new ObjectInputStream(in);
			SerializationObject readObj = (SerializationObject)read.readObject();
			read.close();
			in.close();
			System.out.println("Name : "+readObj.getName());
			System.out.println("Age : "+readObj.getAge());
			System.out.println("Dob : "+readObj.getDob());
			System.out.println("Salary :"+readObj.getSalary());
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getStackTrace());
		}
	}

	private static void serialize() {
		SerializationObject obj = new SerializationObject(25);
		obj.setAge(25);
		obj.setName("sumit");
		obj.setDob(new Date());
		obj.setSalary(10f);
		try {
			FileOutputStream out = new FileOutputStream("/home/sumit/poc_eclipse/resources/serialization.txt");
			ObjectOutputStream write = new ObjectOutputStream(out);
			write.writeObject(obj);
			write.close();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}
}
