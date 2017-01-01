import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

/**
This panel will display a menu for a user in which he can 
	1_ Make a reservation.
	2_Display reservations.
	3_Cancel reservation.
	4_Display resources.
	5_logout
**/
public class userMenu extends JFrame {
	JFrame frame=this;
	JFrame reserve_frame;
	JFrame display_frame;
	JFrame cancel_frame;
	/**
	 * Create the frame.
	 */
	public userMenu(Resource_Management_System system, User user) {
		//To make sure the user really want to logout
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	//To save before exiting the application
		        	system.closeSystem();
		            System.exit(0);
		        }
		    }
		});	//end of addWindowListener	

		GridLayout layout= new GridLayout(5,1);		//the layout of this frame is gridLayout with 5 rows and 1 column
		setLayout(layout);
		layout.setVgap(10);							//Set a vertical gap between the elements
		layout.setHgap(10);							//Set a horizontal gap between the elements
		JButton btnMakeAReservation = new JButton("Make a Reservation");
		add(btnMakeAReservation); //add the button to this "userMenu" frame
		btnMakeAReservation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					//To make an object of class userPanel
					 new ReservationFrame(system, user);
			}	
		});//end of addActionListener of make a reservation button
				
		JButton btnDisplayReservation = new JButton("Display Reservations");
		add(btnDisplayReservation); //add the button to this "userMenu" frame
		btnDisplayReservation.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//To make an object of class DisplayReservationFrame
				JFrame f= new DisplayReservationFrame(system,user);
			}
		});//end of addActionListener of display reservation button
					
		JButton btnCancelReservation = new JButton("Cancel Reservations");
		add(btnCancelReservation); //add the button to this "userMenu" frame
		btnCancelReservation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				    //To make an object of class CancelReservationPanel
					new CancelReservationFrame(system, user);	
			}
		});//end of addActionListener of cancel reservation button
				
		JButton btndisplayresources = new JButton("Display Resources");
		add(btndisplayresources); //add the button to this "userMenu" frame
		btndisplayresources.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame= new JFrame(user.getFirst_Name()+"'s available resources");
				//To make an object of class DisplayAllResources
				frame.add(new DisplayAllResources(user,system));
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});//end of addActionListener of display resources button
				
		JButton btnLogout = new JButton("Logout");
		add(btnLogout); //add the button to this "userMenu" frame
		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 if (JOptionPane.showConfirmDialog(frame, 
				            "Are you sure you want to quit?", "Logout ", 
				            JOptionPane.YES_NO_OPTION,
				            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					 		//when logout save the application and exit the program
				        	system.closeSystem();
				            System.exit(0);
				        }
			}
		});//end of addActionListener of logout button
		this.setVisible(true);     //set this frame as visible
		this.setSize(200,200);     //set the size of this frame
		this.setResizable(false);  //the size of the frame is not changeable
	}
}
