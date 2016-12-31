import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayAllResources extends JPanel {
	Resource_Management_System system;
	public DisplayAllResources(Resource_Management_System system_obj) {
		system = system_obj;
		Display_Resources();
	}
	void Display_Resources(){
		JLabel lblResources = new JLabel("Resources: ");
		lblResources.setBounds(20, 28, 88, 22);		
		JPanel ReservationPanel=new JPanel();
	
		
		Resource[] resources;
		resources= system.get_resources();	
		int numofresources= system.get_resource_count();
		GridLayout layout = new GridLayout(numofresources+1,8);
		layout.setHgap(10);
		layout.setVgap(10);
		ReservationPanel.setLayout(layout);
		ReservationPanel.add(new JLabel("Resource ID"));
		ReservationPanel.add(new JLabel("Status"));
		ReservationPanel.add(new JLabel("Start Date"));
		ReservationPanel.add(new JLabel("End Date"));
		ReservationPanel.add(new JLabel("Start Time"));
		ReservationPanel.add(new JLabel("End Time"));
		ReservationPanel.add(new JLabel("User Type"));
		ReservationPanel.add(new JLabel("Allowance Time"));
		for(int i=0; i<numofresources;i++)
		{
			JPanel Res_Details = new JPanel();
			JTextField id = new JTextField("Resource: "+resources[i].getID());
			id.setForeground(Color.GRAY);
			id.setColumns(15);
			id.setEnabled(false);
			
			JTextField status = new JTextField(""+resources[i].getResource_Status());
			status.setForeground(Color.GRAY);
			status.setColumns(15);
			status.setEnabled(false);
			
			JTextField sDate = new JTextField("Start Date");
			sDate.setForeground(Color.GRAY);
			sDate.setColumns(15);
			sDate.setEnabled(false);
			String datedetails = resources[i].getStart_date()+"";
			sDate.setText(datedetails);
			
			JTextField eDate = new JTextField("End Date");
			eDate.setForeground(Color.GRAY);
			eDate.setColumns(15);
			eDate.setEnabled(false);
			String datedetails1 = resources[i].getend_date()+"";
			eDate.setText(datedetails1);
			
			JTextField sTime = new JTextField("Start Time");
			sTime.setForeground(Color.GRAY);
			sTime.setColumns(15);
			sTime.setEnabled(false);
			String sTimedetails = resources[i].getStart_Time()+"";
			sTime.setText(sTimedetails);
			
			JTextField eTime = new JTextField("End Time");
			eTime.setForeground(Color.GRAY);
			eTime.setColumns(15);
			eTime.setEnabled(false);
			String eTimedetails = resources[i].getEnd_Time()+"";
			eTime.setText(eTimedetails);
			
			JTextField usertype = new JTextField(""+resources[i].getResource_UserType());
			usertype.setForeground(Color.GRAY);
			usertype.setColumns(15);
			usertype.setEnabled(false);
			
			JTextField AllowanceTime = new JTextField(""+resources[i].getAllowance_time());
			AllowanceTime.setForeground(Color.GRAY);
			AllowanceTime.setColumns(15);
			AllowanceTime.setEnabled(false);
			
			JLabel reservationname =new JLabel("Reservation "+(i+1));
			ReservationPanel.add(id);
			ReservationPanel.add(status);
			ReservationPanel.add(sDate);
			ReservationPanel.add(eDate);
			ReservationPanel.add(sTime);
			ReservationPanel.add(eTime);
			ReservationPanel.add(usertype);
			ReservationPanel.add(AllowanceTime);
		}
		
		this.removeAll();
		add(ReservationPanel);
		
		
	}
}
