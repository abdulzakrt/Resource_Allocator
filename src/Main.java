
public class Main {


	public static void main(String[] args) {
		Resource_Management_System System=new Resource_Management_System();
		Resource temp=new Resource();
		temp.setID(1);
		
		System.add_resource_to_array(temp);
		temp=new Resource();
		temp.setID(2);
		System.add_resource_to_array(temp);
		System.Display_resource();
	}

}
