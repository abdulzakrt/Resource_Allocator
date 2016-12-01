
public class Main {


	public static void main(String[] args) {
		
		Resource_Management_System system= new Resource_Management_System();
		system.openSystem();
		system.Display_user();
		Admin a1=new Admin();
		a1.add_user(system);
		a1.Allocate_Resources(system);
		system.Display_resource();
		system.closeSystem();
	}
	
	
}
