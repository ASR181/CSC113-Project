
import java.io.Serializable;

public class Guest implements Serializable{

// Attributes
	private String name;
	private int age;
	
// Constructor
	public Guest(String name, int age) {
		
		setName(name);
		setAge(age);

	}

// Setters and Getters
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

}