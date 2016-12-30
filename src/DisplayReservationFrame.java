import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Group;

public class DisplayReservationFrame extends JFrame {
	User u;
	JFrame frame=this;
	Resource_Management_System system;
	DisplayReservationFrame(Resource_Management_System system_obj, User x){
		system = system_obj;
		u = x;
		this.setLocation(340, 90);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		//JPanel panel= new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	frame.dispose();
		        	//system.closeSystem();
		            //System.exit(0);
		        }
		    }
		});
		Display_Reservations();
		frame.pack();
		frame.setVisible(true);
	}
	//Display Reservations
	void Display_Reservations(){
		JLabel lblReservations = new JLabel("Reservations: ");
		lblReservations.setBounds(20, 28, 88, 22);		
		JPanel ReservationPanel=new JPanel();
	
		
		Reservation[] reservations;
		reservations= system.get_reservations_of_user(u);	
		int numofreservations= system.get_reservation_count(u);
		GridLayout layout = new GridLayout(numofreservations,4);
		layout.setHgap(10);
		layout.setVgap(10);
		ReservationPanel.setLayout(layout);

		for(int i=0; i<numofreservations;i++)
		{
			JPanel Res_Details = new JPanel();
			JTextField resourcename = new JTextField("Resource: "+reservations[i].getResource().getID());
			resourcename.setForeground(Color.GRAY);
			resourcename.setColumns(20);
			resourcename.setEnabled(false);
			
			JTextField Date = new JTextField("Date");
			Date.setForeground(Color.GRAY);
			Date.setColumns(20);
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
			ReservationPanel.add(Date);
			ReservationPanel.add(sTime);
			ReservationPanel.add(eTime);
		}

		frame.add(ReservationPanel);
	}
}
