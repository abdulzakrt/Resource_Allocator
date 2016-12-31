import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CancelReservations extends JPanel {
	Resource_Management_System system;
	CancelReservationPanel users_reservations;
	User[] users;
	String[] menuitems;
	int users_with_res;
	int user_ID_to_cancel_res;
	JButton submit;
	JPanel panel;
	JComboBox menu;
	CancelReservations(Resource_Management_System system)
	{
		panel = this;
		this.system=system;
		users_with_res=0;//counter
		for(int i=0; i<system.get_user_count(); i++)
		{
			if(system.get_reservations_of_user((system.get_users())[i]).length!=0)
				users_with_res++;
		}
		if(users_with_res>0){
			User[] u = new User[users_with_res];
			users = u;
			int j=0;
			for(int i=0; i<system.get_user_count(); i++)
			{
				if(system.get_reservations_of_user((system.get_users())[i]).length!=0)
					{users[j]= system.get_users()[i];j++;}
			}
		}
		String[] menuitems_array=new String[users_with_res];
		menuitems = menuitems_array;
		for(int i=0; i<users_with_res;i++)
		{
			menuitems[i]=""+users[i].getFirst_Name()+" "+users[i].getLast_Name()+" "+ users[i].getLog_in_ID();
		}
	
		JComboBox menun = new JComboBox(menuitems);
		menu = menun;
		menu.setSelectedIndex(-1);
		JButton check_user_reservations = new JButton("Check Reservations");
		submit = check_user_reservations;
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(menu.getSelectedIndex()!=-1){
					user_ID_to_cancel_res = Integer.parseInt(((String)menu.getSelectedItem()).substring(((String)menu.getSelectedItem()).lastIndexOf(" ")+1));
					for(int i=0; i<users_with_res;i++)
					{
						if(users[i].getLog_in_ID()==user_ID_to_cancel_res)
						{
							//CancelReservationPanel x;
							 new CancelReservationPanel(system, users[i]);
							 //users_reservations=x;
						}
					}
					
					}
				users_with_res=0;//counter
				for(int i=0; i<system.get_user_count(); i++)
				{
					if(system.get_reservations_of_user((system.get_users())[i]).length!=0)
						users_with_res++;
				}
				if(users_with_res>0){
					User[] u = new User[users_with_res];
					users = u;
					int j=0;
					for(int i=0; i<system.get_user_count(); i++)
					{
						if(system.get_reservations_of_user((system.get_users())[i]).length!=0)
							{users[j]= system.get_users()[i];j++;}
					}
				}
				String[] menuitems_array=new String[users_with_res];
				menuitems = menuitems_array;
				for(int i=0; i<users_with_res;i++)
				{
					menuitems[i]=""+users[i].getFirst_Name()+" "+users[i].getLast_Name()+" "+ users[i].getLog_in_ID();
				}
			
				JComboBox menun = new JComboBox(menuitems);
				menu = menun;
				//menu.setSelectedIndex(-1);
				
			}
			
		});
		
		this.add(menu);
		this.add(submit);
		
	}
}
