import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Group;

public class DisplayReservationFrame extends JFrame {
	User u;
	JFrame frame=this;
	Resource_Management_System system;
	DisplayReservationFrame(Resource_Management_System system_obj, User x){
		this.setTitle(x.getFirst_Name()+"'s reservations are");
		system = system_obj;
		u = x;
		this.setLocation(340, 90);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	frame.dispose();
		        }
		    }
		});
		Display_Reservations();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	//Display Reservations
	void Display_Reservations(){

		JPanel ReservationPanel=new JPanel();		
		Reservation[] reservations;
		reservations= system.get_reservations_of_user(u);	
		int numofreservations= system.get_reservation_count(u);
		GridLayout layout = new GridLayout(numofreservations+1,4);
		layout.setHgap(10);
		layout.setVgap(10);
		ReservationPanel.setLayout(layout);
		ReservationPanel.add(new JLabel("Reservation"));
		ReservationPanel.add(new JLabel("Resource Booked"));
		ReservationPanel.add(new JLabel("Reservation Date"));
		ReservationPanel.add(new JLabel("Date Booked"));
		ReservationPanel.add(new JLabel("Start Time"));
		ReservationPanel.add(new JLabel("End Time"));
		for(int i=0; i<numofreservations;i++)
		{

			JTextField resourcename = new JTextField("Resource: "+reservations[i].getResource().getID());
			resourcename.setForeground(Color.GRAY);
			resourcename.setColumns(20);
			resourcename.setEnabled(false);
			
			JTextField resDate = new JTextField("Date");
			resDate.setForeground(Color.GRAY);
			resDate.setColumns(15);
			resDate.setMaximumSize(new Dimension(50,50));
			resDate.setEnabled(false);
			String resdatedetails = reservations[i].getDate_of_Reservation()+"";
			resDate.setText(resdatedetails);
			
			
			JTextField Date = new JTextField("Date");
			Date.setForeground(Color.GRAY);
			Date.setColumns(15);
			Date.setMaximumSize(new Dimension(50,50));
			Date.setEnabled(false);
			String datedetails = reservations[i].getStartDate()+"";
			Date.setText(datedetails);
			JTextField sTime = new JTextField("Start Time");
			sTime.setForeground(Color.GRAY);
			sTime.setColumns(15);
			sTime.setEnabled(false);
			String sTimedetails = reservations[i].getStartTime()+"";
			sTime.setText(sTimedetails);
			JTextField eTime = new JTextField("Start Time");
			eTime.setForeground(Color.GRAY);
			eTime.setColumns(15);
			eTime.setEnabled(false);
			String eTimedetails = reservations[i].getEndTime()+"";
			eTime.setText(eTimedetails);
			JLabel reservationname =new JLabel("Reservation "+(i+1));
			ReservationPanel.add(reservationname);
			ReservationPanel.add(resourcename);
			ReservationPanel.add(resDate);
			ReservationPanel.add(Date);
			ReservationPanel.add(sTime);
			ReservationPanel.add(eTime);
		}

		frame.add(new JScrollPane(ReservationPanel));
	}
}
