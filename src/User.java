import java.time.LocalDate;

enum userType {Prof,Staff,Student}

public class User extends Member{
	private userType user_type;
	private String First_Name,Last_Name;
	private int age;
	private LocalDate Date_of_birth;
	public User(String F,String L,int age,userType user,int ID,int pass) {
		this.setLog_in_ID(ID);
		this.setPassword(pass);
		First_Name=F;
		Last_Name=L;
		this.age=age;
		user_type= user;
	}
	public void Reserve(){
		
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public userType getUser_type() {
		return user_type;
	}
	public void setUser_type(userType user_type) {
		this.user_type = user_type;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDate getDate_of_birth() {
		return Date_of_birth;
	}
	public void setDate_of_birth(LocalDate date_of_birth) {
		Date_of_birth = date_of_birth;
	}
	public String toString(){
		
		
		return (First_Name +" " + Last_Name + " " + age + " " +user_type);
		
		
	}
}
