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

public class userMenu extends JFrame {
	JFrame frame=this;
	JFrame reserve_frame;
	JFrame display_frame;
	JFrame cancel_frame;
	/**
	 * Create the panel.
	 */
	public userMenu(Resource_Management_System system, User user) {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	system.closeSystem();
		            System.exit(0);
		        }
		    }
		});		

		GridLayout layout= new GridLayout(5,1);		
		setLayout(layout);
		layout.setVgap(10);		
		layout.setHgap(10);	
				
				JButton btnMakeAReservation = new JButton("Make a Reservation");

				add(btnMakeAReservation);
				
				
				btnMakeAReservation.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
							 new userPanel(system, user);

					}
					
					
				});
				
				
				JButton btnDisplayReservation = new JButton("Display Reservation");
				add(btnDisplayReservation);
				btnDisplayReservation.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFrame f= new DisplayReservationFrame(system,user);
						
					}
					
				});
				
				
				
				JButton btnCancelReservation = new JButton("Cancel Reservation");
				add(btnCancelReservation);
				btnCancelReservation.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
							new CancelReservationPanel(system, user);	

					}
					
					
				});
				
				JButton btndisplayresources = new JButton("Display Resources");
				add(btndisplayresources);
				btndisplayresources.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFrame frame= new JFrame(user.getFirst_Name()+"'s available resources");
						frame.add(new DisplayAllResources(user,system));
						frame.pack();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					}
					
					
				});	
				
				JButton btnLogout = new JButton("Logout");
				add(btnLogout);
				btnLogout.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						 if (JOptionPane.showConfirmDialog(frame, 
						            "Are you sure you want to quit?", "Logout ", 
						            JOptionPane.YES_NO_OPTION,
						            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
						        	system.closeSystem();
						            System.exit(0);
						        }
					}
					
					
				});
				this.setVisible(true);
				this.setSize(200,200);
				this.setResizable(false);
				
				
				

	}

}
