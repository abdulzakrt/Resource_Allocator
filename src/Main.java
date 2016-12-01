
public class Main {


	public static void main(String[] args) {
		
		Resource_Management_System system= new Resource_Management_System();
		system.openSystem();
		system.Display_admins();
		system.Display_user();
		system.login(system);
		system.Display_resource();
		system.closeSystem();
	}
	
	
}
