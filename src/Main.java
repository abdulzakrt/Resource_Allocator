import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {


	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Resource_Management_System system= new Resource_Management_System();
		system=system.openSystem();
		GUI g= new GUI(system);
		g.login();
		//system.Display_admins();
		system.Display_user();
		system.Display_resource();
        system.Display_reservation();
		//Hello
        
		
		
	}
	
	
}
