package tutorials;

import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.dyuproject.protostuff.Tag;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SerializationObject{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 2697013639751027676L;
	
	@Tag(1)
	private String name;
	
	@Tag(2)
	private int age;
	
	@Tag(3)
	private Date dob;
	
	@JsonIgnore
	@Tag(4)
	private float salary; 
	
	SerializationObject(int age){
		
	}
	
	SerializationObject(){
		
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
