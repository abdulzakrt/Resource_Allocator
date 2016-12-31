import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayUsers extends JPanel {
Resource_Management_System system;
	
	DisplayUsers(Resource_Management_System system_obj){
		system = system_obj;
		Display_Users();
	}
	//Display Reservations
	void Display_Users(){
		JLabel lblReservations = new JLabel("Users: ");
		lblReservations.setBounds(20, 28, 88, 22);		
		JPanel UserPanel=new JPanel();
	
		
		User[] users;
		users= system.get_users();	
		int numofusers= system.get_user_count();
		GridLayout layout = new GridLayout(numofusers+1,5);
		layout.setHgap(10);
		layout.setVgap(10);
		UserPanel.setLayout(layout);
		UserPanel.add(new JLabel("ID"));
		UserPanel.add(new JLabel("First Name"));
		UserPanel.add(new JLabel("Last Name"));
		UserPanel.add(new JLabel("Age"));
		UserPanel.add(new JLabel("User Type"));
		
		for(int i=0; i<numofusers;i++)
		{
			
			JTextField UserID = new JTextField(""+users[i].getLog_in_ID());
			UserID.setForeground(Color.GRAY);
			UserID.setColumns(15);
			UserID.setEnabled(false);
			
			JTextField first = new JTextField(""+users[i].getFirst_Name());
			first.setForeground(Color.GRAY);
			first.setColumns(15);
			first.setEnabled(false);
			
			JTextField last = new JTextField(""+users[i].getLast_Name());
			last.setForeground(Color.GRAY);
			last.setColumns(15);
			last.setEnabled(false);
			
			JTextField age = new JTextField(""+users[i].getAge());
			age.setForeground(Color.GRAY);
			age.setColumns(15);
			age.setEnabled(false);
			
			JTextField type = new JTextField(""+users[i].getUser_type());
			type.setForeground(Color.GRAY);
			type.setColumns(15);
			type.setEnabled(false);
			
			UserPanel.add(UserID);
			UserPanel.add(first);
			UserPanel.add(last);
			UserPanel.add(age);
			UserPanel.add(type);
		}
		this.removeAll();
		add(UserPanel);
	}
}
