import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.JFrame;


import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.time.LocalDate;
import java.time.LocalTime;

public class CancelReservationPanel {
	JFrame frame = new JFrame("Cancelling reservations");
	Resource_Management_System system;
	JPanel panel = new JPanel();
	User u;
	Reservation[] reservations;
	Resource resource_to_cancel;
	Reservation res_to_cancel;
	int index;
	int selected=0;
	public CancelReservationPanel(Resource_Management_System system_obj, User x) {
		
		system = system_obj;
		u = x;
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
		        	frame.dispose();
		        	//system.closeSystem();
		            //System.exit(0);
		        }
		    }
		});
		//To fill the menuitems list with reservation which a specific user reserved  
		JLabel lblReservations = new JLabel("Reservations: ");
		lblReservations.setBounds(20, 28, 88, 22);
		panel.add(lblReservations);
		JPanel ReservationCards = new JPanel();		
		//roomCards.setToolTipText("choose the resource u want");
		String[] menuitems=new String[system.get_reservation_count(u)];
		int j=0;
		reservations= system.get_reservations_of_user(u);
		for(int i=0; i<system.get_reservation_count(u);i++)
		{
			if(reservations[i].getResource() instanceof Room ){
				menuitems[j] = "Room "+((Room)reservations[i].getResource()).getRoomName()+" "+reservations[i].getResource().getID();
				j++;}
			if(reservations[i].getResource() instanceof Equipment ){
				menuitems[j] = ((Equipment)reservations[i].getResource()).getEquipmnetType()+" "+reservations[i].getResource().getID();
				j++;}
			if(reservations[i].getResource() instanceof Sports_Courts ){
				menuitems[j] = ((Sports_Courts)reservations[i].getResource()).getCourtName()+" Court "+reservations[i].getResource().getID();
				j++;}
		}
		JTextField Date = new JTextField("Date");
		Date.setForeground(Color.GRAY);
		Date.setColumns(20);
		Date.setEnabled(false);
		String datedetails = reservations[index].getStartDate()+"";
		Date.setText(datedetails);
		JTextField sTime = new JTextField("Start Time");
		sTime.setForeground(Color.GRAY);
		sTime.setColumns(15);
		sTime.setEnabled(false);
		String sTimedetails = reservations[index].getStartTime()+"";
		sTime.setText(sTimedetails);
		JTextField eTime = new JTextField("Start Time");
		eTime.setForeground(Color.GRAY);
		eTime.setColumns(15);
		eTime.setEnabled(false);
		String eTimedetails = reservations[index].getEndTime()+"";
		eTime.setText(eTimedetails);
		JPanel Res_Details = new JPanel();
		//Res_Details.setLayout(new FlowLayout());
		//Res_Details.setLayout(FlowLayout);
		Res_Details.add(Date);
		Res_Details.add(sTime);
		Res_Details.add(eTime);
		//To display the menuitems list in a JCombox
		JComboBox user_reservations = new JComboBox(menuitems);
		ReservationCards.add(lblReservations);
		ReservationCards.add(user_reservations);
		panel.add(ReservationCards);panel.add(Res_Details);
		user_reservations.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				int ID = Integer.parseInt(((String)evt.getItem()).substring(((String)evt.getItem()).lastIndexOf(" ")));
				 index = user_reservations.getSelectedIndex();
				 res_to_cancel= reservations[index];
				 //selected=1;//remember to use this later 
				}
		
		});
		JButton cancel=new JButton("Cancel");
		cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				system.Cancel_reservation(res_to_cancel);
				JOptionPane error = new JOptionPane("reservation cancelled");
				error.createDialog("Cancelled").setVisible(true);
				
			}});
		panel.add(cancel);
		frame.add(panel);
		//frame.pack();
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
}
