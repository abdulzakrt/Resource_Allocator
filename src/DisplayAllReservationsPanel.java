import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayAllReservationsPanel extends JPanel {
	
	Resource_Management_System system;
	
	DisplayAllReservationsPanel(Resource_Management_System system_obj){
		system = system_obj;
		Display_Reservations();
	}
	//Display Reservations
	void Display_Reservations(){
		JLabel lblReservations = new JLabel("Reservations: ");
		lblReservations.setBounds(20, 28, 88, 22);		
		JPanel ReservationPanel=new JPanel();
	
		
		Reservation[] reservations;
		reservations= system.get_reservations();	
		int numofreservations= system.get_reservation_count();
		GridLayout layout = new GridLayout(numofreservations+1,6);
		layout.setHgap(10);
		layout.setVgap(10);
		ReservationPanel.setLayout(layout);
		ReservationPanel.add(new JLabel("Reservation"));
		ReservationPanel.add(new JLabel("User Booked"));
		ReservationPanel.add(new JLabel("Resource Booked"));
		ReservationPanel.add(new JLabel("Date Booked"));
		ReservationPanel.add(new JLabel("Start Time"));
		ReservationPanel.add(new JLabel("End Time"));
		for(int i=0; i<numofreservations;i++)
		{
			JPanel Res_Details = new JPanel();
			JTextField resourcename = new JTextField("Resource: "+reservations[i].getResource().getID());
			resourcename.setForeground(Color.GRAY);
			resourcename.setColumns(15);
			resourcename.setEnabled(false);
			JTextField User = new JTextField(""+reservations[i].get_user_ID());
			User.setForeground(Color.GRAY);
			User.setColumns(15);
			User.setEnabled(false);
			JTextField Date = new JTextField("Date");
			Date.setForeground(Color.GRAY);
			Date.setColumns(15);
			Date.setEnabled(false);
			String datedetails = reservations[i].getStartDate()+"";
			Date.setText(datedetails);
			JTextField sTime = new JTextField("Start Time");
			sTime.setForeground(Color.GRAY);
			sTime.setColumns(15);
			sTime.setEnabled(false);
			String sTimedetails = reservations[i].getStartTime()+"";
			sTime.setText(sTimedetails);
			JTextField eTime = new JTextField("End Time");
			eTime.setForeground(Color.GRAY);
			eTime.setColumns(15);
			eTime.setEnabled(false);
			String eTimedetails = reservations[i].getEndTime()+"";
			eTime.setText(eTimedetails);
			JLabel reservationname =new JLabel("Reservation "+(i+1));
			ReservationPanel.add(reservationname);
			ReservationPanel.add(User);
			ReservationPanel.add(resourcename);
			ReservationPanel.add(Date);
			ReservationPanel.add(sTime);
			ReservationPanel.add(eTime);
		}

		add(ReservationPanel);
	}
}
