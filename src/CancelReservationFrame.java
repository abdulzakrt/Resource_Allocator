import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.event.ListDataListener;
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

public class CancelReservationFrame extends JFrame {
	JFrame frame = this;
	Resource_Management_System system;
	JPanel panel = new JPanel();
	JComboBox res;
	JButton cn;
	User u;
	Reservation[] reservations;
	String[] menu;
	Resource resource_to_cancel;
	Reservation res_to_cancel;
	int index=0;
	int selected=0;
	public CancelReservationFrame(Resource_Management_System system_obj, User x) {
		
		system = system_obj;
		u = x;
		frame.setLocation(340, 90);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
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
		
		//To fill the menuitems list with reservation which a specific user reserved  
		
		String[] menuitems=new String[system.get_reservation_count(u)];
		menu=menuitems;
		int j=0;
		if(system.get_reservation_count(u)==0){
			JOptionPane error = new JOptionPane("No reservations to cancel for user "+u.getLog_in_ID()+" "+u.getFirst_Name());
			error.createDialog("error").setVisible(true);
			//to exit since there are no reservations to cancel
			return;
		}

		reservations= system.get_reservations_of_user(u);
		for(int i=0; i<system.get_reservation_count(u);i++)
		{
			if( (reservations[i].getResource() instanceof Room)){
				menuitems[j] = (j+1)+"Room "+((Room)reservations[i].getResource()).getRoomName()+" "+reservations[i].getResource().getID();
				
			}
			else if( (reservations[i].getResource() instanceof Equipment)){
				menuitems[j] = (j+1)+""+((Equipment)reservations[i].getResource()).getEquipmnetType()+" "+reservations[i].getResource().getID();
				
			}
			else if( (reservations[i].getResource() instanceof Sports_Courts)){
				menuitems[j] = (j+1)+""+((Sports_Courts)reservations[i].getResource()).getCourtName()+" Court "+reservations[i].getResource().getID();
				
			}
			j++;
		}
		JLabel lblReservationslist = new JLabel("Reservations list: ");
		lblReservationslist.setBounds(20, 28, 88, 22);
		JLabel lblReservations = new JLabel("Reservation Details ");
		lblReservations.setBounds(20, 28, 88, 22);
		JPanel ReservationMenu = new JPanel(new FlowLayout());	
		panel.add(lblReservations,0,0);
		//textfields to display the reservations details
		JTextField Date = new JTextField("Date");
		Date.setForeground(Color.GRAY);
		Date.setColumns(20);
		Date.setEnabled(false);
		
		JTextField sTime = new JTextField("Start Time");
		sTime.setForeground(Color.GRAY);
		sTime.setColumns(15);
		sTime.setEnabled(false);
		
		JTextField eTime = new JTextField("Start Time");
		eTime.setForeground(Color.GRAY);
		eTime.setColumns(15);
		eTime.setEnabled(false);
		
		JPanel Res_Details = new JPanel();
		Res_Details.setLayout(new FlowLayout());
		Res_Details.setLayout(new FlowLayout());
		Res_Details.add(Date);
		Res_Details.add(sTime);
		Res_Details.add(eTime);
		panel.add(Res_Details, BorderLayout.CENTER);
		
		//To display the menuitems list in a JCombox
		JComboBox user_reservations = new JComboBox(menu);
		res = user_reservations;
		user_reservations.setSelectedIndex(-1);
		user_reservations.addItemListener(new ItemListener(){
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				int ID = Integer.parseInt(((String)evt.getItem()).substring(((String)evt.getItem()).lastIndexOf(" ")+1));
				 index = user_reservations.getSelectedIndex();
				 if(user_reservations.getSelectedIndex()>=0){
					 res_to_cancel= reservations[index];
					 Date.setText(reservations[index].getStartDate()+"");
					 sTime.setText(reservations[index].getStartTime()+"");
					 eTime.setText(reservations[index].getEndTime()+"");
					 System.out.println(reservations[index].getStartDate()+" "+reservations[index].getStartTime()+" "+reservations[index].isCancelled());
					 //selected=1;//remember to use this later 
					 String[] menuitems=new String[system.get_reservation_count(u)];
						int j=0;
						reservations= system.get_reservations_of_user(u);
						for(int i=0; i<system.get_reservation_count(u);i++)
						{
							if( (reservations[i].getResource() instanceof Room) && (reservations[i].isCancelled() == false) ){
								menuitems[j] = (j+1)+"Room "+((Room)reservations[i].getResource()).getRoomName()+" "+reservations[i].getResource().getID();
								j++;
							}
							if( (reservations[i].getResource() instanceof Equipment) && (reservations[i].isCancelled() == false)  ){
								menuitems[j] = (j+1)+""+((Equipment)reservations[i].getResource()).getEquipmnetType()+" "+reservations[i].getResource().getID();
								j++;
							}
							if( (reservations[i].getResource() instanceof Sports_Courts) && (reservations[i].isCancelled() == false) ){
								menuitems[j] = (j+1)+""+((Sports_Courts)reservations[i].getResource()).getCourtName()+" Court "+reservations[i].getResource().getID();
								j++;
							}
						}
						menu=menuitems;
						index=-1;
						res.setSelectedItem(index);
				}
				else {
					frame.dispose();
				}
			}
		
		});
		ReservationMenu.add(lblReservationslist);
		ReservationMenu.add(user_reservations);
		JPanel cncelbtn = new JPanel();
		JButton cancel=new JButton("Cancel");
		cncelbtn.add(cancel);
		cn=cancel;
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
					if(system.get_reservation_count(u)==0){				
					JOptionPane error = new JOptionPane("No reservations to cancel for user "+u.getLog_in_ID()+" "+u.getFirst_Name());					
					error.createDialog("error").setVisible(true);
					return;
				}
					if(res.getSelectedItem()==null)
					{
						JOptionPane error = new JOptionPane("No reservations selected to cancel for user ");					
						error.createDialog("error").setVisible(true);
						return;
					}
				system.Cancel_reservation(res_to_cancel);
				JOptionPane success = new JOptionPane("reservation cancelled");
				success.createDialog("Cancelled").setVisible(true);
				
