
public class Main {


	public static void main(String[] args) {
		Resource_Management_System system= new Resource_Management_System();
		Admin a1=new Admin();
		a1.Allocate_Resources(system);
		system.Display_resource();
	}
	
	
}
