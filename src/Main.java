

public class Main {


	public static void main(String[] args) {
		Resource_Management_System system= new Resource_Management_System();
		system=system.openSystem();
		system.Display_admins();
		system.Display_user();
		system.Display_resource();
		system.login(system);
		system.closeSystem();
	}
	
	
}
