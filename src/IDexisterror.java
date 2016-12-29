import javax.swing.JOptionPane;

public class IDexisterror extends Exception {
	
	IDexisterror(){
		JOptionPane error = new JOptionPane("ID already exists");
		error.createDialog("Fail").setVisible(true);
	}
	
	
}
