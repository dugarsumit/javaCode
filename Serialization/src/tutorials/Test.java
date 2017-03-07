package tutorials;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.dyuproject.protostuff.ProtostuffIOUtil;


public class Test {
	public static void main(String[] args) {
		SerializationDeSerialization obj = new SerializationDeSerialization();
		obj.setAge(25);
		obj.setName("sumit");
		obj.setDob(new Date());
		obj.setSalary(10f);
		byte data[] = protostuffSerialize(obj, SerializationDeSerialization.class);
		SerializationDeSerialization readObj = (SerializationDeSerialization)protostuffDeSerialize(data, SerializationDeSerialization.class);
		System.out.println("Name : "+readObj.getName());
		System.out.println("Age : "+readObj.getAge());
		System.out.println("Dob : "+readObj.getDob());
		System.out.println("Salary :"+readObj.getSalary());
	}

	private static Object  protostuffDeSerialize(byte[] data,Class clazz){
		Schema schema = RuntimeSchema.getSchema(clazz);
		SerializationDeSerialization obj = new SerializationDeSerialization();
		ProtostuffIOUtil.mergeFrom(data, obj, schema);
		return obj;
	}
	
	private static byte[] protostuffSerialize(Object obj,Class clazz){
		byte[] data = null;
		Schema schema = RuntimeSchema.getSchema(clazz);
		final LinkedBuffer linkedBuffer = LinkedBuffer.allocate(4096);
		try{
			data = ProtostuffIOUtil.toByteArray(obj, schema, linkedBuffer);
		}finally{
			linkedBuffer.clear();
		}
		return data;
	}
	
	
	
	private static void deserialize() {
		try {
			FileInputStream in = new FileInputStream("/home/sumit/poc_eclipse/resources/serialization.txt");
			ObjectInputStream read = new ObjectInputStream(in);
			SerializationDeSerialization readObj = (SerializationDeSerialization)read.readObject();
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
		SerializationDeSerialization obj = new SerializationDeSerialization();
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