				String[] menuitems=new String[system.get_reservation_count(u)];
				int j=0;
				reservations= system.get_reservations_of_user(u);
				for(int i=0; i<system.get_reservation_count(u);i++)
				{
					if( (reservations[i].getResource() instanceof Room) && (reservations[i].isCancelled() == false) ){
						menuitems[j] = (j+1)+"Room "+((Room)reservations[i].getResource()).getRoomName()+" "+reservations[i].getResource().getID();
						j++;
					}
					if( (reservations[i].getResource() instanceof Equipment) && (reservations[i].isCancelled() == false)  ){
						menuitems[j] = (j+1)+""+((Equipment)reservations[i].getResource()).getEquipmnetType()+" "+reservations[i].getResource().getID();
						j++;
					}
					if( (reservations[i].getResource() instanceof Sports_Courts) && (reservations[i].isCancelled() == false) ){
						menuitems[j] = (j+1)+""+((Sports_Courts)reservations[i].getResource()).getCourtName()+" Court "+reservations[i].getResource().getID();
						j++;
					}
				}
				
				menu = menuitems;
				
				//user_reservations.removeAllItems();
				frame.dispose();
				//user_reservations.addItem(menu);
				index=-1;
				
				if(system.get_reservation_count(u)==0) {
					res.setSelectedIndex(index);res.setEnabled(false);
					JOptionPane error = new JOptionPane("No more reservations to cancel for user "+u.getLog_in_ID()+" "+u.getFirst_Name());
					error.createDialog("error").setVisible(true);
					//frame.dispose();
				}
				
				
				
			}});
		GridLayout grid = new GridLayout(3, 0);
		frame.setSize(450, 300);
		frame.setLayout(grid);
		frame.add(ReservationMenu);
		frame.add(panel);
		frame.add(cncelbtn);
		
		//frame.pack();
		
		frame.setVisible(true);
	}

}