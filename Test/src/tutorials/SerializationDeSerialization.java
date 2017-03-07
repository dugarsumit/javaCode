package tutorials;

import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import com.dyuproject.protostuff.Tag;

public class SerializationDeSerialization implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2697013639751027676L;
	
	@Tag(1)
	private String name;
	
	@Tag(2)
	private int age;
	
	@Tag(3)
	private Date dob;
	
	@Tag(4)
	private float salary; 
	
	SerializationDeSerialization(int age){
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	private void writeObject(ObjectOutputStream oos) throws NotSerializableException{
		System.out.println("I am not going to serialize!");
		throw new NotSerializableException();
	}
	
}
