import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class userMenu extends JPanel {
	JFrame frame;
	/**
	 * Create the panel.
	 */
	public userMenu(Resource_Management_System system, User user) {
		frame= new JFrame();
		frame.setLocation(340, 90);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		//JPanel panel= new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		
		JButton btnMakeAReservation = new JButton("Make a Reservation");
		GridBagConstraints gbc_btnMakeAReservation = new GridBagConstraints();
		gbc_btnMakeAReservation.insets = new Insets(0, 0, 5, 5);
		gbc_btnMakeAReservation.gridx = 3;
		gbc_btnMakeAReservation.gridy = 2;
		add(btnMakeAReservation, gbc_btnMakeAReservation);
		
		
		btnMakeAReservation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					userPanel panel = new userPanel(system, user);
					
					frame.add(panel);
					frame.pack();
					frame.setSize(600, 600);
					frame.setVisible(true);
			}
			
			
		});

		
		JButton btnDisplayReservation = new JButton("Display Reservation");
		GridBagConstraints gbc_btnDisplayReservation = new GridBagConstraints();
		gbc_btnDisplayReservation.insets = new Insets(0, 0, 5, 5);
		gbc_btnDisplayReservation.gridx = 3;
		gbc_btnDisplayReservation.gridy = 4;
		add(btnDisplayReservation, gbc_btnDisplayReservation);
		
		JButton btnCancelReservation = new JButton("Cancel Reservation");
		GridBagConstraints gbc_btnCancelReservation = new GridBagConstraints();
		gbc_btnCancelReservation.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelReservation.gridx = 3;
		gbc_btnCancelReservation.gridy = 6;
		add(btnCancelReservation, gbc_btnCancelReservation);
		
		JButton btnLogout = new JButton("Logout");
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogout.gridx = 7;
		gbc_btnLogout.gridy = 8;
		add(btnLogout, gbc_btnLogout);

	}

}
